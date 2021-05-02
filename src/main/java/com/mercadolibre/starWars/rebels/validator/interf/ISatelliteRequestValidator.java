package com.mercadolibre.starWars.rebels.validator.interf;

import com.mercadolibre.starWars.rebels.dto.request.SatelliteInformationRequestDTO;
import com.mercadolibre.starWars.rebels.exception.RebelsBodyArgumentValidationException;

public interface ISatelliteRequestValidator {
	
	Object validate(final SatelliteInformationRequestDTO request) throws RebelsBodyArgumentValidationException;

}
