package com.webshop.dao.generic;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

/**
 * Performing main CRUD operations
 *
 * @param <T>  type of entity
 * @param <PK> primary key type
 */
public interface IGenericDAO<T, PK extends Serializable> {

    Session getSession();

    Class<T> getType();

    void update(T object);

    void delete(PK id);

    <T> List<T> getAll();

    <T> List<T> getAll(int limit, int offset);

    T add(T object);

    T get(PK id);

    Long count();

}
