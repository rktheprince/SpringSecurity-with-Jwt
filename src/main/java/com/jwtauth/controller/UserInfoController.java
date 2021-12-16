package com.jwtauth.controller;

import com.jwtauth.model.JwtRequest;
import com.jwtauth.model.JwtResponse;
import com.jwtauth.model.UserInfo;
import com.jwtauth.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class UserInfoController {
    
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping(path = "/authenticate", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
        JwtResponse jwtResponse = this.userInfoService.authenticate(jwtRequest);
        return ResponseEntity.ok(jwtResponse);
    }
    
    @GetMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserJson(){
        List<UserInfo> abc = this.userInfoService.getUser();
        return ResponseEntity.status(HttpStatus.OK).header("abc", "xyz").body(abc);
    }

    @GetMapping(path = "/userx", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getUserXml(){
        List<UserInfo> abc = this.userInfoService.getUser();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/xml");
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(abc);
    }

    @GetMapping(path = "/user/{userid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfo> getUserById(@PathVariable("userid") int userid){
        UserInfo abc = this.userInfoService.getUserById(userid);
        HttpHeaders headers = new HttpHeaders();
        headers.add("abc", "xyz");
        return new ResponseEntity<UserInfo>(abc, headers, HttpStatus.OK);
    }

    @PostMapping(path = "/register", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> postUser(@RequestBody UserInfo userInfo){
        String abc = this.userInfoService.postUser(userInfo);
        HttpHeaders headers = new HttpHeaders();
        headers.add("abc", "xyz");
        return new ResponseEntity<String>(abc, headers, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/user/{userid}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> deleteUser(@PathVariable("userid") int userid){
        String abc = this.userInfoService.deleteUser(userid);
        return ResponseEntity.ok(abc);
    }

    @PutMapping(path = "/user/{userid}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> putUser(@PathVariable("userid") int userid, @RequestBody UserInfo userInfo){
        String abc = this.userInfoService.putUser(userid, userInfo);
        return ResponseEntity.ok(abc);
    }

}
