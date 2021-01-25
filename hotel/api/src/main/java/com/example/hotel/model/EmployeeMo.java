package com.example.hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeMo {

    /*@Transient
    public static final String SEQUENCE_NAME = "employee_sequence";*/
    @Id
    private String employeeId;
    private String firstName;
    private String lastName;
    private String socialId;

    private HotelMo hotel;

}
