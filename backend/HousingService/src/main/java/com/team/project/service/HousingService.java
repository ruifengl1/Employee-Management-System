package com.team.project.service;

import com.team.project.dao.FacilityDao;
import com.team.project.dao.HousingDao;
import com.team.project.domain.HouseAndFacility;
import com.team.project.entity.Facility;
import com.team.project.entity.FacilityReport;
import com.team.project.domain.request.addReportRequest;
import com.team.project.entity.House;
import com.team.project.entity.Landlord;
import com.team.project.exception.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HousingService {
    private final HousingDao housingDao;
    private final FacilityDao facilityDao;
    @Autowired
    public HousingService(HousingDao housingDao, FacilityDao facilityDao) {
        this.housingDao = housingDao;
        this.facilityDao = facilityDao;
    }
    public List<House> getHouse() {
        return housingDao.getHouse();
    }
    public List<HouseAndFacility> getHouseDetail(){
        List<House> AllHouse = housingDao.getHouse();
        List<HouseAndFacility> res = new ArrayList<>();
        AllHouse.stream().forEach(x->
                res.add(HouseAndFacility.builder()
                                .house(x)
                                .facilityList(facilityDao.getFacilityByHouseId(x.getHouseId()))
                        .build())
        );
        return res;

    }
    public void addNewHouse(Landlord landlord, House house, List<Facility> facilityList){
        Landlord savedLandlord = housingDao.addLandlord(landlord);
        house.setLandlord(savedLandlord);
        House savedHouse = housingDao.addHouse(house);
        housingDao.addFacility(facilityList, savedHouse);
    }
    public void deleteHouse(Integer houseId){
        housingDao.deleteHouse(houseId);
    }
}
