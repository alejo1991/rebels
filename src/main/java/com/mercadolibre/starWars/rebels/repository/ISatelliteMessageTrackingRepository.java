package com.mercadolibre.starWars.rebels.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mercadolibre.starWars.rebels.domain.SatelliteMessageTracking;

@Repository
public interface ISatelliteMessageTrackingRepository extends CrudRepository<SatelliteMessageTracking, Long> {
	
}
