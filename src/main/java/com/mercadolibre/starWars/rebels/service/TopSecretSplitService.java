package com.mercadolibre.starWars.rebels.service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercadolibre.starWars.rebels.domain.bo.SatelliteBO;
import com.mercadolibre.starWars.rebels.dto.request.SatelliteRequestDTO;
import com.mercadolibre.starWars.rebels.dto.response.MessageResponseDTO;
import com.mercadolibre.starWars.rebels.dto.response.PositionResponseDTO;
import com.mercadolibre.starWars.rebels.exception.RebelsUnableToDecodeException;
import com.mercadolibre.starWars.rebels.mapper.SatelliteMessageTrackingEntityMapper;
import com.mercadolibre.starWars.rebels.repository.ISatelliteMessageTrackingRepository;
import com.mercadolibre.starWars.rebels.util.PositionUtils;

@Service
public class TopSecretSplitService extends BaseService {
		
	private String decodedMessage;
	private PositionResponseDTO originPosition;
	
	@Autowired
	private SatelliteMessageTrackingEntityMapper messageTrackingMapper;
	
	@Autowired
	private ISatelliteMessageTrackingRepository messageRepository;
	
	@Transactional(rollbackFor = {Exception.class})
	public String saveSatelliteTransmition(SatelliteRequestDTO request) throws Exception {
		if(Objects.nonNull(messageRepository.save(messageTrackingMapper.getEntity(request)))) {
			return "transmition saved successfully";
		}
		
		throw new Exception();
	}
	
	public MessageResponseDTO getRevealedMessage(SatelliteBO registeredSatellite) 
			throws RebelsUnableToDecodeException, IllegalArgumentException, Exception {
		 		
		positionArray = PositionUtils.getPositionArray(Arrays.asList(registeredSatellite));
		float[] distanceArray = PositionUtils.getDistanceArray(registeredSatellite);
		
		decodedMessage = getMessage(getSatelliteLatestMessage(registeredSatellite));
		originPosition = getLocation(distanceArray);
		
		if(StringUtils.isNotEmpty(decodedMessage) && Objects.nonNull(originPosition)) {
			return MessageResponseDTO.builder().message(decodedMessage).position(originPosition).build();
		} else {
			throw new RebelsUnableToDecodeException();
		}		
	}
	
	private List<String[]> getSatelliteLatestMessage(SatelliteBO registeredSatellite) {
		String latestMessage = registeredSatellite.getMessageTrackingList().stream().map(satellite -> satellite.getSplittedMessage()).findFirst().toString();
		String[] messageArray = latestMessage.split(SatelliteMessageTrackingEntityMapper.SPLIT_CHAR);
		List<String[]> messageList = new LinkedList<>();
		messageList.add(messageArray);
		
		return messageList;
	}
	
	
	
}