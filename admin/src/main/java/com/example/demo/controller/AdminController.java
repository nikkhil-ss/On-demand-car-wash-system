package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.WrapperModel.WasherRatings;
import com.example.demo.model.OrderDetails;
import com.example.demo.model.User;
import com.example.demo.model.WashPacks;
import com.example.demo.services.AdminService;
import com.example.demo.services.WashPackService;

@RestController
@RequestMapping("/admins")
public class AdminController{
    @Autowired
    AdminService adminService;
    @Autowired
    WashPackService washerPackService;

    /** Washer controls through admin using service object */
    //To find all the washpack
    @GetMapping("/findallWP")
    public List<WashPacks> findallWP(){
        return washerPackService.findallWP();
    }
    //To find one WashPack with ID
    @GetMapping("/findoneWP/{id}")
    public ResponseEntity<WashPacks> findoneWP(@PathVariable String id){
        return washerPackService.findoneWP(id);
    }
    //To add a new WashPack
    @PostMapping("/addWP")
    public WashPacks addWP(@RequestBody WashPacks washPacks){
        return washerPackService.addWP(washPacks);
    }
    //To delete a Washpack
    @DeleteMapping("/deleteWP/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteWP(@PathVariable String id){
        return washerPackService.deleteWP(id);
    }
    //To update a Washpack
    @PutMapping("/updateWP/{id}")
    public ResponseEntity<WashPacks> updateWP(@PathVariable String id,@RequestBody WashPacks washPacks){
        return washerPackService.updateWP(id,washPacks);
    }
    //To find washpack with washpack name for user's reciept
    @GetMapping("/wpbyname/{name}")
    public WashPacks wpbyname(@PathVariable String name){
        return washerPackService.findbyname(name);
    }

    /** Order controls through admin using rest template */
    //To assign a washer to the order by Admin
    @PutMapping("/assignWasher")
    public OrderDetails assignWasher(@RequestBody OrderDetails orderDetails){
        return adminService.assignWasher(orderDetails);
    }

    /** Washer controls through admin using rest template */
    //To get one washer
    @GetMapping("/oneWasher/{name}")
    public User getOneWasher(@PathVariable String name){
        return adminService.getOneWasher(name);
    }
    //To get all the ratings of a specific Washer
    @GetMapping("/washerRating/{name}")
    public WasherRatings washerSpecificRatings(@PathVariable String name){
        return adminService.washerSpecificRatings(name);
    }
}