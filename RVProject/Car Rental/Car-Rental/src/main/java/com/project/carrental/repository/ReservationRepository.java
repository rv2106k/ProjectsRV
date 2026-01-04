package com.project.carrental.repository;

import com.project.carrental.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByCustomerId(Long customerId);
    List<Reservation> findByCarId(Long carId);

    @Query("SELECT r FROM Reservation r WHERE r.car.id = :carId AND r.status = 'CONFIRMED' AND r.endDate >= :start AND r.startDate <= :end")
    List<Reservation> findOverlappingReservations(@Param("carId") Long carId, @Param("start") LocalDate start, @Param("end") LocalDate end);

    boolean existsByCarIdAndStatusAndEndDateGreaterThanEqualAndStartDateLessThanEqual(Long carId, String status, LocalDate start, LocalDate end);
}

