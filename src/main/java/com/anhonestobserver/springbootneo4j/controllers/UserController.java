package com.anhonestobserver.springbootneo4j.controllers;

import com.anhonestobserver.springbootneo4j.models.User;
import com.anhonestobserver.springbootneo4j.objects.UserDTO;
import com.anhonestobserver.springbootneo4j.requests.CreateUserRequest;
import com.anhonestobserver.springbootneo4j.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public String loggedInUser(Principal principal){ // principal is what user is authenticated right now
        return principal.getName();
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> signUp(@RequestBody CreateUserRequest request){ // request body comes from insomnia json
        User user = userService.createUser(request);

        UserDTO responseUser = new UserDTO(user.getName(), user.getUsername(), user.getRoles());
        return new ResponseEntity<>(responseUser, HttpStatus.CREATED);
    }
}
