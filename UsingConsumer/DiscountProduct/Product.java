package UsingConsumer.DiscountProduct;

public class Product {
    private String name;
    double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Product{" + " Name : " + name + " | Price : " + price + "}";
    }

}
