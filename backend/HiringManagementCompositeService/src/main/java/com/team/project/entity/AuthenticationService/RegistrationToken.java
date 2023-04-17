package com.team.project.entity.AuthenticationService;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationToken implements java.io.Serializable {
    private Integer registrationTokenId;
    private String token;
    private String email;
    private LocalDateTime expirationDate;
    @Builder.Default()
    private Boolean consumed = false;
    private User createBy;
}