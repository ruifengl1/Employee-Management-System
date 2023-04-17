package com.team.project.dao;

import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;
public class HibernateDao<T> {
    SessionFactory sessionFactory;

    protected Class<T> className;
    @Setter
    protected String idName;
    public HibernateDao(SessionFactory sessionFactory, Class<T> className){this.sessionFactory = sessionFactory;this.className = className;}

    public List<T> getAll() {
        Session session = getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(className);
        criteria.from(className);
        return session.createQuery(criteria).getResultList();
    }

    public T findById(int id) {
        return getCurrentSession().get(className, id);
    }

    public void update(T item) {
        try {
            getCurrentSession().saveOrUpdate(item);
        }
        catch (Exception e) {
            throw e;
        }
    }
    public Integer add(T item) {
        try {
            Integer id = (Integer) getCurrentSession().save(item);
            return id;
        }
        catch (Exception e) {
            throw e;
        }
    }

    public void remove(T item) {
        Transaction tx = sessionFactory.getCurrentSession().beginTransaction();

        try {
            getCurrentSession().delete(item);
            tx.commit();
        }
        catch (Exception e) {
            if(tx!=null){
                tx.rollback();
            }
            throw e;
        }
    }
    public int updateByFieldAndId (int id, String field, Object value) {
        Session session = getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaUpdate<T> update = cb.createCriteriaUpdate(className);
        Root<T> root = update.from(className);
        update.set(field, value);
        update.where(cb.equal(root.get(idName), id));
        return session.createQuery(update).executeUpdate();
    }

    public List<T> getByField(String field, Object value) {
        Session session = getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = cb.createQuery(className);
        Root<T> root = criteria.from(className);
        criteria.where(cb.equal(root.get(field), value));
        return session.createQuery(criteria).getResultList();
    }
    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
