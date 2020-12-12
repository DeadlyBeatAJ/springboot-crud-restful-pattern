package com.deadlybeat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deadlybeat.exception.ResourceNotFoundException;
import com.deadlybeat.model.Employee;
import com.deadlybeat.service.EmployeeService;
import com.deadlybeat.service.EmployeeService2;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController{

	@Autowired
	private EmployeeService empSevice;
	
	@Autowired
	private EmployeeService2 empSevice2;
	
	private boolean isEmployeeExist;
	
	//get all employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployee(){
		System.out.println("Using JPA Repo::");
		return empSevice.getAllEmployee();
	}
		
	//add new employee 
	@PostMapping("employees")
	public Employee createEmployee(@RequestBody Employee emp) {
		return empSevice.createEmployee(emp);
	}

	//update Employee by id
	@PutMapping("employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id")long empId, @RequestBody Employee emp)throws ResourceNotFoundException  {
		Employee updatedEmplyoyee=empSevice.updateEmmployee(emp,empId);
		return ResponseEntity.ok().body(updatedEmplyoyee);
	}
	
	//get Employee by id
	@GetMapping("employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id") long empId) throws ResourceNotFoundException {
		Employee emp=empSevice.getEmployeeById(empId);
		return ResponseEntity.ok().body(emp);
	}
	
	//delete Employee
	@SuppressWarnings("rawtypes")
	@DeleteMapping("employees/{id}")
	public ResponseEntity deletEmployeeById(@PathVariable(value = "id") Long empid) throws ResourceNotFoundException{
		empSevice.deleteEmployeebyId(empid);
		return ResponseEntity.ok().build();
	}	
	
	//get all employees
	@GetMapping("/employees2")
	public List<Employee> getAllEmployee2(){
		System.out.println("Using JDBC Template::");
		return empSevice2.getAllEmployee();
	}

	//add new employee 
	@PostMapping("employees2")
	public Employee createEmployee2(@RequestBody Employee emp) {
		System.out.println("Using JDBC Template::");
		return empSevice2.createEmployee(emp);
	}

	//get Employee by id
	@GetMapping("employees2/{id}")
	public ResponseEntity<Employee> getEmployeeById2(@PathVariable(value="id") long empId) throws ResourceNotFoundException {
		System.out.println("Using JDBC Template::");
		isEmployeeExist=empSevice2.isEmployeeExist(empId);
		if(isEmployeeExist) {
			Employee emp=empSevice2.getEmployeeById(empId);
			return ResponseEntity.ok().body(emp);
		}
		else {
			throw new ResourceNotFoundException("Employee with ID "+empId+" Not found.");
		}
		
	}
	
	//update Employee by id
	@PutMapping("employees2/{id}")
	public ResponseEntity<Employee> updateEmployee2(@PathVariable(value = "id")long empId, @RequestBody Employee emp)throws ResourceNotFoundException  {
		System.out.println("Using JDBC Template::");
		isEmployeeExist=empSevice2.isEmployeeExist(empId);
		if(isEmployeeExist) {
			Employee updatedEmplyoyee=empSevice2.updateEmmployee(emp,empId);
			return ResponseEntity.ok().body(updatedEmplyoyee);
		}
		else {
			throw new ResourceNotFoundException("Employee with ID "+empId+" Not found.");
		}
		
	}
	
	//delete Employee
	@SuppressWarnings("rawtypes")
	@DeleteMapping("employees2/{id}")
	public ResponseEntity deletEmployeeById2(@PathVariable(value = "id") Long empid) throws ResourceNotFoundException{
		System.out.println("Using JDBC Template::");
		isEmployeeExist=empSevice2.isEmployeeExist(empid);
		if(isEmployeeExist) {
			empSevice.deleteEmployeebyId(empid);
			return ResponseEntity.ok().build();
		}
		else {
			throw new ResourceNotFoundException("Employee with ID "+empid+" Not found.");
		}
	}
}
