package com.example.hotel.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.List;

@Document(collection = "hotel_DB")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelMo {

    @Transient
    public static final String SEQUENCE_NAME = "hotels_sequence";
    @Id
    private int hotelId;
    private String address;
    private List<RoomMo> roomsMo;
}
