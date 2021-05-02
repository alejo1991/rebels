package com.mercadolibre.starWars.rebels.util;

import java.util.List;

import com.mercadolibre.starWars.rebels.domain.bo.SatelliteBO;
import com.mercadolibre.starWars.rebels.dto.request.SatelliteRequestDTO;


public class PositionUtils {
	
	/**
	 * 
	 * @param satelliteList
	 * @return
	 */
	public static double[][] getPositionArray(List<SatelliteBO> satelliteList) {
		double[][] positionArray = new double[satelliteList.size()][];
		
		for (int i = 0; i < satelliteList.size(); i++) {
            SatelliteBO satelliteInfo = satelliteList.get(i);
            positionArray[i] = new double[]{satelliteInfo.getPositionHistoryList().get(i).getPositionX(), satelliteInfo.getPositionHistoryList().get(i).getPositionX()};
        }
		
        return positionArray;
	}
	
	/**
	 * 
	 * @param satelliteRequestList
	 * @param satelliteRegisteredList
	 * @return
	 */
	public static float[] getDistanceArray(List<SatelliteRequestDTO> satelliteRequestList, List<SatelliteBO> satelliteRegisteredList) {
		
        float[] distanceArray = new float[satelliteRequestList.size()];
        
        for(int i = 0; i < satelliteRegisteredList.size(); i++) {
        	for(SatelliteRequestDTO requestDto: satelliteRequestList) {
        		if(requestDto.getName().toUpperCase().equals(requestDto.getName())) {
        			distanceArray[i] = requestDto.getDistance();
        			break;
        		}
            }
        }
        
        
        return distanceArray;
    }
	
	

}
