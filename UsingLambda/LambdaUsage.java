import java.util.ArrayList;
import java.util.Arrays;

public class LambdaUsage {

    public ArrayList<Integer> withoutUsingLambda() {
        ArrayList<Integer> numbers = new ArrayList<>();
        Integer[] numbersArray = { 0, 1, 2, 3, 4, 5, 6, 7 };
        numbers.addAll(Arrays.asList(numbersArray));
        return numbers;
    }

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
