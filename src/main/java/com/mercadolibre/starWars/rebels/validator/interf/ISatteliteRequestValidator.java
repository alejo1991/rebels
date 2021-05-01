package com.mercadolibre.starWars.rebels.validator.interf;

import com.mercadolibre.starWars.rebels.dto.request.SatteliteInformationRequestDTO;
import com.mercadolibre.starWars.rebels.exception.RebelsBodyArgumentValidationException;

public interface ISatteliteRequestValidator {
	
	Object validate(final SatteliteInformationRequestDTO request) throws RebelsBodyArgumentValidationException;

}
