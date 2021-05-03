package com.mercadolibre.starWars.rebels.enums;

import lombok.Getter;

@Getter
public enum EventCodeEnum {
	
	NotAbleToDecodeInformationWarning("001", "Sorry! not able to decode message and triangulate carrier position."),
	TransmitionSavedSuccessfully("002", "Transmition from satellite {0} saved successfully."),
	SatelliteNotBelongResistanceWarning("003", "¡Warning! satellite {0} don´t belong to the resistance.");
	
	private String eventCode;
	private String description;
	
	private EventCodeEnum(String eventCode, String description) {
		this.eventCode = eventCode;
		this.description = description;
	}

}
