package com.example.hotel.repositoryMo;

import com.example.hotel.model.CustomerMo;
import com.example.hotel.model.ReservationMo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservationMoRepository extends MongoRepository<ReservationMo, Integer> {
    ReservationMo findByReservationId(String reservationId);
}
