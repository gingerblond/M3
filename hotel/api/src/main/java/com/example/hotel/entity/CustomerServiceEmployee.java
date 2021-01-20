package com.example.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@Entity
@Table
public class CustomerServiceEmployee extends Employee{

    private String phoneNumber;
    private String email;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    @JoinColumn(name="userId", referencedColumnName = "id")
    private User user;

    public CustomerServiceEmployee(int employeeId, String firstName, String lastName, String socialId, Hotel hotel, String phoneNumber, String email, User user) {
        super(employeeId, firstName, lastName, socialId, hotel);
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.user = user;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
