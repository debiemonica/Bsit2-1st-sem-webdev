package Debie.cars.controller;

import Debie.cars.model.BodyType;
import Debie.cars.model.Car;
import Debie.cars.model.EngineType;
import Debie.cars.model.Transmission;
import Debie.cars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/")
    public String home() {
        return "redirect:/cars";
    }

    @GetMapping("/cars")
    public String listCars(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        return "cars/list";
    }

    @GetMapping("/cars/new")
    public String showAddCarForm(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("bodyTypes", BodyType.values());
        model.addAttribute("engineTypes", EngineType.values());
        model.addAttribute("transmissions", Transmission.values());
        return "cars/form";
    }

    @PostMapping("/cars")
    public String saveCar(@ModelAttribute Car car, RedirectAttributes redirectAttributes) {
        try {
            if (carService.existsByLicensePlate(car.getLicensePlateNumber())) {
                redirectAttributes.addFlashAttribute("error",
                        "A car with license plate " + car.getLicensePlateNumber() + " already exists!");
                return "redirect:/cars/new";
            }

            carService.saveCar(car);
            redirectAttributes.addFlashAttribute("success",
                    "Car added successfully!");
            return "redirect:/cars";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "Error adding car: " + e.getMessage());
            return "redirect:/cars/new";
        }
    }

    @GetMapping("/cars/{id}/delete")
    public String deleteCar(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            carService.deleteCar(id);
            redirectAttributes.addFlashAttribute("success", "Car deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting car: " + e.getMessage());
        }
        return "redirect:/cars";
    }



    @GetMapping("/cars/export")
    public ResponseEntity<String> exportCarsToCSV() {
        try {
            String csvData = carService.exportCarsToCSV();
            String filename = "cars_export_" +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".csv";

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .contentType(MediaType.parseMediaType("text/csv"))
                    .body(csvData);
        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body("Error generating CSV: " + e.getMessage());
        }
    }
}