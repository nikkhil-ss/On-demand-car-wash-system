package com.example.demo.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Role;
import com.example.demo.model.User;

public interface UserRepository extends MongoRepository<User, String> {
   
    User findByEmail(String email);
   
    List<User> findByRolesIn(Set<Role> roles);

}
