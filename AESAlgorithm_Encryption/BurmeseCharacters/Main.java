package AESAlgorithm_Encryption.BurmeseCharacters;

public class Main {
    public static void main(String[] args) {
        String plainText = "Hello, this is a secret message.";
        String encryptedString = AESAlgorithm.encryptString(plainText);

        String decryptedString = AESAlgorithm.decryptString(encryptedString);

        System.out.println("Encrypted Value : " + encryptedString);
        System.out.println("Decrypted Value :" + decryptedString);
    }
}
