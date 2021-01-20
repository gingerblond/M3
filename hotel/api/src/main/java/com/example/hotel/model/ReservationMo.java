package com.example.hotel.model;

import com.example.hotel.entity.Customer;
import com.example.hotel.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "reservation_DB")
public class ReservationMo {

    @Transient
    public static final String SEQUENCE_NAME = "reservations_sequence";

    private int id;
    private double price;
    private String date;
    private int duration;
    private CustomerMo customer;
    private RoomMo room;


}
