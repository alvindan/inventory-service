package com.honda.inventory.dto;

import com.honda.inventory.entity.Vehicle;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VehicleDto {
	
	private final Long id;
	private final String name;
	private final String inventoryCode;
	private final String model;
	private final String color;
	
	public static VehicleDto parse(Vehicle entity) {
		
		return VehicleDto.builder()
				  .id(entity.getId())
				  .name(entity.getName())
				  .model(entity.getModel())
				  .color(entity.getColor())
				  .inventoryCode(entity.getInventoryCode())
				  .build();
	}

}
