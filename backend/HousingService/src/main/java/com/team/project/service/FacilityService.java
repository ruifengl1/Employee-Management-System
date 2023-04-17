package com.team.project.service;

import com.team.project.dao.FacilityDao;
import com.team.project.dao.HousingDao;
import com.team.project.entity.Facility;
import com.team.project.entity.FacilityReport;
import com.team.project.domain.request.addReportRequest;
import com.team.project.entity.FacilityReportDetail;
import com.team.project.entity.House;
import com.team.project.exception.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FacilityService {
    private final FacilityDao facilityDao;

    @Autowired
    public FacilityService(FacilityDao facilityDao) {
        this.facilityDao = facilityDao;
    }
    public List<FacilityReport> getFacilityReports() throws NoResultException {
        Optional<List<FacilityReport>> res = facilityDao.getFacilityReports();
        List<FacilityReport> list = res.isPresent()?res.get():new ArrayList<>();
        return list;
    }
    public void addFacilityReports(addReportRequest reportRequest) {
        facilityDao.addFacilityReports(reportRequest);
    }
    public List<Facility> getFacilityByHouseId(Integer id){
        return facilityDao.getFacilityByHouseId(id);
    }
    public List<FacilityReportDetail> getFacilityReportDetailByReportId(Integer id){
        return facilityDao.getFacilityReportDetailByReportId(id);
    }
    public void addCommentInFacilityReportDetail(Integer reportId, Integer userId, String comment){
        facilityDao.addCommentInFacilityReportDetail(reportId, userId, comment);
    }
//    admin
    public void updateFacilityReportsById(Integer reportId, String status){
        facilityDao.updateFacilityReportsById(reportId, status);
    }
}
