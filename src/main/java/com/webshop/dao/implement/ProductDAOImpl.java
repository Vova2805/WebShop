package com.webshop.dao.implement;

import com.webshop.dao.generic.GenericDAOImpl;
import com.webshop.dao.interfaces.IProductDAO;
import com.webshop.model.classes.Category;
import com.webshop.model.classes.Product;
import com.webshop.model.classes.Subcategory;
import com.webshop.model.classes.SubcategoryGroup;
import com.webshop.model.enums.SortDirection;
import com.webshop.model.enums.SortProductBy;
import com.webshop.util.filterFacade.interfaces.IFilterFacade;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAOImpl extends GenericDAOImpl<Product, Integer> implements IProductDAO {

    @Autowired
    private IFilterFacade filterFacade;

    @Override
    public List<Product> getProductsByCategory(Category category) {
        List<Product> goods = new ArrayList<>();
        category.getSubcategoriesByCategoryId()
                .parallelStream()
                .forEach(subcategory -> {
                    subcategory.getSubcategoryGroupsBySubcategoryId()
                            .forEach(subcategoryGroup -> {
                                goods.addAll(subcategoryGroup.getGoodsBySubcategoryGroupId());
                            });
                });
        return goods;
    }

    @Override
    public List<Product> getProductsBySubcategory(Subcategory subcategory) {
//        Criteria cr = productDAO.getSession().createCriteria(Product.class);
//        cr.add(Restrictions.eq("subcategoryId", subcategory.getSubcategoryId()));
//        cr.addOrder(Order.desc("addedDate"));
//        return cr.setCacheable(true).list();
        List<Product> goods = new ArrayList<>();
        subcategory.getSubcategoryGroupsBySubcategoryId()
                .forEach(subcategoryGroup -> {
                    goods.addAll(subcategoryGroup.getGoodsBySubcategoryGroupId());
                });
        return goods;
    }

    @Override
    public List<Product> getProductsByGroup(SubcategoryGroup subcategoryGroup) {
        List<Product> goods = new ArrayList<>();
        goods.addAll(subcategoryGroup.getGoodsBySubcategoryGroupId());
        return goods;
    }

    @Override
    public List<Product> getProductsByCategory(Category category, int limit, int offset) {
        List<Product> goods = getProductsByCategory(category);
        return getSublist(goods, limit, offset);
    }

    @Override
    public List<Product> getProductsBySubcategory(Subcategory subcategory, int limit, int offset) {
        List<Product> goods = getProductsBySubcategory(subcategory);
        return getSublist(goods, limit, offset);
    }

    @Override
    public List<Product> getProductsByGroup(SubcategoryGroup subcategoryGroup, int limit, int offset) {
        List<Product> goods = getProductsByGroup(subcategoryGroup);
        return getSublist(goods, limit, offset);
    }

    @Override
    public List<Product> getPopular(int limit, int offset) {
        Criteria cr = super.getSession().createCriteria(Product.class);
        cr.addOrder(Order.desc("rating"));
        cr.setFirstResult(offset);
        cr.setMaxResults(limit);
        return cr.setCacheable(true).list();
    }

    @Override
    public List<Product> getNewest(int limit, int offset) {
        Criteria cr = super.getSession().createCriteria(Product.class);
        cr.addOrder(Order.desc("addedDate"));
        cr.setFirstResult(offset);
        cr.setMaxResults(limit);
        return cr.setCacheable(true).list();
    }


    @NotNull
    private List<Product> getSublist(List<Product> goods, int limit, int offset) {
        //get top rated goods
        goods = filterFacade.sort(goods, SortProductBy.RATE, SortDirection.DESC);
        return goods
                .subList(
                        offset <= goods.size() ? offset : goods.size(),
                        offset + limit <= goods.size() ? offset + limit : goods.size()
                );
    }

    @Override
    public List<Product> getLatest(int limit) {
        String sql = "from Product ORDER BY productId DESC";
        return getSession()
                .createQuery(sql)
                .setCacheable(true)
                .setMaxResults(limit)
                .list();
    }
}
