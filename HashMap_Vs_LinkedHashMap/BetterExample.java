import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BetterExample {
    public static void main(String[] args) {
        // HashMap Example (Unordered)
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("A", "Apple");
        hashMap.put("B", "Banana");
        hashMap.put("C", "Cherry");
        hashMap.put("D", "Date");

        System.out.println("Initial HashMap: " + hashMap); // Unordered

        // Remove an entry
        hashMap.remove("B");
        System.out.println("After Removing 'B' from HashMap: " + hashMap);

        // Add a new entry
        hashMap.put("E", "Elderberry");
        System.out.println("After Adding 'E' to HashMap: " + hashMap);

        // Update an existing entry
        hashMap.put("C", "Coconut");
        System.out.println("After Updating 'C' in HashMap: " + hashMap);

        System.out.println("\n-------------------------------------\n");

        // LinkedHashMap Example (Ordered)
        Map<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("A", "Apple");
        linkedHashMap.put("B", "Banana");
        linkedHashMap.put("C", "Cherry");
        linkedHashMap.put("D", "Date");

        System.out.println("Initial LinkedHashMap: " + linkedHashMap); // Ordered

        // Remove an entry
        linkedHashMap.remove("B");
        System.out.println("After Removing 'B' from LinkedHashMap: " + linkedHashMap);

        // Add a new entry
        linkedHashMap.put("E", "Elderberry");
        System.out.println("After Adding 'E' to LinkedHashMap: " + linkedHashMap);

        // Update an existing entry
        linkedHashMap.put("C", "Coconut");
        System.out.println("After Updating 'C' in LinkedHashMap: " + linkedHashMap);
    }
}
