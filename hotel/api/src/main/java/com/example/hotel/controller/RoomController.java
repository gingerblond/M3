package com.example.hotel.controller;

import com.example.hotel.entity.Room;
import com.example.hotel.model.RoomMo;
import com.example.hotel.repositoryMo.HotelMoRepository;
import com.example.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@RestController
public class RoomController {

    @Autowired
    private RoomService service;
    @Autowired
    private HotelMoRepository hotelMoRepository;

    /**
     * Add new room
     * @param room
     * @return
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("addRoom")
    public Room addRoom(@RequestBody Room room){
        return service.addRoom(room);
    }

    /**
     * Add list of new rooms
     * @param rooms
     * @return
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("addRooms")
    public List<Room> addRooms(@RequestBody List<Room> rooms){
        return service.addRooms(rooms);
    }

    /**
     * Get list of all rooms
     * @return
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("getRooms")
    public List<Room> getRooms(){
        return service.getRooms();
    }

    /**
     * Get List of all available rooms from MongoDb
     * @return
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("getAvailableRooms")
    public List<RoomMo> getAvailableRooms(){
        return service.getAvailableRoomsMo();
    }
    /**
     * Get rooms by type
     * @param type
     * @return
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("getRooms/{type}")
   /* public List<Room> getRoomsByType(@PathVariable String type){
        return service.getRoomsByType(type);
    }*/
    public List<RoomMo> getRoomsByType(@PathVariable String type){
        List<RoomMo> allRooms = hotelMoRepository.findAll().get(0).getRoomsMo();
        List<RoomMo>toReturn = new ArrayList<>();

        for(RoomMo r:allRooms) {
            if(r.getType().equals(type) && r.isAvailable())
                toReturn.add(r);
        }
        return toReturn;
    }

    /**
     * Get rooms count by Type
     * @return available rooms per Type
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("getRoomsCount/{type}")
    public int getRooms(@PathVariable String type) {
        List<RoomMo> rooms = hotelMoRepository.findAll().get(0).getRoomsMo();
        //List<Room> rooms = service.getRooms();
        int occurancies = 0;
        for (RoomMo room:rooms){
            if(room.getType().equals(type) && room.isAvailable()){
                occurancies++;
            }
        }
        return occurancies;
    }

    /**
     * Update room
     * @param room
     * @return
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("updateRoom")
    public RoomMo updateRoom(@RequestBody RoomMo room){
        return  service.updateRoomMo(room);
    }
    /**
     * Delete Room by room ID
     * @param id
     * @return
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("deleteRoom/{id}")
    public String deleteRoom(@PathVariable int id) {
        return service.deleteRoomMo(id);
    }
}
