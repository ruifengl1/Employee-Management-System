package com.team.project.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class VisaDocument {
    private VisaStatus visaStatus;
    private PersonalDocument personalDocument;
}
