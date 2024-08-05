package Instance_Vs_Static.DevelopersExample;

public class MathUtils {

    // Static Method to Calculate The Factorial of a Number
    public static long factorial(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Number must be non-negative.");
        }

        long result = 1;
        for (int i = 1; i <= number; i++) {
            result *= i;
        }
        return result;
    }

    // Instance Method to Calculate the Square of a Number
    public int square(int number) {
        return number * number;
    }

    public static void main(String[] args) {
        // Using Static Method to Calculate The Factorial of a Number without creating
        // an Instance
        long fact = MathUtils.factorial(5);
        System.out.println("Factorial of the number : " + fact);

        // Using Instance Method to Calculate by Creating an Instance
        MathUtils mathUtils = new MathUtils();
        int square = mathUtils.square(5);
        System.out.println("Square of the number " + square);

    }
}
