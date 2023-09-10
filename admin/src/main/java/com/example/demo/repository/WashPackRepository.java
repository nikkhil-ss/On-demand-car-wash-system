package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.WashPacks;

public interface WashPackRepository extends MongoRepository<WashPacks, String> {
}
