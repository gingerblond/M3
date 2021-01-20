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
@Table(name= "hotel_tbl")
public class Hotel {

    @Id
    private int hotelId;
    private String address;

    @OneToMany(targetEntity = Room.class, cascade = CascadeType.ALL)
    @JoinColumn(name="hotel_id", referencedColumnName = "hotelId")
    private List<Room> rooms;


  /**   @OneToMany(targetEntity = Employee.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id", referencedColumnName = "hotelId")
    private List<Employee> employees;**/

}
