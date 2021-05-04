package com.mercadolibre.starWars.rebels.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.mercadolibre.starWars.rebels.domain.bo.SatelliteBO;
import com.mercadolibre.starWars.rebels.dto.request.SatelliteRequestDTO;
import com.mercadolibre.starWars.rebels.dto.response.MessageResponseDTO;
import com.mercadolibre.starWars.rebels.dto.response.PositionResponseDTO;
import com.mercadolibre.starWars.rebels.enums.EventCodeEnum;
import com.mercadolibre.starWars.rebels.exception.RebelsUnableToDecodeException;
import com.mercadolibre.starWars.rebels.mapper.SatelliteMessageTrackingEntityMapper;
import com.mercadolibre.starWars.rebels.repository.ISatelliteMessageTrackingRepository;
import com.mercadolibre.starWars.rebels.util.PositionUtils;
import com.mercadolibre.starWars.rebels.util.RebelUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TopSecretSplitService extends BaseService {
	
	@Autowired
	private SatelliteMessageTrackingEntityMapper messageTrackingMapper;
	
	@Autowired
	private ISatelliteMessageTrackingRepository messageRepository;
	
	@Transactional(rollbackFor = {Exception.class})
	public String saveSatelliteTransmition(SatelliteRequestDTO request) throws Exception {
		if(Objects.nonNull(messageRepository.save(messageTrackingMapper.getEntity(request)))) {
			return RebelUtils.getFormattedMessage(EventCodeEnum.TransmissionSavedSuccessfully.getDescription(), Arrays.asList(request.getName()));
		}
		
		throw new Exception();
	}
	
	public MessageResponseDTO getRevealedMessage(SatelliteBO registeredSatellite) 
			throws RebelsUnableToDecodeException, Exception {
		 		
		positionArray = PositionUtils.getPositionArray(Arrays.asList(registeredSatellite));
		float[] distanceArray = PositionUtils.getDistanceArray(registeredSatellite);
		
		String decodedMessage = getMessage(getSatelliteLatestMessage(registeredSatellite));
		PositionResponseDTO originPosition = null;
		
		try {
			originPosition = getLocation(distanceArray);
		} catch (IllegalArgumentException e) {
			log.error(EventCodeEnum.NotEnoughInfoForTriangulate.getDescription());
		}
		
		if(StringUtils.isNotEmpty(decodedMessage) || Objects.nonNull(originPosition)) {
			return MessageResponseDTO.builder().message(decodedMessage).position(originPosition).build();
		} else {
			throw new RebelsUnableToDecodeException();
		}		
	}
	
	private List<String[]> getSatelliteLatestMessage(SatelliteBO registeredSatellite) {
		
		if(!CollectionUtils.isEmpty(registeredSatellite.getMessageTrackingList())) {
			
			String latestMessage = registeredSatellite.getMessageTrackingList().stream().map(satellite -> satellite.getSplittedMessage()).findFirst().toString();
			String[] messageArray = latestMessage.split(SatelliteMessageTrackingEntityMapper.SPLIT_CHAR);
			List<String[]> messageList = new LinkedList<>();
			messageList.add(messageArray);
			
			return messageList;
		
		}
		
		return Collections.emptyList();
	}
	
	
	
}
