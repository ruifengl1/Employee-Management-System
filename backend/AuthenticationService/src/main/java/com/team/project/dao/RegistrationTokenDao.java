package com.team.project.dao;

import com.team.project.entity.RegistrationToken;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RegistrationTokenDao extends  AbstractHibernateDao<RegistrationToken> {

    public RegistrationTokenDao() {
        setClassName(RegistrationToken.class);
    }

    public Optional<RegistrationToken> getRegistrationTokenByTokenName(String tokenName) {
        List<RegistrationToken> registrationTokens = getByField("token", tokenName);
        return registrationTokens.size() == 0 ? Optional.empty() : Optional.of(registrationTokens.get(0));
    }

}
