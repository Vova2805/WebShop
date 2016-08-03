package com.webshop.dao.implement;

import com.webshop.dao.generic.GenericDAOImpl;
import com.webshop.dao.interfaces.ICartDAO;
import com.webshop.model.classes.Cart;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDAOImpl extends GenericDAOImpl<Cart, Integer> implements ICartDAO {

    @Override
    public List<Cart> getLatest(int limit) {
        String sql = "from Cart ORDER BY cartProductId DESC";
        return getSession()
                .createQuery(sql)
                .setCacheable(true)
                .setMaxResults(limit)
                .list();
    }
}
