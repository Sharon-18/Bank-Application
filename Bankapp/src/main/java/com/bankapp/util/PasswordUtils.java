package com.bankapp.util;

import java.security.SecureRandom;

public class PasswordUtils {

    // Generate a temporary password
    public static String generateTemporaryPassword() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[8];
        random.nextBytes(bytes);
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString(); // Return plain text temporary password
    }

    // Generate a unique account number
    public static String generateAccountNumber() {
        SecureRandom random = new SecureRandom();
        long randomNumber = Math.abs(random.nextLong());
        return String.format("%016d", randomNumber).substring(0, 10); // Generate a 10-digit account number
    }

    // Example usage
    public static void main(String[] args) {
        String tempPassword = generateTemporaryPassword();

        System.out.println("Temporary Password: " + tempPassword);
    }
}
