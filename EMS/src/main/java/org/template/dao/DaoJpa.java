package org.template.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.template.common.models.QueryParameter;

import java.util.List;

@Repository
@Transactional
public class DaoJpa implements BaseDao {

    @PersistenceContext
    private EntityManager entityManager;

    public DaoJpa(){
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public <T> T getById(Class<T> clazz, long id) {
        return this.entityManager.find(clazz, id);
    }

    @Override
    public <T> List<T> getAll(Class<T> clazz) {
        return entityManager
                .createQuery("SELECT e FROM " + clazz.getSimpleName() + " e", clazz)
                .getResultList();
    }

    @Override
    public void save(Object obj) {
        this.entityManager.persist(obj);
    }

    @Override
    public void update(Object obj) {
        this.entityManager.merge(obj);
    }

    @Override
    public void delete(Object obj) {
        Object managed = entityManager.contains(obj)
                ? obj
                : entityManager.merge(obj);
        entityManager.remove(managed);
    }

    @Override
    @Transactional
    public <T> T saveOrUpdate(T entity) {
        Object id = entityManager.getEntityManagerFactory()
                .getPersistenceUnitUtil()
                .getIdentifier(entity);

        if (id == null) {
            entityManager.persist(entity);
            return entity;
        }
        return entityManager.merge(entity);
    }

    protected <T> List<T> getResultList(String jpaQlStr, List<QueryParameter> paramList, Class<T> clazz){

        TypedQuery<T> query = this.prepareQuery(jpaQlStr, paramList, clazz);

        return query.getResultList();
    }

    private <T> TypedQuery<T> prepareQuery(String jpaQlStr, List<QueryParameter> paramList, Class<T> clazz){
        TypedQuery<T> query = entityManager.createQuery(jpaQlStr, clazz);

        if (!CollectionUtils.isEmpty(paramList)) {

            for (QueryParameter param : paramList) {
                query.setParameter(param.getKey(), param.getValue());
            }
        }

        return query;
    }

}
