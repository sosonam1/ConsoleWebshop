package org.example;

public class Product {

    private int id;
    private String name;
    private double price;
    private int stock;

    public Product(int id, String name, double price, int stock)
    {

        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;

    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public double getPrice()
    {
        return price;
    }

    public int getStock()
    {
        return stock;
    }

    public boolean reduceStock(int amount) {

        if (amount <= 0) {
            return false;
        }

        if (amount > stock) {
            return false;
        }

        stock -= amount;
        return true;
    }

    public void increaseStock(int amount)
    {
        stock += amount;
    }


}
