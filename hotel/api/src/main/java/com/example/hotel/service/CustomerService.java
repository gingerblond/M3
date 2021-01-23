package com.example.hotel.service;

import com.example.hotel.entity.Customer;
import com.example.hotel.entity.Reservation;
import com.example.hotel.model.CustomerMo;
import com.example.hotel.model.ReservationMo;
import com.example.hotel.repository.CustomerRepository;
import com.example.hotel.repositoryMo.CustomerMoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;
    @Autowired
    private CustomerMoRepository customerMoRepository;
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
     * Delete customer by customer's Id. This method deletes also all the reservations made by this customer and sets all rooms to available again in MySQl
     * @param id
     * @return
     */
    public String deleteCustomer(int id) {
        List<Reservation> reservations = new ArrayList<>();
        /**
         * TODO
         */
        //reservations = reservationService.getReservationsByCustomerID(id);
        for (Reservation reservation: reservations) {
            reservationService.deleteReservation(reservation.getReservationID());
        }
        repository.deleteById(id);
        return "Customer with ID: "+id +" successfully removed !! ";
    }

    /**
     * Delete customer by customer's Id. This method deletes also all the reservations made by this customer and sets all rooms to available again in MongoDB
     * @param id
     * @return
     */
    public String deleteCustomerMo(String id) {
        List<ReservationMo> reservations = new ArrayList<>();
        /**
         * TODO
         */
        reservations = reservationService.getReservationsByCustomerID(id);
        for (ReservationMo reservation: reservations) {
            reservationService.deleteReservationMo(reservation.getReservationId());
        }
        customerMoRepository.delete(customerMoRepository.findByCustomerId(id));
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
     * Search by Customers by criteria such as: first Name OR last Name OR ID Card OR Customer's id in MySQL
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

    /**
     * Search by Customers by criteria such as: first Name OR last Name OR ID Card OR Customer's id in MySQL
     * @param search
     * @return
     */
    public List<CustomerMo> findByIdNameIdCardMo(String search){
        List<CustomerMo> foundCustomers = new ArrayList<>();
        List<CustomerMo> customers = customerMoRepository.findAll();
        Customer foundCustomer = new Customer();
        for (CustomerMo customer: customers) {
            if(customer.getFirstName().equals(search) ||
                    customer.getLastName().equals(search) ||
                    customer.getIdCard().equals(search) ||
                    customer.getCustomerId().equals(search)){
                foundCustomers.add(customer);
            }
        }
        return  foundCustomers;
    }


}
