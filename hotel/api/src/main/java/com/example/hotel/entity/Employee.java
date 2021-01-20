package com.example.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @MappedSuperclass
    @Table
    public class Employee {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int employeeId;
        private String firstName;
        private String lastName;
        private String socialId;

        @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
        @JoinColumn(name="hotelId", referencedColumnName = "hotelId")
        private Hotel hotel;


}
