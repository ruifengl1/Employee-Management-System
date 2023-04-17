package com.team.project.service;

import com.team.project.domain.message.SimpleMessage;
import com.team.project.domain.request.NewTokenRequest;
import com.team.project.domain.response.GeneralResponse;
import com.team.project.entity.AuthenticationService.RegistrationToken;
import com.team.project.service.remote.RemoteAuthenticationService;
import com.team.project.util.SerializeUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthEmailService {
    private RemoteAuthenticationService authenticationService;
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    @Autowired
    public AuthEmailService(RemoteAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public boolean createNewRegistrationTokenAndSendEmailViaRabbitMQ(NewTokenRequest request) {
        GeneralResponse<RegistrationToken> response = authenticationService.postRegistrationToken(request);
        if (response.getStatus().isSuccess()) {
            RegistrationToken registrationToken = response.getBody();
            System.out.println(registrationToken);
            this.sendEmail(registrationToken);
            return true;
        }
        return false;
    }

    private void sendEmail(RegistrationToken registrationToken) {
        String url = "http://localhost:4200/signup?token=" + registrationToken.getToken();
        String to = registrationToken.getEmail();
        SimpleMessage newMessage = SimpleMessage.builder()
                .title("No reply - onboarding link from HR")
                .description(url)
                .to(to)
                .build();
        String jsonMessage = SerializeUtil.serialize(newMessage);
        rabbitTemplate.convertAndSend("email.direct", "send", jsonMessage);
    }


}
