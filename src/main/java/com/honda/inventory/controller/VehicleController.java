package com.honda.inventory.controller; 

import static com.honda.inventory.dto.GenericResponseDto.SUCCESS;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.honda.inventory.dto.GenericResponseDto;
import com.honda.inventory.dto.VehicleDto;
import com.honda.inventory.dto.VehicleRequestDto;
import com.honda.inventory.service.VehicleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor
public class VehicleController {
	
	private final VehicleService service;
	
	@PostMapping
	public ResponseEntity<GenericResponseDto<VehicleDto>> create(@RequestBody VehicleRequestDto requestDto) {
		
		var vehicle = service.create(requestDto);
		
		var responseDto = GenericResponseDto.<VehicleDto>builder()
								.data(VehicleDto.parse(vehicle))
								.status(SUCCESS)
								.build();
		
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
	}
	
	@PutMapping("/inventory-code/{inventoryCode}")
	public ResponseEntity<GenericResponseDto<VehicleDto>> create(
			@PathVariable String inventoryCode,
			@RequestBody VehicleRequestDto requestDto) {
		
		var vehicle = service.update(inventoryCode,requestDto);
		
		var responseDto = GenericResponseDto.<VehicleDto>builder()
								.data(VehicleDto.parse(vehicle))
								.status(SUCCESS)
								.build();
		
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}
	
	
}
