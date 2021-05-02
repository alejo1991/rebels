package com.mercadolibre.starWars.rebels.service;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.stereotype.Service;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.mercadolibre.starWars.rebels.domain.bo.SatelliteBO;
import com.mercadolibre.starWars.rebels.dto.request.SatellitesRequestDTO;
import com.mercadolibre.starWars.rebels.dto.response.MessageResponseDTO;
import com.mercadolibre.starWars.rebels.dto.response.PositionResponseDTO;
import com.mercadolibre.starWars.rebels.exception.UnableToDecodeException;
import com.mercadolibre.starWars.rebels.util.MessageUtils;
import com.mercadolibre.starWars.rebels.util.PositionUtils;
import com.mercadolibre.starWars.rebels.util.RebelUtils;

@Service
public class TopSecretService {
	
	private double[][] positionArray;
	private String decodedMessage;
	private PositionResponseDTO originPosition;
	
	public MessageResponseDTO getRevealedMessage(SatellitesRequestDTO request, List<SatelliteBO> registeredSatellite) throws UnableToDecodeException {
		
		positionArray = PositionUtils.getPositionArray(registeredSatellite);
		float[] distanceArray = PositionUtils.getDistanceArray(request.getSatellites(), registeredSatellite);
		List<String[]> messageList = RebelUtils.getTransmitedMessageListFromSatellites(request.getSatellites());
		decodedMessage = getMessage(messageList);
		originPosition = getLocation(distanceArray);
		
		if(StringUtils.isNotEmpty(decodedMessage) || Objects.nonNull(originPosition)) {
			return MessageResponseDTO.builder().message(decodedMessage).position(originPosition).build();
		} else {
			throw new UnableToDecodeException();
		}		
	}
	
	private PositionResponseDTO getLocation(float[] distances) {
		
		NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(
				new TrilaterationFunction(positionArray, RebelUtils.fromFloatToDoubleArray(distances)), 
				new LevenbergMarquardtOptimizer());
        LeastSquaresOptimizer.Optimum optimum = solver.solve();
        double[] point = optimum.getPoint().toArray();
        
        return PositionResponseDTO.builder().positionX((float) point[0]).positionY((float) point[1]).build();
	}
	
	private String getMessage(List<String[]> messageList) {
		
		if(MessageUtils.validateNotEmptyMessageList(messageList)) {
        
			String[] baseMessage = messageList.get(0);
	        
			for (String[] spplitedMessage: messageList) {
	            baseMessage = MessageUtils.joinMessage(baseMessage, spplitedMessage);
	        }
	        
			return String.join(MessageUtils.WHITE_SPACE_SEPARATOR, baseMessage);
			
			}
		
		return "";
    }

}
