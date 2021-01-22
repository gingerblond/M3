package com.example.hotel.service;

import com.example.hotel.entity.Hotel;
import com.example.hotel.model.HotelMo;
import com.example.hotel.repository.HotelRepository;
import com.example.hotel.repositoryMo.HotelMoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository repository;

    @Autowired
    private HotelMoRepository hotelMoRepository;

    /**
     * Add Hotel
     * @param hotel
     * @return
     */
    public Hotel addHotel(Hotel hotel){
        return repository.save(hotel);
    }

    /**
     * Get all hotels SQL
     * @return
     */
    public List<Hotel> getHotels(){
        return  repository.findAll();
    }
    /**
     * Get all hotels Mongo
     * @return
     */
    public List<HotelMo> getHotelsMo(){
        return hotelMoRepository.findAll();
    }
}
