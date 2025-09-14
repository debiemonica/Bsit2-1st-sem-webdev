package com.debie.controllers;

import com.debie.exception.ResourceNotFoundException;
import com.debie.model.Car;
import com.debie.repository.CarRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    private final CarRepository carRepository;

    public HomeController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    // List all cars
    @GetMapping("/")
    public String index(Model model) {
        List<Car> cars = carRepository.findAll();
        model.addAttribute("cars", cars);
        return "index";
    }

    // Create new car form
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("car", new Car());
        return "create";
    }

    // Save new car
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("car") Car car, BindingResult result) {
        if (result.hasErrors()) {
            return "create";
        }
        carRepository.save(car);
        return "redirect:/";
    }

    // Show car details
    @GetMapping("/show/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id: " + id));
        model.addAttribute("car", car);
        return "show";
    }

    // Edit car form
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id: " + id));
        model.addAttribute("car", car);
        return "edit";
    }

    // Update car
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") int id,
                         @Valid @ModelAttribute("car") Car car,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "edit";
        }
        car.setId(id); // preserve ID
        carRepository.save(car);
        return "redirect:/";
    }

    // Delete car
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id: " + id));
        carRepository.delete(car);
        return "redirect:/";
    }
}
