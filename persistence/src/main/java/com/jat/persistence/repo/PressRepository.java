package com.jat.persistence.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jat.persistence.entity.Press;

@Repository
public interface PressRepository extends MongoRepository<Press, String>{

}
