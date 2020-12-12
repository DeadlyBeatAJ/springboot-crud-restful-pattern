package com.deadlybeat.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deadlybeat.exception.ResourceNotFoundException;
import com.deadlybeat.model.Employee;
import com.deadlybeat.repository.EmployeeRepository;
import com.deadlybeat.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository repo;
	
	//ResourceNotFoundException CustomException=new ResourceNotFoundException("Employee not found for id::"+empId);
	
	@Override
	public List<Employee> getAllEmployee() {

		return repo.findAll();
	}

	@Override
	public Employee createEmployee(Employee emp) {

		return repo.save(emp);
	}

	@Override
	public Employee getEmployeeById(long id) throws ResourceNotFoundException {

		Employee emp=repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found for id::"+id));
		//Employee emp2=repo.findById(id).orElseThrow(()->CustomException);
		return emp;
	}

	public Employee updateEmmployee(Employee emp, long empId) throws ResourceNotFoundException {

		Employee updatedEmp=repo.findById(empId).orElseThrow(()->new ResourceNotFoundException("Employee not found for id::"+empId));
		
		updatedEmp.setFirstName(emp.getFirstName());
		updatedEmp.setLastName(emp.getLastName());
		updatedEmp.setEmailId(emp.getEmailId());
		
		return repo.save(updatedEmp);
	}
	
	@Override
	public void deleteEmployeebyId(long empId) throws ResourceNotFoundException{

		repo.findById(empId).orElseThrow(()->new ResourceNotFoundException("Employee not found for id::"+empId));
		repo.deleteById(empId);
	}

}
