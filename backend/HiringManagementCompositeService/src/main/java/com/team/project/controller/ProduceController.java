package com.team.project.controller;

import com.team.project.domain.request.NewTokenRequest;
import com.team.project.domain.response.ErrorResponse;
import com.team.project.domain.response.GeneralResponse;
import com.team.project.service.AuthEmailService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class ProduceController {

    private RabbitTemplate rabbitTemplate;
    private AuthEmailService authEmailService;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    @Autowired
    public void setAuthEmailService(AuthEmailService authEmailService) {
        this.authEmailService = authEmailService;
    }

    @PostMapping("new-token")
    public Object produceNewToken(@RequestBody NewTokenRequest request) {
        if (!authEmailService.createNewRegistrationTokenAndSendEmailViaRabbitMQ(request)) {
            return ErrorResponse.buildErrorResponse("Failed to create token.");
        }
        //rabbitTemplate.convertAndSend("email.direct", "send", jsonMessage);

        return GeneralResponse.buildGeneralResponse("New token to " + request.getEmail() + " sent.");
    }

}
