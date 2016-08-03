package com.webshop.dao.implement;

import com.webshop.dao.generic.GenericDAOImpl;
import com.webshop.dao.interfaces.IComparisonDAO;
import com.webshop.model.classes.Comparison;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComparisonDAOImpl extends GenericDAOImpl<Comparison, Integer> implements IComparisonDAO {

    @Override
    public List<Comparison> getLatest(int limit) {

        String sql = "from Comparison ORDER BY comparisonId DESC";
        return getSession()
                .createQuery(sql)
                .setCacheable(true)
                .setMaxResults(limit)
                .list();
    }
}
