package com.mercadolibre.starWars.rebels.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.starWars.rebels.domain.bo.SatelliteBO;
import com.mercadolibre.starWars.rebels.dto.request.SatellitesRequestDTO;
import com.mercadolibre.starWars.rebels.dto.response.ValidationErrorDTO;
import com.mercadolibre.starWars.rebels.exception.RebelsBodyArgumentValidationException;
import com.mercadolibre.starWars.rebels.exception.UnableToDecodeException;
import com.mercadolibre.starWars.rebels.service.TopSecretService;
import com.mercadolibre.starWars.rebels.validator.interf.ISatellitesInfoValidator;

@RestController
@RequestMapping("/topsecret")
public class TopSecretController {
	
	@Autowired
	private ISatellitesInfoValidator validator;
	
	@Autowired
	private TopSecretService service;
	
	@PostMapping
	public ResponseEntity<?> readTopSecret(@Valid @RequestBody SatellitesRequestDTO request) throws RebelsBodyArgumentValidationException, Exception {
		
		List<SatelliteBO> satelliteBoList = validator.validate(request);
		
		try {
			return new ResponseEntity<>(service.getRevealedMessage(request, satelliteBoList), HttpStatus.OK);
		} catch (UnableToDecodeException e) {
			return new ResponseEntity<>("Not able to decode information", HttpStatus.NOT_FOUND);
		}
		
	}

}
