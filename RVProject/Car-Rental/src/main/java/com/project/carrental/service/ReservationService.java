package com.project.carrental.service;

import com.project.carrental.model.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> listAll();
    Reservation findById(Long id);
    Reservation createReservation(Reservation reservation);
    Reservation cancelReservation(Long id);
    List<Reservation> findByCustomer(Long customerId);
}

