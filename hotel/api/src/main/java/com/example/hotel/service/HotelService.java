package com.example.hotel.service;

import com.example.hotel.entity.Hotel;
import com.example.hotel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository repository;

    /**
     * Add Hotel
     * @param hotel
     * @return
     */
    public Hotel addHotel(Hotel hotel){
        return repository.save(hotel);
    }

    /**
     * Get all hotels
     * @return
     */
    public List<Hotel> getHotels(){
        return  repository.findAll();
    }
}
