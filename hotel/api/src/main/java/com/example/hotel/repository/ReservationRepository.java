package com.example.hotel.repository;

import com.example.hotel.dto.ReservationReport;
import com.example.hotel.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    /**
     * All booked rooms with reservation, customer and room details
     * @return
     */

    @Query("SELECT new com.example.hotel.dto.ReservationReport(t.firstName as NAME, t.lastName, t.idCard, c.reservationID, c.date, c.duration , p.roomID, p.type) FROM Reservation c JOIN c.room p " +
            "JOIN c.customer t")
    public List<ReservationReport> getReportReservation();

    /**
     * All booked SINGLE rooms more than 2 days
     * @return
     */
    @Query("SELECT new com.example.hotel.dto.ReservationReport(t.firstName as NAME, t.lastName, t.idCard, c.reservationID, c.date, c.duration , p.roomID, p.type) FROM Reservation c JOIN c.room p " +
            "JOIN c.customer t where c.duration  > 2 AND p.type = 'SINGLE_ROOM'")
    public List<ReservationReport>  getRoomsBookedMoreThan2Days();

}
