package com.example.projectmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.projectmanagement.dto.EmployeeDto;
import com.example.projectmanagement.dto.ProjectDto;
import com.example.projectmanagement.model.Employee;
import com.example.projectmanagement.model.Project;
import com.example.projectmanagement.repository.EmployeeRepo;
import com.example.projectmanagement.repository.ProjectRepo;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	public ProjectRepo projectRepo;
	
	@Autowired
	public EmployeeRepo employeeRepo;
	

	@Override
	public List<ProjectDto> fetchAll() {

		List<Project> projects = projectRepo.findAll();
		List<ProjectDto> projectDto = new ArrayList<>();
		for (Project project : projects) {
			ProjectDto projectdto = new ProjectDto();
			projectdto.setProjectId(project.getProjectId());
			projectdto.setProjectName(project.getProjectName());
			projectdto.setProjectDes(project.getProjectDes());
			projectdto.setEmployee(project.getEmployee());
			projectDto.add(projectdto);
		}
		return projectDto;
	}

	@Override
	public void addProjects(ProjectDto projectDto) {
		Project project;
		if (projectDto.getProjectId() == null) {
			project = new Project();
		} else {
			project = projectRepo.findById(projectDto.getProjectId()).get();
		}
		project.setProjectId(projectDto.getProjectId());
		project.setProjectName(projectDto.getProjectName());
		project.setProjectDes(projectDto.getProjectDes());
		projectRepo.save(project);
	}
	
	@Override
	public void deleteProjectById(Long projectId) {
		projectRepo.deleteById(projectId);
	}

	@Override
	public void assignProject(Long empId, Long projectId) {
		Project existingProject = projectRepo.findById(projectId).get();
		Employee employee =	 employeeRepo.findById(empId).get();
		if(existingProject != null) {
			existingProject.setEmployee(employee);
			projectRepo.save(existingProject);
		}
	}
}
