package Encryption;

import java.util.Scanner;

public class UserInput {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your password : ");
        byte[] passBytes = sc.nextLine().getBytes();

        String encodedPw = BaseEncryptionAndDecryption.encode(passBytes);
        System.out.println(encodedPw);

        System.out.println("Do you want to Decrypt your password? : Y or N");
        String choice = sc.nextLine();

        if (choice.equalsIgnoreCase("Y")) {
            System.out.println(BaseEncryptionAndDecryption.decode(encodedPw));
        } else {
            System.out.println("Bye");
        }

        sc.close();
    }

}
