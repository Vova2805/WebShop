package com.webshop.dao.generic;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public class GenericDAOImpl<T, PK extends Serializable> implements IGenericDAO<T, PK> {

    @Autowired
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Class<T> type;

    public GenericDAOImpl() {
    }

    public GenericDAOImpl(Class<T> type) {
        this.type = type;
    }

    @Override
    public Class<T> getType() {
        return type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional
    public T add(T object) {
        Session session = getSession();
        session.save(object);
        return object;
    }

    @Override
    @Transactional
    public T get(PK id) {
        return (T) getSession().load(type, id);
    }

    @Override
    @Transactional
    public void update(T obj) {
        Session session = getSession();
        session.update(obj);
        session.flush();
    }

    @Override
    @Transactional
    public void delete(PK id) {
        Session session = getSession();
        session.delete(session.load(type, id));
    }

    @Override
    @Transactional
    public <T> List<T> getAll() {
        String sql = "from " + type.getSimpleName();
        return (List<T>) getSession().createQuery(sql).setCacheable(false).list();
    }

    @Override
    @Transactional
    public <T> List<T> getAll(int limit, int offset) {
        String sql = "from " + type.getSimpleName();
        return getSession()
                .createQuery(sql)
                .setCacheable(true)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
    }

    @Override
    public Long count() {
        return (Long) getSession()
                .createQuery("select count(*) from " + type.getSimpleName())
                .uniqueResult();
    }
}
