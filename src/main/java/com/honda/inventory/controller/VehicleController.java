package com.honda.inventory.controller; 

import static com.honda.inventory.dto.GenericResponseDto.SUCCESS;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.honda.inventory.dto.GenericResponseDto;
import com.honda.inventory.dto.VehicleDto;
import com.honda.inventory.dto.VehicleRequestDto;
import com.honda.inventory.service.VehicleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor
@Slf4j
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
	
	@GetMapping("/pagination")
	public ResponseEntity<GenericResponseDto<Map<String, Object>>> getPaginatedResults(
			@RequestParam(name = "sortProperties", defaultValue = "id") String[] sortProperties,
			@RequestParam(name = "sorDir", defaultValue = "ASC") String sortDir,
			@RequestParam(name = "pageIndex", defaultValue = "0") Integer pageIndex,
			@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
			) {
		
		log.info("[Get Paginated List] sortProperties={}, sorDir={}, pageIndex={}, pageSize={}", sortProperties, sortDir, pageIndex, pageSize);
		
		var page = this.service.getPaginatedResults(sortProperties, sortDir, pageIndex, pageSize);
		
		var map = new HashMap<String, Object>();
		map.put("resultCount", page.getTotalPages());
		map.put("totalCount", page.getTotalElements());
		map.put("totalPages", page.getTotalPages());
		map.put("results", page.get().map(VehicleDto::parse).collect(Collectors.toUnmodifiableList()));
		
		var responseDto = GenericResponseDto.<Map<String, Object>>builder()
				.data(map)
				.status(SUCCESS)
				.build();
		
		return ResponseEntity.ok(responseDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<GenericResponseDto<Object>> delete(@PathVariable Long id) {
		this.service.delete(id);
		return ResponseEntity.ok(GenericResponseDto.builder().status(SUCCESS).build());
	}
	
}
