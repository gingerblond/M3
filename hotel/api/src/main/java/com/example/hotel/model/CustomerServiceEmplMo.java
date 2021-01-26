package com.example.hotel.model;

import com.example.hotel.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Data
@Document(collection = "customerServiceEmpl_DB")
public class CustomerServiceEmplMo extends EmployeeMo{
    @Indexed(name = "custEmpl_phone", unique = true)
    private String phoneNumber;
    private String email;
    private UserMo user;

    public CustomerServiceEmplMo(String employeeId, String firstName, String lastName, String socialId, HotelMo hotel, String phoneNumber, String email, UserMo user) {
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

    public UserMo getUser() {
        return user;
    }

    public void setUser(UserMo user) {
        this.user = user;
    }
}
