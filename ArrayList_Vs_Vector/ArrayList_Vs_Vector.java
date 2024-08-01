package ArrayList_Vs_Vector;

import java.util.ArrayList;
import java.util.Vector;

public class ArrayList_Vs_Vector {

    // Links to Read about ArrayList vs Vector
    String link = "https://www.geeksforgeeks.org/vector-vs-arraylist-java/";
    String link1 = "https://stackoverflow.com/questions/22069923/arraylist-vs-vector-which-one-to-use";
    String link2 = "https://www.javatpoint.com/difference-between-arraylist-and-vector";

    public static void main(String[] args) {

        // Using ArrayList
        System.out.print("\nUsing ArrayList :");
        ArrayList<String> list = new ArrayList<String>();
        list.add("Apple");
        list.add("Banana");
        list.add("Bacon");
        list.add("Cookie");

        // Using Lambda instead of Simple For Loop
        list.forEach((n) -> System.out.println(n + " , "));

        try {
            for (String item : list) {
                if (item.equals("Bacon")) {
                    list.remove(item);
                }
            }
        } catch (Exception e) {
            System.out.println("Concurrent Modification Exception in VectorList : " + e.getMessage());
        }

        // Using Vector
        System.out.println("\nUsing Vector:");
        Vector<String> vector = new Vector<String>();
        vector.add("Fruit");
        vector.add("Drinks");
        vector.add("Vegetables");
        vector.add("Alcohol");

        System.out.print("\nUsing Vector : ");
        vector.forEach((i) -> System.out.println(i + " , "));

        try {
            for(String item : vector){
                if(item.equals("Drinks")){
                    vector.remove(item);
                }
            }
        } catch (Exception e) {
            System.out.println("Concurrent Modification Exception in VectorList : " + e.getMessage());
        }

    }
}
