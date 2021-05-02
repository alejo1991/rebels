package com.mercadolibre.starWars.rebels.domain.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SatteliteMessageTrackingBO {
	
	private Long id;
	private SattelitePositionHistoryBO sattelitePosition;
	private String splittedMessage;
	private Float distance;

}
