package com.mercadolibre.starWars.rebels.validator.interf;

import com.mercadolibre.starWars.rebels.dto.request.SattelitesRequestDTO;
import com.mercadolibre.starWars.rebels.exception.RebelsBodyArgumentValidationException;

public interface ISattelitesInfoValidator {
	
	Object validate(final SattelitesRequestDTO request) throws RebelsBodyArgumentValidationException;

}
