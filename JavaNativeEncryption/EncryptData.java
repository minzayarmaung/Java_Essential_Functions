package JavaNativeEncryption;

public class EncryptData {

    public static void main(String[] args) {
        String password = "testingpassword123";
        String encodedPassword = Encryption.encodePass(password);
        System.out.println("Encoded Password: " + encodedPassword);
    }

}
