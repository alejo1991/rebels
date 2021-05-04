package com.mercadolibre.starWars.rebels.validator.implm;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Objects;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

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
import com.mercadolibre.starWars.rebels.validator.implm.SatelliteRequestValidator;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SatelliteRequestValidatorTest {
	
	@InjectMocks
	private SatelliteRequestValidator validator;
	
	@Mock
	private ISatelliteRepository mockedSatelliteRepo;
	
	@Mock
	private ISatelliteEntityToBoMapper mapper;
	
	@Test
	@DisplayName("Test to check that not errors will be thrown when satellite exist for post request")
	public void validateAttemptSaveMessagePost() throws RebelsBodyArgumentValidationException {
		
		when(mockedSatelliteRepo.getSatelliteWithLatestPosition(anyString())).thenReturn(Optional.ofNullable(new Satellite()));
		validator.validateRequest(TestObjectsHelper.getSatelliteRequestOk());
		
		assertTrue(CollectionUtils.isEmpty(validator.getErrorList()));
	}
	
	@Test
	@DisplayName("Test to check that validate method return BO when satellite exist for post request")
	public void validateReturnBoPost() throws RebelsBodyArgumentValidationException {
		
		when(mockedSatelliteRepo.getSatelliteWithLatestPosition(anyString())).thenReturn(Optional.ofNullable(new Satellite()));
		when(mapper.fromEntityToBO(any())).thenReturn(TestObjectsHelper.getSatelliteBoNameOk());
		SatelliteBO response = validator.validateRequest(TestObjectsHelper.getSatelliteRequestOk());
		
		assertTrue(Objects.nonNull(response));
	}
	
	@Test
	@DisplayName("Test to check that errors will be thrown when incoming message is not meaningful")
	public void validateErrorNotMeaningfulMessagePost() throws RebelsBodyArgumentValidationException {
		
		when(mockedSatelliteRepo.getSatelliteWithLatestPosition(anyString())).thenReturn(Optional.ofNullable(new Satellite()));
		when(mapper.fromEntityToBO(any())).thenReturn(TestObjectsHelper.getSatelliteBoNameOk());
		
		assertThrows(RebelsBodyArgumentValidationException.class, () -> validator.validateRequest(TestObjectsHelper.getSatelliteRequestNotMeaningfulMessage()));
	}
	
	@Test
	@DisplayName("Test to check that errors will be thrown when satellite not exist for post request")
	public void validateSatelliteExistanceErrorPost() {
		
		when(mockedSatelliteRepo.getSatelliteWithLatestPosition(anyString())).thenReturn(Optional.ofNullable(null));
		assertThrows(RebelsBodyArgumentValidationException.class, () -> validator.validateRequest(TestObjectsHelper.getSatelliteRequestOk()));
	}
	
	@Test
	@DisplayName("Test to check that errors list wont be empty when satellite not exist for post request")
	public void validateErrorListNotEmptyPostRequestError() {
		
		when(mockedSatelliteRepo.getSatelliteWithLatestPosition(anyString())).thenReturn(Optional.ofNullable(null));
		try {
			validator.validateRequest(TestObjectsHelper.getSatelliteRequestBadName());
		} catch (RebelsBodyArgumentValidationException e) {
		}
		
		assertFalse(CollectionUtils.isEmpty(validator.getErrorList()));
	}
	
	@Test
	@DisplayName("Test to check that not errors will be thrown when satellite exist for get request")
	public void validateAttemptGetLatestMessage() throws ConstraintViolationException {
		
		when(mockedSatelliteRepo.getSatelliteLatestPositionAndMessage(anyString())).thenReturn(Optional.ofNullable(new Satellite()));
		validator.validate("kenobi");
		
		assertTrue(CollectionUtils.isEmpty(validator.getErrorSet()));
	}
	
	@Test
	@DisplayName("Test to check that validate method return BO when satellite exist for get request")
	public void validateReturnBoGet() throws RebelsBodyArgumentValidationException {
		
		when(mockedSatelliteRepo.getSatelliteLatestPositionAndMessage(anyString())).thenReturn(Optional.ofNullable(new Satellite()));
		when(mapper.fromEntityToBO(any())).thenReturn(TestObjectsHelper.getSatelliteBoNameOk());
		SatelliteBO response = validator.validate("kenobi");
		
		assertTrue(Objects.nonNull(response));
	}
	
	@Test
	@DisplayName("Test to check that errors will be thrown when satellite not exist for get request")
	public void validateSatelliteExistanceErrorGet() {
		
		when(mockedSatelliteRepo.getSatelliteLatestPositionAndMessage(anyString())).thenReturn(Optional.ofNullable(null));
		assertThrows(ConstraintViolationException.class, () -> validator.validate("vader"));
	}
	
	@Test
	@DisplayName("Test to check that errors set wont be empty when satellite not exist for get request")
	public void validateErrorSetNotEmptyGetRequestError() {
		
		when(mockedSatelliteRepo.getSatelliteLatestPositionAndMessage(anyString())).thenReturn(Optional.ofNullable(null));
		try {
			validator.validate("vader");
		} catch (ConstraintViolationException e) {
		}
		
		assertFalse(CollectionUtils.isEmpty(validator.getErrorSet()));
	}

}
