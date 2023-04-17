package com.team.project.controller;

import com.team.project.domain.request.NewTokenRequest;
import com.team.project.domain.response.ErrorResponse;
import com.team.project.domain.response.GeneralResponse;
import com.team.project.entity.RegistrationToken;
import com.team.project.exception.RegistrationTokenNotFoundException;
import com.team.project.exception.UserNotFoundException;
import com.team.project.service.RegistrationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("registration-token")
public class RegistrationTokenController {

    private final RegistrationTokenService registrationTokenService;
    @Autowired
    private RegistrationTokenController(RegistrationTokenService registrationTokenService) {
        this.registrationTokenService = registrationTokenService;
    }

    @GetMapping("/{id}")
    public Object getRegistrationToken(@PathVariable Integer id) throws RegistrationTokenNotFoundException {
        System.out.println(id);
        return GeneralResponse.buildGeneralResponse(null, registrationTokenService.getRegistrationTokenById(id));
    }

    @GetMapping("/token-name/{tokenName}")
    public Object getRegistrationTokenByTokenName(@PathVariable String tokenName) throws RegistrationTokenNotFoundException {
        return GeneralResponse.buildGeneralResponse(null, registrationTokenService.getRegistrationTokenByTokenName(tokenName));
    }
    @PostMapping()
    public Object postRegistrationToken(@Valid @RequestBody NewTokenRequest request, BindingResult bindingResult) throws UserNotFoundException {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            return ErrorResponse.buildErrorResponse(errors);
        }
        RegistrationToken registrationToken = registrationTokenService.addNewRegistrationToken(request);
        return GeneralResponse.buildGeneralResponse(null, registrationToken);
    }
}
