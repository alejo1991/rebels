package com.mercadolibre.starWars.rebels.dto.response;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

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
public class ResponseContainerDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Object response;
	private String message;
	private int status;
	
	public ResponseContainerDTO(Object response, HttpStatus status) {
		this.response = response;
		this.status = status.value();
	}
	
	public ResponseContainerDTO(String message, HttpStatus status) {
		this.message = message;
		this.status = status.value();
	}

}