package MyanmarTimeZone;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class getMyanmarTimeZone {

    public static String getCurrentTime() {
        LocalDateTime date = LocalDateTime.now(ZoneId.of("Asia/Rangoon"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String currentTime = date.format(formatter);
        return currentTime;
    }

    public static void main(String[] args) {
        System.out.println(getCurrentTime());
    }
}
