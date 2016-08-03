package com.webshop.service.implement;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.dao.interfaces.ICategoryDAO;
import com.webshop.dao.interfaces.IProductDAO;
import com.webshop.model.classes.Category;
import com.webshop.model.classes.Product;
import com.webshop.service.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Service
public class CategoryServiceImpl extends ServiceImpl<IGenericDAO<Category, Integer>, Category, Integer> implements ICategoryService {

    @Autowired
    private ICategoryDAO categoryDAO;

    @Autowired
    private IProductDAO productDAO;

    public CategoryServiceImpl() {
    }

    @Autowired
    public CategoryServiceImpl(IGenericDAO<Category, Integer> categoryDAO) {
        super(categoryDAO);
    }

    @Override
    public List<Category> getCategoryByName(String categoryName) {
        List<Category> result = new ArrayList<>();
        List<Category> categories = categoryDAO.getAll();
        for (Category category : categories) {
            if (category.getTitle().equals(categoryName))
                result.add(category);
        }
        return result;
    }

    @Override
    public Map<Category, List<Product>> getTopCategoryProductsMap(List<Category> categories, int limit) {
        Map<Category, List<Product>> map = new LinkedHashMap<>();
        for (Category category : categories) {
            //get limit first goods for each category
            List<Product> goods = productDAO.getProductsByCategory(category, limit, 0);
            map.put(category, goods);
        }
        return map;
    }

    @Override
    public void delete(Integer id) {
        Category category = categoryDAO.get(id);
        category.deleteImagesFromFS();
        category.getSubcategoriesByCategoryId().forEach(subcategory -> {
            subcategory.getSubcategoryGroupsBySubcategoryId().forEach(group -> {
                group.getGoodsBySubcategoryGroupId().forEach(product -> {
                    product.deleteImagesFromFS();
                });
                group.deleteImagesFromFS();
            });
            subcategory.deleteImagesFromFS();
        });
        category.deleteDir();
        super.delete(id);
    }
}
