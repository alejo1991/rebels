package com.mercadolibre.starWars.rebels.util;

import java.util.List;

import com.mercadolibre.starWars.rebels.domain.bo.SatelliteBO;
import com.mercadolibre.starWars.rebels.dto.request.SatelliteRequestDTO;


public class PositionUtils {
	
	/**
	 * Get the position array from satellite list validated against database
	 * @param satelliteList
	 * @return {@link double[][]}
	 */
	public static double[][] getPositionArray(List<SatelliteBO> satelliteList) {
		double[][] positionArray = new double[satelliteList.size()][];
		
		for (int i = 0; i < satelliteList.size(); i++) {
            SatelliteBO satelliteInfo = satelliteList.get(i);
            positionArray[i] = new double[]{satelliteInfo.getPositionHistoryList().get(0).getPositionX(), satelliteInfo.getPositionHistoryList().get(0).getPositionY()};
        }
		
        return positionArray;
	}
	
	/**
	 * Get the distance array from satellite request list ordering base on the satellite list validated against database 
	 * @param satelliteRequestList
	 * @param satelliteRegisteredList
	 * @return {@link float[]}
	 */
	public static float[] getDistanceArray(List<SatelliteRequestDTO> satelliteRequestList, List<SatelliteBO> satelliteRegisteredList) {
		
        float[] distanceArray = new float[satelliteRequestList.size()];
        
        for(int i = 0; i < satelliteRegisteredList.size(); i++) {
        	for(SatelliteRequestDTO requestDto: satelliteRequestList) {
        		if(requestDto.getName().toUpperCase().equals(satelliteRegisteredList.get(i).getName().toUpperCase())) {
        			distanceArray[i] = requestDto.getDistance();
        			break;
        		}
            }
        }
        
        
        return distanceArray;
    }
	
	/**
	 * 
	 * @param satelliteRegistered
	 * @return
	 */
	public static float[] getDistanceArray(SatelliteBO satelliteRegistered) {
		
        float[] distanceArray = new float[1];
        distanceArray[0] = satelliteRegistered.getMessageTrackingList().get(0).getDistance();
        
        return distanceArray;
    }
	
	

}
