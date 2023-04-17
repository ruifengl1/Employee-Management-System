package com.team.project.dao;

import com.team.project.entity.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleDao extends  AbstractHibernateDao<UserRole> {
    public UserRoleDao() {
        setClassName(UserRole.class);
    }

}
