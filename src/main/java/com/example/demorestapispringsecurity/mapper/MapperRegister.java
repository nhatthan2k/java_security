package com.example.demorestapispringsecurity.mapper;

import com.example.demorestapispringsecurity.model.dto.request.RegisterRequest;
import com.example.demorestapispringsecurity.model.dto.response.RegisterResponse;
import com.example.demorestapispringsecurity.model.entity.ERoles;
import com.example.demorestapispringsecurity.model.entity.Roles;
import com.example.demorestapispringsecurity.model.entity.Users;
import com.example.demorestapispringsecurity.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Component
public class MapperRegister implements MapperGeneric<Users, RegisterRequest, RegisterResponse> {
    @Autowired
    private RolesService rolesService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Users mapperRequestToEntity(RegisterRequest registerRequest) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //Xử lý quyền user khi đăng ký
        Set<String> strRoles = registerRequest.getListRoles();
        Set<Roles> listRoles = new HashSet<>();
        if (strRoles == null) {
            //User quyen mac dinh Role_user
            Roles userRole = rolesService.findByName(ERoles.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            listRoles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Roles adminRole = rolesService.findByName(ERoles.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        listRoles.add(adminRole);
                        break;
                    case "moderator":
                        Roles modRole = rolesService.findByName(ERoles.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        listRoles.add(modRole);
                        break;
                    case "user":
                        Roles userRole = rolesService.findByName(ERoles.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        listRoles.add(userRole);
                        break;
                }
            });
        }
        return Users.builder().userName(registerRequest.getUserName())
                //Mã hóa mật khẩu người dùng khi đăng ký
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .phone(registerRequest.getPhone())
                .address(registerRequest.getAddress())
                .created(sdf.format(new Date()))
                .status(true)
                .listRoles(listRoles).build();
    }

    @Override
    public RegisterResponse mapperEntityToResponse(Users users) {
        return RegisterResponse.builder().id(users.getId())
                .userName(users.getUserName())
                .password(users.getPassword())
                .email(users.getEmail())
                .phone(users.getPhone())
                .address(users.getAddress()).build();
    }
}