package com.project.carrental.service.impl;

import com.project.carrental.model.Car;
import com.project.carrental.model.enums.CarStatus;
import com.project.carrental.repository.CarRepository;
import com.project.carrental.service.CarService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> listAll() {
        return carRepository.findAll();
    }

    @Override
    public Car findById(Long id) {
        Optional<Car> opt = carRepository.findById(id);
        return opt.orElseThrow(() -> new RuntimeException("Car not found"));
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car update(Long id, Car car) {
        Car existing = findById(id);
        existing.setMake(car.getMake());
        existing.setModel(car.getModel());
        existing.setPlateNumber(car.getPlateNumber());
        existing.setYear(car.getYear());
        existing.setDailyRate(car.getDailyRate());
        existing.setStatus(car.getStatus());
        return carRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public List<Car> findAvailable(LocalDate start, LocalDate end) {
        return carRepository.findAvailableCars(start, end, CarStatus.AVAILABLE);
    }
}

