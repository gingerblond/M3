package com.example.hotel.service;

import com.example.hotel.entity.Customer;
import com.example.hotel.entity.CustomerServiceEmployee;
import com.example.hotel.repository.CustomerServiceEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceEmployeeService {

    @Autowired
    private CustomerServiceEmployeeRepository repository;

    /**
     * POST add new customer customer service employee
     * @param employee
     * @return
     */
    public CustomerServiceEmployee addCustomerServiceEmployee(CustomerServiceEmployee employee){
        return repository.save(employee);
    }

    /**
     * Get list with all customer service employees
     * @return
     */
    public List<CustomerServiceEmployee> getCustomerServiceEmployees() {
        return repository.findAll();
    }

    /**
     * Find customer service employee by Id
     * @param id
     * @return
     */
    public CustomerServiceEmployee getCustomerServiceEmployeeById(int id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Delete customer Service employee
     * @param id
     * @return
     */
    public String deleteCustomerServiceEmployee(int id) {
         repository.deleteById(id);
         return "Customer Servise employee with ID :" + id + " successfully removed!";
    }

    /**
     * Update Customer service employee
     * @param employee
     * @return
     */
    public CustomerServiceEmployee updateCustomerServiceEmployee(CustomerServiceEmployee employee){
        CustomerServiceEmployee existingEmployee = repository.findById(employee.getEmployeeId()).orElse(null);
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setSocialId(employee.getSocialId());
        existingEmployee.setHotel(employee.getHotel());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhoneNumber(employee.getPhoneNumber());
        existingEmployee.setUser(employee.getUser());
        return repository.save(existingEmployee);
    }

}
