package com.example.jarayoung.core.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthProvider {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final AuthDao authDao;

    public AuthProvider(AuthDao authDao) {
        this.authDao = authDao;
    }
}
