package com.team.project.service;

import com.team.project.dao.ApplicationDao;
import com.team.project.entity.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApplicationService {
    private ApplicationDao applicationDao;

    @Autowired
    public ApplicationService(ApplicationDao applicationDao) {
        this.applicationDao = applicationDao;
    }

    @Transactional
    public List<Application> getAllApplications() {
        return applicationDao.getAllApplication();
    }

    @Transactional
    public Application getApplicationById(int id) {
        return applicationDao.getApplicationById(id);
    }

    @Transactional
    public Application getApplicationByEmployeeId(int employeeId) {
        return applicationDao.getApplicationByEmployeeId(employeeId);
    }

    @Transactional
    public void updateApplicationStatus(int employeeId, String newStatus) {
        applicationDao.updateApplicationByEmployeeId(employeeId, newStatus);
    }


    @Transactional
    public void updateApplication(Application application) {
        applicationDao.update(application);
    }

    @Transactional
    public List<Application> getApplicationsByStatus(String status) {
        return applicationDao.getApplicationsByStatus(status);
    }

    @Transactional
    public void createApplication(Application application) {
        applicationDao.save(application);
    }

}
