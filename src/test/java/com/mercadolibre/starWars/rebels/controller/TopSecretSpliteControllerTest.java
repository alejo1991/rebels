package com.mercadolibre.starWars.rebels.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Objects;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.mercadolibre.starWars.rebels.exception.RebelsBodyArgumentValidationException;
import com.mercadolibre.starWars.rebels.exception.RebelsUnableToDecodeException;
import com.mercadolibre.starWars.rebels.helper.TestObjectsHelper;
import com.mercadolibre.starWars.rebels.service.TopSecretSplitService;
import com.mercadolibre.starWars.rebels.validator.interf.ISatelliteRequestValidator;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TopSecretSpliteControllerTest {
	
	@InjectMocks
	private TopSecretSplitController controller;
	
	@Mock
	private ISatelliteRequestValidator validator;
	
	@Mock
	private TopSecretSplitService service;
	
	@Test
	@DisplayName("Test scenario when response status is 200 for saving")
	public void validateSaveSuccessfullResponse() throws RebelsUnableToDecodeException, IllegalArgumentException, Exception {
		when(validator.validateRequest(any())).thenReturn(TestObjectsHelper.getSatelliteBo());
		when(service.saveSatelliteTransmition(any())).thenReturn("success");
		
		ResponseEntity<?> responseEntity = controller.saveTrasmittedMessage("kenobi", TestObjectsHelper.getSatelliteRequestOk());
		
		assertEquals(responseEntity.getStatusCodeValue(), 200);
		assertTrue(Objects.nonNull(responseEntity.getBody()));
	}
	
	@Test
	@DisplayName("Test scenario when Exception is thrown for saving")
	public void validateSaveErrorResponse() throws RebelsUnableToDecodeException, IllegalArgumentException, Exception {
		when(validator.validateRequest(any())).thenReturn(TestObjectsHelper.getSatelliteBo());
		when(service.saveSatelliteTransmition(any())).thenThrow(Exception.class);
		
		assertThrows(Exception.class, () -> controller.saveTrasmittedMessage("kenobi", TestObjectsHelper.getSatelliteRequestOk()));
	}
	
	@Test
	@DisplayName("Test scenario when validation fails for saving")
	public void validateSaveErrorResponseDueValidation() throws RebelsUnableToDecodeException, IllegalArgumentException, Exception {
		when(validator.validateRequest(any())).thenThrow(RebelsBodyArgumentValidationException.class);
		
		assertThrows(RebelsBodyArgumentValidationException.class, () -> controller.saveTrasmittedMessage("kenobi", TestObjectsHelper.getSatelliteRequestOk()));
	}
	
	@Test
	@DisplayName("Test scenario when response status is 200 for getting")
	public void validateGetSuccessfullResponse() throws IllegalArgumentException, Exception {
		when(validator.validate(anyString())).thenReturn(TestObjectsHelper.getSatelliteBo());
		when(service.getRevealedMessage(any())).thenReturn(TestObjectsHelper.getMockedSuccessfullResponse());
		
		ResponseEntity<?> responseEntity = controller.getLatestSatelliteMessage("kenobi");
		
		assertEquals(responseEntity.getStatusCodeValue(), 200);
		assertTrue(Objects.nonNull(responseEntity.getBody()));
	}
	
	@Test
	@DisplayName("Test scenario when Exception is thrown for getting")
	public void validateGetErrorResponse() throws IllegalArgumentException, Exception {
		when(validator.validate(anyString())).thenReturn(TestObjectsHelper.getSatelliteBo());
		when(service.getRevealedMessage(any())).thenThrow(Exception.class);
		
		assertThrows(Exception.class, () -> controller.getLatestSatelliteMessage("kenobi"));
	}
	
	@Test
	@DisplayName("Test scenario when validation fails for getting")
	public void validateGetErrorResponseDueValidation() throws IllegalArgumentException, Exception {
		when(validator.validate(anyString())).thenThrow(ConstraintViolationException.class);
		
		assertThrows(ConstraintViolationException.class, () -> controller.getLatestSatelliteMessage("kenobi"));
	}
	
	@Test
	@DisplayName("Test scenario when response status is 404 due to RebelsUnableToDecodeException")
	public void validateErrorResponseNotDecoded() throws RebelsUnableToDecodeException, IllegalArgumentException, Exception {
		when(validator.validate(anyString())).thenReturn(TestObjectsHelper.getSatelliteBo());
		when(service.getRevealedMessage(any())).thenThrow(RebelsUnableToDecodeException.class);
		
		ResponseEntity<?> responseEntity = controller.getLatestSatelliteMessage("kenobi");
		
		assertEquals(responseEntity.getStatusCodeValue(), 404);
	}
	
	@Test
	@DisplayName("Test scenario when response status is 404 due to IllegalArgumentException")
	public void validateErrorResponseNotDecodedPosition() throws RebelsUnableToDecodeException, IllegalArgumentException, Exception {
		when(validator.validate(anyString())).thenReturn(TestObjectsHelper.getSatelliteBo());
		when(service.getRevealedMessage(any())).thenThrow(IllegalArgumentException.class);
		
		ResponseEntity<?> responseEntity = controller.getLatestSatelliteMessage("kenobi");
		
		assertEquals(responseEntity.getStatusCodeValue(), 404);
	}


}
