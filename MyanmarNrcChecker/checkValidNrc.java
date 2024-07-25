import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class checkValidNrc {

    public static String validNRCFormat(String check) {
        String nrcPattern = "^[0-9]{1,14}/[a-zA-Z]{2}[a-zA-Z]{2}[a-zA-Z]{2}\\([NTRD]\\)[0-9]{6}$";
        Pattern pattern = Pattern.compile(nrcPattern);
        Matcher matcher = pattern.matcher(check);
        if (matcher.matches()) {
            return "Valid NRC format";
        } else {
            return "Invalid NRC format";
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your NRC Number : ");
        String input = sc.nextLine();
        String result = validNRCFormat(input);
        System.out.println(result);
        sc.close();
    }
}
