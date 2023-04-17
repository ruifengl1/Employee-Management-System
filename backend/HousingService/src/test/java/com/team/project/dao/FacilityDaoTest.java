package com.team.project.dao;

import com.team.project.entity.FacilityReport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles(value = "test")
@SpringBootTest
public class FacilityDaoTest {
    @Autowired
    FacilityDao facilityDao;
    @Test
    @Transactional
    public void testGetFacilityReports(){
        Optional<List<FacilityReport>> res = facilityDao.getFacilityReports();
        assertTrue(res.isPresent());
    }
}
