package com.team.project.dao;

import com.team.project.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class HousingDao {
    @Autowired
    SessionFactory sessionFactory;

    public List<House> getHouse(){
        HibernateDao hibernateDao = new HibernateDao<House>(sessionFactory, House.class);
        return hibernateDao.getAll();
    }
    public Optional<House> getHouseById(Integer Id){
        HibernateDao hibernateDao = new HibernateDao<House>(sessionFactory, House.class);
        return Optional.of((House) hibernateDao.findById(Id));
    }
    public Optional<List<Landlord>> getLandlord(){
        HibernateDao hibernateDao = new HibernateDao<Landlord>(sessionFactory, Landlord.class);
        return Optional.of(hibernateDao.getAll());
    }
    public Optional<Landlord> getLandlordById(Integer Id){
        HibernateDao hibernateDao = new HibernateDao<Landlord>(sessionFactory, Landlord.class);
        return Optional.of((Landlord) hibernateDao.findById(Id));
    }

    public House addHouse(House house){
        HibernateDao hibernateDao = new HibernateDao<House>(sessionFactory, House.class);
        Integer id = hibernateDao.add(house);
        return house;
    }
    public void deleteHouse(Integer houseId){
        Session session;
        Transaction transaction = null;
        try{
            session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();

            Optional<House> house = getHouseById(houseId);
            if(house.isPresent()){
                House cur_house = house.get();
                Set<Facility> facilitySet = cur_house.getFacilities();
                for(Facility f : facilitySet){
                    List<FacilityReport> facilityReportList = f.getFacilityReports();
                    for(FacilityReport fp: facilityReportList){
                        List<FacilityReportDetail> facilityReportDetailList = fp.getFacilityReportDetail();
                        for(FacilityReportDetail fpd: facilityReportDetailList){
                            session.delete(fpd);
                        }
                        session.delete(fp);
                    }
                    session.delete(f);
                }
                session.delete(cur_house);
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
    public Landlord addLandlord(Landlord landlord){
        HibernateDao hibernateDao = new HibernateDao<Landlord>(sessionFactory, Landlord.class);
        Optional<List<Landlord>> exist_landlord = Optional.of(hibernateDao.getByField("email", landlord.getEmail()));
        if(exist_landlord.isPresent()){
            if(exist_landlord.get().size()>0){
                return exist_landlord.get().get(0);
            }
        }
        Integer id = hibernateDao.add(landlord);
        return landlord;
    }
    public void addFacility(List<Facility> facilityList, House house){
        HibernateDao hibernateDao = new HibernateDao<Facility>(sessionFactory, Facility.class);
        for(Facility facility : facilityList) {
            facility.setHouse(house);
            hibernateDao.add(facility);
        }
    }


}
