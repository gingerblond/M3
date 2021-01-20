package com.example.hotel.service;

import com.example.hotel.entity.Room;
import com.example.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository repository;

    public Room addRoom(Room room) {
        return repository.save(room);
    }

    public List<Room> addRooms(List<Room> rooms){

        return repository.saveAll(rooms);
    }

    public List<Room> getRooms() {
        return  repository.findAll();
    }

    public List<Room> getAvailableRooms(){
        List<Room>allRooms = getRooms();
        List<Room>toReturn = new ArrayList<>();

        for(Room r:allRooms) {
            if(r.isAvailable())
                toReturn.add(r);
        }
        return toReturn;
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

    public Room updateRoom(Room room){
        Room existingRoom = repository.findById(room.getRoomID()).orElse(null);
        existingRoom.setAvailable(room.isAvailable());
        existingRoom.setType(room.getType());
        return repository.save(existingRoom);
    }

    public String deleteRoom(int id) {
        repository.deleteById(id);
        return "Room with ID :" + id + " successfully removed!";
    }


}
