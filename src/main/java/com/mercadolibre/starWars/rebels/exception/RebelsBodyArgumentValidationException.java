package com.mercadolibre.starWars.rebels.exception;

import java.util.List;

import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mercadolibre.starWars.rebels.dto.response.ValidationErrorDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class RebelsBodyArgumentValidationException extends MethodArgumentNotValidException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	List<ValidationErrorDTO> validations;
	
	public RebelsBodyArgumentValidationException(List<ValidationErrorDTO> validations,
			MethodParameter parameter, BindingResult bindingResult) {
		super(parameter, bindingResult);
		this.validations = validations;
	}
	
	public RebelsBodyArgumentValidationException(MethodParameter parameter, BindingResult bindingResult) {
		super(parameter, bindingResult);
	}
	
	@Override
	public String getMessage() {
		return null;
	}
	

}
