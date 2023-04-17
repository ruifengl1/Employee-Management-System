package com.team.project.service.remote;

import com.team.project.domain.HouseFacilityResident;
import com.team.project.entity.FacilityReport;
import com.team.project.entity.FacilityReportDetail;
import com.team.project.entity.House;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "housing-service")
public interface RemoteHousingService {

    @GetMapping("housing-service/house/all")
    List<House> getHouse();
    @GetMapping("housing-service/facility/reports/user/{id}")
    List<FacilityReport> getFacilityReportsByUserId(@PathVariable Integer id);
    @GetMapping("housing-service/facility/reports/house/{id}")
    List<FacilityReport> getFacilityReportsByHouseId(@PathVariable Integer id);
    @GetMapping(value = "housing-service/facility/reports_detail/{id}")
    List<FacilityReportDetail> getFacilityReportDetailByReportId(@PathVariable("id") Integer id);
    @GetMapping("housing-service/house/all/detail")
    List<HouseFacilityResident> getHouseDetail();

}
