package com.example.projectmanagement.controller;

import java.util.List;

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

import com.example.projectmanagement.dto.EmployeeDto;
import com.example.projectmanagement.dto.ProjectDto;
import com.example.projectmanagement.dto.ResponseDto;
import com.example.projectmanagement.service.ProjectService;


@Controller
@RequestMapping("/api/projects")
public class ProjectController {
	@Autowired
	public ProjectService projectService;
	
	@GetMapping("")
	@ResponseBody
	public ResponseEntity<List<ProjectDto>> getAll(){
		return ResponseEntity.ok(projectService.fetchAll());
	}
	
	@PostMapping({"/addProjects","/updateProject"})
	@ResponseBody
	public ResponseEntity<String> addProject(@RequestBody ProjectDto projetDto ){
		try {
			projectService.addProjects(projetDto);
			return ResponseEntity.ok("Project Added !");
		}catch(Exception e){
	 		e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	@GetMapping("/getProject")
	@ResponseBody
	public ResponseDto getProjectById(@RequestParam Long projectId) {
		ProjectDto project = projectService.getprojectById(projectId);
		return new ResponseDto(200,"Success", project);
	}
	@DeleteMapping("/deleteProject/{projectId}")
	@ResponseBody
	public ResponseDto deleteProjectById(@PathVariable Long projectId){
		projectService.deleteProjectById(projectId);
		return new ResponseDto(200, "Deleted !", "");
	}
	
	@PostMapping("/assignEmployee")
	@ResponseBody
	public ResponseEntity<String> assignEmployee(@RequestParam Long empId,@RequestParam Long projectId){
		projectService.assignProject(empId, projectId);
		return ResponseEntity.ok("Assign SuccessFully!");
	}

}

