package com.example.hotel.model;

import com.example.hotel.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Data
@Document(collection = "customerServiceEmpl_DB")
public class CustomerServiceEmplMo extends EmployeeMo{

    private String phoneNumber;
    private String email;
    private UserMo userMo;

    public CustomerServiceEmplMo(String employeeId, String firstName, String lastName, String socialId, HotelMo hotel, String phoneNumber, String email, UserMo userMo) {
        super(employeeId, firstName, lastName, socialId, hotel);
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userMo = userMo;
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

    public UserMo getUserMo() {
        return userMo;
    }

    public void setUserMo(UserMo userMo) {
        this.userMo = userMo;
    }
}
