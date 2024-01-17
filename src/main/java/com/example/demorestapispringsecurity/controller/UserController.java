package com.example.demorestapispringsecurity.controller;

import com.example.demorestapispringsecurity.model.dto.request.LoginRequest;
import com.example.demorestapispringsecurity.model.dto.request.RegisterRequest;
import com.example.demorestapispringsecurity.model.dto.response.LoginResponse;
import com.example.demorestapispringsecurity.model.dto.response.RegisterResponse;
import com.example.demorestapispringsecurity.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/auth")
public class UserController {
    @Autowired
    private UsersService usersService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        boolean isExistUserName = usersService.existsByUserName(registerRequest.getUserName());
        if (isExistUserName) {
            return ResponseEntity.badRequest().body("UserName is exist");
        }
        boolean isExistEmail = usersService.existsByEmail(registerRequest.getEmail());
        if (isExistEmail) {
            return ResponseEntity.badRequest().body("Email is exist");
        }
        RegisterResponse registerResponse = usersService.saveOrUpdate(registerRequest);
        return new ResponseEntity<>(registerResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = usersService.login(loginRequest);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }
}

