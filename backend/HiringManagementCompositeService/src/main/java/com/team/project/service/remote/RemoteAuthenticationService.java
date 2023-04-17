package com.team.project.service.remote;

import com.team.project.domain.request.NewTokenRequest;
import com.team.project.domain.response.GeneralResponse;
import com.team.project.entity.AuthenticationService.RegistrationToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient("auth")
public interface RemoteAuthenticationService {
    @GetMapping(value = "auth/registration-token/token-name/{tokenName}")
    public GeneralResponse<RegistrationToken> getRegistrationTokenByTokenName(@PathVariable String tokenName);
    @PostMapping(value = "auth/registration-token")
    public GeneralResponse<RegistrationToken> postRegistrationToken(@RequestBody NewTokenRequest request);
}
