package com.honda.inventory.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.honda.inventory.dto.VehicleRequestDto;
import com.honda.inventory.entity.Vehicle;
import com.honda.inventory.exception.ServiceException;
import com.honda.inventory.repository.VehicleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
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
	
	public Vehicle update(final String inventoryCode, final VehicleRequestDto requestDto) {
		final Vehicle vehicle = repository.findByInventoryCode(inventoryCode)
				.orElseThrow(() -> ServiceException.badRequest("Vehicle with Inventory Code="+ inventoryCode + " does not exist!"));
		
		vehicle.setName(requestDto.getName());
		vehicle.setModel(requestDto.getModel());
		vehicle.setColor(requestDto.getColor());
		
		return repository.save(vehicle);
	}
	
	/**
	 * Queries paginated data based on the parameters supplied.
	 * 
	 * @param sortProperties sort properties
	 * @param sortDir values are ASC or DESC
	 * @param pageIndex starts at 0
	 * @param pageSize desired number of elements per page
	 * @return  {@link Page}
	 */
	public Page<Vehicle> getPaginatedResults(String[] sortProperties, String sortDir, int pageIndex, int pageSize) {
		return repository.findAll(
				PageRequest.of(pageIndex, pageSize, Sort.Direction.valueOf(sortDir), sortProperties)
				);
	}
	
	public void delete(Long id) {
		log.debug("Deleting vehicle with id={}.", id);
		this.repository.findById(id)
		.ifPresentOrElse(
				repository::delete, 
				() -> {
					throw ServiceException.badRequest("Vehicle with id="+id+" does not exist!");
				});
	}
	
	
	
}
