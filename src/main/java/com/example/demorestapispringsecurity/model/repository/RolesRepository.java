package com.example.demorestapispringsecurity.model.repository;

import com.example.demorestapispringsecurity.model.entity.ERoles;
import com.example.demorestapispringsecurity.model.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(ERoles name);
}
