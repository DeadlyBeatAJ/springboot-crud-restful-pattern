package com.deadlybeat.dalImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.deadlybeat.dal.EmployeeDAL;
import com.deadlybeat.exception.ResourceNotFoundException;
import com.deadlybeat.mapper.EmployeeRowMapper;
import com.deadlybeat.model.Employee;

@Repository
public class EmployeeDALImpl implements EmployeeDAL{

	@Autowired
	private JdbcTemplate template;
	
	@Override
	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		String sql="SELECT id,first_name,last_name,email_id FROM EMPLOYEES_TBL";
		System.out.println(sql);
		return template.query(sql, new EmployeeRowMapper());
	}

	@Override
	@Transactional
	public Employee createEmployee(Employee emp) {
		String sql="INSERT INTO EMPLOYEES_TBL"+"(first_name,last_name,email_id) VALUES (?,?,?)";
		System.out.println(sql);
		KeyHolder holder= new GeneratedKeyHolder();
		template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException 
			{
				PreparedStatement ps=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, emp.getFirstName());
				ps.setString(2, emp.getLastName());
				ps.setString(3, emp.getEmailId());
				return ps;
			}
		},holder);
		
		//to work with this, the primary column must have auto_increment constraint 
		//(here id field should have auto increment)  check query.txt file for the queries
		long generatedEmpID=holder.getKey().intValue();
		emp.setId(generatedEmpID);
		return emp;
	}

	@Override
	@Transactional()
	public Employee getEmployeeById(long id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		String sql= "SELECT id,first_name,last_name,email_id FROM EMPLOYEES_TBL WHERE ID=?";
		System.out.println(sql);
		Employee emp= template.queryForObject(sql, new Object[] {id},  new EmployeeRowMapper());
		return emp;
	}

	@Override
	public Employee updateEmmployee(Employee emp, long empId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		String sql="UPDATE EMPLOYEES_TBL SET first_name=?, last_name=?, email_id=? WHERE ID=?";
		System.out.println(sql);
		template.update(sql, emp.getFirstName(), emp.getLastName(), emp.getEmailId(), empId);
		emp.setId(empId);
		return emp;
	}

	@Override
	public void deleteEmployeebyId(long empId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		String sql="DELETE FROM EMPLOYEES_TBL WHERE ID=?";
		System.out.println(sql);
		template.update(sql, empId); 
		
	}

	//check if employee is present or not based on count
	@Override
	public boolean isEmployeeExist(long id)
	{
		String sql = "SELECT COUNT(*) FROM EMPLOYEES_TBL where id=?";

		int count = template.queryForObject(sql, new Object[] { id }, Integer.class);
		if (count >= 1)
		{
			return true;
		}
		return false;
	}
	
}
