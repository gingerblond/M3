package com.example.hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customer_DB")
public class CustomerMo {
    /*  @Transient
      public static final String SEQUENCE_NAME = "customers_sequence";*/
    @Id
    private String customerId;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String idCard;



}



