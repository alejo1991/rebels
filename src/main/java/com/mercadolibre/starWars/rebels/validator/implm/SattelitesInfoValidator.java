package com.mercadolibre.starWars.rebels.validator.implm;

import org.springframework.stereotype.Service;

import com.mercadolibre.starWars.rebels.dto.request.SattelitesRequestDTO;
import com.mercadolibre.starWars.rebels.exception.RebelsBodyArgumentValidationException;
import com.mercadolibre.starWars.rebels.validator.interf.ISattelitesInfoValidator;

@Service
public class SattelitesInfoValidator implements ISattelitesInfoValidator {

	@Override
	public Object validate(SattelitesRequestDTO request) throws RebelsBodyArgumentValidationException {
		// TODO Auto-generated method stub
		return null;
	}

}
