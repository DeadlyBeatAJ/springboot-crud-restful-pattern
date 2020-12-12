package com.deadlybeat.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.deadlybeat.dal.EmployeeDAL;
import com.deadlybeat.exception.ResourceNotFoundException;
import com.deadlybeat.model.Employee;
import com.deadlybeat.service.EmployeeService2;

@Service
@Primary
@Qualifier(value = "Service2")
public class EmployeeServiceImpl2 implements EmployeeService2{

	@Autowired
	private EmployeeDAL employeeDAL;
	
	//ResourceNotFoundException CustomException=new ResourceNotFoundException("Employee not found for id::"+empId);
	
	@Override
	public List<Employee> getAllEmployee() {

		return employeeDAL.getAllEmployee();
	}

	@Override
	public Employee createEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return employeeDAL.createEmployee(emp);
	}

	@Override
	public Employee getEmployeeById(long id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return employeeDAL.getEmployeeById(id);
	}

	@Override
	public Employee updateEmmployee(Employee emp, long empId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return employeeDAL.updateEmmployee(emp, empId);
	}

	@Override
	public void deleteEmployeebyId(long empId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		employeeDAL.deleteEmployeebyId(empId);
		
	}

	@Override
	public boolean isEmployeeExist(long id) {
		// TODO Auto-generated method stub
		return employeeDAL.isEmployeeExist(id);
	}

}
