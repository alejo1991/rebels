package com.mercadolibre.starWars.rebels.enums;

import lombok.Getter;

@Getter
public enum EventCodeEnum {
	
	NotAbleToDecodeInformationWarning("001", "Sorry! not able to decode message and triangulate carrier position."),
	TransmissionSavedSuccessfully("002", "Transmission from satellite {0} saved successfully."),
	SatelliteNotBelongResistanceWarning("003", "¡Warning! satellite {0} don´t belong to the resistance."),
	TransmissionMessageNotMeaningful("004", "Transmission from satellite {0} hasn't meaningful message."),
	NotEnoughInfoForTriangulate("005", "Error traingulating carrier position, not enough info");
	
	private String eventCode;
	private String description;
	
	private EventCodeEnum(String eventCode, String description) {
		this.eventCode = eventCode;
		this.description = description;
	}

}
