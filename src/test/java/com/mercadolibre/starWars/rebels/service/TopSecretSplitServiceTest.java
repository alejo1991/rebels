package com.mercadolibre.starWars.rebels.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.mercadolibre.starWars.rebels.domain.SatelliteMessageTracking;
import com.mercadolibre.starWars.rebels.dto.response.MessageResponseDTO;
import com.mercadolibre.starWars.rebels.exception.RebelsUnableToDecodeException;
import com.mercadolibre.starWars.rebels.helper.TestObjectsHelper;
import com.mercadolibre.starWars.rebels.mapper.SatelliteMessageTrackingEntityMapper;
import com.mercadolibre.starWars.rebels.repository.ISatelliteMessageTrackingRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TopSecretSplitServiceTest {
	
	@InjectMocks
	private TopSecretSplitService service;
	
	@Mock
	private SatelliteMessageTrackingEntityMapper messageTrackingMapper;
	
	@Mock
	private ISatelliteMessageTrackingRepository messageRepository;
	
	@Test
	@DisplayName("Test scenario for position not triangulated and message not decoded")
	public void testUnableToDecodeByPosition() throws Exception {
		
		assertThrows(RebelsUnableToDecodeException.class, () -> service.getRevealedMessage(TestObjectsHelper.getSatellitesBoIncomplete()));
	}
	
	@Test
	@DisplayName("Test scenario when message is decoded but position can't be triangulated")
	public void testMessageDecodedWithoutPosition() throws RebelsUnableToDecodeException, Exception {
		
		MessageResponseDTO response = service.getRevealedMessage(TestObjectsHelper.getSatelliteBo());
		
		assertTrue(StringUtils.isNotEmpty(response.getMessage()));
		assertFalse(Objects.nonNull(response.getPosition()));
	}
	
	@Test
	@DisplayName("Test successfull scenario for saving message from one satellite")
	public void testSuccessfullSaveMessage() throws Exception {
		
		when(messageRepository.save(any())).thenReturn(new SatelliteMessageTracking());
		String response = service.saveSatelliteTransmition(TestObjectsHelper.getSatelliteRequestOk());
		
		assertTrue(StringUtils.isNotEmpty(response));
	}
	
	@Test
	@DisplayName("Test error scenario for saving message from one satellite")
	public void testErrorSaveMessage() {
		
		when(messageRepository.save(any())).thenReturn(null);
		
		assertThrows(Exception.class, () -> service.saveSatelliteTransmition(TestObjectsHelper.getSatelliteRequestOk()));
	}

}
