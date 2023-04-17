package com.team.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="User")
public class User implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer userId;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    @JsonIgnore
    private String password;
    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime createDate = LocalDateTime.now();
    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime lastModificationDate = LocalDateTime.now();
    @Column(nullable = false)
    @Builder.Default
    private boolean activeFlag = true;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "createBy")
    @JsonIgnore // to avoid infinite loop
    @ToString.Exclude
    private Set<RegistrationToken> registrationTokens = new HashSet<>(0);

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @JsonIgnore
    //@JsonIgnore // to avoid infinite loop
    //@ToString.Exclude
    private Set<UserRole> userRoles = new HashSet<>(0);
    //@JsonInclude(JsonInclude.Include.NON_NULL)
    public Set<String> getUserRoleNames() {
        if (userRoles == null)
            return new HashSet<>(0);
        return this.userRoles.stream()
                .map(UserRole::getRole)
                .map(Role::getRoleName)
                .collect(toSet());
    }
    public Boolean isUserAdmin() {
        Set<String> userRoleNames = this.getUserRoleNames();
        return !userRoleNames.isEmpty() && userRoleNames.contains("admin");
    }
}
