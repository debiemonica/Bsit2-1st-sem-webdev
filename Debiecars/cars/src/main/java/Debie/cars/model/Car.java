package Debie.cars.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    @Column(nullable = false, unique = true)
    private String licensePlateNumber;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    // ✅ Avoid reserved keyword "year"
    @Column(name = "manufacture_year", nullable = false)
    private Integer year;

    @Column(nullable = false)
    private String color;

    @Enumerated(EnumType.STRING) // ✅ Stored as VARCHAR
    @Column(nullable = false)
    private BodyType bodyType;

    @Enumerated(EnumType.STRING) // ✅ Stored as VARCHAR
    @Column(nullable = false)
    private EngineType engineType;

    @Enumerated(EnumType.STRING) // ✅ Stored as VARCHAR
    @Column(nullable = false)
    private Transmission transmission;

    // Default constructor
    public Car() {}

    // Constructor with parameters
    public Car(String licensePlateNumber, String make, String model, Integer year,
               String color, BodyType bodyType, EngineType engineType, Transmission transmission) {
        this.licensePlateNumber = licensePlateNumber;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.bodyType = bodyType;
        this.engineType = engineType;
        this.transmission = transmission;
    }

    // Getters and Setters
    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", licensePlateNumber='" + licensePlateNumber + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", color='" + color + '\'' +
                ", bodyType=" + bodyType +
                ", engineType=" + engineType +
                ", transmission=" + transmission +
                '}';
    }
}
