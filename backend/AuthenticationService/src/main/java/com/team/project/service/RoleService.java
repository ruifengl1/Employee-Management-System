package com.team.project.service;

import com.team.project.dao.RoleDao;
import com.team.project.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class RoleService {

    private RoleDao roleDao;

    @Autowired
    public void setUser(RoleDao roleDao) {
        this.roleDao = roleDao;

    }
    @Transactional
    public Optional<Role> getRoleByRoleName(String roleName) {
        return roleDao.getRoleByRoleName(roleName);
    }
}
