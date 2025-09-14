package com.debie.service;


import com.debie.model.Car;
import com.debie.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    // Fetch all cars
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    // Fetch a car by id
    public Car getCarById(Integer id) {
        return carRepository.findById(id).orElse(null);
    }

    // Save or update a car
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    // Delete a car
    public void deleteCar(Integer id) {
        carRepository.deleteById(id);
    }
}

