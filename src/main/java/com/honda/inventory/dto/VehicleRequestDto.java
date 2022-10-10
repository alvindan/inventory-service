package com.honda.inventory.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class VehicleRequestDto {
	private final String name;
	private final String inventoryCode;
	private final String model;
	private final String color;

}
