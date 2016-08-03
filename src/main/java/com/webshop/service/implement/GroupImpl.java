package com.webshop.service.implement;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.dao.interfaces.IGroupDAO;
import com.webshop.model.classes.SubcategoryGroup;
import com.webshop.service.interfaces.IGroupService;
import com.webshop.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GroupImpl extends ServiceImpl<IGenericDAO<SubcategoryGroup, Integer>, SubcategoryGroup, Integer> implements IGroupService {

    @Autowired
    private IGroupDAO groupDAO;

    @Autowired
    private IProductService productService;

    @Autowired
    public GroupImpl(IGenericDAO<SubcategoryGroup, Integer> groupDAO) {
        super(groupDAO);
    }

    @Override
    public void delete(Integer id) {
        SubcategoryGroup group = groupDAO.get(id);
        group.deleteImagesFromFS();
        group.getGoodsBySubcategoryGroupId().forEach(product -> {
            product.deleteImagesFromFS();
        });
        super.delete(id);
    }
}
