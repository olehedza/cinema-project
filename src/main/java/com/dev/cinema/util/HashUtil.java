package com.dev.cinema.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.springframework.stereotype.Component;

@Component
public class HashUtil {
    private static final String HASHING_ALGORITHM = "SHA-512";

    public String getPasswordDigest(String password, byte[] salt) {
        StringBuilder hashString = new StringBuilder();

        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASHING_ALGORITHM);
            messageDigest.update(salt);
            byte[] digestBytes = messageDigest.digest(password.getBytes());

            for (byte b : digestBytes) {
                hashString.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(String
                    .format("Hashing algorithm '%s' not found.", HASHING_ALGORITHM), e);
        }
        return hashString.toString();
    }

    public byte[] getSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] saltBytes = new byte[16];
        secureRandom.nextBytes(saltBytes);
        return saltBytes;
    }
}
