package AESAlgorithm_Encryption.BurmeseCharacters;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESAlgorithm {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Text :");
        String text = sc.nextLine(); // Burmese text
        String key = "1234567890123456"; // 16-byte key for AES

        System.out.println("Original Text: " + text);

        // Encrypting the text
        String encrypted = encrypt(text, key);
        System.out.println("Encrypted Value: " + encrypted);

        // Decrypting the text
        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted Value: " + decrypted);
    }

    public static String encrypt(String strToEncrypt, String secret) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
    }

    public static String decrypt(String strToDecrypt, String secret) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(strToDecrypt));

        // Print bytes to verify decryption
        System.out.println("Decrypted Bytes: " + java.util.Arrays.toString(decryptedBytes));

        // Convert the decrypted bytes to String using UTF-8
        String decryptedString = new String(decryptedBytes, StandardCharsets.UTF_8);
        return decryptedString;
    }
}
