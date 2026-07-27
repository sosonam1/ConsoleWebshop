package org.example;

import java.util.ArrayList;

public class ProductService {

    public void showProducts(ArrayList<Product> products){
        IO.println("\nProduct");
        for(Product product : products){
            IO.println(product.getName()
             + " - €" + product.getPrice()
                    + " (Stock: " + product.getStock() + ")");
        }
    }


    public Product findProductById(ArrayList<Product> products, int id){
        for(Product product : products){
            if(product.getId() == id){
                return product;
            }
        }
        return null;
    }

  
    public void addProduct(ArrayList<Product> products, Product product){
        products.add(product);

        IO.println("Product added successfully");

    }

    public void removeProduct(ArrayList<Product> products, int id){

        Product product = findProductById(products, id);

        if(product == null){
            IO.println("Product not found");
            return;
        }

        products.remove(product);
        IO.println("Product removed successfully");




    }

    public void updateStock(ArrayList<Product> products, int id, int newStock){
        Product product = findProductById(products, id);

        if(product == null){
            IO.println("Product not found.");
            return;
        }

        if(newStock < 0){
            IO.println("Stock cannot be negative");
            return;
        }

        product.setStock(newStock);

        IO.println("Stock updated successfully");
        IO.println("New stock: " + product.getStock());
    }
}
