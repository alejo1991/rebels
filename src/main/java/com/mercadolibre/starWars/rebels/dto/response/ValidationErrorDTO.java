package com.mercadolibre.starWars.rebels.dto.response;

import java.io.Serializable;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.metadata.ConstraintDescriptor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ValidationErrorDTO implements ConstraintViolation<String>, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String fieldName;
	private String message;
	
	@Override
	public String getMessage() {
		return this.message;
	}
	
	@Override
	public String getMessageTemplate() {
		return null;
	}
	
	@Override
	public String getRootBean() {
		return null;
	}
	
	@Override
	public Class<String> getRootBeanClass() {
		return null;
	}
	
	@Override
	public Object getLeafBean() {
		return null;
	}
	
	@Override
	public Object[] getExecutableParameters() {
		return null;
	}
	
	@Override
	public Object getExecutableReturnValue() {
		return null;
	}
	
	@Override
	public Path getPropertyPath() {
		return null;
	}
	
	@Override
	public Object getInvalidValue() {
		return null;
	}
	
	@Override
	public ConstraintDescriptor<?> getConstraintDescriptor() {
		return null;
	}
	
	@Override
	public <U> U unwrap(Class<U> type) {
		return null;
	}

}
