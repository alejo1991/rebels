package com.mercadolibre.starWars.rebels.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mercadolibre.starWars.rebels.domain.Satellite;
import com.mercadolibre.starWars.rebels.domain.SatellitePositionHistory;

@Repository
public interface ISatellitePositionHistoryRepository extends CrudRepository<SatellitePositionHistory, Long> {
	
	Optional<SatellitePositionHistory> findBySatellite(Satellite satellite);

}
