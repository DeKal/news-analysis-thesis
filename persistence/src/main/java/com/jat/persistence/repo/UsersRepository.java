package com.jat.persistence.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jat.persistence.entity.Users;

@Repository
public interface UsersRepository extends MongoRepository<Users, String>{
}