package com.example.hotel.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "reservation_DB")
public class ReservationMo {

    /**@Transient
    public static final String SEQUENCE_NAME = "reservations_sequence";**/
    @Id
    private String reservationId;
    private double price;
    private String date;
    private int duration;
    private CustomerMo customer;
    private RoomMo room;


}
