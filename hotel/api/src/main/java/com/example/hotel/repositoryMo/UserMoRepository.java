package com.example.hotel.repositoryMo;

import com.example.hotel.model.UserMo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMoRepository extends MongoRepository<UserMo, Integer> {
}
