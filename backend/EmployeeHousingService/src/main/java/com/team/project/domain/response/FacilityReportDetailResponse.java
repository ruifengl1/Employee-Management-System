package com.team.project.domain.response;

import com.team.project.domain.FinalFacilityReportDetail;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacilityReportDetailResponse {
    private String message;
    private List<FinalFacilityReportDetail> body;

}
