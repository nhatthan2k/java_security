package com.example.demorestapispringsecurity.model.repository;

import com.example.demorestapispringsecurity.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByUserNameAndStatus(String userName,boolean status);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
}
