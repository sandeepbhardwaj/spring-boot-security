package com.koko.springsecurity.controller;

import com.koko.springsecurity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {
    private static List<Employee> EMPLOYEES = Arrays.asList(
            new Employee(1, "Sandeep", 35),
            new Employee(2, "Sumit", 22),
            new Employee(3, "Naman", 22),
            new Employee(4, "Daman", 22)
    );

    @GetMapping(path = "{employeeId}")
    public Employee getEmployeeById(@PathVariable("employeeId") Integer employeeId) {
        log.info("Get employee with id :{}",employeeId);
        return EMPLOYEES.stream().filter(e -> employeeId.equals(e.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Employee " + employeeId + " not found"));
    }

}
