import java.util.ArrayList;
import java.util.Arrays;

public class LambdaUsage {

    // Simple Usage of the Lambda
    public ArrayList<Integer> withoutUsingLambda() {
        ArrayList<Integer> numbers = new ArrayList<>();
        Integer[] numbersArray = { 0, 1, 2, 3, 4, 5, 6, 7 };
        numbers.addAll(Arrays.asList(numbersArray));
        return numbers;
    }

    // You can also use Lambda with Consumer Interface
    /* What is Consumer Interface in Java ?? */
    // Here 's the Link you can read in from StackOverFlow'

    String link = "https://stackoverflow.com/questions/28417262/when-and-why-would-you-use-javas-supplier-and-consumer-interfaces#:~:text=So%20in%20layman%20terms%2C%20a,does%20some%20operations%20on%20them.";

    public static void main(String[] args) {
        LambdaUsage lu = new LambdaUsage();
        ArrayList<Integer> numbers = lu.withoutUsingLambda();

        // Without Using Lambda Function -- We will use Simple For Loop
        System.out.println("Without Using Lambda Function");
        for (Integer i : numbers) {
            System.out.print(i + " , ");
        }

        System.out.println("\nUsing Lambda Function");
        // Now We will Use Lambda Function
        numbers.forEach((i) -> System.out.print(i + " , "));
    }
}
