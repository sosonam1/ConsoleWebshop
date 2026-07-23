package org.example;

import java.util.ArrayList;

public class Order {
    private int orderId;
    private ArrayList<CartItem> items;
    private double total;

    public Order(int orderId, ArrayList<CartItem> items, double total)
    {
        this.orderId =orderId;
        this.items = items;
        this.total = total;
    }

    public int getOrderId(){
        return orderId;
    }

    public ArrayList<CartItem> getItems(){
        return items;
    }

    public double getTotal(){
        return total;
    }

    public void showOrder(){
        IO.println("\nOrder #" + orderId);

        for(CartItem item : items){
            IO.println(
                    item.getProduct().getName() +
                            "x" + item.getQuantity() +
                            " = €" + item.getSubtotal()
            );
        }
        IO.println("Total: €" + total);
    }
}
