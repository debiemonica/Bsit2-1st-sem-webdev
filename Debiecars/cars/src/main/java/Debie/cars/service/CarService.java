package Debie.cars.service;

import Debie.cars.model.Car;
import Debie.cars.repository.CarRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    // ✅ Correct: get by primary key (Long carId)
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    // ✅ delete by primary key (Long carId)
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }


    // ✅ check by license plate (business key)
    public boolean existsByLicensePlate(String licensePlateNumber) {
        return carRepository.existsByLicensePlateNumber(licensePlateNumber);
    }

    public String exportCarsToCSV() throws IOException {
        List<Car> cars = getAllCars();
        StringWriter stringWriter = new StringWriter();

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader("ID", "License Plate", "Make", "Model", "Year",
                        "Color", "Body Type", "Engine Type", "Transmission")
                .build();

        try (CSVPrinter csvPrinter = new CSVPrinter(stringWriter, csvFormat)) {
            for (Car car : cars) {
                csvPrinter.printRecord(
                        car.getCarId(),
                        car.getLicensePlateNumber(),
                        car.getMake(),
                        car.getModel(),
                        car.getYear(),
                        car.getColor(),
                        car.getBodyType().getDisplayName(),
                        car.getEngineType().getDisplayName(),
                        car.getTransmission().getDisplayName()
                );
            }
        }

        return stringWriter.toString();
    }
}
