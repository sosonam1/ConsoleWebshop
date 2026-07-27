# Java Console Webshop

A simple console-based webshop application written in Java. The project was created to practise object-oriented programming, Maven, collections, user authentication, stock management, and unit testing with JUnit.

## Features

- View all available products
- Add new products
- Find products by ID
- Remove products
- Update product stock
- Prevent negative stock values
- Add products to a shopping cart
- Remove products from the shopping cart
- Display the shopping cart
- Calculate the total price
- Place an order
- Basic user authentication
- Support for user roles
- Unit tests for the product service

## Technologies

- Java 26
- Maven
- JUnit 5
- IntelliJ IDEA

## Project Structure

```text
src
├── main
│   ├── java
│   │   └── org.example
│   │       ├── AuthenticationService.java
│   │       ├── CartItem.java
│   │       ├── Main.java
│   │       ├── Order.java
│   │       ├── Product.java
│   │       ├── ProductService.java
│   │       ├── Role.java
│   │       ├── ShoppingCart.java
│   │       └── User.java
│   └── resources
│
└── test
    └── java
        └── org.example
            └── ProductServiceTest.java
