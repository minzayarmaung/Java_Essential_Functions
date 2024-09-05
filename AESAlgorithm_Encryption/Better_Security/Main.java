package AESAlgorithm_Encryption.Better_Security;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your Text to Encrypt : ");
        String plainText = sc.nextLine();
        String encryptedString = AESAlgorithm.encryptString(plainText);

        String decryptedString = AESAlgorithm.decryptString(encryptedString);

        System.out.println("Encrypted Value : " + encryptedString);
        System.out.println("Decrypted Value :" + decryptedString);
    }
}
