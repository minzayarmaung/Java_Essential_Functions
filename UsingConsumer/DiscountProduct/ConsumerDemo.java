package UsingConsumer.DiscountProduct;

import java.util.List;
import java.util.function.ObjDoubleConsumer;

public class ConsumerDemo {
    public static void main(String[] args) {

        var Products = List.of(
                new Product("Samsung S24 Ultra", 10000),
                new Product("iPhone 15 Pro Max", 13000),
                new Product("Macbook Pro M3 Max", 860000));

        // Using Lambda
        ObjDoubleConsumer<Product> priceDiscount = (Product products, double percentage) -> {
            double newPrice = products.price * (1 - (percentage / 100));
            products.setPrice(newPrice);
        };

        // Without Using Lambda

        Products.forEach(products -> priceDiscount.accept(products, 10));

        Products.forEach(System.out::println);

    }
}
