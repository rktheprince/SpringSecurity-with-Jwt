package com.jwtauth.service;

import com.jwtauth.ExceptionHandling.NoUserFoundException;
import com.jwtauth.jwt.JwtUtil;
import com.jwtauth.model.JwtRequest;
import com.jwtauth.model.JwtResponse;
import com.jwtauth.model.UserInfo;
import com.jwtauth.repository.UserInfoRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepo userInfoRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    Logger LOGGER = LoggerFactory.getLogger(UserInfoService.class);

    public JwtResponse authenticate(JwtRequest jwtRequest) throws Exception {
        try{
                this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        }catch(UsernameNotFoundException e){
            e.printStackTrace();
            throw new Exception("Username not found");
        }catch(BadCredentialsException e){
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }

        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtil.generateToken(userDetails);

        return new JwtResponse(token);
    }


    public List<UserInfo> getUser() {
       LOGGER.info("Getting All Users");
        return this.userInfoRepo.findAll();
    }

    public UserInfo getUserById(int userid){
        LOGGER.info("Getting All Users");
        return this.userInfoRepo.findByUserId(userid);
    }

    public String postUser(UserInfo userInfo){
        LOGGER.info("Saving the User");
        String passwd = userInfo.getPassword();
        userInfo.setPassword(passwordEncoder.encode(passwd));
        this.userInfoRepo.save(userInfo);
        return "User Created Successfully";
    }
    public String deleteUser(int userid){
        LOGGER.warn("Deleting the User");
        UserInfo userInfo = this.userInfoRepo.findByUserId(userid);
        if(userInfo != null) {
            this.userInfoRepo.deleteById(userid);
            return "User Deleted Successfully";
        }
        else{
            throw new NoUserFoundException("No user to delete with this userid" + userid);
        }

    }

    public String putUser(int userid, UserInfo userInfo) {
        LOGGER.warn("Updating the User");
        UserInfo abc = this.userInfoRepo.findByUserId(userid);
        abc.setFirstname(userInfo.getFirstname());
        abc.setLastname(userInfo.getLastname());
        abc.setUsername(userInfo.getUsername());
        String passwd = userInfo.getPassword();
        abc.setPassword(passwordEncoder.encode(passwd));
        this.userInfoRepo.save(abc);
        return "User Updated Successfully";
    }
}
