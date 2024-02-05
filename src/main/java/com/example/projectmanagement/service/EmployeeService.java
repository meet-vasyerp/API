package com.example.projectmanagement.service;
import java.util.*;
import com.example.projectmanagement.dto.EmployeeDto;

public interface EmployeeService {
	
	List<EmployeeDto> fetchAll();

	void addEmployee(EmployeeDto employeeDto);
	
	EmployeeDto employeegetById(Long empId);
	
	void deleteEmployeeById(Long empId);

}
