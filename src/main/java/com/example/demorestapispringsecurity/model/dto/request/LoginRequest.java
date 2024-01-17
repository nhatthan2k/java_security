package com.example.demorestapispringsecurity.model.dto.request;

import lombok.Data;

@Data
public class LoginRequest{
    private String userName;
    private String password;
}
