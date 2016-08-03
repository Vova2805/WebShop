package com.webshop.service.interfaces;

import com.webshop.dao.generic.IGenericDAO;

import java.io.Serializable;
import java.util.List;


public interface IService<T_DAO extends IGenericDAO<T, PK>, T, PK extends Serializable> {

    void update(T object);

    void delete(PK id);

    <T> List<T> getAll();

    <T> List<T> getAll(int limit, int offset);

    T add(T instance);

    T get(PK id);

    Long count();
}
