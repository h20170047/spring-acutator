package com.svj.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.svj.dto.Employee;
import com.svj.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class EmployeeController {
    EmployeeService employeeService;

    EmployeeController(EmployeeService employeeService){
        this.employeeService= employeeService;
    }

//    @GetMapping("/health")
//    public HttpStatus getHealth(){
//        return HttpStatus.OK;
//    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() throws JsonProcessingException {
        List<Employee> employees = employeeService.getEmployees();
        log.info("EmployeeController:getAllEmployees fetch all employees {}", new ObjectMapper().writeValueAsString(employees));
        return employees;
    }

}
