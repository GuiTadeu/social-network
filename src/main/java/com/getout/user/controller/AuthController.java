package com.getout.user.controller;

import com.getout.config.security.jwt.AuthTokenOutput;
import com.getout.config.security.jwt.TokenManager;
import com.getout.user.form.UserLoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenManager tokenManager;

    @PostMapping("/auth")
    public ResponseEntity<AuthTokenOutput> authenticate(@RequestBody UserLoginForm loginForm) {

        UsernamePasswordAuthenticationToken authenticationToken = loginForm.build();

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        String jwt = tokenManager.generateToken(authentication);
        var tokenResponse = new AuthTokenOutput("Bearer", jwt);

        return ResponseEntity.ok(tokenResponse);
    }
}
