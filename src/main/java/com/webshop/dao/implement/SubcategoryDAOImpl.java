package com.webshop.dao.implement;

import com.webshop.dao.generic.GenericDAOImpl;
import com.webshop.dao.interfaces.ISubcategoryDAO;
import com.webshop.model.classes.Subcategory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubcategoryDAOImpl extends GenericDAOImpl<Subcategory, Integer> implements ISubcategoryDAO {

    @Override
    public Subcategory getSubcategoryByName(String name) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(Subcategory.class);
        criteria.add(Restrictions.eq("subcategoryTitle", name));
        List results = criteria.list();
        if (results.size() > 0) {
            return (Subcategory) results.get(0);
        }
        return null;
    }
}
