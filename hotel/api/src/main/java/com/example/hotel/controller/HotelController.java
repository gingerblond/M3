package com.example.hotel.controller;

import com.example.hotel.entity.Hotel;
import com.example.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class HotelController {

    @Autowired
    HotelService hotelService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("getHotels")
    public List<Hotel> getHotels(){
        return hotelService.getHotels();
    }
}
