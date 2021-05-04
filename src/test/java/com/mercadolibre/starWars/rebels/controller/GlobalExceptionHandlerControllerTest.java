package com.mercadolibre.starWars.rebels.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.mercadolibre.starWars.rebels.dto.response.ResponseContainerDTO;
import com.mercadolibre.starWars.rebels.helper.TestObjectsHelper;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerControllerTest {
	
	@InjectMocks
	private GlobalExceptionHandlerController controller;
	
	@Test
	@DisplayName("Test scenario when ConstraintViolationException is thrown by any rest controller and response is 400")
	public void handleConstraintViolationException() {
		ResponseContainerDTO response = controller.handleConstraintViolationException(TestObjectsHelper.buildMockedConstraintException());
		
		assertTrue(Objects.nonNull(response.getResponse()));
		assertEquals(400, response.getStatus());
	}
	
	@Test
	@DisplayName("Test scenario when Exception is thrown by any rest controller and response is 500")
	public void handleExceptions() {
		ResponseEntity<ResponseContainerDTO> response = controller.handleExceptions(new Exception(), null);
		
		assertTrue(Objects.nonNull(response.getStatusCode()));
		assertEquals(500, response.getStatusCodeValue());
	}
	
	@Test
	@DisplayName("Test scenario when MethodArgumentNotValidException is thrown by any rest controller and response is 400")
	public void handleMethodArgumentNotValid() {
		ResponseEntity<Object> response = controller.handleMethodArgumentNotValid(TestObjectsHelper.buildMockedMethodArgumentNotValidException(), null, null, null);
		
		assertTrue(Objects.nonNull(response.getBody()));
		assertEquals(400, response.getStatusCodeValue());
	}
	
	@Test
	@DisplayName("Test scenario when MethodArgumentNotValidException with bindingResult is thrown by any rest controller and response is 400")
	public void handleMethodArgumentNotValidWithBindingResult() {
		ResponseEntity<Object> response = controller.handleMethodArgumentNotValid(TestObjectsHelper.buildMockedBindingMethodArgumentNotValidException(), null, null, null);
		
		assertTrue(Objects.nonNull(response.getBody()));
		assertEquals(400, response.getStatusCodeValue());
	}

}
