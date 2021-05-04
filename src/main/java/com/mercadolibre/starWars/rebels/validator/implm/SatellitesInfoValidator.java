package com.mercadolibre.starWars.rebels.validator.implm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.mercadolibre.starWars.rebels.domain.Satellite;
import com.mercadolibre.starWars.rebels.domain.bo.SatelliteBO;
import com.mercadolibre.starWars.rebels.dto.request.SatelliteRequestDTO;
import com.mercadolibre.starWars.rebels.dto.request.SatellitesRequestDTO;
import com.mercadolibre.starWars.rebels.dto.response.ValidationErrorDTO;
import com.mercadolibre.starWars.rebels.enums.EventCodeEnum;
import com.mercadolibre.starWars.rebels.exception.RebelsBodyArgumentValidationException;
import com.mercadolibre.starWars.rebels.mapper.ISatelliteEntityToBoMapper;
import com.mercadolibre.starWars.rebels.repository.ISatelliteRepository;
import com.mercadolibre.starWars.rebels.util.RebelUtils;
import com.mercadolibre.starWars.rebels.validator.interf.ISatellitesInfoValidator;

import lombok.Getter;

@Service
public class SatellitesInfoValidator extends BaseValidator implements ISatellitesInfoValidator {
	
	@Autowired
	private ISatelliteRepository satelliteRepository;
	
	@Getter
	private List<ValidationErrorDTO> errorList;
	
	@Autowired
	private ISatelliteEntityToBoMapper mapper;

	@Override
	public List<SatelliteBO> validate(SatellitesRequestDTO request) throws RebelsBodyArgumentValidationException {
		errorList = new LinkedList<>();
		
		List<SatelliteBO> registeredSatelliteList = validateIfSatelliteExist(request);
		
		if(!CollectionUtils.isEmpty(errorList)) {
			throw new RebelsBodyArgumentValidationException(errorList, null, getEmptyBindingResult());
		}
		
		return registeredSatelliteList;
	}
	
	private List<SatelliteBO> validateIfSatelliteExist(SatellitesRequestDTO request) {
		
		List<Satellite> registeredSatelliteList = new LinkedList<>();
		
		for(SatelliteRequestDTO satellite: request.getSatellites()) {
			Optional<Satellite> registeredSatellite = satelliteRepository.getSatelliteWithLatestPosition(satellite.getName());
			if(!registeredSatellite.isPresent()) {
				errorList.add(ValidationErrorDTO.builder()
						.message(RebelUtils.getFormattedMessage(EventCodeEnum.SatelliteNotBelongResistanceWarning.getDescription(), 
								Arrays.asList(satellite.getName()))).build());
			} else {
				registeredSatelliteList.add(registeredSatellite.get());
			}
		}
		
		return mapper.fromEntityListToBOList(registeredSatelliteList);
	}

}
