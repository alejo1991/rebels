package com.mercadolibre.starWars.rebels.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.mercadolibre.starWars.rebels.domain.bo.SatelliteBO;
import com.mercadolibre.starWars.rebels.domain.bo.SatelliteMessageTrackingBO;
import com.mercadolibre.starWars.rebels.domain.bo.SatellitePositionHistoryBO;
import com.mercadolibre.starWars.rebels.dto.request.SatelliteRequestDTO;
import com.mercadolibre.starWars.rebels.dto.request.SatellitesRequestDTO;

public class TestObjectsHelper {
	
	public static SatelliteRequestDTO getSatelliteRequestOk() {
		
		String[] message = {"este", "", "", "", "auxilio"};
		
		return SatelliteRequestDTO.builder()
			.distance(100f)
			.message(message)
			.name("kenobi")
			.build();
	}
	
	public static SatelliteRequestDTO getSatelliteRequestNotMeaningfulMessage() {
		
		String[] message = {"", "", "", "", ""};
		
		return SatelliteRequestDTO.builder()
			.distance(100f)
			.message(message)
			.name("kenobi")
			.build();
	}
	
	public static SatelliteRequestDTO getSatelliteRequestOk(String name) {
		
		String[] message = {"este", "", "", "", "auxilio"};
		
		return SatelliteRequestDTO.builder()
			.distance(100f)
			.message(message)
			.name(name)
			.build();
	}
	
	public static SatelliteRequestDTO getSatelliteRequestOk(String name, float distance, int index) {
		
		return SatelliteRequestDTO.builder()
			.distance(distance)
			.message(getMessageByIndex(index))
			.name(name)
			.build();
	}
	
	public static SatelliteRequestDTO getSatelliteRequestDiffMessageLenght(String name, float distance, int index) {
		
		return SatelliteRequestDTO.builder()
			.distance(distance)
			.message(getMessageDiffLenghtByIndex(index))
			.name(name)
			.build();
	}
	
	public static SatelliteRequestDTO getSatelliteRequestIncompleteMessage(String name, float distance, int index) {
		
		String[] message = {"", "", "", "", ""};
		
		return SatelliteRequestDTO.builder()
			.distance(distance)
			.message(message)
			.name(name)
			.build();
	}
	
	public static SatelliteRequestDTO getSatelliteRequestBadName() {
		
		String[] message = {"este", "", "", "", "auxilio"};
		
		return SatelliteRequestDTO.builder()
			.distance(100f)
			.message(message)
			.name("vader")
			.build();
	}
	
	public static SatelliteBO getSatelliteBoNameOk() {
		return SatelliteBO.builder()
			.name("kenobi")
			.build();
	}
	
	public static SatelliteBO getSatelliteBoNameOk(String name) {
		return SatelliteBO.builder()
			.name(name)
			.build();
	}
	
	public static SatelliteBO getSatelliteBoCompleteOk(String name) {
		
		return SatelliteBO.builder()
			.name(name)
			.positionHistoryList(Arrays.asList(getPositionHistoryBo(name)))
			.messageTrackingList(Arrays.asList(getMessageTrackingBo(name)))
			.build();
	}
	
	public static SatelliteBO getSatelliteBoIncompleteMessage(String name) {
		
		return SatelliteBO.builder()
			.name(name)
			.positionHistoryList(Arrays.asList(getPositionHistoryBo(name)))
			.messageTrackingList(Collections.emptyList())
			.build();
	}
	
	private static SatellitePositionHistoryBO getPositionHistoryBo(String name) {
		
		Map<String, SatellitePositionHistoryBO> positionBoMap = new LinkedHashMap<>();
		positionBoMap.put("kenobi", new SatellitePositionHistoryBO(1L, -500f, -200f));
		positionBoMap.put("skywalker", new SatellitePositionHistoryBO(2L, 100f, -100f));
		positionBoMap.put("sato", new SatellitePositionHistoryBO(3L, 500f, 100f));
		
		return positionBoMap.get(name);
	}
	
	private static SatelliteMessageTrackingBO getMessageTrackingBo(String name) {
		Map<String, SatelliteMessageTrackingBO> messageBoMap = new LinkedHashMap<>();
		messageBoMap.put("kenobi", SatelliteMessageTrackingBO.builder().id(1L).distance(100f).splittedMessage("este,,,,auxilio").build());
		messageBoMap.put("skywalker", SatelliteMessageTrackingBO.builder().id(2L).distance(115.5f).splittedMessage(",es,un,,auxilio").build());
		messageBoMap.put("sato", SatelliteMessageTrackingBO.builder().id(3L).distance(142.5f).splittedMessage("este,,,mensaje,auxilio").build());
		
		return messageBoMap.get(name);
	}
	
	public static SatellitesRequestDTO getSatellitesRequestOk() {		
		List<SatelliteRequestDTO> satellites = new LinkedList<>();
		int i = 0;
		
		Iterator<Map.Entry<String, Float>> iterator = getSatelliteRequestData().entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Float> entry = iterator.next();
			satellites.add(getSatelliteRequestOk(entry.getKey(), entry.getValue(), i));
			i++;
		}
		
		return SatellitesRequestDTO.builder().satellites(satellites).build();
	}
	
	public static SatellitesRequestDTO getSatellitesRequestOkDiffMessageLenght() {		
		List<SatelliteRequestDTO> satellites = new LinkedList<>();
		int i = 0;
		
		Iterator<Map.Entry<String, Float>> iterator = getSatelliteRequestData().entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Float> entry = iterator.next();
			satellites.add(getSatelliteRequestDiffMessageLenght(entry.getKey(), entry.getValue(), i));
			i++;
		}
		
		return SatellitesRequestDTO.builder().satellites(satellites).build();
	}
	
	public static SatellitesRequestDTO getSatellitesRequestIncompleteMessage() {
		List<SatelliteRequestDTO> satellites = new LinkedList<>();
		int i = 0;
		
		Iterator<Map.Entry<String, Float>> iterator = getSatelliteRequestData().entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Float> entry = iterator.next();
			satellites.add(getSatelliteRequestIncompleteMessage(entry.getKey(), entry.getValue(), i));
			i++;
		}
		
		return SatellitesRequestDTO.builder().satellites(satellites).build();
	}
	
	public static SatellitesRequestDTO getSatellitesRequestIncompleteMessageAndDistance() {
		List<SatelliteRequestDTO> satellites = new LinkedList<>();
		satellites.add(getSatelliteRequestIncompleteMessage("kenobi", 0f, 0));
		
		return SatellitesRequestDTO.builder().satellites(satellites).build();
	}
	
	public static SatellitesRequestDTO getSatellitesRequestIncompleteDistanceInfo() {
		List<SatelliteRequestDTO> satellites = new LinkedList<>();
		satellites.add(getSatelliteRequestOk("kenobi", 0f, 0));
		
		return SatellitesRequestDTO.builder().satellites(satellites).build();
	}
	
	private static Map<String, Float> getSatelliteRequestData() {
		Map<String, Float> satelliteData = new LinkedHashMap<>();
		satelliteData.put("kenobi", 100f);
		satelliteData.put("skywalker", 115.5f);
		satelliteData.put("sato", 142.7f);
		
		return satelliteData;
	}
	
	public static List<SatelliteBO> getSatellitesBoOk() {
		List<String> satelliteNames = new LinkedList<>();
		satelliteNames.add("kenobi");
		satelliteNames.add("skywalker");
		satelliteNames.add("sato");
		
		return satelliteNames.stream().map(sat -> getSatelliteBoCompleteOk(sat)).collect(Collectors.toList());
	}
	
	public static SatelliteBO getSatellitesBoIncomplete() {		
		return getSatelliteBoIncompleteMessage("kenobi");
	}
	
	public static SatelliteBO getSatelliteBo() {		
		return getSatelliteBoCompleteOk("kenobi");
	}
	
	public static List<SatelliteBO> getSatellitesBoCompleteOk() {
		List<String> satelliteNames = new LinkedList<>();
		satelliteNames.add("kenobi");
		satelliteNames.add("skywalker");
		satelliteNames.add("sato");
		
		return satelliteNames.stream().map(sat -> getSatelliteBoCompleteOk(sat)).collect(Collectors.toList());
	}
	
	public static String[] getMessageByIndex(int index) {
		String[] message1 = {"este", "", "", "", "auxilio"};
		String[] message2 = {"", "es", "un", "", "auxilio"};
		String[] message3 = {"este", "", "", "mensaje", "auxilio"};
		
		List<String[]> mockedMessageList = new ArrayList<>();
		mockedMessageList.add(message1);
		mockedMessageList.add(message2);
		mockedMessageList.add(message3);
		
		return mockedMessageList.get(index);
	}
	
	public static String[] getMessageDiffLenghtByIndex(int index) {
		String[] message1 = {"este", "", "", "mensaje", "de", "auxilio"};
		String[] message2 = {"", "es", "un", "", "auxilio"};
		String[] message3 = {"este", "", "", "mensaje", "auxilio"};
		
		List<String[]> mockedMessageList = new ArrayList<>();
		mockedMessageList.add(message1);
		mockedMessageList.add(message2);
		mockedMessageList.add(message3);
		
		return mockedMessageList.get(index);
	}
	
	

}
