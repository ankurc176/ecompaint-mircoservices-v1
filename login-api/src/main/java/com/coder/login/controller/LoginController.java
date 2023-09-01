package com.coder.login.controller;

import com.coder.login.DTORequest.LoginRequest;
import com.coder.login.DTOResponse.LoginResponse;
import com.coder.login.model.Login;
import com.coder.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student/login")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostMapping()
    public ResponseEntity<Login> createUserAccount(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(loginService.createUserService(loginRequest),HttpStatus.CREATED);
    }


    @GetMapping()
    public ResponseEntity<LoginResponse> getLoginCredentials(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(loginService.CheckLoginCredentials(loginRequest), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<LoginResponse> updateUserAccount(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(loginService.updateUserService(loginRequest), HttpStatus.OK);
    }

}
