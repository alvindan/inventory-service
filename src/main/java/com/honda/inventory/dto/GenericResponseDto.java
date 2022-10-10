package com.honda.inventory.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(content = Include.NON_NULL)
public class GenericResponseDto<T> {
	
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	
	private final String status;
	private final T data;
	
}
