package com.example.hotel.controller;


import com.example.hotel.dto.CleaningServiceEmployeeReport;
import com.example.hotel.entity.CleaningServiceEmployee;
import com.example.hotel.entity.CustomerServiceEmployee;
import com.example.hotel.model.CleaningServiceEmplMo;
import com.example.hotel.model.CustomerServiceEmplMo;
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
    public CustomerServiceEmplMo addCustomerEmployee(@RequestBody CustomerServiceEmplMo employee){
        return customerServiceEmployeeService.addCustomerServiceEmplMo(employee);
    }

    /**
     * Get customer Service Employees
     * @return List<Obj: CustomerServiceEmployee>
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("getCustomerEmployees")
    public List<CustomerServiceEmplMo> findAllCustomerServiceEmployees() {
        return customerServiceEmployeeService.getCustomerServiceEmployeesMo();
    }

    /**
     * Delete customer service employee by ID
     * @param id
     * @return String confirmation
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("deleteCustomerEmployee/{id}")
    public String deleteCustomerEmployee(@PathVariable String id) {
        return customerServiceEmployeeService.deleteCustomerServiceEmplMo(id);
    }

    /**
     * Get customer service employee by ID
     * @param id
     * @return Obj: CustomerServiceEmployee
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("getCustomerEmployee/{id}")
        public CustomerServiceEmplMo getCustomerEmployeeById(@PathVariable String id){
        return customerServiceEmployeeService.getCustomerServiceEmplMoById(id);
    }

    /**
     * Update Customer Employee
     * @param employee
     * @return Obj: CustomerServiceEmployee
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("updateCustomerEmployee")
    public CustomerServiceEmplMo updateCustomerEmployee(@RequestBody CustomerServiceEmplMo employee){
        return customerServiceEmployeeService.updateCustomerServiceEmplMo(employee);
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
    public CleaningServiceEmplMo addCustomerEmployee(@RequestBody CleaningServiceEmplMo employee){
        return cleaningEmployeeService.addCleaningServiceEmplMo(employee);
    }

    /**
     * Get cleaning Service Employees
     * @return
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("getCleaningEmployees")
    public List<CleaningServiceEmplMo> findAllCleaningServiceEmployees() {
        return cleaningEmployeeService.getCleaningServiceEmployeesMo();
    }

    /**
     * Delete cleaning servise employee by ID
     * @param id
     * @return
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("deleteCleaningEmployee/{id}")
    public String deleteCleaningEmployee(@PathVariable String id) {
        return cleaningEmployeeService.deleteCleaningServiceEmplMo(id);
    }

    /**
     * Get cleaning servise employee by ID
     * @param id
     * @return Obj: CleaningServiceEmployee
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("getCleaningEmployee/{id}")
    public CleaningServiceEmplMo getCleaningEmployeeById(@PathVariable String id){
        return cleaningEmployeeService.getCleaningServiceEmplMoById(id);
    }

    /**
     * Update CleaningServiceEmployee
     * @param employee
     * @return Obj: CleaningServiceEmployee
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("updateCleaningEmployee")
    public CleaningServiceEmplMo updateCustomerEmployee(@RequestBody CleaningServiceEmplMo employee){
        return cleaningEmployeeService.updateCustomerServiceEmplMo(employee);
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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/mongoCleaningService20Hours")
    public List<CleaningServiceEmplMo> mongoEmployee20Hours(){
        return cleaningEmployeeService.employees20hours();
    }

}
