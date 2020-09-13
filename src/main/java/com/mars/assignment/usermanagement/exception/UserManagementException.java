package com.mars.assignment.usermanagement.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class UserManagementException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String description;
	private final String reason;
	private final HttpStatus httpStatus;

	public UserManagementException(String description, String reason, HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
		this.reason = reason;
		this.description = description;
	}
}
