package com.project.carrental.config;

import com.project.carrental.model.Car;
import com.project.carrental.model.Customer;
import com.project.carrental.model.Reservation;
import com.project.carrental.model.enums.CarStatus;
import com.project.carrental.model.enums.ReservationStatus;
import com.project.carrental.repository.CarRepository;
import com.project.carrental.repository.CustomerRepository;
import com.project.carrental.repository.ReservationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(CarRepository carRepository, CustomerRepository customerRepository, ReservationRepository reservationRepository) {
        return args -> {
            if (carRepository.count() == 0) {
                Car c1 = new Car();
                c1.setMake("Toyota");
                c1.setModel("Corolla");
                c1.setPlateNumber("ABC-123");
                c1.setYear(2020);
                c1.setDailyRate(BigDecimal.valueOf(40));
                c1.setStatus(CarStatus.AVAILABLE);
                carRepository.save(c1);

                Car c2 = new Car();
                c2.setMake("Honda");
                c2.setModel("Civic");
                c2.setPlateNumber("XYZ-789");
                c2.setYear(2019);
                c2.setDailyRate(BigDecimal.valueOf(45));
                c2.setStatus(CarStatus.AVAILABLE);
                carRepository.save(c2);
            }

            if (customerRepository.count() == 0) {
                Customer cu1 = new Customer();
                cu1.setFirstName("John");
                cu1.setLastName("Doe");
                cu1.setEmail("john@example.com");
                cu1.setPhone("+1-555-0100");
                customerRepository.save(cu1);

                Customer cu2 = new Customer();
                cu2.setFirstName("Jane");
                cu2.setLastName("Smith");
                cu2.setEmail("jane@example.com");
                cu2.setPhone("+1-555-0101");
                customerRepository.save(cu2);
            }

            if (reservationRepository.count() == 0) {
                Car car = carRepository.findAll().get(0);
                Customer customer = customerRepository.findAll().get(0);
                Reservation r = new Reservation();
                r.setCar(car);
                r.setCustomer(customer);
                r.setStartDate(LocalDate.now().plusDays(1));
                r.setEndDate(LocalDate.now().plusDays(3));
                r.setTotalPrice(car.getDailyRate().multiply(java.math.BigDecimal.valueOf(2)));
                r.setStatus(ReservationStatus.CONFIRMED);
                reservationRepository.save(r);
            }
        };
    }
}

