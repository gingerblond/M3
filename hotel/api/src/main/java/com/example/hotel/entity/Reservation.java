package com.example.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name= "reservation_tbl")
public class Reservation {

    @Id
    @GeneratedValue
    private int reservationID;
    private double price;
    private String date;
    private int duration;

    @ManyToOne( fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    @JoinColumn(name="customerId", referencedColumnName = "customerId")
    private Customer customer;

    @ManyToOne(fetch=FetchType.EAGER,cascade = CascadeType.REFRESH)
    @JoinColumn(name="roomID", referencedColumnName = "roomID")
    private Room room;
}
