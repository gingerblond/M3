package com.example.hotel.controller;


import com.example.hotel.dto.CleaningServiceEmployeeReport;
import com.example.hotel.entity.CleaningServiceEmployee;
import com.example.hotel.entity.CustomerServiceEmployee;
import com.example.hotel.repository.CleaningServiceEmployeeRepository;
import com.example.hotel.repository.CustomerServiceEmployeeRepository;
import com.example.hotel.repository.HotelRepository;
import com.example.hotel.service.CleaningServiceEmployeeService;
import com.example.hotel.service.CustomerServiceEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private CleaningServiceEmployeeService cleaningEmployeeService;

    @Autowired
    private CustomerServiceEmployeeService customerServiceEmployeeService;

    @Autowired
    private CustomerServiceEmployeeRepository customerServiceEmployeeRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private CleaningServiceEmployeeRepository cleaningServiceEmployeeRepository;

    /*******************************************************************************
     *************** APIS for Customer Service Employees below:*********************
     ******************************************************************************/

    /**
     * Add Customer Service Employee
     * @param employee
     * @return Obj: CustomerServiceEmployee
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("addCustomerEmployee")
    public CustomerServiceEmployee addCustomerEmployee(@RequestBody CustomerServiceEmployee employee){
        return customerServiceEmployeeService.addCustomerServiceEmployee(employee);
    }

    /**
     * Get customer Service Employees
     * @return List<Obj: CustomerServiceEmployee>
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("getCustomerEmployees")
    public List<CustomerServiceEmployee> findAllCustomerServiceEmployees() {
        return customerServiceEmployeeService.getCustomerServiceEmployees();
    }

    /**
     * Delete customer servise employee by ID
     * @param id
     * @return String confirmation
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("deleteCustomerEmployee/{id}")
    public String deleteCustomerEmployee(@PathVariable int id) {
        return customerServiceEmployeeService.deleteCustomerServiceEmployee(id);
    }

    /**
     * Get customer servise employee by ID
     * @param id
     * @return Obj: CustomerServiceEmployee
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("getCustomerEmployee/{id}")
        public CustomerServiceEmployee getCustomerEmployeeById(@PathVariable int id){
        return customerServiceEmployeeService.getCustomerServiceEmployeeById(id);
    }

    /**
     * Update Customer Employee
     * @param employee
     * @return Obj: CustomerServiceEmployee
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("updateCustomerEmployee")
    public CustomerServiceEmployee updateCustomerEmployee(@RequestBody CustomerServiceEmployee employee){
        return customerServiceEmployeeService.updateCustomerServiceEmployee(employee);
    }


    /*******************************************************************************
     *************** APIS for Cleaning Service Employees below:*********************
     *******************************************************************************/

    /**
     * Add Cleaning Service Employee
     * @param employee
     * @return
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("addCleaningEmployee")
    public CleaningServiceEmployee addCustomerEmployee(@RequestBody CleaningServiceEmployee employee){
        return cleaningEmployeeService.addCleaningServiceEmployee(employee);
    }

    /**
     * Get cleaning Service Employees
     * @return
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("getCleaningEmployees")
    public List<CleaningServiceEmployee> findAllCleaningServiceEmployees() {
        return cleaningEmployeeService.getCleaningServiceEmployees();
    }

    /**
     * Delete cleaning servise employee by ID
     * @param id
     * @return
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("deleteCleaningEmployee/{id}")
    public String deleteCleaningEmployee(@PathVariable int id) {
        return cleaningEmployeeService.deleteCleaningServiceEmployee(id);
    }

    /**
     * Get cleaning servise employee by ID
     * @param id
     * @return Obj: CleaningServiceEmployee
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("getCleaningEmployee/{id}")
    public CleaningServiceEmployee getCleaningEmployeeById(@PathVariable int id){
        return cleaningEmployeeService.getCleaningServiceEmployeeById(id);
    }

    /**
     * Update CleaningServiceEmployee
     * @param employee
     * @return Obj: CleaningServiceEmployee
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("updateCleaningEmployee")
    public CleaningServiceEmployee updateCustomerEmployee(@RequestBody CleaningServiceEmployee employee){
        return cleaningEmployeeService.updateCustomerServiceEmployee(employee);
    }

    /**
     * Get all cleaning service employees of Hotel 5 who work 20 hours per week
     * @return
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/CleaningServiceEmployees20Hours")
    public List<CleaningServiceEmployeeReport> getReportClean(){
        return cleaningServiceEmployeeRepository.getReportCleaningServiceEmployee();
    }


}
