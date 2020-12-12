package com.deadlybeat.service;

import java.util.List;

import com.deadlybeat.exception.ResourceNotFoundException;
import com.deadlybeat.model.Employee;

public interface EmployeeService {
	
	public List<Employee> getAllEmployee();

	public Employee createEmployee(Employee emp);
	
	public Employee getEmployeeById(long id) throws ResourceNotFoundException;
	
	public Employee updateEmmployee(Employee emp, long empId) throws ResourceNotFoundException;

	public void deleteEmployeebyId(long empId) throws ResourceNotFoundException;
}
