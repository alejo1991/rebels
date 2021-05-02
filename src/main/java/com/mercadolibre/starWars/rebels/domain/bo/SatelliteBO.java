package com.mercadolibre.starWars.rebels.domain.bo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SatelliteBO {
	
	private Long id;
	private String name;
	private List<SatelliteMessageTrackingBO> messageTrackingList;
	private List<SatellitePositionHistoryBO> positionHistoryList;

}
