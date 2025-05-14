package com.nike.apinike.service;

import com.nike.apinike.request.AuthRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticationService extends UserDetailsService {

    String getToken(AuthRequest auth);

    String validateJwtToken(String token);
}
