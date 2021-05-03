package com.mercadolibre.starWars.rebels.validator.implm;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.mercadolibre.starWars.rebels.domain.Satellite;
import com.mercadolibre.starWars.rebels.domain.bo.SatelliteBO;
import com.mercadolibre.starWars.rebels.dto.request.SatelliteRequestDTO;
import com.mercadolibre.starWars.rebels.dto.response.ValidationErrorDTO;
import com.mercadolibre.starWars.rebels.enums.EventCodeEnum;
import com.mercadolibre.starWars.rebels.enums.FindSatelliteCriteriaEnum;
import com.mercadolibre.starWars.rebels.exception.RebelsBodyArgumentValidationException;
import com.mercadolibre.starWars.rebels.mapper.ISatelliteEntityToBoMapper;
import com.mercadolibre.starWars.rebels.repository.ISatelliteRepository;
import com.mercadolibre.starWars.rebels.util.RebelUtils;
import com.mercadolibre.starWars.rebels.validator.interf.ISatelliteRequestValidator;

import lombok.Getter;

@Service
public class SatelliteRequestValidator extends BaseValidator implements ISatelliteRequestValidator {
	
	@Autowired
	private ISatelliteRepository satelliteRepository;
	
	@Getter
	private List<ValidationErrorDTO> errorList;
	
	@Getter
	private Set<ValidationErrorDTO> errorSet;
	
	@Autowired
	private ISatelliteEntityToBoMapper mapper;


	@Override
	public SatelliteBO validate(SatelliteRequestDTO request) throws RebelsBodyArgumentValidationException {
		errorList = new LinkedList<>();
		
		SatelliteBO registeredSatellite = validateIfSatelliteExist(request, FindSatelliteCriteriaEnum.LATEST_POSITION);
		
		if(!CollectionUtils.isEmpty(errorList)) {
			throw new RebelsBodyArgumentValidationException(errorList, null, getBindingResult(errorList));
		}
		
		return registeredSatellite;
	}
	
	@Override
	public SatelliteBO validate(String satelliteName) throws ConstraintViolationException {
		errorSet = new LinkedHashSet<>();
		
		SatelliteRequestDTO request = SatelliteRequestDTO.builder().name(satelliteName).build();
		SatelliteBO registeredSatellite = validateIfSatelliteExist(request, FindSatelliteCriteriaEnum.LATEST_MESSAGE);
		
		if(!CollectionUtils.isEmpty(errorList)) {
			throw new ConstraintViolationException(errorSet);
		}	
		
		return registeredSatellite;
	}
	
	private SatelliteBO validateIfSatelliteExist(SatelliteRequestDTO request, FindSatelliteCriteriaEnum findCriteria) {
		
		Optional<Satellite> registeredSatellite = findSatelliteInfo(findCriteria, request.getName());
		
		if(!registeredSatellite.isPresent()) {
			errorList.add(ValidationErrorDTO.builder()
					.message(RebelUtils.getFormattedMessage(EventCodeEnum.SatelliteNotBelongResistanceWarning.getDescription(), 
							Arrays.asList(request.getName()))).build());
		} else {
			return mapper.fromEntityToBO(registeredSatellite.get());
		}
		
		return null;
	}
	
	private Optional<Satellite> findSatelliteInfo(FindSatelliteCriteriaEnum findCriteria, String satelliteName) {
		switch(findCriteria) {
			case LATEST_MESSAGE:
				return satelliteRepository.getSatelliteLatestPositionAndMessage(satelliteName);
			case LATEST_POSITION:
				return satelliteRepository.getSatelliteWithLatestPosition(satelliteName);
			default:
				return Optional.empty();
		}
	}

}
