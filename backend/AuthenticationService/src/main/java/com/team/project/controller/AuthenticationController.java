package com.team.project.controller;

import com.team.project.domain.body.LoginBody;
import com.team.project.domain.request.LoginRequest;
import com.team.project.domain.request.RegisterRequest;
import com.team.project.domain.response.ErrorResponse;
import com.team.project.domain.response.GeneralResponse;
import com.team.project.entity.User;
import com.team.project.exception.UserAlreadyExistsException;
import com.team.project.service.AuthService;
import com.team.project.service.RegistrationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping()
public class AuthenticationController {

    private AuthService authService;
    private RegistrationTokenService registrationTokenService;

    @Autowired
    public AuthenticationController(AuthService authService, RegistrationTokenService registrationTokenService) {
        this.authService = authService;
        this.registrationTokenService = registrationTokenService;
    }
    @GetMapping
    public String getAuthentication(){
        return "Authentication Service";
    }

    @PostMapping("/signup")
    public Object registerNewUser(
            @Valid @RequestBody RegisterRequest request,
            BindingResult bindingResult
    ) throws UserAlreadyExistsException {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            return ErrorResponse.buildErrorResponse(errors);
        }
        User user = authService.registerUser(request);
        registrationTokenService.consumeRegistrationToken(request.getTokenId());
        return GeneralResponse.buildGeneralResponse("New user created", user);
    }

    @PostMapping("/login")
    public Object login(@RequestBody LoginRequest request) throws AuthenticationException {

        LoginBody loginBody = authService.userLogin(request.getUsername(), request.getPassword());
        //Returns the token as a response to the frontend/postman
        return GeneralResponse.buildGeneralResponse("Login successful", loginBody);
    }
}
