package com.team.project.dao;

import com.team.project.entity.*;
import com.team.project.domain.request.addReportRequest;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class FacilityDao {
    @Autowired
    SessionFactory sessionFactory;

    public Optional<List<FacilityReport>> getFacilityReports() {
        HibernateDao hibernateDao = new HibernateDao<FacilityReport>(sessionFactory, FacilityReport.class);
        return Optional.ofNullable(hibernateDao.getAll());
    }
    public Optional<FacilityReport> getFacilityReportsById(Integer id) {
        HibernateDao hibernateDao = new HibernateDao<FacilityReport>(sessionFactory, FacilityReport.class);
        return Optional.ofNullable((FacilityReport) hibernateDao.findById(id));
    }
    public void addFacilityReports(addReportRequest reportRequest) {
        HibernateDao hibernateDao = new HibernateDao<Facility>(sessionFactory, Facility.class);
        Optional<Facility> facility = Optional.of((Facility) hibernateDao.findById(reportRequest.getFacilityId()));

        FacilityReport newFacilityReport = new FacilityReport();
        newFacilityReport.setFacility(facility.get());
        newFacilityReport.setEmployeeId(reportRequest.getUserId());
        newFacilityReport.setTitle(reportRequest.getTitle());
        newFacilityReport.setDescription(reportRequest.getDescription());
        newFacilityReport.setCreateDate(new Timestamp(new Date().getTime()));
        newFacilityReport.setLastModifyDate(new Timestamp(new Date().getTime()));
        newFacilityReport.setStatus("Open");
        sessionFactory.getCurrentSession().save(newFacilityReport);

    }
    public void updateFacilityReportsById(Integer id, String status){
        Session session;
        Transaction transaction = null;
        try{
            session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();

            Optional<FacilityReport> facilityReport = getFacilityReportsById(id);
            if(facilityReport.isPresent()){
                FacilityReport cur_facilityReport = facilityReport.get();
                cur_facilityReport.setStatus(status);
                cur_facilityReport.setLastModifyDate(new Timestamp(new Date().getTime()));
                session.saveOrUpdate(cur_facilityReport);
                transaction.commit();
            }

        }
        catch(Exception e){
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
    public Optional<Facility> getFacilityById(Integer facilityId){
        HibernateDao hibernateDao = new HibernateDao<Facility>(sessionFactory, Facility.class);
        return Optional.of((Facility) hibernateDao.findById(facilityId));
    }
    public List<Facility> getFacilityByHouseId(Integer houseId){
        HibernateDao hibernateDao = new HibernateDao<Facility>(sessionFactory, Facility.class);
        return hibernateDao.getByField("house", houseId);
    }
    public List<FacilityReportDetail> getFacilityReportDetailByReportId(Integer reportId){
        HibernateDao hibernateDao = new HibernateDao<FacilityReportDetail>(sessionFactory, FacilityReportDetail.class);
        Optional<FacilityReport> facilityReport = getFacilityReportsById(reportId);
        List<FacilityReportDetail> res = new ArrayList<>();
        if(facilityReport.isPresent())
            res = hibernateDao.getByField("facilityReport", facilityReport.get());
        res = res.stream().sorted((o1, o2)->o2.getFacilityReportDetailId().
                        compareTo(o1.getFacilityReportDetailId())).
                collect(Collectors.toList());
        return res;
    }
    public void addCommentInFacilityReportDetail(Integer reportId, Integer userId, String comment){
        HibernateDao hibernateDao = new HibernateDao<FacilityReportDetail>(sessionFactory, FacilityReportDetail.class);
        Timestamp currentTime = new Timestamp(new Date().getTime());
        Optional<FacilityReport> facilityReport = getFacilityReportsById(reportId);
        FacilityReportDetail facilityReportDetail = new FacilityReportDetail();
        facilityReportDetail.setFacilityReport(facilityReport.get());
        facilityReportDetail.setComment(comment);
        facilityReportDetail.setUserId(userId);
        facilityReportDetail.setCreateDate(currentTime);
        updateFacilityReportsById(reportId,facilityReport.get().getStatus());
        hibernateDao.add(facilityReportDetail);
    }


}
