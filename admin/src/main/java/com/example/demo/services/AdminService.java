package com.example.demo.services;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.WrapperModel.WasherRatings;
import com.example.demo.model.Ratings;
import com.example.demo.model.User;

@Service
public class AdminService {
    @Autowired
    private RestTemplate restTemplate;

    //Url to access the methods of Order Service
    String url="http://ORDER-SERVICE/orders";
    //Url to access the methods of User Service
    String url2="http://USERS-SERVICE/users";
    //Url to access the methods of Zuul Service
    String url4="http://ZUUL-SECURITY/manage";


 

    /** Washer controls through admin using rest template*/
    //To get a single washer
    public User getOneWasher(String name){
        return restTemplate.getForObject(url4+"/washer/"+name,User.class);
    }
    //To get the details/raatings of Washers
    public WasherRatings washerSpecificRatings(String washerName){
        Ratings[] ratingsList=restTemplate.getForObject(url2+"/washerSpecificRating/"+washerName,Ratings[].class);
        //Wrapping into a "Proxy class"
        return new WasherRatings(Arrays.asList(ratingsList));
    }
    //To get the details of all Washers with all their reviews
    public WasherRatings washerRatings(){
        Ratings[] ratingsList=restTemplate.getForObject(url2+"/getallRatings",Ratings[].class);
        //Wrapping into a "Proxy class"
        return new WasherRatings(Arrays.asList(ratingsList));
    }
    
}