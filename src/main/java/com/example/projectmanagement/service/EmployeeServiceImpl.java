package com.example.projectmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projectmanagement.dto.EmployeeDto;
import com.example.projectmanagement.model.Employee;
import com.example.projectmanagement.repository.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements  EmployeeService{

	@Autowired
	public EmployeeRepo  employeeRepo;
	
	@Override
	public List<EmployeeDto> fetchAll() {
		List<Employee> employee = employeeRepo.findAll();
		
		List<EmployeeDto> employeeDto = new ArrayList<>();
		for (Employee employee1 : employee) {
			EmployeeDto empoyeeDto = new EmployeeDto();
			empoyeeDto.setEmpId(employee1.getEmpId());
			empoyeeDto.setEmpName(employee1.getEmpName());
			empoyeeDto.setEmpEmail(employee1.getEmpEmail());
			employeeDto.add(empoyeeDto);			
		}
		return employeeDto;
	}

	public void addEmployee(EmployeeDto employeeDto) {
		Employee employee;
		if (employeeDto.getEmpId() == null) {
			employee = new Employee();
		}else {
			employee = employeeRepo.findById(employeeDto.getEmpId()).get();
		}
		employee.setEmpId(employeeDto.getEmpId());
		employee.setEmpName(employeeDto.getEmpName());
		employee.setEmpEmail(employeeDto.getEmpEmail());
		employeeRepo.save(employee);
	}

	@Override
	public EmployeeDto employeegetById(Long empId) {
		Employee employee = employeeRepo.findById(empId).get();
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setEmpId(employee.getEmpId());
		employeeDto.setEmpName(employee.getEmpName());
		employeeDto.setEmpEmail(employee.getEmpEmail());
		return employeeDto;
	}

	@Override
	public void deleteEmployeeById(Long empId) {
		employeeRepo.deleteById(empId);
	}

}
