package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.OrderDetails;

public interface OrderRepo extends MongoRepository<OrderDetails, String> {

}
