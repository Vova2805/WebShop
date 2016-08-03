package com.webshop.service.implement;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.service.interfaces.IService;

import java.io.Serializable;
import java.util.List;


public abstract class ServiceImpl<T_DAO extends IGenericDAO<T, PK>, T, PK extends Serializable> implements IService<T_DAO, T, PK> {

    private T_DAO genericDAO;

    public ServiceImpl() {
    }

    public ServiceImpl(T_DAO genericDAO) {
        this.genericDAO = genericDAO;
    }

    @Override
    public void update(T object) {
        genericDAO.update(object);
    }

    @Override
    public void delete(PK id) {
        genericDAO.delete(id);
    }

    @Override
    public <T> List<T> getAll() {
        List<T> tList = (List<T>) (Object) genericDAO.getAll();
        return tList;
    }

    @Override
    public <T> List<T> getAll(int limit, int offset) {
        return (List<T>) (Object) genericDAO.getAll(limit, offset);
    }

    @Override
    public T add(T instance) {
        return genericDAO.add(instance);
    }

    @Override
    public T get(PK id) {
        return (T) genericDAO.get(id);
    }

    @Override
    public Long count() {
        return genericDAO.count();
    }
}
