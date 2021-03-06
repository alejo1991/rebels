package com.mercadolibre.starWars.rebels.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class MessageUtils {
	
	public static final String WHITE_SPACE_SEPARATOR = " ";
	
	/**
	 * Validates if all elements in given list are not empty or null elements
	 * @param messages
	 * @return {@link Boolean}
	 */
	public static Boolean validateNotEmptyMessageList(List<String[]> messages) {
		for(String[] messageReceived: messages) {
			for(int i = 0; i < messageReceived.length; i++) {
				if(messageReceived[i] != null && messageReceived[i] != StringUtils.EMPTY) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Validates if all elements in given array are not empty or null elements
	 * @param messages
	 * @return {@link Boolean}
	 */
	public static Boolean validateNotEmptyMessageArray(String[] messages) {
			for(int i = 0; i < messages.length; i++) {
				if(messages[i] != null && messages[i] != StringUtils.EMPTY) {
					return true;
				}
			}
		
		return false;
	}

	/**
	 * Build and return a String array given two string arrays and deciding if may built in
	 * ascending or descending order according with parameters array size
	 * @param firstMessage
	 * @param secondMessage
	 * @return
	 */
    public static String[] joinMessage(String[] firstMessage, String[] secondMessage) {
        
    	int maxSize = Math.max(firstMessage.length, secondMessage.length);
        int minSize = Math.min(firstMessage.length, secondMessage.length);
        
        String[] resultingMessage = new String[maxSize];
        
        if (maxSize == minSize) {
            joinMessagesWithSameSize(firstMessage, secondMessage, minSize, resultingMessage);
        } else {
            joinMessageWithDifferentSize(firstMessage.length > secondMessage.length ? firstMessage : secondMessage, firstMessage.length > secondMessage.length ? secondMessage : firstMessage, maxSize, minSize, resultingMessage);
        }
        
        return resultingMessage;
    }
    
    private static void joinMessagesWithSameSize(String[] firstMessage, String[] secondMessage, int arraySize, String[] resultingMessage) {
        
    	for (int j = 0; j < arraySize; j++) {
    		
    		if(firstMessage[j].isEmpty() && secondMessage[j].isEmpty()) {
    			resultingMessage[j] = firstMessage[j];
    		} else {
    			resultingMessage[j] = getNotEmptyString(firstMessage[j], secondMessage[j]);
    		}
        }
    }

    private static void joinMessageWithDifferentSize(String[] messageMaxSize, String[] messageMinSize, int maxMessageSize, int minMessageSize, 
    		String[] resultingMessage) {
    	
        int sizeDiff = Math.max(maxMessageSize, minMessageSize) - Math.min(maxMessageSize, minMessageSize);
        
        if (isAscendingOrderBaseOnCompare(messageMaxSize, messageMinSize, maxMessageSize, sizeDiff)) {
            joinMessageAscendingOrder(messageMaxSize, messageMinSize, maxMessageSize, sizeDiff, resultingMessage);
        } else {
            joinMessageDescendingOrder(messageMaxSize, messageMinSize, maxMessageSize, minMessageSize, resultingMessage);
        }
    }

    /**
     * Add message to main message array taking into account if the order to add it
     * is descending
     * @param messageMaxSize
     * @param messageMinSize
     * @param maxMessageSize
     * @param minMessageSize
     * @param resultingMessage
     */
    private static void joinMessageDescendingOrder(String[] messageMaxSize, String[] messageMinSize, int maxMessageSize, int minMessageSize, 
    		String[] resultingMessage) {
    	
        for (int j = 0; j < maxMessageSize; j++) {
        	
            if (j < minMessageSize) {
            	
            	if (messageMaxSize[j].isEmpty() && messageMinSize[j].isEmpty()) {
            		resultingMessage[j] = messageMaxSize[j];
            	} else {
            		resultingMessage[j] = getNotEmptyString(messageMaxSize[j], messageMinSize[j]);
            	}
            } else {
                resultingMessage[j] = messageMaxSize[j];
            }

        }
    }

    /**
     * Add message to main message array taking into account if the order to add it
     * is ascending
     * @param messageMaxSize
     * @param messageMinSize
     * @param maxMessageSize
     * @param diffSize
     * @param resultingMessage
     */
    private static void joinMessageAscendingOrder(String[] messageMaxSize, String[] messageMinSize, int maxMessageSize,  
    		int diffSize, String[] resultingMessage) {
    	
        for (int j = 0; j < maxMessageSize; j++) {
        	
            if (j >= diffSize) {
            	
            	if (messageMaxSize[j].isEmpty() && messageMinSize[j - diffSize].isEmpty()) {
            		resultingMessage[j] = messageMaxSize[j];
            	} else {
            		resultingMessage[j] = getNotEmptyString(messageMaxSize[j], messageMinSize[j - diffSize]);
            	}
            } else {
                resultingMessage[j] = messageMaxSize[j];
            }

        }
    }

    /**
     * Compare two string arrays and determinate if the the order may be 
     * ascending (true) or not (false) for joining purposes
     * @param messageMaxSize
     * @param messageMinSize
     * @param maxArraySize
     * @param sizeDiff
     * @return {@link Boolean}
     */
    private static boolean isAscendingOrderBaseOnCompare(String[] messageMaxSize, String[] messageMinSize, int maxArraySize, int sizeDiff) {
        
        for (int j = sizeDiff; j < maxArraySize; j++) {
            if ((!messageMaxSize[j].isEmpty() && !messageMinSize[j - sizeDiff].isEmpty()) 
            		&& (!messageMaxSize[j].equals(messageMinSize[j - sizeDiff]))) {
            	return false;
            }

        }
        
        return true;
    }

    /**
     * Returns the first parameter if not empty. Otherwise returns the second one
     * @param first
     * @param second
     * @return {@link String}
     */
    private static String getNotEmptyString(String first, String second) {
        return !first.isEmpty() ? first : second;
    }

}
