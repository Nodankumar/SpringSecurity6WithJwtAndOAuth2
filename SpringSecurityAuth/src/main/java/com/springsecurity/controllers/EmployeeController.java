package com.springsecurity.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.model.Employee;

import jakarta.servlet.http.HttpServletRequest;


@RestController
public class EmployeeController {
	
	List<Employee> empList = new ArrayList<Employee>(Arrays.asList(new Employee(1, "Grogu", 23000), new Employee(2, "Gojo", 25000)));
	
	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		return empList;
	}
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee emp) {
		empList.add(emp);
		return emp;
	}
	
	
	@GetMapping("csrftoken")
	public CsrfToken getToken(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
	}
	

}
