package com.honda.inventory.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class ServiceException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	private final int code;
	
	public ServiceException(int code,String message) {
        super(message);
        this.code = code;
    }
	
	public static ServiceException badRequest(String message) {
		return new ServiceException(HttpStatus.BAD_REQUEST.value(), message);
	}

}
