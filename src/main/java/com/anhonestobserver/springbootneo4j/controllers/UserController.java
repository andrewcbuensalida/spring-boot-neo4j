package com.anhonestobserver.springbootneo4j.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {
    @GetMapping("/me")
    public String loggedInUser(Principal principal){ // principal is what user is authenticated right now
        return principal.getName();
    }
}
