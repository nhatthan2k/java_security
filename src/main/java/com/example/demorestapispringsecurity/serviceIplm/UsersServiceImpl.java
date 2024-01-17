package com.example.demorestapispringsecurity.serviceIplm;

import com.example.demorestapispringsecurity.jwt.JwtTokenProvider;
import com.example.demorestapispringsecurity.mapper.MapperRegister;
import com.example.demorestapispringsecurity.model.dto.request.LoginRequest;
import com.example.demorestapispringsecurity.model.dto.request.RegisterRequest;
import com.example.demorestapispringsecurity.model.dto.response.LoginResponse;
import com.example.demorestapispringsecurity.model.dto.response.RegisterResponse;
import com.example.demorestapispringsecurity.model.entity.Users;
import com.example.demorestapispringsecurity.model.repository.UsersRepository;
import com.example.demorestapispringsecurity.security.CustomUserDetail;
import com.example.demorestapispringsecurity.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private MapperRegister mapperRegister;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Override
    public Users findByUserNameAndStatus(String userName, boolean status) {
        return usersRepository.findByUserNameAndStatus(userName, status);
    }

    @Override
    public boolean existsByUserName(String userName) {
        return usersRepository.existsByUserName(userName);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usersRepository.existsByEmail(email);
    }

    @Override
    public RegisterResponse saveOrUpdate(RegisterRequest registerRequest) {
        return mapperRegister.mapperEntityToResponse(usersRepository.save(mapperRegister
                .mapperRequestToEntity(registerRequest)));
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
        //Sinh JWT tra ve client
        String jwt = jwtTokenProvider.generateToken(customUserDetail);
        //Lay cac quyen cua user
        List<String> listRoles = customUserDetail.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return new LoginResponse(jwt,"Bearer",customUserDetail.getUsername(),
                customUserDetail.getEmail(),customUserDetail.getPhone(),listRoles);
    }
}
