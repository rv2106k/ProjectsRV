package com.project.carrental.web;

import com.project.carrental.model.Car;
import com.project.carrental.model.Customer;
import com.project.carrental.model.Reservation;
import com.project.carrental.service.CarService;
import com.project.carrental.service.CustomerService;
import com.project.carrental.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final CarService carService;
    private final CustomerService customerService;

    public ReservationController(ReservationService reservationService, CarService carService, CustomerService customerService) {
        this.reservationService = reservationService;
        this.carService = carService;
        this.customerService = customerService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("reservations", reservationService.listAll());
        return "reservations/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("cars", carService.listAll());
        model.addAttribute("customers", customerService.listAll());
        return "reservations/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("reservation") Reservation reservation, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("cars", carService.listAll());
            model.addAttribute("customers", customerService.listAll());
            return "reservations/form";
        }
        reservationService.createReservation(reservation);
        return "redirect:/reservations";
    }
}

