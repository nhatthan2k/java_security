package com.example.demorestapispringsecurity.service;

import com.example.demorestapispringsecurity.model.dto.request.LoginRequest;
import com.example.demorestapispringsecurity.model.dto.request.RegisterRequest;
import com.example.demorestapispringsecurity.model.dto.response.LoginResponse;
import com.example.demorestapispringsecurity.model.dto.response.RegisterResponse;
import com.example.demorestapispringsecurity.model.entity.Users;

public interface UsersService {
    Users findByUserNameAndStatus(String userName, boolean status);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    RegisterResponse saveOrUpdate(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest);
}
