package com.example.projectmanagement.dto;

import lombok.Data;

@Data
public class ResponseDto {
	private int status;
	private String message;
	private Object data;
	public ResponseDto(int status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
}
