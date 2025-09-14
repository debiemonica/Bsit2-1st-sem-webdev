package com.debie.controllers;

import com.debie.model.Car;
import com.debie.repository.CarRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    private final CarRepository carRepository;

    public DashboardController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal UserDetails user, Model model) {
        List<Car> cars = carRepository.findAll();
        int size = cars.size();
        List<Car> recentCars = cars.subList(Math.max(0, size - 5), size);

        model.addAttribute("username", user.getUsername());
        model.addAttribute("totalCars", cars.size());
        model.addAttribute("recentCars", recentCars);

        return "dashboard";
    }
}
