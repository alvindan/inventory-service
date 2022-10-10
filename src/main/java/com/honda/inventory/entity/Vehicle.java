package com.honda.inventory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.honda.inventory.dto.VehicleRequestDto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@EqualsAndHashCode.Include
	@Column(name="INVENTORY_CODE", unique = true, updatable = false, nullable = false)
	private String inventoryCode;
	
	private String name;
	private String model;
	private String color;
	
	public static Vehicle parse(VehicleRequestDto requestDto) {
		
		var vehicle = new Vehicle();
		vehicle.setInventoryCode(requestDto.getInventoryCode());
		vehicle.setName(requestDto.getName());
		vehicle.setModel(requestDto.getModel());
		vehicle.setColor(requestDto.getColor());
		
		return vehicle;
	}
	
	
}
