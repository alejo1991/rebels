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
public class SatteliteBO {
	
	private Long id;
	private String name;
	private List<SatteliteMessageTrackingBO> messageTrackingList;
	private List<SattelitePositionHistoryBO> positionHistoryList;

}
