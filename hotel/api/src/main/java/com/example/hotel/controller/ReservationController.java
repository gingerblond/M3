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
    @Autowired
    private HotelMoRepository hotelMoRepository;

    /**
     * POST Save a new reservation
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("addReservation")
    /*public Reservation addReservation(@RequestBody Reservation reservation){
        return service.saveReservation(reservation);
    }*/
    public ReservationMo addReservation(@RequestBody ReservationMo reservationMo){
        changeAvailability(reservationMo.getRoom());
        reservationMo.getRoom().setAvailable(!reservationMo.getRoom().isAvailable());
        return reservationMoRepository.save(reservationMo);
    }

    public HotelMo changeAvailability(RoomMo room){
        List<RoomMo> rooms = hotelMoRepository.findAll().get(0).getRoomsMo();
        for(RoomMo r: rooms){
            if(r.getRoomId()==room.getRoomId()){
                r.setAvailable(!room.isAvailable());
                r.setType(room.getType());
            }
        }
        HotelMo hotel = hotelMoRepository.findAll().get(0);
        HotelMo hotelMo= new HotelMo(hotel.getHotelId(),hotel.getAddress(),rooms);
        return hotelMoRepository.save(hotelMo);
    }

    /**
     * DELETE Reservation
     * @param id
     * @return
     */

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping ("deleteReservation/{id}")
    public String deleteReservation(@PathVariable int id) {
        return service.deleteReservation(id);
    }

    /**
     * Get reservation by ID
     * @param id
     * @return
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping ("getReservation/{id}")
    public Reservation getReservation(@PathVariable int id){
        return service.getReservationById(id);
    }

    /**
     * Get reservations List by Customer ID
     * @param id
     * @return
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping ("getReservationsByCustomer/{id}")
    public List<Reservation> getReservationsByCustomerID(@PathVariable int id){
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
    public Reservation updateReservation(@RequestBody Reservation reservation) {
        return service.updateReservation(reservation);
    }

    /**
     * Report about all reservation with full customer, reservation and room information
     * @return
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getReservationReport")
    public List<ReservationReport> getJoinInformation(){
        return reservationRepository.getReportReservation();
    }

    /**
     * Get all booked SINGLE rooms more than 2 days
     * @return
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getSingleRoomsBookedMoreThan2Days")
    public List<ReservationReport> getBookedRooms(){
        return reservationRepository.getRoomsBookedMoreThan2Days();
    }

}
