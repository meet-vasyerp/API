package com.example.projectmanagement.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.projectmanagement.dto.ResponseDto;
import com.example.projectmanagement.exception.EmployeeNotFoundException;
import com.example.projectmanagement.exception.ProjectNotFound;


@RestControllerAdvice
public class CustomExceptionHandler {
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler
	public ResponseDto employeeNotFoundExceptionHandler(EmployeeNotFoundException en) {
		return new ResponseDto(404, "Error", en.getMessage());
	}

	public ResponseDto projectNotFoundExceptionHandler(ProjectNotFound pn) {
		return new ResponseDto(404, "Error", pn.getMessage());  
	}
}
