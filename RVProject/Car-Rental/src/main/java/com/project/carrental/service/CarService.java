package com.project.carrental.service;

import com.project.carrental.model.Car;

import java.time.LocalDate;
import java.util.List;

public interface CarService {
    List<Car> listAll();
    Car findById(Long id);
    Car save(Car car);
    Car update(Long id, Car car);
    void delete(Long id);
    List<Car> findAvailable(LocalDate start, LocalDate end);
}

