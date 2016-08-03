package com.webshop.dao.implement;

import com.webshop.dao.generic.GenericDAOImpl;
import com.webshop.dao.interfaces.ICategoryDAO;
import com.webshop.model.classes.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAOImpl extends GenericDAOImpl<Category, Integer> implements ICategoryDAO {


    @Override
    public boolean alreadyExists(Category category) {
        List<Category> categories = getAll();
        if (categories == null) return false;

        for (Category existedCategory : categories) {
            if (existedCategory.getClass().equals(category.getTitle())) {
                return true;
            }
        }
        return false;
    }

}
