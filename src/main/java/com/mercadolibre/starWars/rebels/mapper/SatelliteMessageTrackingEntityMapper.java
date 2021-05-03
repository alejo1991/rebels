package com.mercadolibre.starWars.rebels.mapper;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mercadolibre.starWars.rebels.domain.Satellite;
import com.mercadolibre.starWars.rebels.domain.SatelliteMessageTracking;
import com.mercadolibre.starWars.rebels.domain.SatellitePositionHistory;
import com.mercadolibre.starWars.rebels.dto.request.SatelliteRequestDTO;
import com.mercadolibre.starWars.rebels.repository.ISatellitePositionHistoryRepository;
import com.mercadolibre.starWars.rebels.repository.ISatelliteRepository;

@Component
public class SatelliteMessageTrackingEntityMapper {
	
	public static final String SPLIT_CHAR = ",";
	
	@Autowired
	private ISatelliteRepository satelliteRepository;
	
	@Autowired
	private ISatellitePositionHistoryRepository positionRepository;
	
	public SatelliteMessageTracking getEntity(SatelliteRequestDTO request) {
		
		Optional<Satellite> satellite = satelliteRepository.findByName(request.getName());
		Optional<SatellitePositionHistory> position = positionRepository.findBySatellite(satellite.get());
		
		SatelliteMessageTracking messageTracking = new SatelliteMessageTracking();
		messageTracking.setDistance(request.getDistance());
		messageTracking.setSplittedMessage(String.join(SPLIT_CHAR, request.getMessage()));
		messageTracking.setSatellite(satellite.get());
		messageTracking.setSatellitePosition(position.get());
		
		return messageTracking;
	}

}
