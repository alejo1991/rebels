package com.mercadolibre.starWars.rebels.domain.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SatellitePositionHistoryBO {
	
	private Long id;
	private Float positionX;
	private Float positionY;

}
