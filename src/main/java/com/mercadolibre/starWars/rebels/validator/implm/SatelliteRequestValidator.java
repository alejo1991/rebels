package com.mercadolibre.starWars.rebels.validator.implm;

import org.springframework.stereotype.Service;

import com.mercadolibre.starWars.rebels.dto.request.SatelliteInformationRequestDTO;
import com.mercadolibre.starWars.rebels.exception.RebelsBodyArgumentValidationException;
import com.mercadolibre.starWars.rebels.validator.interf.ISatelliteRequestValidator;

@Service
public class SatelliteRequestValidator implements ISatelliteRequestValidator {

	@Override
	public Object validate(SatelliteInformationRequestDTO request) throws RebelsBodyArgumentValidationException {
		// TODO Auto-generated method stub
		return null;
	}

}
