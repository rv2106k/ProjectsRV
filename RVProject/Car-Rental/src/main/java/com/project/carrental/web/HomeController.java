package com.project.carrental.web;

import com.project.carrental.service.CarService;
import com.project.carrental.service.CustomerService;
import com.project.carrental.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CarService carService;
    private final CustomerService customerService;
    private final ReservationService reservationService;

    public HomeController(CarService carService, CustomerService customerService, ReservationService reservationService) {
        this.carService = carService;
        this.customerService = customerService;
        this.reservationService = reservationService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("carCount", carService.listAll().size());
        model.addAttribute("customerCount", customerService.listAll().size());
        model.addAttribute("reservationCount", reservationService.listAll().size());
        return "index";
    }
}

