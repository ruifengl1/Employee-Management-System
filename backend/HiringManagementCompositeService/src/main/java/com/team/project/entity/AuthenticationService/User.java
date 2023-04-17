package com.team.project.entity.AuthenticationService;

import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements java.io.Serializable {
    private Integer userId;
    private String username;
    private String email;

    @Builder.Default
    private LocalDateTime createDate = LocalDateTime.now();
    @Builder.Default
    private LocalDateTime lastModificationDate = LocalDateTime.now();
    @Builder.Default
    private boolean activeFlag = true;
    @Builder.Default
    private Set<String> userRoleNames = new HashSet<>();
    private Boolean userAdmin;
}
