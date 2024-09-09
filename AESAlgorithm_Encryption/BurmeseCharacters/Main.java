package AESAlgorithm_Encryption.BurmeseCharacters;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String plainText = "Hello, this is a secret message.";
        String burmeseText = "k9znc+gXWvANwgtlssIu+dTBXgrrQCWIkfFxMQzvOaXHyZ4BSYuCZrUucPeu8GSG";
        String encryptedString = AESAlgorithm.encryptString(plainText);

        String decryptedString = AESAlgorithm.decryptString(encryptedString);

        System.out.println("Encrypted Value : " + encryptedString);
        System.out.println("Decrypted Value :" + decryptedString);

        System.out.println("Burmese Character : " + AESAlgorithm.decryptString(burmeseText));
    }
}
