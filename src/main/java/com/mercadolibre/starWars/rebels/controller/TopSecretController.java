package com.mercadolibre.starWars.rebels.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.starWars.rebels.domain.bo.SatelliteBO;
import com.mercadolibre.starWars.rebels.dto.request.SatellitesRequestDTO;
import com.mercadolibre.starWars.rebels.dto.response.MessageResponseDTO;
import com.mercadolibre.starWars.rebels.enums.EventCodeEnum;
import com.mercadolibre.starWars.rebels.exception.RebelsBodyArgumentValidationException;
import com.mercadolibre.starWars.rebels.exception.RebelsUnableToDecodeException;
import com.mercadolibre.starWars.rebels.service.TopSecretService;
import com.mercadolibre.starWars.rebels.validator.interf.ISatellitesInfoValidator;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/topsecret")
public class TopSecretController {
	
	@Autowired
	private ISatellitesInfoValidator validator;
	
	@Autowired
	private TopSecretService service;
	
	@PostMapping
	@ApiOperation(
		value = "Get decoded message and location from imperial load carrier",
	    notes = "Returns the decoded message and triangulates the position from imperial load carrier using current distance and encoded message intercepted",
	    response = MessageResponseDTO.class,
	    produces = MediaType.APPLICATION_JSON_VALUE,
	    consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> readTopSecret(@Valid @RequestBody SatellitesRequestDTO request) throws RebelsBodyArgumentValidationException, Exception {
		
		List<SatelliteBO> satelliteBoList = validator.validate(request);
		
		try {
			return new ResponseEntity<>(service.getRevealedMessage(request, satelliteBoList), HttpStatus.OK);
		} catch (RebelsUnableToDecodeException | IllegalArgumentException e) {
			return new ResponseEntity<>(EventCodeEnum.NotAbleToDecodeInformationWarning.getDescription(), HttpStatus.NOT_FOUND);
		}
		
	}

}
