package com.example.hotel.repositoryMo;

import com.example.hotel.model.HotelMo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HotelMoRepository extends MongoRepository<HotelMo,Integer> {
}
