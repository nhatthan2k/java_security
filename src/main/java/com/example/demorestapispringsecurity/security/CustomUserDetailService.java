package com.example.demorestapispringsecurity.security;

import com.example.demorestapispringsecurity.model.entity.Users;
import com.example.demorestapispringsecurity.model.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByUserNameAndStatus(username,true);
        if (user==null){
            throw new UsernameNotFoundException("User not found or blocked");
        }
        return CustomUserDetail.mapUserToUserDetail(user);
    }
}
