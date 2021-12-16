package com.jwtauth.service;

import com.jwtauth.model.CustomUserDetails;
import com.jwtauth.model.UserInfo;
import com.jwtauth.repository.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepo userInfoRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = this.userInfoRepo.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("No Such User Found");
        }
        return new CustomUserDetails(user);
    }
}
