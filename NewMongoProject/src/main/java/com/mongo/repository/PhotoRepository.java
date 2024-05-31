package com.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongo.entity.Photo;

@Repository
public interface PhotoRepository extends MongoRepository<Photo, String>{

}
