package com.example.hotel.service;

import com.example.hotel.entity.CleaningServiceEmployee;
import com.example.hotel.entity.CustomerServiceEmployee;
import com.example.hotel.model.CleaningServiceEmplMo;
import com.example.hotel.repository.CleaningServiceEmployeeRepository;
import com.example.hotel.repositoryMo.CleaningServiceEmplMoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CleaningServiceEmployeeService {

    @Autowired
    private CleaningServiceEmployeeRepository repository;
    @Autowired
    private CleaningServiceEmplMoRepository cleaningServiceEmplMoRepository;
    @Autowired
    private MongoOperations mongoOperations;

    /**
     * POST add new cleaning service employee to MySQL
     * @param employee
     * @return
     */
    public CleaningServiceEmployee addCleaningServiceEmployee(CleaningServiceEmployee employee){
        return repository.save(employee);
    }

    /**
     * POST add new cleaning service employee to MongoDB
     * @param employee
     * @return
     */
     public CleaningServiceEmplMo addCleaningServiceEmplMo(CleaningServiceEmplMo employee){
        return cleaningServiceEmplMoRepository.save(employee);
    }

    /**
     * Get list with all cleaning service employees from MySQL
     * @return
     */
    public List<CleaningServiceEmployee> getCleaningServiceEmployees() {
        return repository.findAll();
    }
    /**
     * Get list with all cleaning service employees from MongoDB
     * @return
     */
    public List<CleaningServiceEmplMo> getCleaningServiceEmployeesMo() {
        return cleaningServiceEmplMoRepository.findAll();
    }

    /**
     * Find cleaning service employee by Id in MySql
     * @param id
     * @return
     */
    public CleaningServiceEmployee getCleaningServiceEmployeeById(int id) {
        return repository.findById(id).orElse(null);
    }
    /**
     * Find cleaning service employee by Id in MongoDB
     * @param id
     * @return
     */
    public CleaningServiceEmplMo getCleaningServiceEmplMoById(String id) {
        return cleaningServiceEmplMoRepository.findByEmployeeId(id);
    }

    /**
     * Delete CleaningServiceEmployee from MySql
     * @param id
     * @return
     */
    public String deleteCleaningServiceEmployee(int id) {
        repository.deleteById(id);
        return "Cleaning Servise employee with ID :" + id + " successfully removed!";
    }

    /**
     * Delete CleaningServiceEmployee from MongoDb
     * @param id
     * @return
     */
    public String deleteCleaningServiceEmplMo(String id) {
        cleaningServiceEmplMoRepository.delete(cleaningServiceEmplMoRepository.findByEmployeeId(id));
        return "Cleaning Servise employee with ID :" + id + " successfully removed!";
    }

    /**
     * Update Cleaning service employee in MySQL
     * @param employee
     * @return
     */
    public CleaningServiceEmployee updateCustomerServiceEmployee(CleaningServiceEmployee employee){
        CleaningServiceEmployee existingEmployee = repository.findById(employee.getEmployeeId()).orElse(null);
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setSocialId(employee.getSocialId());
        existingEmployee.setHotel(employee.getHotel());
        existingEmployee.setWorkingHours(employee.getWorkingHours());
        existingEmployee.setResponsibility(employee.getResponsibility());
        return repository.save(existingEmployee);
    }
    /**
     * Update Cleaning service employee in MongoDb
     * @param employee
     * @return
     */
    public CleaningServiceEmplMo updateCustomerServiceEmplMo(CleaningServiceEmplMo employee){
        CleaningServiceEmplMo existingEmployee = cleaningServiceEmplMoRepository.findByEmployeeId(employee.getEmployeeId());
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setSocialId(employee.getSocialId());
        existingEmployee.setHotel(employee.getHotel());
        existingEmployee.setWorkingHours(employee.getWorkingHours());
        existingEmployee.setResponsibility(employee.getResponsibility());
        return cleaningServiceEmplMoRepository.save(existingEmployee);
    }


    public List<CleaningServiceEmplMo> getReportCleaningServiceEmployee(){
        Query query = new Query(Criteria.where("workingHours").is(20));
        List<CleaningServiceEmplMo> employees = mongoOperations.find(query,CleaningServiceEmplMo.class);
        return employees;
    }
}
