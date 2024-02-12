package com.example.projectmanagement.exception;

import lombok.Data;

public class EmployeeNotFoundException extends RuntimeException{
	
	public EmployeeNotFoundException() {
		super();
	}
	public EmployeeNotFoundException(String message) {
		super(message);
	}
	
}
