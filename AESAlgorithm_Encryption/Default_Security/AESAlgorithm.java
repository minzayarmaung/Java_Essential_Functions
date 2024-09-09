package AESAlgorithm_Encryption.Default_Security;

import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESAlgorithm {
    public static final String KEY = "@minzayarmaung@!";
    public static final String IV = "#&1205*gh&pm@07A";

    public static String encryptString(String value) {
        try {
            // byte[] ivBytes = getHashByte(IV, "MD5");
            // byte[] keyBytes = getHashByte(KEY, "SHA-256");
            // byte[] keyBytes = getHashByte(KEY, "MD5");

            byte[] ivBytes = IV.getBytes("UTF-8");
            byte[] keyBytes = KEY.getBytes("UTF-8");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(ivBytes);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, paramSpec);
            // return new String (cipher.doFinal(value.getBytes("UTF-16")));
            return Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error Encrypting Value";
    }

    public static String decryptString(String value) {
        try {
            // byte[] ivBytes = getHashByte(IV, "MD5");
            // byte[] keyBytes = getHashByte(KEY, "SHA-256");
            // byte[] keyBytes = getHashByte(KEY, "MD5");

            byte[] ivBytes = IV.getBytes("UTF-8");
            byte[] keyBytes = KEY.getBytes("UTF-8");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(ivBytes);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, paramSpec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(value)), "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
