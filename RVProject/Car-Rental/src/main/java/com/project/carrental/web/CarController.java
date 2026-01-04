package com.project.carrental.web;

import com.project.carrental.model.Car;
import com.project.carrental.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("cars", carService.listAll());
        return "cars/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("car", new Car());
        return "cars/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("car") Car car, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "cars/form";
        }
        carService.save(car);
        return "redirect:/cars";
    }
}

