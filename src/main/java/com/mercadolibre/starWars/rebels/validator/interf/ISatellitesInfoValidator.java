package com.mercadolibre.starWars.rebels.validator.interf;

import java.util.List;

import com.mercadolibre.starWars.rebels.domain.bo.SatelliteBO;
import com.mercadolibre.starWars.rebels.dto.request.SatellitesRequestDTO;
import com.mercadolibre.starWars.rebels.exception.RebelsBodyArgumentValidationException;

public interface ISatellitesInfoValidator {
	
	List<SatelliteBO> validate(final SatellitesRequestDTO request) throws RebelsBodyArgumentValidationException;

}
