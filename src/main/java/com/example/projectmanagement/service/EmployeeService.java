package com.example.projectmanagement.service;
import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.projectmanagement.dto.EmployeeDto;

public interface EmployeeService {
	
	List<EmployeeDto> fetchAll();

	void addEmployee(EmployeeDto employeeDto);
	
	EmployeeDto employeegetById(Long empId);
	
	void deleteEmployeeById(Long empId);
	
	String fileUpload(MultipartFile file);
	
	String UploadTextFile(MultipartFile file);
	ResponseEntity<Object> downloadfile(String Name);
}
