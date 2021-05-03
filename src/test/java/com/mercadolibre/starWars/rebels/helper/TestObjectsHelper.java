package com.mercadolibre.starWars.rebels.helper;

import com.mercadolibre.starWars.rebels.domain.bo.SatelliteBO;
import com.mercadolibre.starWars.rebels.dto.request.SatelliteRequestDTO;

public class TestObjectsHelper {
	
	public static SatelliteRequestDTO getSatelliteRequestOk() {
		
		String[] message = {"este", "", "", "", "auxilio"};
		
		return SatelliteRequestDTO.builder()
			.distance(100f)
			.message(message)
			.name("kenobi")
			.build();
	}
	
	public static SatelliteRequestDTO getSatelliteRequestBadName() {
		
		String[] message = {"este", "", "", "", "auxilio"};
		
		return SatelliteRequestDTO.builder()
			.distance(100f)
			.message(message)
			.name("vader")
			.build();
	}
	
	public static SatelliteBO getSatelliteBoNameOk() {
		return SatelliteBO.builder()
			.name("kenobi")
			.build();
	}

}
