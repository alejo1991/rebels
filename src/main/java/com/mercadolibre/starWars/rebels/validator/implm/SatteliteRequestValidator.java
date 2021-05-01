package com.mercadolibre.starWars.rebels.validator.implm;

import org.springframework.stereotype.Service;

import com.mercadolibre.starWars.rebels.dto.request.SatteliteInformationRequestDTO;
import com.mercadolibre.starWars.rebels.exception.RebelsBodyArgumentValidationException;
import com.mercadolibre.starWars.rebels.validator.interf.ISatteliteRequestValidator;

@Service
public class SatteliteRequestValidator implements ISatteliteRequestValidator {

	@Override
	public Object validate(SatteliteInformationRequestDTO request) throws RebelsBodyArgumentValidationException {
		// TODO Auto-generated method stub
		return null;
	}

}
