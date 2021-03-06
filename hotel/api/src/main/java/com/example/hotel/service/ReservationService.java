package com.example.hotel.service;

import com.example.hotel.entity.Customer;
import com.example.hotel.entity.Reservation;
import com.example.hotel.entity.Room;
import com.example.hotel.model.CustomerMo;
import com.example.hotel.model.HotelMo;
import com.example.hotel.model.ReservationMo;
import com.example.hotel.model.RoomMo;
import com.example.hotel.repository.CustomerRepository;
import com.example.hotel.repository.ReservationRepository;
import com.example.hotel.repository.RoomRepository;
import com.example.hotel.repositoryMo.CustomerMoRepository;
import com.example.hotel.repositoryMo.HotelMoRepository;
import com.example.hotel.repositoryMo.ReservationMoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository repository;
    @Autowired 
    private ReservationMoRepository reservationMoRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerMoRepository customerMoRepository;
    @Autowired
    private HotelMoRepository hotelMoRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

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
     * DELETE a reservation by its id SQL
     * @param id
     * @return
     */
    public String deleteReservation(int id){
        changeAvailability(getReservationById(id).getRoom());
        repository.deleteById(id);
        return "Reservation with id: " +id + " successfully deleted!";
    }

    /**
     * DELETE a reservation by its id Mongo
     * @param id
     * @return
     */
    public String deleteReservationMo(String id){
        changeAvailabilityMo(getReservationMoById(id).getRoom());
        reservationMoRepository.delete(getReservationMoById(id));
        return "Reservation with id: " +id + " successfully deleted!";
    }
    public Reservation getReservationById(int id) {
        return repository.findById(id).orElse(null);
    }

    public ReservationMo getReservationMoById(String id){
        return reservationMoRepository.findByReservationId(id);
    }

    /**
     * Get list of all reservations SQL
     * @return
     */
    public List<Reservation> getReservations() {
        return repository.findAll() ;
    }
    /**
     * Get list of all reservations Mongo
     * @return
     */
    public List<ReservationMo> getReservationsMo(){
        return reservationMoRepository.findAll();
    }

    /**
     * Get all reservations by Customer ID from MongoDb
     * @param customerId
     * @return
     */
    public List<ReservationMo> getReservationsByCustomerID(String customerId){
        List<ReservationMo> reservationsByCustomer= new ArrayList<>();
        CustomerMo customer = customerMoRepository.findByCustomerId(customerId);
        List<ReservationMo> reservations= getReservationsMo();
        for (int i=0; i<reservations.size();i++) {
            if(reservations.get(i).getCustomer().getCustomerId().equals(customerId)){
                reservationsByCustomer.add(reservations.get(i));
            }
        }
        return reservationsByCustomer;
    }



    /**
     * Update reservation in MySQL
     * @param reservation
     * @return
     */
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
     * Update reservation in MongoDb
     * @param reservation
     * @return
     */
    public ReservationMo updateReservationMo(ReservationMo reservation){
        ReservationMo existingRes= reservationMoRepository.findByReservationId(reservation.getReservationId());
        existingRes.setPrice(reservation.getPrice());
        existingRes.setDate(reservation.getDate());
        existingRes.setDuration(reservation.getDuration());
        changeAvailabilityMo(existingRes.getRoom());
        changeAvailabilityMo(reservation.getRoom());
        existingRes.setRoom(reservation.getRoom());
        existingRes.getRoom().setAvailable(!existingRes.getRoom().isAvailable());
        existingRes.setCustomer(reservation.getCustomer());
        return  reservationMoRepository.save(existingRes);
    }

    /**
     * Change availability of the rooms SQL
     * @param room
     * @return
     */
    public Room changeAvailability(Room room){
        Room existingRoom = roomRepository.findById(room.getRoomID()).orElse(null);
        existingRoom.setAvailable(!room.isAvailable());
        existingRoom.setType(room.getType());
        return roomRepository.save(existingRoom);
    }
    /**
     * Change availability of the rooms Mongo
     * @param room
     * @return
     */
    public HotelMo changeAvailabilityMo(RoomMo room){
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
     * Report about all single rooms that are booked for more than 2 days from MongoDb
     * @return
     */
    public List<ReservationMo> reservationSingleRoomMoreThan2Days() {
        Query query = new Query()
                .addCriteria(Criteria.where("room.type").is("SINGLE_ROOM")
                        .and("duration").gt(2));
        query.fields().include("room.roomId");
        query.fields().include("id");
        query.fields().include("customer.idCard");
        query.fields().include("duration");
        query.fields().include("date");
        query.with(Sort.by(Sort.Order.desc("duration")));

        return mongoTemplate.find(query, ReservationMo.class);

    }

    /**
     * Report about all current reservations from MongoDb
     * @return
     */
    public List<ReservationMo> allReservations () {
        Query query = new Query();
        query.fields().include("room.roomId");
        query.fields().include("id");
        query.fields().include("customer.idCard");
        query.fields().include("customer.firstName");
        query.fields().include("customer.lastName");
        query.fields().include("duration");
        query.fields().include("room.type");
        query.fields().include("date");
        query.with(Sort.by(Sort.Order.asc("room.roomId")));

        return mongoTemplate.find(query, ReservationMo.class);
    }
}
