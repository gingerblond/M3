package com.example.hotel.service;

import com.example.hotel.entity.Customer;
import com.example.hotel.entity.Reservation;
import com.example.hotel.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;
    @Autowired
    private ReservationService reservationService;

    /**
     * POST add new customer
     * @param customer
     * @return
     */
    public Customer addCustomer(Customer customer){
        return repository.save(customer);
    }

    /**
     * Get list with all customers
     * @return
     */
    public List<Customer> getCustomers() {
        return repository.findAll();
    }

    /**
     * Find customer by Id
     * @param id
     * @return
     */
    public Customer getCustomerById(int id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Delete customer by customer's Id. This method deletes also all the reservations made by this customer and sets all rooms to available again
     * @param id
     * @return
     */
    public String deleteCustomer(int id) {
        List<Reservation> reservations = new ArrayList<>();
        reservations = reservationService.getReservationsByCustomerID(id);
        for (Reservation reservation: reservations) {
            reservationService.deleteReservation(reservation.getReservationID());
        }
        repository.deleteById(id);
        return "Customer with ID: "+id +"successfully removed !! ";
    }

    /**
     * Updates the information about an existing customer
     * @param customer
     * @return
     */
    public Customer updateCustomer(Customer customer) {
        Customer existingCustomer = repository.findById(customer.getCustomerId()).orElse(null);
        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        return repository.save(existingCustomer);
    }

    /**
     * Search by Customers by criteria such as: first Name OR last Name OR ID Card OR Customer's id
     * @param search
     * @return
     */
    public List<Customer> findByIdNameIdCard(String search){
        List<Customer> foundCustomers = new ArrayList<>();
        List<Customer> customers = repository.findAll();
        Customer foundCustomer = new Customer();
        for (Customer customer: customers) {
            if(customer.getFirstName().equals(search) ||
                    customer.getLastName().equals(search) ||
                    customer.getIdCard().equals(search) ||
                    Integer.toString(customer.getCustomerId()).equals(search)){
                foundCustomers.add(customer);
            }
        }
        return  foundCustomers;
    }

}
