package com.example.hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Document(collection = "customer_DB")
public class CustomerMo {
    /*  @Transient
      public static final String SEQUENCE_NAME = "customers_sequence";*/
    @Id
    private String customerId;
    private String firstName;
    private String lastName;
    private String idCard;



}



