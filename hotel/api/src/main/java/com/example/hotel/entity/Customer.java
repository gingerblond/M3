package com.example.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name= "customer_tbl")
public class Customer {

    @Id
    @GeneratedValue()
    private int customerId;
    private String firstName;
    private String lastName;
    private String idCard;


/**
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy="customer")
    private List<Reservation> reservations;
   /** @OneToMany(targetEntity = Reservation.class, cascade = CascadeType.ALL)
    @JoinColumn(name="customerId", referencedColumnName = "customerId")
    private List<Reservation> reservations;
    **/


    /**public Customer(String firstName, String lastName, String idCard){
        //ako je bilo koji null ili prazan baci exception
        this.firstName = firstName;
        this.lastName = lastName;
        this.idCard = idCard;
    }**/

}
