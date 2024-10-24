package com.showbooking.service;

import com.showbooking.models.Users;
import com.showbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(username);
        if(user ==null) {
            System.out.println("No user found");
            throw new UsernameNotFoundException("No user found");
        }
        return new UserPrincipal(user);
    }
}