package com.example.projectmanagement.exception;

public class ProjectNotFound extends RuntimeException{
	
	 public ProjectNotFound(){
		super();
	}
	
	public ProjectNotFound(String message){
		super(message);
	}

}
