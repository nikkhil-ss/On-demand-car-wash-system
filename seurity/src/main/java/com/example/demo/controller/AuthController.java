package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.JwtTokenProvider;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.CustomUserDetailsService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private CustomUserDetailsService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthBody data) {
        try {
            String username = data.getEmail();
            System.out.println(username);
            try {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.out.println(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword())));

            String token = jwtTokenProvider.createToken(username, this.userRepository.findByEmail(username).getRoles());
            //Method defined in user repo
            User ExistingUser=userService.findUserByEmail(username);
            return new ResponseEntity<>(userService.updateTokenByID(ExistingUser,token),HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Email or Password is Invalid, Please try again with correct credentials");
        }
    }

   
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            return new ResponseEntity<>("User Exists Already, Try with a different E-mail address", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.saveUser(user),HttpStatus.CREATED);
    }
}