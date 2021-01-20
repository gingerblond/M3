package com.example.hotel.service;

import com.example.hotel.entity.Employee;
import com.example.hotel.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public Employee addEmployee(Employee employee){
        return repository.save(employee);
    }

    public List<Employee> getEmployees() {
        return repository.findAll();
    }

    public Employee getEmployeeById(int id) {
        return repository.findById(id).orElse(null);
    }

    public String deleteEmployee(int id) {
        repository.deleteById(id);
        return "Employee with id: " +id + " has been removed." ;
    }

    public Employee updateEmployee(Employee employee) {
        Employee existingEmployee = repository.findById(employee.getEmployeeId()).orElse(null);
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        return repository.save(existingEmployee);
    }

}
