package com.example.projectmanagement.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.function.ServerRequest.Headers;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.projectmanagement.dto.EmployeeDto;
import com.example.projectmanagement.exception.EmployeeNotFoundException;
import com.example.projectmanagement.model.Employee;
import com.example.projectmanagement.repository.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	public EmployeeRepo employeeRepo;

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
		} else {
			employee = employeeRepo.findById(employeeDto.getEmpId()).get();
		}
		employee.setEmpId(employeeDto.getEmpId());
		employee.setEmpName(employeeDto.getEmpName());
		employee.setEmpEmail(employeeDto.getEmpEmail());
		employeeRepo.save(employee);
	}

	@Override
	public EmployeeDto employeegetById(Long empId) {
		Employee employee = employeeRepo.findById(empId).orElseThrow(()->new EmployeeNotFoundException("Employee Not found Please Enter Valid Id !"));		
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setEmpId(employee.getEmpId());
		employeeDto.setEmpName(employee.getEmpName());
		employeeDto.setEmpEmail(employee.getEmpEmail());
		return employeeDto;	
	}

	@Override
	public void deleteEmployeeById(Long empId) {
		if(employeeRepo.existsById(empId)) {
		employeeRepo.deleteById(empId);
		}else {
			throw new EmployeeNotFoundException("Employee Not Found Please Enter Valid id !");
		}
	}

	private static final String UPLOAD_DIR = "D:\\JavaCore\\ProjectManagement\\UploadFiles";

	public String fileUpload(MultipartFile file) {

		String fileName;
		try {
			File directory = new File(UPLOAD_DIR);
			if (!directory.exists()) {
				directory.mkdirs();
			}

			fileName = file.getOriginalFilename();
			Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName);
			Files.write(filePath, file.getBytes(), StandardOpenOption.CREATE);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/api/Employees/download/").path(fileName).toUriString();
			return "File upload Successfully Download URL:" + fileDownloadUri;
		} catch (IOException ex) {
			return ex.getMessage();
		}
	}

	@Override
	public ResponseEntity<Object> downloadfile(String Name) {
		ResponseEntity<Object> response = null;
		try {
			Path filePath = Paths.get(UPLOAD_DIR).resolve(Name).normalize();
			File file = filePath.toFile();

			if (file.exists()) {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.setContentDispositionFormData("attachment", Name);

				response = ResponseEntity.ok().headers(headers).contentLength(file.length())
						.body(new FileSystemResource(file));

			} else {
				response = ResponseEntity.notFound().build();
			}

		} catch (Exception e) {
			return response;
		}
		return response;
	}

	@Override
	public String UploadTextFile(MultipartFile file) {
		String fileName;
		try {

			File directory = new File(UPLOAD_DIR);
			if (!directory.exists()) {
				directory.mkdirs();
			}

			if (file.getContentType().equals("text/plain")) {
				fileName = "index.txt";
				Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName);
				if (!filePath.toFile().exists()) {
					filePath.toFile().createNewFile();
				}
				Files.write(filePath, file.getBytes(), StandardOpenOption.APPEND);
			} else {

				fileName = file.getOriginalFilename();
				Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName);
				Files.write(filePath, file.getBytes(), StandardOpenOption.CREATE);
			}

			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/Employees/download/")
					.path(fileName).toUriString();

			System.out.println(file.getContentType());

			return ("File uploaded successfully. Download URL: " + fileDownloadUri);
		} catch (IOException ex) {
			 return ex.getMessage();
		}
	
	}

}
