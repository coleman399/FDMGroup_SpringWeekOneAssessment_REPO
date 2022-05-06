package com.fdmgroup.spring_week_one_assessment.controller;

import java.util.List;

import com.fdmgroup.spring_week_one_assessment.model.Employee;
import com.fdmgroup.spring_week_one_assessment.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeController {

    private EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String goHome(Model model) {
        model.addAttribute("employees", service.getEmployees());
        return "index";
    }

    @GetMapping("/index")
    public String goToIndex(Model model, String keyword) {
        model.addAttribute("employees", service.search(keyword));
        return "index";
    }

    @PostMapping("/index")
    public String searchByKeyword(Model model, @RequestParam("keyword") String keyword) {
        List<Employee> employeeList = service.search(keyword);
        model.addAttribute("employees", employeeList);
        model.addAttribute("keyword", keyword);
        return "index";
    }

    @GetMapping("/register")
    public String goToRegistration(Model model) {
        model.addAttribute("employee", new Employee());
        return "registration";
    }

    @PostMapping("/register")
    public String addEmployee(Employee employee) {
        if (service.createEmployee(employee)) {
            return "redirect:/index";
        } else {
            return "redirect:/register";
        }
    }
}
