package com.mercadolibre.starWars.rebels.util;

import java.util.List;

import com.mercadolibre.starWars.rebels.domain.bo.SatteliteBO;
import com.mercadolibre.starWars.rebels.dto.request.SatteliteRequestDTO;


public class PositionUtils {
	
	/**
	 * 
	 * @param satteliteList
	 * @return
	 */
	public static double[][] getPositionArray(List<SatteliteBO> satteliteList) {
		double[][] positionArray = new double[satteliteList.size()][];
		
		for (int i = 0; i < satteliteList.size(); i++) {
            SatteliteBO satteliteInfo = satteliteList.get(i);
            positionArray[i] = new double[]{satteliteInfo.getPositionHistoryList().get(i).getPositionX(), satteliteInfo.getPositionHistoryList().get(i).getPositionX()};
        }
		
        return positionArray;
	}
	
	/**
	 * 
	 * @param satteliteRequestList
	 * @param satteliteRegisteredList
	 * @return
	 */
	public static float[] getDistanceArray(List<SatteliteRequestDTO> satteliteRequestList, List<SatteliteBO> satteliteRegisteredList) {
		
        float[] distanceArray = new float[satteliteRequestList.size()];
        
        for(int i = 0; i < satteliteRegisteredList.size(); i++) {
        	for(SatteliteRequestDTO requestDto: satteliteRequestList) {
        		if(requestDto.getName().toUpperCase().equals(requestDto.getName())) {
        			distanceArray[i] = requestDto.getDistance();
        			break;
        		}
            }
        }
        
        
        return distanceArray;
    }
	
	

}
