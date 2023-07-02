package com.anhonestobserver.springbootneo4j.services;

import com.anhonestobserver.springbootneo4j.models.User;
import com.anhonestobserver.springbootneo4j.repositories.UserRepository;
import com.anhonestobserver.springbootneo4j.requests.CreateUserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public List<User> getUsers() {
        return userRepository.getUsers();
    }
    public User createUser(CreateUserRequest request){
//        if(userRepository.findUserByUsername(request.getUsername()).isEmpty()){
            User user = new User();
            user.setName(request.getName());
            // TODO: make sure username doesn't exist
            user.setUsername(request.getUsername());
            user.setRoles(request.getRoles());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            userRepository.save(user); // This saves to neo4j aura database I guess
            return user;
//        }else {
//            return new Exception("Username already exists");
//        }
    }
}
