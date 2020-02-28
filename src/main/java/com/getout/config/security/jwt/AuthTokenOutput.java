package com.getout.config.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthTokenOutput {

    private String tokenType;
    private String token;
}
