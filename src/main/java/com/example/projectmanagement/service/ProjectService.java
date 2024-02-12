package com.example.projectmanagement.service;

import java.util.List;

import com.example.projectmanagement.dto.EmployeeDto;
import com.example.projectmanagement.dto.ProjectDto;
import com.example.projectmanagement.model.Project;

public interface ProjectService {
	
	List<ProjectDto> fetchAll();
	
	void addProjects(ProjectDto projectDto);
	
	void deleteProjectById(Long projectId);
	
	void assignProject(Long empId,Long projectId);

	ProjectDto getprojectById(Long projectId);
}
