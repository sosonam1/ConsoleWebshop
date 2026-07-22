package org.example;

import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Product> products = new ArrayList<>();

        products.add( new Product(1, "Laptop", 899.99, 10));
        products.add(new Product(2, "Mouse", 24.99, 30));
        products.add(new Product(3, "Keyboard", 49.99, 20));
        products.add(new Product(4, "WebCam", 19.99, 15));

        ShoppingCart cart = new ShoppingCart();

        boolean running = true;

        while (running) {

            IO.println("\n===== WEBSHOP =====");
            IO.println("1. View products");
            IO.println("2. Add product to shopping cart");
            IO.println("3. View shopping cart");
            IO.println("4. Remove product from shopping cart");
            IO.println("5. Checkout");
            IO.println("6. Exit");
            IO.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    IO.println("\nProducts:");

                    for (Product product : products) {
                        IO.println(
                                product.getId() + ". "
                                        + product.getName()
                                        + " - €" + product.getPrice()
                                        + " (Stock: " + product.getStock() + ")"
                        );
                    }

                    break;

                case 2:
                    IO.print("Enter the product number: ");
                    int id = scanner.nextInt();

                    IO.print("Enter the quantity: ");
                    int quantity = scanner.nextInt();

                    Product foundProduct = null;

                    for (Product product : products) {
                        if (product.getId() == id) {
                            foundProduct = product;
                            break;
                        }
                    }

                    if (foundProduct != null) {
                        if(foundProduct.reduceStock(quantity))
                            cart.addProduct(foundProduct, quantity);
                        IO.println("Product added to the shopping cart!");
                        IO.println("Remaining stock: " + foundProduct.getStock());
                    } else {
                        IO.println("Product not found.");
                    }

                    break;

                case 3:
                    cart.showCart();
                    break;

                case 4:
                    cart.showCart();
                    IO.println("Enter product ID to remove: ");
                    int RemoveId = scanner.nextInt();
                    cart.removeCartItem(RemoveId);
                    break;


                case 5:
                    IO.println("Total amount: €" + cart.getTotal());
                    IO.println("Thank you for your order!");
                    running = false;
                    break;

                case 6:
                    IO.println("Closing the webshop.");
                    running = false;
                    break;

                default:
                    IO.println("Invalid option. Please try again.");
            }
        }

    }
}
