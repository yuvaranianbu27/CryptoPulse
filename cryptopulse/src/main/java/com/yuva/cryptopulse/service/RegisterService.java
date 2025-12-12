package com.yuva.cryptopulse.service;

import com.yuva.cryptopulse.Entity.User;
import com.yuva.cryptopulse.dto.RegisterRequestDTO;
import com.yuva.cryptopulse.repository.UserDetailsRepository;
import lombok.Data;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Data
public class RegisterService {
    private UserDetailsRepository userDetailsRepository;
    private PasswordEncoder passwordEncoder;
    private CompromisedPasswordChecker passwordChecker;


    public User registerUser(RegisterRequestDTO request) {
        //check strong password
        CompromisedPasswordDecision decision = passwordChecker.check(request.getPassword());
        if (decision.isCompromised()) {
            throw new IllegalArgumentException("Choose Strong Password");
        }
        //check email already exist;
        if (userDetailsRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }

        // Encode password
            String encodedPassword = passwordEncoder.encode(request.getPassword());

            //save user
            User user = User.builder()
                    .fullName(request.getName())
                    .email(request.getEmail())
                    .password(encodedPassword)
                    .build();

            return userDetailsRepository.save(user);

        }

    }

