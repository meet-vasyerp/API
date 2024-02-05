package com.example.projectmanagement.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="project_id")
	private Long projectId;
	@Column(name="project_name")
	private String projectName;
	@Column(name="project_dest")
	private String projectDes;
	
	@OneToOne
	private Employee employee;
	

}
