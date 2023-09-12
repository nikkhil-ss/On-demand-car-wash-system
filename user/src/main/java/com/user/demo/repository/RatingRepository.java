package com.user.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.user.demo.model.Ratings;

public interface RatingRepository extends MongoRepository<Ratings, String> {

}
