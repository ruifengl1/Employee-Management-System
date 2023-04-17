package com.team.project.controller;

import com.team.project.domain.request.updateFacilityReportDetail;
import com.team.project.domain.response.GeneralResponse;
import com.team.project.entity.Facility;
import com.team.project.entity.FacilityReport;
import com.team.project.domain.request.addReportRequest;
import com.team.project.domain.response.AddResponse;
import com.team.project.domain.response.ResponseStatus;
import com.team.project.entity.FacilityReportDetail;
import com.team.project.entity.House;
import com.team.project.exception.NoResultException;
import com.team.project.service.FacilityService;
import com.team.project.service.HousingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("facility")
@RefreshScope
public class FacilityController {
    String role="user";

    private final FacilityService facilityService;
    @Autowired
    public FacilityController(FacilityService facilityService) {
        this.facilityService = facilityService;
    }
    @GetMapping("reports")
    public GeneralResponse<FacilityReport> getFacilityReports() throws NoResultException {
        return GeneralResponse.buildGeneralResponse(null, facilityService.getFacilityReports());
    }
    @GetMapping("reports/user/{id}")
    public List<FacilityReport> getFacilityReportsByUserId(@PathVariable Integer id) throws NoResultException {
        List<FacilityReport> reports = facilityService.getFacilityReports();
        reports = reports.stream().filter(x->x.getEmployeeId().equals(id)).collect(Collectors.toList());
        reports = reports.stream().sorted((o1, o2)->o2.getLastModifyDate().
                        compareTo(o1.getLastModifyDate())).
                collect(Collectors.toList());
        return reports;
    }
//    use by admin
    @GetMapping("reports/house/{id}")
    public List<FacilityReport> getFacilityReportsByHouseId(@PathVariable Integer id) throws NoResultException {
        List<FacilityReport> reports = facilityService.getFacilityReports();
        reports = reports.stream().filter(x->x.getFacility().getHouse().getHouseId().equals(id)).collect(Collectors.toList());
        reports = reports.stream().sorted((o1, o2)->o2.getCreateDate().
                        compareTo(o1.getCreateDate())).
                collect(Collectors.toList());
        return reports;
    }

    @PostMapping("reports")
    public AddResponse addFacilityReports(@RequestBody addReportRequest reportRequest){
        facilityService.addFacilityReports(reportRequest);
        return AddResponse.builder().message("successful").build();
    }
    @GetMapping("{id}")
    public GeneralResponse<Facility> getFacilityByHouseId(@PathVariable Integer id){
        List<Facility> facilityList = facilityService.getFacilityByHouseId(id);
        return GeneralResponse.buildGeneralResponse(null, facilityList);
    }
    @PostMapping("reports_detail/{id}")
    public ResponseStatus addCommentInFacilityReportDetail(@PathVariable Integer id, @RequestBody updateFacilityReportDetail requestBody){
        facilityService.addCommentInFacilityReportDetail(id, requestBody.getUserId(), requestBody.getComment());
        return ResponseStatus.builder().success(true).message("successful").build();
    }
    @GetMapping("reports_detail/{id}")
    public List<FacilityReportDetail> getFacilityReportDetailByReportId(@PathVariable Integer id){
        List<FacilityReportDetail> facilityReportDetailList = facilityService.getFacilityReportDetailByReportId(id);
        return facilityReportDetailList;
    }
//    admin
//    update report status
    @PatchMapping("report/{id}")
    public ResponseStatus updateFacilityReportsById(@PathVariable Integer id, @RequestBody FacilityReport requestBody){
        facilityService.updateFacilityReportsById(id, requestBody.getStatus());
        return ResponseStatus.builder().success(true).message("successful").build();

    }
}
