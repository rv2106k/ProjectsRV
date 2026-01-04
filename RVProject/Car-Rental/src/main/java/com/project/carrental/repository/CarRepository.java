package com.project.carrental.repository;

import com.project.carrental.model.Car;
import com.project.carrental.model.enums.CarStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findByPlateNumber(String plateNumber);
    List<Car> findByStatus(CarStatus status);

    @Query("SELECT c FROM Car c WHERE c.status = :status AND c.id NOT IN (SELECT r.car.id FROM Reservation r WHERE r.status = 'CONFIRMED' AND r.endDate >= :start AND r.startDate <= :end)")
    List<Car> findAvailableCars(@Param("start") LocalDate start, @Param("end") LocalDate end, @Param("status") CarStatus status);
}

