package com.bankapp.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordUtils {

    // Hash a password using SHA-256
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // Generate a temporary password
    public static String generateTemporaryPassword() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[8];
        random.nextBytes(bytes);
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    // Verify if the provided password matches the hashed password
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        return hashPassword(plainPassword).equals(hashedPassword);
    }

    // Generate a unique account number
    public static String generateAccountNumber() {
        SecureRandom random = new SecureRandom();
        long randomNumber = Math.abs(random.nextLong());
        return String.format("%016d", randomNumber).substring(0, 10); // Generate a 10-digit account number
    }
    
    public static void main(String[] args) {
    	String pass = hashPassword("Hello");
    	System.out.println(pass);
    	System.out.print(verifyPassword("Hello", "185f8db32271fe25f561a6fc938b2e264306ec304eda518007d1764826381969"));
    }
}
