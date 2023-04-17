package com.team.project.controller;

import com.team.project.domain.HouseFacilityResident;
import com.team.project.domain.response.EmployeeHousingResponse;
import com.team.project.domain.response.HousingDetailResponse;
import com.team.project.entity.Employee;
import com.team.project.entity.House;
import com.team.project.service.CompositeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("")
public class CompositeController {

    private CompositeService compositeService;

    @Autowired
    public void setCompositeService(CompositeService compositeService) {
        this.compositeService = compositeService;
    }
//user
    @GetMapping("houseDetails/{userId}")
    public Object getHousesAndUserByUserId(@PathVariable Integer userId){

        EmployeeHousingResponse employeeHousingResponse = compositeService.getAllHousesAndEmployees();

        List<Employee> employeeList = employeeHousingResponse.getEmployees();
        List<House> houseList = employeeHousingResponse.getHouses();

        Employee cur_employee = employeeList.stream().filter(x->x.getUserID()==userId).collect(Collectors.toList()).get(0);
        Integer houseId = cur_employee.getHouseID();
        Optional<House> cur_house = houseList.stream().filter(x->x.getHouseId().equals(houseId)).findFirst();
        String address = cur_house.isPresent()?cur_house.get().getAddress():"not assign";

        List <Employee> roommates = new ArrayList<>();
        if(!address.equals("not assign"))
            roommates = employeeList.stream()
                    .filter(x->x.getHouseID()!=null && x.getHouseID().equals(houseId) && !x.getUserID().equals(userId))
                    .collect(Collectors.toList());

        return HousingDetailResponse.builder()
                .message("")
                .houseId(houseId)
                .address(address)
                .roommates(roommates)
                .build();
//        List<House> houses = employeeHousingResponse.getHouses();
//
//        return GeneralResponse.<List<House>>builder().body(houses).build();
    }
    @GetMapping("reports_detail/{reportId}")
    public Object getReportDetail(@PathVariable Integer reportId){
        return compositeService.getReportDetail(reportId);
    }
    @GetMapping("reports/user/{userId}")
    public Object getReportByUserId(@PathVariable Integer userId){
        return compositeService.getReportByUserId(userId);
    }
//    admin
    @GetMapping("reports/house/{houseId}")
    public Object getReportByHouseId(@PathVariable Integer houseId){
        return compositeService.getReportByHouseId(houseId);
    }
    @GetMapping("houseDetailsWithResidentInfo")
    public Object getAllHouseDetailWithResidentInfo(){
        List<HouseFacilityResident> res = compositeService.getHouseDetail();
        res.stream().forEach(x->{
            x.setResidentList(compositeService.getResidentByHouseId(x.getHouse().getHouseId()));
        });
        return res;
    }


    @GetMapping("getAvailableHouseId")
    public Integer getAvailableHouseId(){
        List<HouseFacilityResident> res = compositeService.getHouseDetail();
        res.stream().forEach(x->{
            x.setResidentList(compositeService.getResidentByHouseId(x.getHouse().getHouseId()));
        });
        res = res.stream().filter(x->x.getResidentList().size()< x.getHouse().getMaxOccupant().intValue()).collect(Collectors.toList());
        Random rand = new Random();
        int randomHouseId = res.get(rand.nextInt(res.size())).getHouse().getHouseId();
        return randomHouseId;
    }

}
