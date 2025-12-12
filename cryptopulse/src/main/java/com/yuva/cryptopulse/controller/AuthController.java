package com.yuva.cryptopulse.controller;

import com.yuva.cryptopulse.Entity.User;
import com.yuva.cryptopulse.dto.AuthRequestDTO;
import com.yuva.cryptopulse.dto.RegisterRequestDTO;
import com.yuva.cryptopulse.service.RegisterService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Data
public class AuthController {
    private RegisterService registerService;
    private AuthenticationManager authenticationManager;


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequestDTO registerRequestDTO){
        User user = registerService.registerUser(registerRequestDTO);
        return ResponseEntity.ok(user);
    }


    @PostMapping("/signin")
    public ResponseEntity<?> authenticaterUser(@RequestBody AuthRequestDTO authRequestDTO){

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequestDTO.getEmail(), authRequestDTO.getPassword())
            );


        }catch (Exception e){

        }


        return ResponseEntity.ok(user);
    }


    }

