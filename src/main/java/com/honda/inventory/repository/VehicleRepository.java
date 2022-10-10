package com.honda.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.honda.inventory.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>{
	
	public boolean existsByInventoryCode(String inventoryCode);

}
