package Debie.cars.repository;

import Debie.cars.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
    boolean existsByLicensePlateNumber(String licensePlateNumber);
}

