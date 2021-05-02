package com.mercadolibre.starWars.rebels.util;

import java.util.List;
import java.util.stream.Collectors;

import com.mercadolibre.starWars.rebels.dto.request.SatelliteRequestDTO;

public class RebelUtils {
	
	public static double[] fromFloatToDoubleArray(final float[] array) {
		
		double[] doubleArray = new double[array.length];
 		
		for(int i = 0; i < array.length; i++) {
			doubleArray[i] = array[i];
		}
		
		return doubleArray;
	}
	
	public static List<String[]> getTransmitedMessageListFromSatellites(List<SatelliteRequestDTO> satelliteList) {
		
		return satelliteList.stream().map(satellite -> satellite.getMessage()).collect(Collectors.toList());
	}

}
