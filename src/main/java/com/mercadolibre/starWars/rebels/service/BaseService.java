package com.mercadolibre.starWars.rebels.service;

import java.util.List;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.mercadolibre.starWars.rebels.dto.response.PositionResponseDTO;
import com.mercadolibre.starWars.rebels.util.MessageUtils;
import com.mercadolibre.starWars.rebels.util.RebelUtils;

public class BaseService {
	
	protected double[][] positionArray;
	
	protected PositionResponseDTO getLocation(float[] distances) throws IllegalArgumentException {
		
		NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(
				new TrilaterationFunction(positionArray, RebelUtils.fromFloatToDoubleArray(distances)), 
				new LevenbergMarquardtOptimizer());
        LeastSquaresOptimizer.Optimum optimum = solver.solve();
        double[] point = optimum.getPoint().toArray();
        
        return PositionResponseDTO.builder().positionX((float) point[0]).positionY((float) point[1]).build();
	}
	
	protected String getMessage(List<String[]> messageList) {
		
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
