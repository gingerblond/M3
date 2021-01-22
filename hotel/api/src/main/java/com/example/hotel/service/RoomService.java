package com.example.hotel.service;

import com.example.hotel.entity.Room;
import com.example.hotel.model.HotelMo;
import com.example.hotel.model.ReservationMo;
import com.example.hotel.model.RoomMo;
import com.example.hotel.repository.RoomRepository;
import com.example.hotel.repositoryMo.HotelMoRepository;
import com.example.hotel.repositoryMo.ReservationMoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository repository;
    @Autowired
    private HotelMoRepository hotelMoRepository;
    @Autowired
    ReservationMoRepository reservationMoRepository;

    public Room addRoom(Room room) {
        return repository.save(room);
    }

    public List<Room> addRooms(List<Room> rooms){

        return repository.saveAll(rooms);
    }

    public List<Room> getRooms() {
        return  repository.findAll();
    }

    /**
     * Get available Rooms from MySQl
     * @return
     */
    public List<Room> getAvailableRooms(){
        List<Room>allRooms = getRooms();
        List<Room>toReturn = new ArrayList<>();

        for(Room r:allRooms) {
            if(r.isAvailable())
                toReturn.add(r);
        }
        return toReturn;
    }

    /**
     * Get available Rooms from MongoDB OR Get all unavailable rooms, which are also not in any reservation
     * @return
     */
    public List<RoomMo> getAvailableRoomsMo(){
        List<RoomMo>allRooms = hotelMoRepository.findAll().get(0).getRoomsMo();
        List<RoomMo>toReturn = new ArrayList<>();

        for(RoomMo r:allRooms) {
            if(r.isAvailable() || (!r.isAvailable()&& !reservedRoom(r)))
                toReturn.add(r);
        }
        return toReturn;
    }

    /**
     * Chck if unavailable room is also reserved
     * @param roomMo
     * @return true if this room is reserved, false if the room is under construction
     */
    private boolean reservedRoom(RoomMo roomMo){
        List<ReservationMo> reservations = reservationMoRepository.findAll();
        boolean res=false;
        for (ReservationMo reservation: reservations) {
            if(reservation.getRoom().equals(roomMo)){
                res= true;
            }
        }
        return res;
    }




    public List<Room> getRoomsByType (String type) {
        List<Room>allRooms = getRooms();
        List<Room>toReturn = new ArrayList<>();

        /*if(!type.equals("SINGLE_ROOM")|| !type.equals("DOUBLE_ROOM"))
            throw new IllegalArgumentException("Illegal Room type");*/

        for(Room r:allRooms) {
            if(r.getType().equals(type) && r.isAvailable())
                toReturn.add(r);
        }
        return toReturn;
    }

    /**
     * Update an available room in MySQL
     * @param room
     * @return
     */
    public Room updateRoom(Room room){
        Room existingRoom = repository.findById(room.getRoomID()).orElse(null);
        existingRoom.setAvailable(room.isAvailable());
        existingRoom.setType(room.getType());
        return repository.save(existingRoom);
    }

    /**
     * Update an available room in MongoDB
     * @param room
     * @return
     */
    public RoomMo updateRoomMo(RoomMo room){
        List<RoomMo> rooms = hotelMoRepository.findAll().get(0).getRoomsMo();
        RoomMo updatedRoom = new RoomMo();
        for(RoomMo r: rooms){
            if(r.getRoomId()==room.getRoomId()){
                r.setAvailable(room.isAvailable());
                r.setType(room.getType());
                updatedRoom=r;
            }
        }
        HotelMo hotel = hotelMoRepository.findAll().get(0);
        HotelMo hotelMo= new HotelMo(hotel.getHotelId(),hotel.getAddress(),rooms);
        hotelMoRepository.save(hotelMo);
        return updatedRoom;
    }

    /**
     * Delete room from MySql
     * @param id
     * @return
     */
    public String deleteRoom(int id) {
        repository.deleteById(id);
        return "Room with ID :" + id + " successfully removed!";
    }

    /**
     * Delete room from MongoDb
     * @param id
     * @return
     */
    public String deleteRoomMo(int id) {
        List<RoomMo> rooms = hotelMoRepository.findAll().get(0).getRoomsMo();
        RoomMo room = rooms.get(id);
        rooms.remove(room);
        HotelMo hotel = hotelMoRepository.findAll().get(0);
        HotelMo hotelMo= new HotelMo(hotel.getHotelId(),hotel.getAddress(),rooms);
        hotelMoRepository.save(hotelMo);
        return "Room with ID :" + id + " successfully removed!";
    }


}
