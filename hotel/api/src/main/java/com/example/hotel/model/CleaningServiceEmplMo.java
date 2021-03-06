package com.example.hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Data
@Document(collection = "cleaningServiceEmpl_DB")
public class CleaningServiceEmplMo extends EmployeeMo{

    private int workingHours;
    private String responsibility;

    public CleaningServiceEmplMo(String employeeId, String firstName, String lastName, String socialId, HotelMo hotel, int workingHours, String responsibility) {
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
