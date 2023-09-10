package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.User;
import com.example.demo.services.AuthService;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/manage")
public class RepoController {
    @Autowired
    private AuthService authService;

    @GetMapping("/all")
    public List<User> getAllUser(){
        return authService.getAllUser();
    }
    @GetMapping("/userByName/{name}")
    public User getSpecificUser(@PathVariable String name){
        return authService.getSpecificUser(name);
    }
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteUser(@PathVariable String id){
        return authService.deleteUser(id);
    }
    @GetMapping("/users/{role}")
    public List<User> getUserByRole(@PathVariable String role){
        return authService.findListbyRole(role);
    }
    @GetMapping("/washer/{name}")
    public User getWasher(@PathVariable String name){
        return authService.getWasher(name);
    }
}