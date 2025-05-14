package com.nike.apinike.controller;

import com.nike.apinike.request.AuthRequest;
import com.nike.apinike.response.AuthResponse;
import com.nike.apinike.service.impl.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {


    @Autowired
    private AuthenticationServiceImpl authenticationServiceImpl;

    @PostMapping
    public <LoginRequest> ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        String token = authenticationServiceImpl.getToken(authRequest);

        return ResponseEntity.ok(new AuthResponse(token));
    }


}
