package com.mercadolibre.starWars.rebels.domain.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SatelliteMessageTrackingBO {
	
	private Long id;
	private SatellitePositionHistoryBO satellitePosition;
	private String splittedMessage;
	private Float distance;

}
