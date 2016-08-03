package com.webshop.service.implement;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.dao.interfaces.ISubcategoryDAO;
import com.webshop.model.classes.Subcategory;
import com.webshop.service.interfaces.ISubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SubcategoryServiceImpl extends ServiceImpl<IGenericDAO<Subcategory, Integer>, Subcategory, Integer> implements ISubcategoryService {

    @Autowired
    private ISubcategoryDAO subcategoryDAO;

    public SubcategoryServiceImpl() {
    }

    @Autowired
    public SubcategoryServiceImpl(IGenericDAO<Subcategory, Integer> subcategoryDAO) {
        super(subcategoryDAO);
    }

    @Override
    public List<Subcategory> getSubcategoryByName(String subcategoryName) {
        List<Subcategory> result = new ArrayList<>();
        List<Subcategory> subcategories = subcategoryDAO.getAll();
        for (Subcategory subcategory : subcategories) {
            if (subcategory.getTitle().equals(subcategoryName))
                result.add(subcategory);
        }
        return result;
    }

    @Override
    public void delete(Integer id) {
        Subcategory subcategory = get(id);
        subcategory.deleteImagesFromFS();
        subcategory.getSubcategoryGroupsBySubcategoryId().forEach(group -> {
            group.getGoodsBySubcategoryGroupId().forEach(product -> {
                product.deleteImagesFromFS();
            });
            group.deleteImagesFromFS();
            subcategory.deleteImagesFromFS();
        });
        super.delete(id);
    }
}
