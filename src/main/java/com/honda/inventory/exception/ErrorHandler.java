package com.honda.inventory.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.honda.inventory.dto.GenericResponseDto;

import lombok.extern.slf4j.Slf4j;

import static com.honda.inventory.dto.GenericResponseDto.ERROR;




@Slf4j
@RestControllerAdvice
public class ErrorHandler {
	
	 @ExceptionHandler(ServiceException.class)
	 public ResponseEntity<GenericResponseDto<Map<String,Object>>> handleServiceException(ServiceException ex) {
		 
		 log.error("Service Exception: {}", ex.getMessage());
		 var errorMap = new LinkedHashMap<String,Object>();
		 errorMap.put("code", ex.getCode());
		 errorMap.put("message", ex.getMessage());
		 GenericResponseDto<Map<String, Object>> responseDto = GenericResponseDto.<Map<String,Object>>builder()
				 .data(errorMap)
				 .status(ERROR)
				 .build();
		 return ResponseEntity.status(HttpStatus.valueOf(ex.getCode())).body(responseDto);
	 }
	 
	 @ExceptionHandler(Exception.class)
	 public ResponseEntity<GenericResponseDto<Map<String,Object>>> handleException(Exception ex) {
		 
		 log.error("Exception: {}", ex.getMessage());
		 var errorMap = new LinkedHashMap<String,Object>();
		 errorMap.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
		 errorMap.put("message", "Unexpected error occured!");
		 
		 GenericResponseDto<Map<String, Object>> responseDto = GenericResponseDto.<Map<String,Object>>builder()
				 .data(errorMap)
				 .status(ERROR)
				 .build();
		 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
	 }

}
