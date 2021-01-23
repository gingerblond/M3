package com.example.hotel.repositoryMo;

import com.example.hotel.model.ReservationMo;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ReservationMoRepository extends MongoRepository<ReservationMo, Integer> {



}
