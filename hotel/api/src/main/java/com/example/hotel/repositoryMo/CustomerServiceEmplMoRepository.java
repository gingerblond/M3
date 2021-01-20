package com.example.hotel.repositoryMo;

import com.example.hotel.model.CustomerServiceEmplMo;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface CustomerServiceEmplMoRepository extends MongoRepository<CustomerServiceEmplMo, Integer> {
}
