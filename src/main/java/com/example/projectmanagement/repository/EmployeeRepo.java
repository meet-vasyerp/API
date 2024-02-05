package com.example.projectmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projectmanagement.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee,Long>{

}
