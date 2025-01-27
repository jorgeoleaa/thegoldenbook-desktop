package com.pinguela.thegoldenbook.ui.desktop.utils;

import java.util.Random;

public class PasswordGenerator {
	
	public static String generateRandomPassword() {
		
		Random random = new Random();
		int minLength = 8;
		int maxLength = 18;
		int length = minLength + random.nextInt(maxLength - minLength + 1); 
		
        int lowerBound = 33;  
        int upperBound = 126; 

        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int asciiValue = lowerBound + random.nextInt(upperBound - lowerBound + 1);
            password.append((char) asciiValue);
        }

        return password.toString();
    }

}
