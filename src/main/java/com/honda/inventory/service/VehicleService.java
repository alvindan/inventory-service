package com.honda.inventory.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.honda.inventory.dto.VehicleRequestDto;
import com.honda.inventory.entity.Vehicle;
import com.honda.inventory.exception.ServiceException;
import com.honda.inventory.repository.VehicleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleService {
	
	private final VehicleRepository repository;

	public Vehicle create(final VehicleRequestDto requestDto) {
		
		if(StringUtils.isBlank(requestDto.getInventoryCode())) {
			throw ServiceException.badRequest("Inventory Code is required!");
		}
		
		if(this.repository.existsByInventoryCode(requestDto.getInventoryCode())) {
			throw ServiceException.badRequest("Vehicle with Inventory Code="+requestDto.getInventoryCode()+ " already exist!");
		}
		
		final Vehicle vehicle = Vehicle.parse(requestDto);
		return repository.save(vehicle);
	}
	
	
	
}
