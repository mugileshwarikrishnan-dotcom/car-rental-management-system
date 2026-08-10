package com.carrental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrental.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    // Search car by brand
    List<Car> findByBrandContainingIgnoreCase(String brand);

}