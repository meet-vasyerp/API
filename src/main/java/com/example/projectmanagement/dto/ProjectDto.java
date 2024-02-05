package com.example.projectmanagement.dto;
//import javax.persistence.OneToOne;

import com.example.projectmanagement.model.Employee;

//import com.example.projectmanagement.model.Employee;

import lombok.Data;

@Data
public class ProjectDto {

	private Long projectId;
	private String projectName;
	private String projectDes;

	private Employee employee;
}
