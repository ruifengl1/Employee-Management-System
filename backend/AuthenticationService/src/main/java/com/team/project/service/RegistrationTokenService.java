package com.team.project.service;

import com.team.project.dao.RegistrationTokenDao;
import com.team.project.domain.request.NewTokenRequest;
import com.team.project.entity.RegistrationToken;
import com.team.project.entity.User;
import com.team.project.exception.RegistrationTokenNotFoundException;
import com.team.project.exception.UserNotFoundException;
import com.team.project.util.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationTokenService {

    private RegistrationTokenDao registrationTokenDao;
    private UserService userService;
    @Autowired
    public void setUser(RegistrationTokenDao registrationTokenDao, UserService userService) {
        this.registrationTokenDao = registrationTokenDao;
        this.userService = userService;

    }
    @Transactional
    public List<RegistrationToken> getAllRegistrationTokens() {
        return registrationTokenDao.getAll();
    }
    @Transactional
    public RegistrationToken getRegistrationTokenById(Integer id) throws RegistrationTokenNotFoundException {
        RegistrationToken registrationToken = registrationTokenDao.findById(id);
        if (registrationToken == null) {
            throw new RegistrationTokenNotFoundException("Registration token not found.");
        }
        return registrationToken;
    }
    @Transactional
    public RegistrationToken getRegistrationTokenByTokenName(String tokenName) throws RegistrationTokenNotFoundException {
        Optional<RegistrationToken> registrationToken = registrationTokenDao.getRegistrationTokenByTokenName(tokenName);
        if (!registrationToken.isPresent()) {
            throw new RegistrationTokenNotFoundException("Registration token not found.");
        }
        return registrationToken.get();
    }
    @Transactional
    public RegistrationToken addNewRegistrationToken(NewTokenRequest request) throws UserNotFoundException {
        User createBy;
        try {
            createBy = userService.getUserById(request.getCreateBy());
        } catch (UserNotFoundException userNotFoundException) {
            throw new UserNotFoundException("CreateBy user is invalid.");
        }
        RegistrationToken registrationToken = RegistrationToken.builder()
                .createBy(createBy)
                .email(request.getEmail())
                .expirationDate(LocalDateTime.now().plusHours(3))
                .token(TokenGenerator.generateToken())
                .build();
        registrationTokenDao.add(registrationToken);
        return registrationToken;
    }
    @Transactional
    public void consumeRegistrationToken(Integer tokenId) {
        RegistrationToken registrationToken = registrationTokenDao.findById(tokenId);
        registrationToken.setConsumed(true);
        registrationTokenDao.update(registrationToken);
    }
}
