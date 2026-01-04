package com.project.carrental.service.impl;

import com.project.carrental.exception.BusinessException;
import com.project.carrental.model.Car;
import com.project.carrental.model.Reservation;
import com.project.carrental.model.enums.ReservationStatus;
import com.project.carrental.repository.CarRepository;
import com.project.carrental.repository.CustomerRepository;
import com.project.carrental.repository.ReservationRepository;
import com.project.carrental.service.ReservationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, CarRepository carRepository, CustomerRepository customerRepository) {
        this.reservationRepository = reservationRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Reservation> listAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findById(Long id) {
        Optional<Reservation> opt = reservationRepository.findById(id);
        return opt.orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    @Override
    @Transactional
    public Reservation createReservation(Reservation reservation) {
        if (reservation.getStartDate().isAfter(reservation.getEndDate()) || reservation.getStartDate().isEqual(reservation.getEndDate())) {
            throw new BusinessException("End date must be after start date");
        }

        Car car = carRepository.findById(reservation.getCar().getId()).orElseThrow(() -> new BusinessException("Car not found"));
        // check overlap
        boolean existsOverlap = reservationRepository.existsByCarIdAndStatusAndEndDateGreaterThanEqualAndStartDateLessThanEqual(
                car.getId(), ReservationStatus.CONFIRMED.name(), reservation.getStartDate(), reservation.getEndDate());
        if (existsOverlap) {
            throw new BusinessException("Car is not available for the selected dates");
        }

        long days = ChronoUnit.DAYS.between(reservation.getStartDate(), reservation.getEndDate());
        if (days <= 0) days = 1;
        BigDecimal total = car.getDailyRate().multiply(BigDecimal.valueOf(days));
        reservation.setTotalPrice(total);
        reservation.setStatus(ReservationStatus.CONFIRMED);
        return reservationRepository.save(reservation);
    }

    @Override
    @Transactional
    public Reservation cancelReservation(Long id) {
        Reservation r = findById(id);
        r.setStatus(ReservationStatus.CANCELLED);
        return reservationRepository.save(r);
    }

    @Override
    public List<Reservation> findByCustomer(Long customerId) {
        return reservationRepository.findByCustomerId(customerId);
    }
}

