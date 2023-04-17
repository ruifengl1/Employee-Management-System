package com.team.project.service;

import com.team.project.domain.body.LoginBody;
import com.team.project.domain.request.RegisterRequest;
import com.team.project.entity.Role;
import com.team.project.entity.User;
import com.team.project.entity.UserRole;
import com.team.project.exception.UserAlreadyExistsException;
import com.team.project.security.AuthUserDetail;
import com.team.project.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthService implements UserDetailsService {
    private UserService userService;
    private UserRoleService userRoleService;
    private RoleService roleService;
    private AuthenticationManager authenticationManager;
    private JwtProvider jwtProvider;

    @Autowired
    public void setService(UserService userService, UserRoleService userRoleService, RoleService roleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.roleService = roleService;
    }
    @Autowired
    public void setAuthentication(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userService.getUserByUsername(username);
        if (!userOptional.isPresent()){
            throw new UsernameNotFoundException("Username does not exist");
        }
        User user = userOptional.get(); // database user
        return AuthUserDetail.builder() // spring security's userDetail
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(getAuthoritiesFromUser(user))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .isAdmin(user.isUserAdmin())
                .id(user.getUserId())
                .build();
    }
    public User registerUser(RegisterRequest registerRequest) throws UserAlreadyExistsException {
        String encodedPassword = new BCryptPasswordEncoder().encode(registerRequest.getPassword());
        if (userService.getUserByUsername(registerRequest.getUsername()).isPresent()
            || userService.getUserByEmail(registerRequest.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        User user = User.builder()
                .username(registerRequest.getUsername())
                .password(encodedPassword)
                .email(registerRequest.getEmail())
                .build();
        Integer id = userService.addNewUser(user);
        user.setUserId(id);
        addNewUserRole(user);
        return user;
    }
    public LoginBody userLogin(String username, String password) throws AuthenticationException {
        Authentication authentication;
        //Try to authenticate the user using the username and password
        authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        //Successfully authenticated user will be stored in the authUserDetail object
        AuthUserDetail authUserDetail = (AuthUserDetail) authentication.getPrincipal(); //getPrincipal() returns the user object
        //A token wil be created using the username/email/userId and permission
        return new LoginBody(jwtProvider.createToken(authUserDetail), authUserDetail.isAdmin(), authUserDetail.getId());
    }
    private void addNewUserRole(User user) {
        Optional<Role> role = roleService.getRoleByRoleName("user");
        role.ifPresent(value -> addUserRole(user, value));
    }
    private Integer addUserRole(User user, Role role) {
        UserRole userRole = UserRole.builder()
                .user(user)
                .role(role)
                .build();
        user.setUserRoles(new HashSet<>(Arrays.asList(userRole)));
        return userRoleService.addNewUserRole(userRole);
    }
    private List<GrantedAuthority> getAuthoritiesFromUser(User user){
        List<GrantedAuthority> userAuthorities = new ArrayList<>();
        if (user.isUserAdmin()) {
            userAuthorities.add(new SimpleGrantedAuthority("admin"));
        }
        userAuthorities.add(new SimpleGrantedAuthority("common"));
        return userAuthorities;
    }
}
