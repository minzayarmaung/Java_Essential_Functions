package Instance_Vs_Static.BeginnerFriendlyExample;

public class Cars {

    // Instance variable
    private String brand;
    // Static variable
    private static int numberOfCars;

    // Constructor
    public Cars(String brand) {
        this.brand = brand;
        numberOfCars++;
    }

    // Instance Method
    public void displayBrand() {
        System.out.println("The Brand of the Car is: " + brand);
    }

    // Static Method
    public static void displayNumberOfCars() {
        System.out.println("Number of Car: " + numberOfCars);
    }

    public static void main(String[] args) {
        // Need to Create a new instances
        Cars myCar = new Cars("Toyota");
        Cars myCar1 = new Cars("Honda");
        Cars myCar2 = new Cars("Mercedes");

        myCar.displayBrand();
        myCar1.displayBrand();
        myCar2.displayBrand();

        // No need to Create an Instance
        Cars.displayNumberOfCars(); // Output will be 3
    }

}
