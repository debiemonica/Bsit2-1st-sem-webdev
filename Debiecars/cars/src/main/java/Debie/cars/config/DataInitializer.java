package Debie.cars.config;

import Debie.cars.model.*;
import Debie.cars.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CarRepository carRepository;

    @Override
    public void run(String... args) throws Exception {
        // Add some sample data if the database is empty
        if (carRepository.count() == 0) {
            carRepository.save(new Car("ABC-1234", "Toyota", "Corolla", 2021, "Silver",
                    BodyType.SEDAN, EngineType.GASOLINE, Transmission.AUTOMATIC));

            carRepository.save(new Car("XYZ-5678", "Ford", "Mustang", 2022, "Red",
                    BodyType.COUPE, EngineType.GASOLINE, Transmission.MANUAL));

            carRepository.save(new Car("DEF-9012", "Tesla", "Model 3", 2023, "White",
                    BodyType.SEDAN, EngineType.ELECTRIC, Transmission.AUTOMATIC));

            carRepository.save(new Car("GHI-3456", "Honda", "CR-V", 2020, "Blue",
                    BodyType.SUV, EngineType.GASOLINE, Transmission.AUTOMATIC));

            carRepository.save(new Car("JKL-7890", "Toyota", "Prius", 2021, "Green",
                    BodyType.HATCHBACK, EngineType.HYBRID, Transmission.CVT));
        }
    }
}