package com.mercadolibre.starWars.rebels.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import com.mercadolibre.starWars.rebels.domain.Satellite;
import com.mercadolibre.starWars.rebels.domain.bo.SatelliteBO;
import com.mercadolibre.starWars.rebels.exception.RebelsBodyArgumentValidationException;
import com.mercadolibre.starWars.rebels.helper.TestObjectsHelper;
import com.mercadolibre.starWars.rebels.mapper.ISatelliteEntityToBoMapper;
import com.mercadolibre.starWars.rebels.repository.ISatelliteRepository;
import com.mercadolibre.starWars.rebels.validator.implm.SatellitesInfoValidator;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SalitellitesInfoValidatorTest {
	
	@InjectMocks
	private SatellitesInfoValidator validator;
	
	@Mock
	private ISatelliteRepository mockedSatelliteRepo;
	
	@Mock
	private ISatelliteEntityToBoMapper mapper;
	
	@Test
	@DisplayName("Test to check that not errors will be thrown when all satellites exists")
	public void validateAttemptSaveMessage() throws RebelsBodyArgumentValidationException {
		
		when(mockedSatelliteRepo.getSatelliteWithLatestPosition(anyString())).thenReturn(Optional.ofNullable(new Satellite()));
		validator.validate(TestObjectsHelper.getSatellitesRequestOk());
		
		assertTrue(CollectionUtils.isEmpty(validator.getErrorList()));
	}
	
	@Test
	@DisplayName("Test to check that validate method return BO when all satellites exists")
	public void validateReturnBo() throws RebelsBodyArgumentValidationException {
		
		when(mockedSatelliteRepo.getSatelliteWithLatestPosition(anyString())).thenReturn(Optional.ofNullable(new Satellite()));
		when(mapper.fromEntityListToBOList(any())).thenReturn(TestObjectsHelper.getSatellitesBoOk());
		List<SatelliteBO> response = validator.validate(TestObjectsHelper.getSatellitesRequestOk());
		
		assertFalse(CollectionUtils.isEmpty(response));
	}
	
	@Test
	@DisplayName("Test to check that errors will be thrown when at least one satellite does not exist")
	public void validateSatelliteExistanceError() {
		
		when(mockedSatelliteRepo.getSatelliteWithLatestPosition(anyString())).thenReturn(Optional.ofNullable(null));
		assertThrows(RebelsBodyArgumentValidationException.class, () -> validator.validate(TestObjectsHelper.getSatellitesRequestOk()));
	}
	
	@Test
	@DisplayName("Test to check that errors list wont be empty when at least one satellite does not exist")
	public void validateErrorListNotEmpty() {
		
		when(mockedSatelliteRepo.getSatelliteWithLatestPosition(anyString())).thenReturn(Optional.ofNullable(null));
		try {
			validator.validate(TestObjectsHelper.getSatellitesRequestOk());
		} catch (RebelsBodyArgumentValidationException e) {
		}
		
		assertFalse(CollectionUtils.isEmpty(validator.getErrorList()));
	}

}
