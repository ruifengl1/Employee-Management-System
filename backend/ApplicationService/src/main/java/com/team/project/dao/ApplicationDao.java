package com.team.project.dao;

import com.team.project.entity.Application;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ApplicationDao extends AbstractHibernateDao<Application> {
    public ApplicationDao() {
        setClazz(Application.class);
    }

    public List<Application> getAllApplication() {
        return this.getAll();
    }

    public Application getApplicationById(int id) {
        return findById(id);
    }

    public Application getApplicationByEmployeeId(int employeeId) {
        return findByEmployeeId(employeeId);
    }

    public void updateApplicationByEmployeeId(int employeeId, String newStatus) {
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaUpdate<Application> update = cb.createCriteriaUpdate(Application.class);
        Root<Application> root = update.from(Application.class);
        update.set(root.get("status"), newStatus);
        update.where(cb.equal(root.get("employeeId"), employeeId));
        getCurrentSession().createQuery(update).executeUpdate();
    }

    public List<Application> getApplicationsByStatus(String status) {
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Application> cq = cb.createQuery(Application.class);
        Root<Application> root = cq.from(Application.class);
        cq.where(cb.equal(root.get("status"), status));
        return getCurrentSession().createQuery(cq).getResultList();
    }

    public void update(Application application) {
        getCurrentSession().merge(application);
    }

    public void save(Application application) {
        getCurrentSession().save(application);
    }



}
