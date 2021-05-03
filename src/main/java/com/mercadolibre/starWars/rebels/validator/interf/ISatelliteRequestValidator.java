package com.mercadolibre.starWars.rebels.validator.interf;

import javax.validation.ConstraintViolationException;

import com.mercadolibre.starWars.rebels.domain.bo.SatelliteBO;
import com.mercadolibre.starWars.rebels.dto.request.SatelliteRequestDTO;
import com.mercadolibre.starWars.rebels.exception.RebelsBodyArgumentValidationException;

public interface ISatelliteRequestValidator {
	
	SatelliteBO validate(final SatelliteRequestDTO request) throws RebelsBodyArgumentValidationException;
	
	SatelliteBO validate(final String satelliteName) throws ConstraintViolationException;

}
