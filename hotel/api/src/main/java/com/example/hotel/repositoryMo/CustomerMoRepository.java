package com.example.hotel.repositoryMo;

import com.example.hotel.model.CustomerMo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerMoRepository extends MongoRepository<CustomerMo, Integer> {
}
