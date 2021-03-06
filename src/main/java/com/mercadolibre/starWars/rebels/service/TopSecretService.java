package com.mercadolibre.starWars.rebels.service;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.mercadolibre.starWars.rebels.domain.bo.SatelliteBO;
import com.mercadolibre.starWars.rebels.dto.request.SatellitesRequestDTO;
import com.mercadolibre.starWars.rebels.dto.response.MessageResponseDTO;
import com.mercadolibre.starWars.rebels.dto.response.PositionResponseDTO;
import com.mercadolibre.starWars.rebels.exception.RebelsUnableToDecodeException;
import com.mercadolibre.starWars.rebels.util.PositionUtils;
import com.mercadolibre.starWars.rebels.util.RebelUtils;

@Service
public class TopSecretService extends BaseService {
	
	/**
	 * Return the response with the message decoded and the triangulated position
	 * of the target given the request and the database validated info. If not able
	 * to resolve at least one of those returns {@link RebelsUnableToDecodeException>
	 * @param request
	 * @param registeredSatellite
	 * @return {@link MessageResponseDTO}
	 * @throws RebelsUnableToDecodeException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public MessageResponseDTO getRevealedMessage(SatellitesRequestDTO request, List<SatelliteBO> registeredSatellite) 
			throws RebelsUnableToDecodeException, IllegalArgumentException, Exception {
		
		positionArray = PositionUtils.getPositionArray(registeredSatellite);
		float[] distanceArray = PositionUtils.getDistanceArray(request.getSatellites(), registeredSatellite);
		List<String[]> messageList = RebelUtils.getTransmitedMessageListFromSatellites(request.getSatellites());
		String decodedMessage = getMessage(messageList);
		PositionResponseDTO originPosition = getLocation(distanceArray);
		
		if(StringUtils.isNotEmpty(decodedMessage) && Objects.nonNull(originPosition)) {
			return MessageResponseDTO.builder().message(decodedMessage).position(originPosition).build();
		} else {
			throw new RebelsUnableToDecodeException();
		}		
	}

}
