import java.nio.charset.StandardCharsets;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AESAlgorithm {
    private static final String ENCRYPTION_KEY = "@Bank1234Bank123"; // 16-byte key for AES-128
    private static final String CIPHER = "AES/CBC/PKCS5Padding";

    public static String decryptABankData(String value) {
        try {
            byte[] decodedData = Base64.getDecoder().decode(value);
            int ivLength = 16; // AES block size is 16 bytes
            byte[] iv = new byte[ivLength];
            System.arraycopy(decodedData, 0, iv, 0, ivLength);
            byte[] encryptedData = new byte[decodedData.length - ivLength];
            System.arraycopy(decodedData, ivLength, encryptedData, 0, encryptedData.length);

            SecretKeySpec secretKey = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), "AES");
            IvParameterSpec ivParams = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance(CIPHER);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParams);
            byte[] decryptedData = cipher.doFinal(encryptedData);

            return new String(decryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encryptLMS(String strToEncrypt ) {
        final String ENCRYPTION_KEY = "@Bank1234";
        try {
            if (strToEncrypt != "") {
                byte[] iv = new byte[16];
                IvParameterSpec ivspec = new IvParameterSpec(iv);
                SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
                KeySpec spec = new PBEKeySpec(ENCRYPTION_KEY.toCharArray(), iv, 65536, 256);
                SecretKey tmp = factory.generateSecret(spec);
                SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
                return Base64.getEncoder()
                        .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
            }
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decryptLMS(String strToEncrypt ) {
        final String ENCRYPTION_KEY = "@Bank1234";
        try {
            byte[] iv = new byte[16];
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(ENCRYPTION_KEY.toCharArray(), iv, 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToEncrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }


}
