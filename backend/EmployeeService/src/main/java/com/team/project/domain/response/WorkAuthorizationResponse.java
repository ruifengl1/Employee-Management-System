package com.team.project.domain.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class WorkAuthorizationResponse {
    private String visaType;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date expirationDate;
    private long daysLeft;
}
