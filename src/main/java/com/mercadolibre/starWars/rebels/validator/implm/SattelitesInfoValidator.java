package com.mercadolibre.starWars.rebels.validator.implm;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.mercadolibre.starWars.rebels.domain.Sattelite;
import com.mercadolibre.starWars.rebels.domain.bo.SatteliteBO;
import com.mercadolibre.starWars.rebels.dto.request.SatteliteRequestDTO;
import com.mercadolibre.starWars.rebels.dto.request.SattelitesRequestDTO;
import com.mercadolibre.starWars.rebels.dto.response.ValidationErrorDTO;
import com.mercadolibre.starWars.rebels.exception.RebelsBodyArgumentValidationException;
import com.mercadolibre.starWars.rebels.mapper.ISatteliteEntityToBoMapper;
import com.mercadolibre.starWars.rebels.repository.ISatteliteRepository;
import com.mercadolibre.starWars.rebels.validator.interf.ISattelitesInfoValidator;

import lombok.Getter;

@Service
public class SattelitesInfoValidator implements ISattelitesInfoValidator {
	
	@Autowired
	private ISatteliteRepository satteliteRepository;
	
	@Getter
	private List<ValidationErrorDTO> errorList;
	
	private ISatteliteEntityToBoMapper mapper;

	@Override
	public List<SatteliteBO> validate(SattelitesRequestDTO request) throws RebelsBodyArgumentValidationException {
		errorList = new LinkedList<>();
		
		List<SatteliteBO> registeredSatteliteList = validateIfSatteliteExist(request);
		
		if(!CollectionUtils.isEmpty(errorList)) {
			throw new RebelsBodyArgumentValidationException(errorList, null, null);
		}
		
		return registeredSatteliteList;
	}
	
	private List<SatteliteBO> validateIfSatteliteExist(SattelitesRequestDTO request) {
		
		List<Sattelite> registeredSatteliteList = new LinkedList<>();
		
		for(SatteliteRequestDTO sattelite: request.getSattelites()) {
			Optional<Sattelite> registeredSattelite = satteliteRepository.findByName(sattelite.getName());
			if(!registeredSattelite.isPresent()) {
				errorList.add(ValidationErrorDTO.builder().message("¡Warning! Sattelite: " + sattelite.getName() + " don´t belong to the resistence.").build());
			} else {
				registeredSatteliteList.add(registeredSattelite.get());
			}
		}
		
		return mapper.fromEntityListToBOList(registeredSatteliteList);
	}

}
