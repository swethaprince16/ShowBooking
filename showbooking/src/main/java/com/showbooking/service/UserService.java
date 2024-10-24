package com.showbooking.service;

import com.showbooking.models.Users;
import com.showbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder encoder= new BCryptPasswordEncoder(12);
    public Users registerUser(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
