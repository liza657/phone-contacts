package com.example.phonecontacts.controller;

import com.example.phonecontacts.dto.AuthResponseDto;
import com.example.phonecontacts.dto.LoginDto;
import com.example.phonecontacts.entity.Role;
import com.example.phonecontacts.entity.User;

import com.example.phonecontacts.repository.RoleRepository;
import com.example.phonecontacts.repository.UserRepository;
import com.example.phonecontacts.security.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JWTGenerator jwtGenerator;




    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>("Email is taken!", HttpStatus.BAD_REQUEST);
        }
        User newUser = new User();
        user.setUsername(user.getUsername());
         user.setPassword(encoder.encode(user.getPassword()));

        Role roles = roleRepository.findByName("USER");
        user.setRoles(Collections.singletonList(roles));
        userRepository.save(user);
        return new ResponseEntity<>("User registration is success", HttpStatus.OK);
    }


}
