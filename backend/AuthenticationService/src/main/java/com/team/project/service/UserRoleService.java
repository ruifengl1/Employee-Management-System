package com.team.project.service;

import com.team.project.dao.UserRoleDao;
import com.team.project.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserRoleService {

    private UserRoleDao userRoleDao;

    @Autowired
    public void setUser(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;

    }
    @Transactional
    public Integer addNewUserRole(UserRole userRole) {
        return userRoleDao.add(userRole);
    }
}
