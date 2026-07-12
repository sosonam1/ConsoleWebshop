package org.example;

public class Product {

    private int Id;
    private String Name;
    private double Price;
    private int Stock;

    public Product(int id, String name, double price, int stock)
    {

        this.Id = id;
        this.Name = name;
        this.Price = price;
        this.Stock = stock;

    }

    public int getId(){return Id;}
    public String getName(){return Name;}
    public double getPrice(){return Price;}
    public int getStock(){return Stock;}

  public void reduceStock(int amount){
        if(amount > Stock){
            IO.println("Not enough stock");
        }
        Stock -= amount;
  }
}
