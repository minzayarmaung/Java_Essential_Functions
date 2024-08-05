package Instance_Vs_Static.BeginnerFriendlyExample;

public class Cars {

    // Instance variable
    private String brand;

    // Constructor
    public Cars(String brand) {
        this.brand = brand;
    }

    // Instance Method
    public void displayBrand() {
        System.out.println("The Brand of the Car is: " + brand);
    }

    public static void main(String[] args) {
        Cars myCar = new Cars("Toyota");
        myCar.displayBrand();
    }

}
