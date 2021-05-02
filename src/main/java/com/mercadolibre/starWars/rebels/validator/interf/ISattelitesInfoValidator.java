package com.mercadolibre.starWars.rebels.validator.interf;

import java.util.List;

import com.mercadolibre.starWars.rebels.domain.bo.SatteliteBO;
import com.mercadolibre.starWars.rebels.dto.request.SattelitesRequestDTO;
import com.mercadolibre.starWars.rebels.exception.RebelsBodyArgumentValidationException;

public interface ISattelitesInfoValidator {
	
	List<SatteliteBO> validate(final SattelitesRequestDTO request) throws RebelsBodyArgumentValidationException;

}
