package com.example.hotel.service;

import com.example.hotel.entity.Customer;
import com.example.hotel.entity.CustomerServiceEmployee;
import com.example.hotel.model.CustomerServiceEmplMo;
import com.example.hotel.repository.CustomerServiceEmployeeRepository;
import com.example.hotel.repositoryMo.CustomerServiceEmplMoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceEmployeeService {

    @Autowired
    private CustomerServiceEmployeeRepository repository;
    @Autowired
    private CustomerServiceEmplMoRepository customerServiceEmplMoRepository;

    /**
     * POST add new customer customer service employee to MySQL
     * @param employee
     * @return
     */
    public CustomerServiceEmployee addCustomerServiceEmployee(CustomerServiceEmployee employee){
        return repository.save(employee);
    }

    /**
     * Add new customer service employee to MongoDB
     * @param employee
     * @return
     */
    public CustomerServiceEmplMo addCustomerServiceEmplMo(CustomerServiceEmplMo employee){
        return customerServiceEmplMoRepository.save(employee);
    }

    /**
     * Get list with all customer service employees from MySQL
     * @return
     */
    public List<CustomerServiceEmployee> getCustomerServiceEmployees() {
        return repository.findAll();
    }
    /**
     * Get list with all customer service employees from MongoDB
     * @return
     */
    public List<CustomerServiceEmplMo> getCustomerServiceEmployeesMo(){
        return customerServiceEmplMoRepository.findAll();
    }

    /**
     * Find customer service employee by Id from MySQL
     * @param id
     * @return
     */
    public CustomerServiceEmployee getCustomerServiceEmployeeById(int id) {
        return repository.findById(id).orElse(null);
    }
    /**
     * Find customer service employee by Id from MongoDB
     * @param id
     * @return
     */
    public CustomerServiceEmplMo getCustomerServiceEmplMoById(String id) {
        return customerServiceEmplMoRepository.findByEmployeeId(id);
    }

    /**
     * Delete customer Service employee from MySQL
     * @param id
     * @return
     */
    public String deleteCustomerServiceEmployee(int id) {
         repository.deleteById(id);
         return "Customer Servise employee with ID :" + id + " successfully removed!";
    }

    /**
     * Delete customer Service employee from MongoDB
     * @param id
     * @return
     */
    public String deleteCustomerServiceEmplMo(String id) {
        customerServiceEmplMoRepository.delete(customerServiceEmplMoRepository.findByEmployeeId(id));
        return "Customer Servise employee with ID :" + id + " successfully removed!";
    }

    /**
     * Update Customer service employee in MySQL
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

    /**
     * Update Customer service employee in MongoDB
     * @param employee
     * @return
     */
    public CustomerServiceEmplMo updateCustomerServiceEmplMo(CustomerServiceEmplMo employee){
        CustomerServiceEmplMo existingEmployee = customerServiceEmplMoRepository.findByEmployeeId(employee.getEmployeeId());
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setSocialId(employee.getSocialId());
        existingEmployee.setHotel(employee.getHotel());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhoneNumber(employee.getPhoneNumber());
        existingEmployee.setUser(employee.getUser());
        return customerServiceEmplMoRepository.save(existingEmployee);
    }

}
