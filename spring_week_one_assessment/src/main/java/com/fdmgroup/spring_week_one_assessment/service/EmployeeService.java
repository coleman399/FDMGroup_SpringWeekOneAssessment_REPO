package com.fdmgroup.spring_week_one_assessment.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import com.fdmgroup.spring_week_one_assessment.Repository.EmployeeRepository;
import com.fdmgroup.spring_week_one_assessment.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> search(String keyword) {
        if(keyword == null) {
            return employeeRepository.findAll();
        } else {
            return employeeRepository.search(keyword);
        }
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public boolean createEmployee(Employee employee) {
        employee.setHireDate(LocalDate.now());
        employee.setId(new Random().nextLong() * (0 - 1000));
        if (!employeeRepository.existsById(employee.getId())) {
            employeeRepository.save(employee);
            return true;
        }
        return false;
    }
}
