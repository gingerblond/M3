package com.example.hotel.service;

import com.example.hotel.entity.CleaningServiceEmployee;
import com.example.hotel.entity.CustomerServiceEmployee;
import com.example.hotel.model.CleaningServiceEmplMo;
import com.example.hotel.repository.CleaningServiceEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CleaningServiceEmployeeService {

    @Autowired
    private CleaningServiceEmployeeRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * POST add new cleaning service employee
     * @param employee
     * @return
     */
    public CleaningServiceEmployee addCleaningServiceEmployee(CleaningServiceEmployee employee){
        return repository.save(employee);
    }

    /**
     * Get list with all cleaning service employees
     * @return
     */
    public List<CleaningServiceEmployee> getCleaningServiceEmployees() {
        return repository.findAll();
    }

    /**
     * Find cleaning service employee by Id
     * @param id
     * @return
     */
    public CleaningServiceEmployee getCleaningServiceEmployeeById(int id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Delete CleaningServiceEmployee
     * @param id
     * @return
     */
    public String deleteCleaningServiceEmployee(int id) {
        repository.deleteById(id);
        return "Cleaning Servise employee with ID :" + id + " successfully removed!";
    }

    /**
     * Update Cleaning service employee
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

    public List<CleaningServiceEmplMo> employees20hours () {
        Query query = new Query()
                .addCriteria(Criteria.where("workingHours").is(20));
        query.fields().exclude("hotelMo");

        return mongoTemplate.find(query, CleaningServiceEmplMo.class);
    }
}
