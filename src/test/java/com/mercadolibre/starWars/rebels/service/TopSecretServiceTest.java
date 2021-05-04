package com.mercadolibre.starWars.rebels.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.mercadolibre.starWars.rebels.dto.response.MessageResponseDTO;
import com.mercadolibre.starWars.rebels.exception.RebelsUnableToDecodeException;
import com.mercadolibre.starWars.rebels.helper.TestObjectsHelper;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TopSecretServiceTest {
	
	@InjectMocks
	private TopSecretService service;
	
	@Test
	@DisplayName("Test successfull scenario for decoded message and position triangulated")
	public void testSuccessfullResponse() throws RebelsUnableToDecodeException, IllegalArgumentException, Exception {
		MessageResponseDTO response = service.getRevealedMessage(TestObjectsHelper.getSatellitesRequestOk(), TestObjectsHelper.getSatellitesBoCompleteOk());
		
		assertEquals(response.getPosition().getPositionX(), -58.31525f);
		assertEquals(response.getPosition().getPositionY(), -69.551414f);
		assertEquals(response.getMessage(), "este es un mensaje auxilio");
	}
	
	@Test
	@DisplayName("Test successfull scenario for decoded message and position triangulated when incoming message lenght is different")
	public void testSuccessfullResponseDiffMessageLenght() throws RebelsUnableToDecodeException, IllegalArgumentException, Exception {
		MessageResponseDTO response = service.getRevealedMessage(TestObjectsHelper.getSatellitesRequestOkDiffMessageLenght(), TestObjectsHelper.getSatellitesBoCompleteOk());
		
		assertEquals(response.getPosition().getPositionX(), -58.31525f);
		assertEquals(response.getPosition().getPositionY(), -69.551414f);
		assertEquals(response.getMessage(), "este es un mensaje de auxilio");
	}
	
	@Test
	@DisplayName("Test expected error scenario for not decoded message and position triangulated")
	public void testUnableToDecodeByMessage() throws RebelsUnableToDecodeException, IllegalArgumentException, Exception {
		
		assertThrows(RebelsUnableToDecodeException.class, 
				() -> service.getRevealedMessage(TestObjectsHelper.getSatellitesRequestIncompleteMessage(), TestObjectsHelper.getSatellitesBoCompleteOk()));
	}
	
	@Test
	@DisplayName("Test expected error scenario for not decoded message and position not triangulated")
	public void testUnableToDecodeByPositionAndMessage() throws RebelsUnableToDecodeException, IllegalArgumentException, Exception {
		
		assertThrows(IllegalArgumentException.class, 
				() -> service.getRevealedMessage(TestObjectsHelper.getSatellitesRequestIncompleteMessageAndDistance(), TestObjectsHelper.getSatellitesBoCompleteOk()));
	}
	
	@Test
	@DisplayName("Test expected error scenario for position not triangulated")
	public void testUnableToDecodeByPosition() throws RebelsUnableToDecodeException, IllegalArgumentException, Exception {
		
		assertThrows(IllegalArgumentException.class, 
				() -> service.getRevealedMessage(TestObjectsHelper.getSatellitesRequestIncompleteDistanceInfo(), TestObjectsHelper.getSatellitesBoCompleteOk()));
	}
	
	

}
