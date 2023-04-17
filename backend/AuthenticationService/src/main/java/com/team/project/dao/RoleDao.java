package com.team.project.dao;

import com.team.project.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoleDao extends  AbstractHibernateDao<Role> {
    public RoleDao() {
        setClassName(Role.class);
    }
    public Optional<Role> getRoleByRoleName(String roleName) {
        List<Role> roles =  getByField("roleName", roleName);
        return roles.size() == 0 ? Optional.empty() : Optional.of(roles.get(0));
    }
}
