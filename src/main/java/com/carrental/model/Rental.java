package com.carrental.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Rental {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private int customerId;

    private int carId;

    private int days;

    private double totalAmount;

    private String status;

    private String rentDate;

    private String returnDate;



    public Rental(){

    }



    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }



    public int getCustomerId() {
        return customerId;
    }


    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }



    public int getCarId() {
        return carId;
    }


    public void setCarId(int carId) {
        this.carId = carId;
    }



    public int getDays() {
        return days;
    }


    public void setDays(int days) {
        this.days = days;
    }



    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}

public String getRentDate() {
    return rentDate;
}

public void setRentDate(String rentDate) {
    this.rentDate = rentDate;
}

public String getReturnDate() {
    return returnDate;
}

public void setReturnDate(String returnDate) {
    this.returnDate = returnDate;
}
}