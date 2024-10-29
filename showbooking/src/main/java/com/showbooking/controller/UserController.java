package com.showbooking.controller;

import com.showbooking.dto.UserDto;
import com.showbooking.models.Users;
import com.showbooking.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Users registerUser(@RequestBody Users user){
        return userService.registerUser(user);

    }

    @PostMapping("/login")
    public String loginUser(@RequestBody Users user){
        return userService.verify(user);
    }
    @GetMapping("/details/{id}")
    public ResponseEntity<UserDto> getUserDetailsById(@PathVariable Integer id){
        log.info("inside details method!!!");
        return new ResponseEntity<>(userService.getUserDetailsById(id), HttpStatus.OK);

    }
    @PutMapping
    public ResponseEntity<UserDto> updateUserDetails(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.updateUserDetails(userDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);

    }
}
