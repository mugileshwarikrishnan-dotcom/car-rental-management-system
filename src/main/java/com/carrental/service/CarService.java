package com.carrental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrental.model.Car;
import com.carrental.repository.CarRepository;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    // Save Car
    public void saveCar(Car car) {

        // New car default status
        if (car.getStatus() == null || car.getStatus().isEmpty()) {
            car.setStatus("AVAILABLE");
        }

        carRepository.save(car);
    }

    // View All Cars
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    // Find Car
    public Car getCarById(int id) {
        Optional<Car> car = carRepository.findById(id);
        return car.orElse(null);
    }

    // Update Status
    public void updateCarStatus(int id, String status) {

        Optional<Car> optional = carRepository.findById(id);

        if (optional.isPresent()) {

            Car car = optional.get();

            car.setStatus(status);

            carRepository.save(car);
        }
    }
    // Search Cars by Brand
    public List<Car> searchCars(String brand) {

    return carRepository.findByBrandContainingIgnoreCase(brand);

}
    // Delete Car
public void deleteCar(int id) {

    carRepository.deleteById(id);

}
    // Update Car
public void updateCar(Car car) {

    carRepository.save(car);

}
}