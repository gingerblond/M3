package com.example.hotel.repository;

import com.example.hotel.dto.CleaningServiceEmployeeReport;

import com.example.hotel.entity.CleaningServiceEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CleaningServiceEmployeeRepository extends JpaRepository<CleaningServiceEmployee, Integer> {

    @Query("SELECT new com.example.hotel.dto.CleaningServiceEmployeeReport(c.employeeId, c.firstName , c.lastName, c.workingHours, p.hotelId, p.address, c.responsibility, c.socialId) FROM CleaningServiceEmployee c JOIN c.hotel p " +
            "WHERE c.workingHours = 20")
    public List<CleaningServiceEmployeeReport> getReportCleaningServiceEmployee();
}
