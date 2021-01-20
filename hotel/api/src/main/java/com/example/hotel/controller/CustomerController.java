package com.example.hotel.controller;

import com.example.hotel.entity.Customer;
import com.example.hotel.repository.CustomerRepository;
import com.example.hotel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private CustomerService service;

    /**
     * Save new customer
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/addCustomer")
    public Customer addCustomer(@RequestBody Customer customer){
        return service.addCustomer(customer);
    }

    /**
     * List all customers
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/customers")
    public List<Customer> findAllCustomers() {
        return service.getCustomers();
    }

    /**
     * Search for customer by id
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/customerById/{id}")
    public Customer findCustomerById(@PathVariable int id) {
        return service.getCustomerById(id);
    }

    /**
    * Update customer details
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/updateCustomer")
    public Customer updateCustomer(@RequestBody Customer customer) {
        return service.updateCustomer(customer);
    }

    /**
     * Delete customer by id
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable int id) {
        return service.deleteCustomer(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("getcustomersBySearch/{search}")
    public List<Customer> findByIdNameIdCard(@PathVariable String search){
        return service.findByIdNameIdCard(search);
    }

}
