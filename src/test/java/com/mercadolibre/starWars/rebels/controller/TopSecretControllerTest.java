package com.mercadolibre.starWars.rebels.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Objects;

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
import com.mercadolibre.starWars.rebels.service.TopSecretService;
import com.mercadolibre.starWars.rebels.validator.interf.ISatellitesInfoValidator;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TopSecretControllerTest {
	
	@InjectMocks
	private TopSecretController controller;
	
	@Mock
	private ISatellitesInfoValidator validator;
	
	@Mock
	private TopSecretService service;
	
	@Test
	@DisplayName("Test scenario when response status is 200")
	public void validateSuccessfullResponse() throws RebelsUnableToDecodeException, IllegalArgumentException, Exception {
		when(validator.validate(any())).thenReturn(TestObjectsHelper.getSatellitesBoCompleteOk());
		when(service.getRevealedMessage(any(), any())).thenReturn(TestObjectsHelper.getMockedSuccessfullResponse());
		
		ResponseEntity<?> responseEntity = controller.readTopSecret(TestObjectsHelper.getSatellitesRequestOk());
		
		assertEquals(responseEntity.getStatusCodeValue(), 200);
		assertTrue(Objects.nonNull(responseEntity.getBody()));
	}
	
	@Test
	@DisplayName("Test scenario when response status is 404 due to RebelsUnableToDecodeException")
	public void validateErrorResponseNotDecoded() throws RebelsUnableToDecodeException, IllegalArgumentException, Exception {
		when(validator.validate(any())).thenReturn(TestObjectsHelper.getSatellitesBoCompleteOk());
		when(service.getRevealedMessage(any(), any())).thenThrow(RebelsUnableToDecodeException.class);
		
		ResponseEntity<?> responseEntity = controller.readTopSecret(TestObjectsHelper.getSatellitesRequestOk());
		
		assertEquals(responseEntity.getStatusCodeValue(), 404);
	}
	
	@Test
	@DisplayName("Test scenario when response status is 404 due to IllegalArgumentException")
	public void validateErrorResponseNotDecodedLocation() throws RebelsUnableToDecodeException, IllegalArgumentException, Exception {
		when(validator.validate(any())).thenReturn(TestObjectsHelper.getSatellitesBoCompleteOk());
		when(service.getRevealedMessage(any(), any())).thenThrow(IllegalArgumentException.class);
		
		ResponseEntity<?> responseEntity = controller.readTopSecret(TestObjectsHelper.getSatellitesRequestOk());
		
		assertEquals(responseEntity.getStatusCodeValue(), 404);
	}
	
	@Test
	@DisplayName("Test scenario when Exception is thrown")
	public void validateErrorResponse() throws RebelsUnableToDecodeException, IllegalArgumentException, Exception {
		when(validator.validate(any())).thenReturn(TestObjectsHelper.getSatellitesBoCompleteOk());
		when(service.getRevealedMessage(any(), any())).thenThrow(Exception.class);
		
		assertThrows(Exception.class, () -> controller.readTopSecret(TestObjectsHelper.getSatellitesRequestOk()));
	}
	
	@Test
	@DisplayName("Test scenario when validation fails")
	public void validateErrorResponseDueValidation() throws RebelsUnableToDecodeException, IllegalArgumentException, Exception {
		when(validator.validate(any())).thenThrow(RebelsBodyArgumentValidationException.class);
		
		assertThrows(RebelsBodyArgumentValidationException.class, () -> controller.readTopSecret(TestObjectsHelper.getSatellitesRequestOk()));
	}

}
