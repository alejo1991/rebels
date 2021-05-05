package com.mercadolibre.starWars.rebels.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.util.CollectionUtils;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.mercadolibre.starWars.rebels.dto.response.PositionResponseDTO;
import com.mercadolibre.starWars.rebels.util.MessageUtils;
import com.mercadolibre.starWars.rebels.util.RebelUtils;

public class BaseService {
	
	protected double[][] positionArray;
	
	/**
	 * Calculates and return the target position [x, y] given an array of distances from satellites
	 * and internally calls for previously populated positionArray with the position of every
	 * satellite. This method sign obey to requirement definition.
	 * @param distances
	 * @return {@link PositionResponseDTO}
	 * @throws IllegalArgumentException
	 */
	protected PositionResponseDTO getLocation(float[] distances) throws IllegalArgumentException {
		
		NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(
				new TrilaterationFunction(positionArray, RebelUtils.fromFloatToDoubleArray(distances)), 
				new LevenbergMarquardtOptimizer());
        LeastSquaresOptimizer.Optimum optimum = solver.solve();
        double[] point = optimum.getPoint().toArray();
        
        return PositionResponseDTO.builder().positionX((float) point[0]).positionY((float) point[1]).build();
	}
	
	/**
	 * Calculate and return the message intercepted from target given a list of messages 
	 * transmitted by at least one satellite
	 * @param messageList
	 * @return {@link String}
	 */
	protected String getMessage(List<String[]> messageList) {
		
		if(MessageUtils.validateNotEmptyMessageList(messageList) && !CollectionUtils.isEmpty(messageList)) {
        
			String[] baseMessage = messageList.get(0);
	        
			for (String[] spplitedMessage: messageList) {
	            baseMessage = MessageUtils.joinMessage(baseMessage, spplitedMessage);
	        }
	        
			return String.join(MessageUtils.WHITE_SPACE_SEPARATOR, baseMessage);
			
		}
		
		return StringUtils.EMPTY;
    }

}
