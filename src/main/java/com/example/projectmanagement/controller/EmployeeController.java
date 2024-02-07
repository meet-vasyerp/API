package com.example.projectmanagement.controller;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import javax.swing.plaf.multi.MultiButtonUI;

import org.apache.tomcat.util.http.fileupload.UploadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.projectmanagement.dto.EmployeeDto;
import com.example.projectmanagement.service.EmployeeService;

@Controller
@RequestMapping("/api/Employees")
public class EmployeeController {
	private static final String UPLOAD_DIR = null;
	@Autowired
	public EmployeeService employeeService;
	
	@GetMapping("")
	@ResponseBody
	public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
			return ResponseEntity.ok(employeeService.fetchAll());
	}
		
	@PostMapping({"/addEmployees","/updateEmployee"})
	@ResponseBody
	public ResponseEntity<String> addEmplyees(@RequestBody EmployeeDto employeeDto){
		try {
			employeeService.addEmployee(employeeDto);
			return ResponseEntity.ok("Success!");
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/get")
	@ResponseBody
	public  ResponseEntity<EmployeeDto> getEmployeebyId(@RequestParam Long empid){
		return ResponseEntity.ok(employeeService.employeegetById(empid));
	}
	
	@PostMapping("/uploadFile")
	@ResponseBody
	public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file){
		return ResponseEntity.ok(employeeService.fileUpload(file));
	}
	
	@PostMapping("/uploadTextFile")
	@ResponseBody
	public ResponseEntity<String> uploadTextFile(@RequestParam(value = "file") MultipartFile file){
		return ResponseEntity.ok(employeeService.UploadTextFile(file));
	}
	
	@GetMapping("/download/{Name}")
	@ResponseBody
	public ResponseEntity<Object> downloadFile(@PathVariable String Name){
		return employeeService.downloadfile(Name);
	}
	
	@DeleteMapping("/deleteEmployee/{empId}")
	@ResponseBody
	public ResponseEntity<String> deleteEmployeeById(@PathVariable Long empId){
		employeeService.deleteEmployeeById(empId);
		return ResponseEntity.ok("Deleted !");
	}
	 
}
