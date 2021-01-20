package com.example.hotel.service;

import com.example.hotel.entity.Customer;
import com.example.hotel.entity.Reservation;
import com.example.hotel.entity.Room;
import com.example.hotel.repository.CustomerRepository;
import com.example.hotel.repository.ReservationRepository;
import com.example.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository repository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;

    /**
     * POST Save new reservation
     * @param reservation
     * @return
     */
    public Reservation saveReservation(Reservation reservation){
        changeAvailability(reservation.getRoom());
        return repository.save(reservation);
    }

    /**
     * DELETE a reservation by its id
     * @param id
     * @return
     */
    public String deleteReservation(int id){
        changeAvailability(getReservationById(id).getRoom());
        repository.deleteById(id);
        return "Reservation with id: " +id + " successfully deleted!";
    }
    public Reservation getReservationById(int id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Get list of all reservations
     * @return
     */
    public List<Reservation> getReservations() {
        return  repository.findAll();
    }

    /**
     * Get all reservations by Customer ID
     * @param customerId
     * @return
     */
    public List<Reservation> getReservationsByCustomerID(int customerId){
        List<Reservation> reservationsByCustomer= new ArrayList<>();
        Customer customer = customerService.getCustomerById(customerId);
        List<Reservation> reservations= getReservations();
        for (int i=0; i<reservations.size();i++) {
            if(reservations.get(i).getCustomer().getCustomerId()==customerId){
                reservationsByCustomer.add(reservations.get(i));
            }
        }
        return reservationsByCustomer;
    }

    public Reservation updateReservation(Reservation reservation){
        Reservation existingReservation= repository.findById(reservation.getReservationID()).orElse(null);
        existingReservation.setPrice(reservation.getPrice());
        existingReservation.setDate(reservation.getDate());
        existingReservation.setDuration(reservation.getDuration());
        changeAvailability(existingReservation.getRoom());
        changeAvailability(reservation.getRoom());
        existingReservation.setRoom(reservation.getRoom());
        existingReservation.setCustomer(reservation.getCustomer());
        return  repository.save(existingReservation);
    }

    /**
     * Change availability of the rooms
     * @param room
     * @return
     */
    public Room changeAvailability(Room room){
        Room existingRoom = roomRepository.findById(room.getRoomID()).orElse(null);
        existingRoom.setAvailable(!room.isAvailable());
        existingRoom.setType(room.getType());
        return roomRepository.save(existingRoom);
    }
}
