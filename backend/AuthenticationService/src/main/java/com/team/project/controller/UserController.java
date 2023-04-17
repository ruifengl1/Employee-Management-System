package com.team.project.controller;

import com.team.project.domain.response.GeneralResponse;
import com.team.project.exception.UserNotFoundException;
import com.team.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    private UserService userService;
    @Autowired
    private UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public Object getAllUser(){
        return GeneralResponse.buildGeneralResponse(null, userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public Object getUser(@PathVariable Integer userId) throws UserNotFoundException {
        return GeneralResponse.buildGeneralResponse(null, userService.getUserById(userId));
    }
}
