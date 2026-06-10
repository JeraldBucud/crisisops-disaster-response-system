package drsinitial.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Provides basic SHA-256 hashing for password/privacy support.
 */
public class EncryptionService {

    public String hashText(String plainText) {
        if (plainText == null) {
            return "";
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(plainText.getBytes(StandardCharsets.UTF_8));
            StringBuilder builder = new StringBuilder();
            for (byte value : hash) {
                builder.append(String.format("%02x", value));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException exception) {
            return plainText;
        }
    }

    public boolean matches(String plainText, String storedValue) {
        if (storedValue == null) {
            return false;
        }
        return storedValue.equals(plainText) || storedValue.equals(hashText(plainText));
    }
}
