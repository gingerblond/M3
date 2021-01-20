package com.example.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_DB")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMo {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
    @Id
    private int id;
    private  String username;
    private  String password;
    private  boolean loggedIn;
}
