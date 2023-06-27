package com.anhonestobserver.springbootneo4j.services;

import com.anhonestobserver.springbootneo4j.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class NeoUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public NeoUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // username comes from insomnia basic auth
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("Username not found: " + username));
    }
}
