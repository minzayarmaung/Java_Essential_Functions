package JavaNativeEncryption;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Encryption {

    public static String encodePass(String password) {
        String encodeData = "";
        if (!password.equals("")) {
            encodeData = Base64.getEncoder().encodeToString(
                    password.getBytes(StandardCharsets.UTF_8));
        }
        return encodeData;
    }
}
