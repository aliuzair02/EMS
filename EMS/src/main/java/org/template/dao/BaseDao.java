package org.template.dao;

import java.util.List;

public interface BaseDao {

    <T> T getById(Class<T> clazz, long id);

    <T> List<T> getAll(Class<T> clazz);

    void save(Object obj);

    void update(Object obj);

    void delete(Object obj);

    <T> T saveOrUpdate(T entity);

}