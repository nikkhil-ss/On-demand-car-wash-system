package com.example.demo.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.API_requestException;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    //To get all the users from DB
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    //This method will be consumed by 3-entity micro services using rest template
    //To find a user of any role using name
    public User getSpecificUser(String name){
        return userRepository.findAll().stream().filter(x -> x.getFullname().contains(name)).findFirst().get();
    }
    //To delete a User
    public ResponseEntity<Map<String,Boolean>> deleteUser(String id){
        User user=userRepository.findById(id).orElseThrow(() -> new API_requestException("User with ID -> "+id+" not found, deletion failed"));
        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("User Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    //To fnd user with their role
    public List<User> findListbyRole(String role) {
        Role r=roleRepository.findByRole(role);
        Set<Role> roles= new HashSet<>();
        roles.add(r);
        return userRepository.findByRolesIn(roles);
    }
    public User getWasher(String name){
        Role r=roleRepository.findByRole("WASHER");
        Set<Role> roles= new HashSet<>();
        roles.add(r);
        return userRepository.findByRolesIn(roles).stream().filter(x -> x.getFullname().contains(name)).findFirst().get();
    }
}