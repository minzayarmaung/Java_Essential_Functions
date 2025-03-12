public class Optional {
    public static void main(String[] args) {
        String name = getName();
        if (name != null) {
            System.out.println(name.toUpperCase());
        } else {
            System.out.println("No Name Provided !");
        }
    }

    public static String getName() {
        return null;
    }
}
