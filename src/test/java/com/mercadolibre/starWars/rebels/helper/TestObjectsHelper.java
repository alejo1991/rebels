package com.mercadolibre.starWars.rebels.helper;

import java.beans.PropertyEditor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.mercadolibre.starWars.rebels.domain.bo.SatelliteBO;
import com.mercadolibre.starWars.rebels.domain.bo.SatelliteMessageTrackingBO;
import com.mercadolibre.starWars.rebels.domain.bo.SatellitePositionHistoryBO;
import com.mercadolibre.starWars.rebels.dto.request.SatelliteRequestDTO;
import com.mercadolibre.starWars.rebels.dto.request.SatellitesRequestDTO;
import com.mercadolibre.starWars.rebels.dto.response.MessageResponseDTO;
import com.mercadolibre.starWars.rebels.dto.response.PositionResponseDTO;
import com.mercadolibre.starWars.rebels.dto.response.ValidationErrorDTO;
import com.mercadolibre.starWars.rebels.exception.RebelsBodyArgumentValidationException;

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
	
	public static MessageResponseDTO getMockedSuccessfullResponse() {
		
		return MessageResponseDTO.builder()
			.message("este es un mensaje auxilio")
			.position(PositionResponseDTO.builder().positionX(-58.31525f).positionY(-69.551414f).build())
			.build();
	}
	
	public static ConstraintViolationException buildMockedConstraintException() {
		Set<ValidationErrorDTO> errorSet = new HashSet<>();
		errorSet.add(ValidationErrorDTO.builder().fieldName("a").message("error in field a").build());
		errorSet.add(ValidationErrorDTO.builder().fieldName("b").message("error in field b").build());
		
		return new ConstraintViolationException(errorSet);
	}
	
	public static MethodArgumentNotValidException buildMockedMethodArgumentNotValidException() {
		List<ValidationErrorDTO> errorList = new ArrayList<>();
		errorList.add(ValidationErrorDTO.builder().fieldName("a").message("error in field a").build());
		errorList.add(ValidationErrorDTO.builder().fieldName("b").message("error in field b").build());
		
		return new RebelsBodyArgumentValidationException(errorList, null, getEmptyBindingResult());
	}
	
	public static MethodArgumentNotValidException buildMockedBindingMethodArgumentNotValidException() {
		List<ValidationErrorDTO> errorList = new ArrayList<>();
		errorList.add(ValidationErrorDTO.builder().fieldName("a").message("error in field a").build());
		errorList.add(ValidationErrorDTO.builder().fieldName("b").message("error in field b").build());
		
		return new RebelsBodyArgumentValidationException(null, null, getPopulatedBindingResult(errorList));
	}
	
	
	
	public static BindingResult getEmptyBindingResult() {
			
			BindingResult bindingResult = new BindingResult() {
				
				@Override
				public void setNestedPath(String nestedPath) {				
				}
				
				@Override
				public void rejectValue(String field, String errorCode, Object[] errorArgs, String defaultMessage) {
				}
				
				@Override
				public void rejectValue(String field, String errorCode, String defaultMessage) {
					
					
				}
				
				@Override
				public void rejectValue(String field, String errorCode) {
					
					
				}
				
				@Override
				public void reject(String errorCode, Object[] errorArgs, String defaultMessage) {
					
					
				}
				
				@Override
				public void reject(String errorCode, String defaultMessage) {
					
					
				}
				
				@Override
				public void reject(String errorCode) {
					
					
				}
				
				@Override
				public void pushNestedPath(String subPath) {
					
					
				}
				
				@Override
				public void popNestedPath() throws IllegalStateException {
					
					
				}
				
				@Override
				public boolean hasGlobalErrors() {
					
					return false;
				}
				
				@Override
				public boolean hasFieldErrors(String field) {
					
					return false;
				}
				
				@Override
				public boolean hasFieldErrors() {
					
					return false;
				}
				
				@Override
				public boolean hasErrors() {
					
					return false;
				}
				
				@Override
				public String getObjectName() {
					
					return null;
				}
				
				@Override
				public String getNestedPath() {
					
					return null;
				}
				
				@Override
				public List<ObjectError> getGlobalErrors() {
					
					return null;
				}
				
				@Override
				public int getGlobalErrorCount() {
					
					return 0;
				}
				
				@Override
				public ObjectError getGlobalError() {
					
					return null;
				}
				
				@Override
				public Object getFieldValue(String field) {
					
					return null;
				}
				
				@Override
				public Class<?> getFieldType(String field) {
					
					return null;
				}
				
				@Override
				public List<FieldError> getFieldErrors(String field) {
					
					return null;
				}
				
				@Override
				public List<FieldError> getFieldErrors() {
					
					return null;
				}
				
				@Override
				public int getFieldErrorCount(String field) {
					
					return 0;
				}
				
				@Override
				public int getFieldErrorCount() {
					
					return 0;
				}
				
				@Override
				public FieldError getFieldError(String field) {
					
					return null;
				}
				
				@Override
				public FieldError getFieldError() {
					
					return null;
				}
				
				@Override
				public int getErrorCount() {
					
					return 0;
				}
				
				@Override
				public List<ObjectError> getAllErrors() {
					
					return new ArrayList<>();
				}
				
				@Override
				public void addAllErrors(Errors errors) {
					
					
				}
				
				@Override
				public String[] resolveMessageCodes(String errorCode, String field) {
					
					return null;
				}
				
				@Override
				public String[] resolveMessageCodes(String errorCode) {
					
					return null;
				}
				
				@Override
				public Object getTarget() {
					
					return null;
				}
				
				@Override
				public Object getRawFieldValue(String field) {
					
					return null;
				}
				
				@Override
				public PropertyEditorRegistry getPropertyEditorRegistry() {
					
					return null;
				}
				
				@Override
				public Map<String, Object> getModel() {
					
					return null;
				}
				
				@Override
				public PropertyEditor findEditor(String field, Class<?> valueType) {
					
					return null;
				}
				
				@Override
				public void addError(ObjectError error) {
					
					
				}
			};
			
			return bindingResult;
	}
	
	public static BindingResult getPopulatedBindingResult(List<ValidationErrorDTO> errorList) {
		
		BindingResult bindingResult = new BindingResult() {
			
			@Override
			public void setNestedPath(String nestedPath) {				
			}
			
			@Override
			public void rejectValue(String field, String errorCode, Object[] errorArgs, String defaultMessage) {
			}
			
			@Override
			public void rejectValue(String field, String errorCode, String defaultMessage) {
				
				
			}
			
			@Override
			public void rejectValue(String field, String errorCode) {
				
				
			}
			
			@Override
			public void reject(String errorCode, Object[] errorArgs, String defaultMessage) {
				
				
			}
			
			@Override
			public void reject(String errorCode, String defaultMessage) {
				
				
			}
			
			@Override
			public void reject(String errorCode) {
				
				
			}
			
			@Override
			public void pushNestedPath(String subPath) {
				
				
			}
			
			@Override
			public void popNestedPath() throws IllegalStateException {
				
				
			}
			
			@Override
			public boolean hasGlobalErrors() {
				
				return false;
			}
			
			@Override
			public boolean hasFieldErrors(String field) {
				
				return false;
			}
			
			@Override
			public boolean hasFieldErrors() {
				
				return false;
			}
			
			@Override
			public boolean hasErrors() {
				
				return false;
			}
			
			@Override
			public String getObjectName() {
				
				return null;
			}
			
			@Override
			public String getNestedPath() {
				
				return null;
			}
			
			@Override
			public List<ObjectError> getGlobalErrors() {
				
				return null;
			}
			
			@Override
			public int getGlobalErrorCount() {
				
				return errorList.size();
			}
			
			@Override
			public ObjectError getGlobalError() {
				
				return null;
			}
			
			@Override
			public Object getFieldValue(String field) {
				
				return null;
			}
			
			@Override
			public Class<?> getFieldType(String field) {
				
				return null;
			}
			
			@Override
			public List<FieldError> getFieldErrors(String field) {
				
				return null;
			}
			
			@Override
			public List<FieldError> getFieldErrors() {
				return errorList.stream().map(error -> new FieldError(error.getFieldName(), error.getFieldName(), error.getMessage())).collect(Collectors.toList());
			}
			
			@Override
			public int getFieldErrorCount(String field) {
				
				return 0;
			}
			
			@Override
			public int getFieldErrorCount() {
				
				return errorList.size();
			}
			
			@Override
			public FieldError getFieldError(String field) {
				
				return null;
			}
			
			@Override
			public FieldError getFieldError() {
				
				return null;
			}
			
			@Override
			public int getErrorCount() {
				
				return 0;
			}
			
			@Override
			public List<ObjectError> getAllErrors() {
				
				return new ArrayList<>();
			}
			
			@Override
			public void addAllErrors(Errors errors) {
				
				
			}
			
			@Override
			public String[] resolveMessageCodes(String errorCode, String field) {
				
				return null;
			}
			
			@Override
			public String[] resolveMessageCodes(String errorCode) {
				
				return null;
			}
			
			@Override
			public Object getTarget() {
				
				return null;
			}
			
			@Override
			public Object getRawFieldValue(String field) {
				
				return null;
			}
			
			@Override
			public PropertyEditorRegistry getPropertyEditorRegistry() {
				
				return null;
			}
			
			@Override
			public Map<String, Object> getModel() {
				
				return null;
			}
			
			@Override
			public PropertyEditor findEditor(String field, Class<?> valueType) {
				
				return null;
			}
			
			@Override
			public void addError(ObjectError error) {
				
				
			}
		};
		
		return bindingResult;
	}
	
}
