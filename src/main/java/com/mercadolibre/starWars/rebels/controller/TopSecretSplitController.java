package com.mercadolibre.starWars.rebels.controller;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.starWars.rebels.domain.bo.SatelliteBO;
import com.mercadolibre.starWars.rebels.dto.request.SatelliteInformationRequestDTO;
import com.mercadolibre.starWars.rebels.dto.request.SatelliteRequestDTO;
import com.mercadolibre.starWars.rebels.dto.response.MessageResponseDTO;
import com.mercadolibre.starWars.rebels.enums.EventCodeEnum;
import com.mercadolibre.starWars.rebels.exception.RebelsBodyArgumentValidationException;
import com.mercadolibre.starWars.rebels.exception.RebelsUnableToDecodeException;
import com.mercadolibre.starWars.rebels.service.TopSecretSplitService;
import com.mercadolibre.starWars.rebels.validator.interf.ISatelliteRequestValidator;

import io.swagger.annotations.ApiOperation;

@RestController
@Validated
@RequestMapping("/topsecret_split")
public class TopSecretSplitController {
	
	@Autowired
	private ISatelliteRequestValidator validator;
	
	@Autowired
	private TopSecretSplitService service;
	
    @PostMapping("/{satellite_name}")
	@ApiOperation(
		value = "Save transmitted data by specific satellite",
		notes = "Save decoded message and distance intercepted by satellite from imperial load carrier",
	    response = String.class)
    public ResponseEntity<String> saveTrasmittedMessage(@PathVariable(name = "satellite_name") String satelliteName, 
    		@Valid @RequestBody SatelliteInformationRequestDTO request) throws RebelsBodyArgumentValidationException, Exception {
		
    	SatelliteRequestDTO requestDto = SatelliteRequestDTO.builder().name(satelliteName).distance(request.getDistance()).message(request.getMessage()).build();
    	
		validator.validateRequest(requestDto);
		
		return new ResponseEntity<>(service.saveSatelliteTransmition(requestDto), HttpStatus.OK);
	}

    @GetMapping("/{satellite_name}")
    @ApiOperation(
    		value = "Get decoded message and distance from imperial load carrier by satellite name",
    	    notes = "Returns the decoded message and triangulates the position from imperial load carrier using current distance and encoded message intercepted",
    	    response = MessageResponseDTO.class,
    	    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLatestSatelliteMessage(@PathVariable(name = "satellite_name", required = true) String satelliteName) 
    	throws ConstraintViolationException, Exception {
    	
		SatelliteBO registeredSatellite = validator.validate(satelliteName);
		
		try {
			return new ResponseEntity<>(service.getRevealedMessage(registeredSatellite), HttpStatus.OK);
	    } catch (RebelsUnableToDecodeException | IllegalArgumentException e) {
			return new ResponseEntity<>(EventCodeEnum.NotAbleToDecodeInformationWarning.getDescription(), HttpStatus.NOT_FOUND);
		}
	}   

}
