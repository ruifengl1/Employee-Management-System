package com.team.project.controller;

import com.team.project.domain.request.addNewHouseRequest;
import com.team.project.domain.response.GeneralResponse;
import com.team.project.domain.response.ResponseStatus;

import com.team.project.entity.Facility;
import com.team.project.entity.FacilityReport;
import com.team.project.domain.request.addReportRequest;
import com.team.project.domain.response.AddResponse;
import com.team.project.domain.response.GetResponse;
import com.team.project.entity.House;
import com.team.project.entity.Landlord;
import com.team.project.exception.NoResultException;
import com.team.project.service.FacilityService;
import com.team.project.service.HousingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("house")
@RefreshScope
public class HousingController {
    String role = "user";
    //    @Value("${cook.count}")
//    int cookCount;
    private final HousingService housingService;
    private final FacilityService facilityService;

    @Autowired
    public HousingController(HousingService housingService, FacilityService facilityService) {
        this.housingService = housingService;
        this.facilityService = facilityService;
    }

    @GetMapping("/all")
    public List<House> getHouse() {
        return housingService.getHouse();
    }

    @GetMapping("/all/detail")
    public Object getHouseDetail() {
        return housingService.getHouseDetail();
    }

    @PostMapping("/add")
    public AddResponse addNewHouse(@RequestBody addNewHouseRequest request) {
        Landlord landlord = Landlord.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .cellphone(request.getCellphone())
                .build();
        House house = House.builder()
                .address(request.getAddress())
                .maxOccupant(request.getMaxOccupant())
                .build();
        List<Facility> facilityList = request.getFacilities();
        housingService.addNewHouse(landlord, house, facilityList);
        return AddResponse.builder().message("successful").build();
    }

    @DeleteMapping("/delete/{id}")
    public Object deleteHouseByHouseId(@PathVariable Integer id) {
        housingService.deleteHouse(id);
        return ResponseStatus.builder().success(true).message("delete successful").build();
    }

}
