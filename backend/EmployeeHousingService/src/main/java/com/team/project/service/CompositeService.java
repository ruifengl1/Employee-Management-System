package com.team.project.service;


import com.team.project.domain.HouseFacilityResident;
import com.team.project.domain.response.EmployeeHousingResponse;
import com.team.project.domain.response.FacilityReportDetailResponse;
import com.team.project.entity.Employee;
import com.team.project.entity.FacilityReport;
import com.team.project.entity.FacilityReportDetail;
import com.team.project.domain.FinalFacilityReportDetail;
import com.team.project.entity.FinalFacilityReport;
import com.team.project.service.remote.RemoteHousingService;
import com.team.project.service.remote.RemoteEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompositeService {

    private RemoteHousingService housingService;
    private RemoteEmployeeService employeeService;

    @Autowired
    public void setHousingService(RemoteHousingService housingService) {
        this.housingService = housingService;
    }

    @Autowired
    public void setUserService(RemoteEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public EmployeeHousingResponse getAllHousesAndEmployees(){
        return EmployeeHousingResponse.builder()
                .employees(employeeService.getAllUsers())
                .houses(housingService.getHouse())
                .build();
    }
    public FacilityReportDetailResponse getReportDetail(Integer reportId){
        List<FacilityReportDetail> details = housingService.getFacilityReportDetailByReportId(reportId);
        List<FinalFacilityReportDetail> res = new ArrayList<>();
        details.stream().forEach(x->
                res.add(FinalFacilityReportDetail.builder()
                            .author(getUserNameBuUserId(x.getUserId()))
                            .facilityReportDetailId(x.getFacilityReportDetailId())
                            .userId(x.getUserId())
                            .comment(x.getComment())
                            .createDate(x.getCreateDate())
                        .build())
        );
        return FacilityReportDetailResponse.builder().body(res).build();
    }
    public String getUserNameBuUserId(Integer id){
        Optional<Employee> employee = Optional.ofNullable(employeeService.findEmployeeByUserID(id));
        String res= id.toString();
        if(employee.isPresent()){
            Employee e = employee.get();
            res = e.getFirstName() + " " + e.getLastName();
        }
        else{
            res = "Admin";
        }
        return res;
    }
    public List<HouseFacilityResident> getHouseDetail(){
        return housingService.getHouseDetail();
    }
    public List<Employee> getResidentByHouseId(Integer houseId){
        return employeeService.findEmployeeByHouseId(houseId);
    }
    public List<FinalFacilityReport> getReportByHouseId(Integer houseId){
        List<FacilityReport> reports = housingService.getFacilityReportsByHouseId(houseId);
        return getFinalFacilityReport(reports);
    }
    public List<FinalFacilityReport> getReportByUserId(Integer userId){
        List<FacilityReport> reports = housingService.getFacilityReportsByUserId(userId);
        return getFinalFacilityReport(reports);
    }
    public List<FinalFacilityReport> getFinalFacilityReport(List<FacilityReport> reports){
        List<FinalFacilityReport> res = new ArrayList<>();
        reports.stream().forEach(x->
                res.add(FinalFacilityReport.builder()
                        .author(getUserNameBuUserId(x.getEmployeeId()))
                        .facilityReportDetail(x.getFacilityReportDetail())
                        .employeeId(x.getEmployeeId())
                        .title(x.getTitle())
                        .description(x.getDescription())
                        .lastModifyDate(x.getLastModifyDate())
                        .status(x.getStatus())
                        .facilityReportId(x.getFacilityReportId())
                        .facility(x.getFacility())
                        .createDate(x.getCreateDate())
                        .build())
        );
        return res;
    }
}
