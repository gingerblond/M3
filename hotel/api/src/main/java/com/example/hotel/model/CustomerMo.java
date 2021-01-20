package com.example.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customer_DB")
public class CustomerMo {
    @Transient
    public static final String SEQUENCE_NAME = "customers_sequence";
    @Id
    private int id;
    private String firstName;
    private String lastName;
    private String idCard;
}
