package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        AuthenticationService authenticationService = new AuthenticationService();

        ProductService productService = new ProductService();

        User loggedInUser = null;

        while (loggedInUser == null)
        {
            IO.println("\n====== USER AUTHENTICATION ======");
            IO.println("1. Login");
            IO.println("2. Register");
            IO.println("3. Exit");
            IO.println("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice)
            {
                case 1:
                    IO.println("Username: ");
                    String loginUsername = scanner.nextLine();

                    IO.println("Password: ");
                    String loginPassword = scanner.nextLine();

                    loggedInUser = authenticationService.login(loginUsername, loginPassword);
                    if(loggedInUser != null)
                    {
                        if(loggedInUser.getRole()== Role.Admin){
                            IO.println("Welcome Admin!");
                        }
                        else{
                            IO.println("Welcome " + loggedInUser.getUsername() +"!");
                        }
                    }

                    break;

                case 2:
                    IO.println("Choose a username: ");
                    String newUsername = scanner.nextLine();

                    IO.println("Choose a password: ");
                    String newPassword = scanner.nextLine();

                    authenticationService.register(newUsername, newPassword);
                    break;

                case 3:
                    IO.println("Closing the application");
                    scanner.close();
                    return;

                default:
                    IO.println("invalid option");

            }
        }

        ArrayList<Product> products = new ArrayList<>();

        products.add( new Product(1, "Laptop", 899.99, 10));
        products.add(new Product(2, "Mouse", 24.99, 30));
        products.add(new Product(3, "Keyboard", 49.99, 20));
        products.add(new Product(4, "WebCam", 19.99, 15));


        ShoppingCart cart = new ShoppingCart();
        int nextProductId = 5;
        int nextOrderId = 1001;

        boolean running = true;

        while (running) {

            if(loggedInUser.getRole() == Role.Admin){
                IO.println("\n===== ADMIN MENU =====");
                IO.println("Logged in as: " + loggedInUser.getUsername());
                IO.println("1. View products");
                IO.println("2. Add product");
                IO.println("3. Remove product");
                IO.println("4. Update stock");
                IO.println("5. Logout");
            }
            else{
                IO.println("\n===== CUSTOMER MENU =====");
                IO.println("Logged in as: " + loggedInUser.getUsername());
                IO.println("1. View products");
                IO.println("2. Add product to shopping cart");
                IO.println("3. View shopping cart");
                IO.println("4. Remove product from shopping cart");
                IO.println("5. Checkout");
                IO.println("6. View order history");
                IO.println("7. Exit");

            }

            IO.print("Choose an option: ");

            int choice = scanner.nextInt();

            //admin switch

            if(loggedInUser.getRole() == Role.Admin){

                switch (choice){
                    case 1:
                        productService.showProducts(products);
                        break;

                    case 2:
                        IO.println("Enter product name: ");
                        String productName = scanner.nextLine();

                        IO.println("Enter product price: ");
                        double productPrice = scanner.nextDouble();

                        IO.println("Enter product stock: ");
                        int productStock = scanner.nextInt();
                        scanner.nextLine();

                        Product newProduct = new Product(nextProductId, productName,
                                productPrice, productStock);

                        productService.addProduct(products, newProduct);

                        nextProductId++;
                        break;

                    case 3:
                        productService.showProducts(products);

                        IO.println("Enter product ID to remove: ");
                        int removeProductId = scanner.nextInt();
                        scanner.nextLine();

                        productService.removeProduct(products, removeProductId);
                        break;

                    case 4:
                        productService.showProducts(products);

                        IO.println("Enter product Id: ");
                        int updateProductId = scanner.nextInt();
                        scanner.nextLine();


                        IO.println("Enter new stock amount: ");
                        int newStock= scanner.nextInt();
                        scanner.nextLine();

                        productService.updateStock(products, updateProductId, newStock);
                        break;

                    case 5:
                        IO.println("Admin logged out.");
                        running = false;
                        break;

                    default:
                        IO.println("Invalid option");



                }



            } else {

                switch (choice) {

                    case 1:
                        IO.println("\nProducts:");
                        productService.showProducts(products);
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
                        if(cart.getItems().isEmpty())
                        {
                            IO.println("Your shopping cart is empty");
                            break;
                        }
                        cart.showCart();
                        double total = cart.getTotal();
                        Order order = new Order(nextOrderId, cart.getItems(), total);
                        loggedInUser.addOrder(order);

                        IO.println("Order #" + nextOrderId + " has been created.");
                        IO.println("Total amount: €" + total);
                        IO.println("Thank you for your order!");

                        nextOrderId++;
                        cart.clearCart();
                        break;

                    case 6:
                        loggedInUser.showOrderHistory();
                        break;

                    case 7:
                        IO.println("Closing the webshop.");
                        running = false;
                        break;

                    default:
                        IO.println("Invalid option. Please try again.");
                }
            }
        }


    }
}
