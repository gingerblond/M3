package com.example.hotel.repositoryMo;

import com.example.hotel.model.CleaningServiceEmplMo;
import com.example.hotel.model.ReservationMo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CleaningServiceEmplMoRepository extends MongoRepository<CleaningServiceEmplMo, Integer> {

    CleaningServiceEmplMo findByEmployeeId(String employeeId);
}
