package com.webshop.service.implement;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.dao.interfaces.IProductDAO;
import com.webshop.model.classes.Category;
import com.webshop.model.classes.Product;
import com.webshop.model.classes.Subcategory;
import com.webshop.model.classes.SubcategoryGroup;
import com.webshop.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Service
public class ProductServiceImpl extends ServiceImpl<IGenericDAO<Product, Integer>, Product, Integer> implements IProductService {

    @Autowired
    private IProductDAO productDAO;


    public ProductServiceImpl() {
    }

    @Autowired
    public ProductServiceImpl(IGenericDAO<Product, Integer> productDAO) {
        super(productDAO);
    }

    @Override
    public List<Product> getPopular(int limit, int offset) {
        return productDAO.getPopular(limit, offset);
    }

    @Override
    public List<Product> getNewest(int limit, int offset) {
        return productDAO.getNewest(limit, offset);
    }

    @Override
    public Map<String, List<Product>> getPopularNewestMap(int limit) {
        Map<String, List<Product>> result = new LinkedHashMap<>();
        result.put("Popular", getPopular(limit, 0));
        result.put("Latest", getNewest(limit, 0));
        return result;
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        return productDAO.getProductsByCategory(category);
    }

    @Override
    public List<Product> getProductsBySubcategory(Subcategory subcategory) {
        return productDAO.getProductsBySubcategory(subcategory);
    }

    @Override
    public List<Product> getProductsByGroup(SubcategoryGroup subcategoryGroup) {
        return productDAO.getProductsByGroup(subcategoryGroup);
    }

    @Override
    public List<Product> getProductsByCategory(Category category, int limit, int offset) {
        return productDAO.getProductsByCategory(category, limit, offset);
    }

    @Override
    public List<Product> getProductsBySubcategory(Subcategory subcategory, int limit, int offset) {
        return productDAO.getProductsBySubcategory(subcategory, limit, offset);
    }

    @Override
    public List<Product> getProductsByGroup(SubcategoryGroup subcategoryGroup, int limit, int offset) {
        return productDAO.getProductsByGroup(subcategoryGroup, limit, offset);
    }

    @Override
    public List<Product> getLatest(int limit) {
        return productDAO.getLatest(limit);
    }

    @Override
    public void delete(Integer id) {
        Product product = productDAO.get(id);
        product.deleteImagesFromFS();
        super.delete(id);
    }


}
