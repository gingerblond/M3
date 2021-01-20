package com.example.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table
public class CleaningServiceEmployee extends Employee{

    private int workingHours;
    private String responsibility;

    public CleaningServiceEmployee(int employeeId, String firstName, String lastName, String socialId, Hotel hotel, int workingHours, String responsibility) {
        super(employeeId, firstName, lastName, socialId, hotel);
        this.workingHours = workingHours;
        this.responsibility = responsibility;
    }

    public int getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }
}
