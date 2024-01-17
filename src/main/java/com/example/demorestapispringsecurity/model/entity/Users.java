package com.example.demorestapispringsecurity.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Users {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user_name",unique = true, nullable = false)
    private String userName;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "email",nullable = false,unique = true)
    private String email;
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String created;
    private String address;
    @Column(name = "user_status")
    private boolean status;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "User_Roles",joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> listRoles = new HashSet<>();
}
