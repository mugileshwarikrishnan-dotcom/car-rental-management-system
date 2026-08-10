package com.carrental.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.carrental.model.Car;
import com.carrental.model.Customer;

import com.carrental.service.CarService;
import com.carrental.service.CustomerService;

import com.carrental.model.Rental;
import com.carrental.service.RentalService;



@Controller
public class HomeController {


    @Autowired
    private CarService carService;


    @Autowired
    private CustomerService customerService;

    @Autowired
    private RentalService rentalService;




    // Login Page
    @GetMapping("/")
    public String home() {

        return "login";

    }



    // Login Check
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password) {


        if(username.equals("admin") && password.equals("admin123")) {

            return "dashboard";

        }
        else {

            return "login";

        }

    }



    // Dashboard
    @GetMapping("/dashboard")
    public String dashboard() {

        return "dashboard";

    }



    // Add Car Page
    @GetMapping("/addcar")
    public String addCarPage() {

        return "addcar";

    }



    // Save Car
    @PostMapping("/savecar")
    public String saveCar(@RequestParam String brand,
                          @RequestParam String model,
                          @RequestParam double pricePerDay) {


        Car car = new Car();


        car.setBrand(brand);
        car.setModel(model);
        car.setPricePerDay(pricePerDay);


        carService.saveCar(car);


        return "redirect:/dashboard";

    }




    // View Cars
    @GetMapping("/viewcars")
    public String viewCars(Model model) {
    model.addAttribute("cars", carService.getAllCars());
    return "viewcars";
}

    
    // Delete Car Page
    @GetMapping("/deletecar")
    public String deleteCarPage() {

    return "deletecar";

}

// Delete Car
    @PostMapping("/deletecar")
public String deleteCar(@RequestParam int carId, Model model) {

    carService.deleteCar(carId);

    model.addAttribute("message", "Car Deleted Successfully!");

    model.addAttribute("cars", carService.getAllCars());

    return "viewcars";

}
    // Search Car Page
@GetMapping("/searchcar")
public String searchCarPage() {

    return "searchcar";

}
    // Search Car
    @PostMapping("/searchcar")
public String searchCar(@RequestParam String brand, Model model) {

    model.addAttribute("cars", carService.searchCars(brand));

    return "viewcars";

}
    // Update Car Page
@GetMapping("/updatecar")
public String updateCarPage() {

    return "updatecar";

}

// Update Car
@PostMapping("/updatecar")
public String updateCar(@RequestParam int id,
                        @RequestParam String brand,
                        @RequestParam String model,
                        @RequestParam double pricePerDay,
                        Model modelObj) {

    Car car = carService.getCarById(id);

    if (car != null) {

        car.setBrand(brand);
        car.setModel(model);
        car.setPricePerDay(pricePerDay);

        carService.updateCar(car);

        modelObj.addAttribute("message", "Car Updated Successfully!");
    }

    modelObj.addAttribute("cars", carService.getAllCars());

    return "viewcars";
}

    // Add Customer Page
    @GetMapping("/addcustomer")
    public String addCustomerPage() {


        return "addcustomer";

    }




    // Save Customer
    @PostMapping("/savecustomer")
    public String saveCustomer(@RequestParam String name,
                               @RequestParam String phone,
                               @RequestParam String email,
                               @RequestParam String license) {



        Customer customer = new Customer();


        customer.setName(name);
        customer.setPhone(phone);
        customer.setEmail(email);
        customer.setLicense(license);



        customerService.saveCustomer(customer);



        return "redirect:/dashboard";

    }

@GetMapping("/viewcustomers")
public String viewCustomers(Model model) {

    model.addAttribute("customers",
            customerService.getAllCustomers());

    return "viewcustomers";

}

// Rent Car Page
@GetMapping("/rentcar")
public String rentCarPage() {

    return "rentcar";

}


// Save Rental
@PostMapping("/saverental")
public String saveRental(@RequestParam int customerId,
                         @RequestParam int carId,
                         @RequestParam int days) {


    Rental rental = new Rental();


    rental.setCustomerId(customerId);

    rental.setCarId(carId);

    rental.setDays(days);


    double amount = days * 2500;

    rental.setTotalAmount(amount);
    rental.setStatus("RENTED");
    rental.setRentDate(java.time.LocalDate.now().toString());

    rentalService.saveRental(rental);

    // Update car status
    carService.updateCarStatus(carId, "RENTED");

    return "redirect:/dashboard";

}
@GetMapping("/viewrentals")
public String viewRentals(Model model) {


    model.addAttribute("rentals",
            rentalService.getAllRentals());


    return "viewrentals";

}
@GetMapping("/returncar")
public String returnCarPage() {

    return "returncar";

}
@PostMapping("/returncar")
public String returnCar(@RequestParam int rentalId) {

    Rental rental = rentalService.getRentalById(rentalId);

    if (rental != null) {

        // Update Rental Status
        rental.setStatus("RETURNED");

        // Update Return Date
        rental.setReturnDate(java.time.LocalDate.now().toString());

        // Save Updated Rental
        rentalService.updateRental(rental);

        // Update Car Status
        carService.updateCarStatus(rental.getCarId(), "AVAILABLE");
    }

    return "redirect:/dashboard";

}
@GetMapping("/receipt")
public String receipt(@RequestParam int rentalId, Model model) {

    Rental rental = rentalService.getRentalById(rentalId);

    model.addAttribute("rental", rental);

    return "receipt";
}
}

