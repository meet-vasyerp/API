package com.example.projectmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projectmanagement.model.Project;

public interface ProjectRepo extends JpaRepository<Project, Long>{

}
