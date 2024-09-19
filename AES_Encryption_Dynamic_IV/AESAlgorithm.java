import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESAlgorithm {
    public static String encryptPlainText(String plainText) {
      String cipherText = "";
      try {
           String keyString = "@Bank12345678910";

           //Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy
           Security.setProperty("crypto.policy", "unlimited");

           Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
           SecretKeySpec keyspec = new SecretKeySpec(keyString.getBytes(), "AES");

           byte[] v = new byte[16];
           new SecureRandom().nextBytes(v);
           IvParameterSpec iv = new IvParameterSpec(v);

           cipher.init(Cipher.ENCRYPT_MODE, keyspec, iv);
           byte[] cipherTextByteArray = cipher.doFinal(plainText.getBytes());

           //appending iv to ciphertext without any additional libraries to handle the concatenation of the two byte arrays
           byte[] ivWithCipherTextByteArray = new byte[v.length + cipherTextByteArray.length];
           System.arraycopy(v, 0, ivWithCipherTextByteArray, 0, v.length);
           System.arraycopy(cipherTextByteArray, 0, ivWithCipherTextByteArray, v.length, cipherTextByteArray.length);

           cipherText = new String(Base64.getEncoder().encode(ivWithCipherTextByteArray));
      } catch (Exception e) {
          e.printStackTrace();
      }

      return cipherText;
 }

    public static String decryptCipherText(String cipherText) {
        String plainText="";
        try {
            String keyString = "@Bank1234";

            Security.setProperty("crypto.policy", "unlimited");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            SecretKeySpec keyspec = new SecretKeySpec(keyString.getBytes(), "AES");

            byte[] cipherTextByteArray = Base64.getDecoder().decode(cipherText);

            //initialize the IvParameterSpec with the first 16 bytes of the cipherText
            IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(cipherTextByteArray, 0, 16));

            //cipherText to decrypt is now the original one with the first 16 bytes removed (the IV used above)
            cipherTextByteArray = Arrays.copyOfRange(cipherTextByteArray, 16, cipherTextByteArray.length);

            cipher.init(Cipher.DECRYPT_MODE, keyspec, iv);
            plainText = new String(cipher.doFinal(cipherTextByteArray));
        } catch (Exception e) {
           e.printStackTrace();
        }

        return plainText;
    }

}
