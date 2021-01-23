package com.example.hotel.controller;

import com.example.hotel.entity.Customer;
import com.example.hotel.model.CustomerMo;
import com.example.hotel.repository.CustomerRepository;
import com.example.hotel.repositoryMo.CustomerMoRepository;
import com.example.hotel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerMoRepository customerMoRepository;
    @Autowired
    private CustomerService service;

    /**
     * Save new customer
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/addCustomer")
    /*public Customer addCustomer(@RequestBody Customer customer){
        return service.addCustomer(customer);
    }*/
    public CustomerMo addCustomer(@RequestBody CustomerMo customerMo) {
        return customerMoRepository.insert(customerMo);

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
    public CustomerMo findCustomerById(@PathVariable String id) {
        // return service.getCustomerById(id);
        return customerMoRepository.findByCustomerId(id);

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
    public String deleteCustomer(@PathVariable String id) {
        return service.deleteCustomerMo(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("getcustomersBySearch/{search}")
    public List<CustomerMo> findByIdNameIdCard(@PathVariable String search){
        return service.findByIdNameIdCardMo(search);
    }

}
