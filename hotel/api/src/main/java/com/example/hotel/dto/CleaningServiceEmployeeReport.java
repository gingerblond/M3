package com.example.hotel.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CleaningServiceEmployeeReport {

    private int employeeId;
    private String firstName;
    private String lastName;
    private int workingHours;
    private int hotelId;
    private String address;
    private String responsibility;
    private String socialId;
}
