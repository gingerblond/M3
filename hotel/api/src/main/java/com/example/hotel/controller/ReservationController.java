package com.example.hotel.controller;

import com.example.hotel.dto.ReservationReport;

import com.example.hotel.entity.Reservation;
import com.example.hotel.entity.Room;
import com.example.hotel.model.HotelMo;
import com.example.hotel.model.ReservationMo;
import com.example.hotel.model.RoomMo;
import com.example.hotel.repository.CustomerRepository;
import com.example.hotel.repository.ReservationRepository;
import com.example.hotel.repository.RoomRepository;
import com.example.hotel.repositoryMo.HotelMoRepository;
import com.example.hotel.repositoryMo.ReservationMoRepository;
import com.example.hotel.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private ReservationService service;

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReservationMoRepository reservationMoRepository;
    @Autowired
    private RoomRepository roomRepository;


    /**
     * POST Save a new reservation
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("addReservation")
    /*public Reservation addReservation(@RequestBody Reservation reservation){
        return service.saveReservation(reservation);
    }*/
    public ReservationMo addReservation(@RequestBody ReservationMo reservationMo){
        if (reservationMo.getCustomer().getCustomerId().equals("")) {
            return null;
        } else {
            service.changeAvailabilityMo(reservationMo.getRoom());
            reservationMo.getRoom().setAvailable(!reservationMo.getRoom().isAvailable());
            return reservationMoRepository.save(reservationMo);
        }
    }

    /**
     * DELETE Reservation
     * @param id
     * @return
     */

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping ("deleteReservation/{id}")
    public String deleteReservation(@PathVariable String id) {
        return service.deleteReservationMo(id);
        //return service.deleteReservation(id);
    }

    /**
     * Get reservation by ID
     * @param id
     * @return
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping ("getReservation/{id}")
    public ReservationMo getReservation(@PathVariable String id){
        return service.getReservationMoById(id);
    }

    /**
     * Get reservations List by Customer ID
     * @param id
     * @return
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping ("getReservationsByCustomer/{id}")
    public List<ReservationMo> getReservationsByCustomerID(@PathVariable String id){
        return service.getReservationsByCustomerID(id);
    }

    /**
     * Get list of all reservations
     * @return List of rooms
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping ("getReservations")
    public List<Reservation> getReservation(){
        return service.getReservations();
    }

    /**
     * Update existing reservation
     * @param reservation
     * @return
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping ("updateReservation")
    public ReservationMo updateReservation(@RequestBody ReservationMo reservation) {
        //return service.updateReservation(reservation);
        return service.updateReservationMo(reservation);
    }

    /**
     * Report about all reservation with full customer, reservation and room information from MySQL
     * @return
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getReservationReport")
    public List<ReservationReport> getJoinInformation(){
        return reservationRepository.getReportReservation();
    }

    /**
     * Get all booked SINGLE rooms more than 2 days from MySQL
     * @return
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getSingleRoomsBookedMoreThan2Days")
    public List<ReservationReport> getBookedRooms(){
        return reservationRepository.getRoomsBookedMoreThan2Days();
    }

    /**
     * Report about all reservation with full customer, reservation and room information from MongoDB
     * @return
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/mongoSingleRoomsMoreThan2Days")
    public List<ReservationMo> mongoTest(){
        return service.reservationSingleRoomMoreThan2Days();
    }
    /**
     * Get all booked SINGLE rooms more than 2 days from MongoDB
     * @return
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/mongoAllReservations")
    public List<ReservationMo> mongoReservation(){
        return service.allReservations();
    }

}
