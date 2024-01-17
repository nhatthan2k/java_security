package com.example.demorestapispringsecurity.service;

import com.example.demorestapispringsecurity.model.entity.ERoles;
import com.example.demorestapispringsecurity.model.entity.Roles;

import java.util.Optional;

public interface RolesService {
    Optional<Roles> findByName(ERoles name);
}
