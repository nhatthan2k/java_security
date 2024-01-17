package com.example.demorestapispringsecurity.serviceIplm;

import com.example.demorestapispringsecurity.model.entity.ERoles;
import com.example.demorestapispringsecurity.model.entity.Roles;
import com.example.demorestapispringsecurity.model.repository.RolesRepository;
import com.example.demorestapispringsecurity.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolesServiceImpl implements RolesService{
    @Autowired
    private RolesRepository rolesRepository;
    @Override
    public Optional<Roles> findByName(ERoles name) {
        return rolesRepository.findByName(name);
    }
}
