package com.example.hotel.controller;

import com.example.hotel.entity.Reservation;
import com.example.hotel.service.DBFeedingService;
import com.example.hotel.service.MigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DBFeedingController {
    @Autowired
    DBFeedingService service;
    @Autowired
    MigrationService migrationService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("dbFeeding")
    public String dbFeeding() throws Exception {
        try {
            service.startFeedingDB();
            return "DB feed successful!";
        } catch (Exception e) {
            return "Feed DB failed";
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/migration")
    public String migrationToMongo() throws Exception {
        try {
            migrationService.startMigration();
            return "Migration successful!";
        } catch (Exception e) {
            return "Migration failed";
        }
    }
}
