package com.webshop.dao.implement;

import com.webshop.dao.generic.GenericDAOImpl;
import com.webshop.dao.interfaces.IWishesDAO;
import com.webshop.model.classes.Wish;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishesDAOImpl extends GenericDAOImpl<Wish, Integer> implements IWishesDAO {

    @Override
    public List<Wish> getLatest(int limit) {
        String sql = "from Wish ORDER BY wishId DESC";
        return getSession()
                .createQuery(sql)
                .setCacheable(true)
                .setMaxResults(limit)
                .list();
    }
}
