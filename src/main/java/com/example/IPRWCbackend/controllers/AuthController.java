package com.example.IPRWCbackend.controllers;

import com.example.IPRWCbackend.daos.AccountDAO;
import com.example.IPRWCbackend.models.Account;
import com.example.IPRWCbackend.models.LoginCredentials;
import com.example.IPRWCbackend.security.JWTUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "${frontend_url}")
public class AuthController {
    private final JWTUtil jwtUtil;
    private final AccountDAO accountDAO;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public AuthController(JWTUtil jwtUtil, AccountDAO accountDAO, PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.accountDAO = accountDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public Object loginHandler(@RequestBody LoginCredentials body) {
        try {
            if (accountDAO.findAccountByEmail(body.getEmail()).isPresent()) {
                Optional<Account> account = accountDAO.findAccountByEmail(body.getEmail());
                if (passwordEncoder.matches(body.getPassword(), account.get().getPassword())) {
                    HashMap<String, Object> response = new HashMap<>();
                    response.put("token", jwtUtil.generateToken(body.getEmail(), account.get().getRole().getName()));
                    response.put("account", account.get());

                    ObjectMapper objectMapper = new ObjectMapper();
                    String accountJson = objectMapper.writeValueAsString(response);

                    return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
                } else {
                    return new ResponseEntity<>(Collections.singletonMap("message", "Invalid Login Credentials"), HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(Collections.singletonMap("message", "Invalid Login Credentials"), HttpStatus.UNAUTHORIZED);
            }

        } catch (AuthenticationException authExc) {
            return new ResponseEntity<>(Collections.singletonMap("message", "Invalid Login Credentials"), HttpStatus.UNAUTHORIZED);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}