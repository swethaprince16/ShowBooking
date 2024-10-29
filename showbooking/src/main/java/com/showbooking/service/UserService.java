package com.showbooking.service;

import com.showbooking.dto.UserDto;
import com.showbooking.models.Users;
import com.showbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder= new BCryptPasswordEncoder(12);
    public Users registerUser(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String verify(Users user) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getUsername());

        return "fail";
    }

    @Transactional
    @Cacheable(value = "users", key = "#id", unless = "#result == null")
    public UserDto getUserDetailsById(Integer id) {
        Users user = userRepository.findById(id).get();
        user.getTickets().size();
        return Users.toDto(user);
    }

    @CachePut(value = "users", keyGenerator = "customKeyGenerator")
    public UserDto updateUserDetails(UserDto userDto) {
        Users user = userRepository.findById(userDto.getId()).get();
        if(StringUtils.hasText(userDto.getEmail()))
            user.setEmail(userDto.getEmail());
        if(StringUtils.hasText(userDto.getMobile()))
            user.setMobile(userDto.getMobile());

        userRepository.save(user);
        return Users.toDto(user);
    }

    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(Integer id) {
        Users user = userRepository.findById(id).get();
        if(user!=null)
            userRepository.delete(user);
    }
}
