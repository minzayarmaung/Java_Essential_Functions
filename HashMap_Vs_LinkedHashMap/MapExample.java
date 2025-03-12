import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapExample {
    public static void main(String[] args) {
        // HashMap (Messy Box)
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("A", "Apple");
        hashMap.put("B", "Banana");
        hashMap.put("C", "Cherry");
        System.out.println("HashMap: " + hashMap); // Order may change

        // LinkedHashMap (Neatly Arranged Box)
        Map<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("A", "Apple");
        linkedHashMap.put("B", "Banana");
        linkedHashMap.put("C", "Cherry");
        System.out.println("LinkedHashMap: " + linkedHashMap); // Order remains
    }
}
