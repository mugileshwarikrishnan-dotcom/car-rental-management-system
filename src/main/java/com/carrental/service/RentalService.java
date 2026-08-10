package com.carrental.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrental.model.Rental;
import com.carrental.repository.RentalRepository;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    // Save Rental
    public void saveRental(Rental rental) {

        rentalRepository.save(rental);

    }

    // Get All Rentals
    public List<Rental> getAllRentals() {

        return rentalRepository.findAll();

    }

    // Get Rental By ID
    public Rental getRentalById(int id) {

        return rentalRepository.findById(id).orElse(null);

    }

    // Update Rental
    public void updateRental(Rental rental) {

        rentalRepository.save(rental);

    }

    // Delete Rental
    public void deleteRental(int id) {

        rentalRepository.deleteById(id);
    }

}