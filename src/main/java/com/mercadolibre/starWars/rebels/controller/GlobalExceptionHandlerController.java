package com.mercadolibre.starWars.rebels.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mercadolibre.starWars.rebels.dto.response.ResponseContainerDTO;
import com.mercadolibre.starWars.rebels.dto.response.ValidationErrorDTO;
import com.mercadolibre.starWars.rebels.exception.RebelsBodyArgumentValidationException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@RestController
@Slf4j
public class GlobalExceptionHandlerController extends ResponseEntityExceptionHandler {
	
	/**
	 * Exception handler method for (@link ConstraintViolationException) exceptions
	 * on requestParams or pathVariables on incoming request 
	 * @param e
	 * @return (@link ResponseContainerDTO) instance with detailed error information
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	ResponseContainerDTO handleConstraintViolationException(ConstraintViolationException e) {
		log.info("Request has validation errors", e);

		final ResponseContainerDTO error = ResponseContainerDTO.builder()
				.response(buildErrorList(e))
				.message(HttpStatus.BAD_REQUEST.name())
				.status(HttpStatus.BAD_REQUEST.value()).build();
		return error;
	}
	
	/**
	 * Overrides default implementation for handleMethodArgumentNotValid method
	 * including custom (@link ResponseContainerDTO) response
	 * when (@link MethodArgumentNotValidException) occurs
	 * for request with requestBody
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.info("Request has validation errors", ex);

		final ResponseContainerDTO error = ResponseContainerDTO.builder()
				.response(buildBodyErrorList(ex))
				.message(HttpStatus.BAD_REQUEST.name())
				.status(HttpStatus.BAD_REQUEST.value()).build();
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Exception Handler controller for exceptions different to MethodArgumentNotValidException or 
	 * ConstraintViolationException
	 * @param ex
	 * @param request
	 * @return (@link ResponseEntity<ResponseContainerDTO>) response with error main info
	 */
	@ExceptionHandler(Exception.class)
	ResponseEntity<ResponseContainerDTO> handleExceptions(final Exception ex, final WebRequest request) {
		log.error(ex.getMessage(), ex);
		
		final ResponseContainerDTO error = ResponseContainerDTO.builder()
				.message(ex.getMessage())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.build();
		
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * Build the validation errors for request with body
	 * @param ex
	 * @return the (@link List<ValidationErrorDTO>) list with error validations
	 */
	public List<ValidationErrorDTO> buildBodyErrorList(MethodArgumentNotValidException ex) {
		
		if(Objects.nonNull(ex.getBindingResult()) && !CollectionUtils.isEmpty(ex.getBindingResult().getFieldErrors())) {
			
			List<ValidationErrorDTO> validationList = new LinkedList<>();
			
			for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
				validationList.add(new ValidationErrorDTO(fieldError.getField(), fieldError.getDefaultMessage()));
			}
			
			return validationList;
		} else {
			RebelsBodyArgumentValidationException e = (RebelsBodyArgumentValidationException) ex;
			
			return e.getValidations();
		}
		
	}
	
	/**
	 * Build the error list from (@link ConstraintViolationException) instance
	 * @param e
	 * @return the (@link List<ValidationErrorDTO>) list with error validations
	 */
	@SuppressWarnings("rawtypes")
	private List<ValidationErrorDTO> buildErrorList(ConstraintViolationException e) {
		
		List<ValidationErrorDTO> validationList = new LinkedList<>();
		
		for (ConstraintViolation violation : e.getConstraintViolations()) {
			
			ValidationErrorDTO errorDetail = (ValidationErrorDTO) violation;
			
			validationList
					.add(new ValidationErrorDTO(
							Objects.nonNull(errorDetail.getPropertyPath()) ? 
							errorDetail.getPropertyPath().toString()
							: errorDetail.getFieldName(), 
							errorDetail.getMessage()));
		}
		
		return validationList;
	}

}
