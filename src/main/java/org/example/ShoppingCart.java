package org.example;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    public List<CartItem> items = new ArrayList<>();

    public void addProduct(Product product, int quantity){
        items.add(new CartItem(product, quantity));

    }

    public void showCart(){
        if(items.isEmpty()){
            IO.println("Shopping cart is empty");
            return;
        }

        for(CartItem item : items){
            IO.println(
                    item.getProduct().getName() + "x" + item.getQuantity() + " = €" + item.getSubtotal()
            );
        }

        IO.println("Total: = € " + getTotal());
    }

    public void clearCart(){
        items.clear();
    }

    public ArrayList<CartItem> getItems(){
        return new ArrayList<>(items);
    }

    public double getTotal(){
        double total = 0;
        for (CartItem item : items){total += item.getSubtotal();}

        return total;
    }

    public void removeCartItem(int ProductId )
    {
        for(int i = 0; i < items.size(); i++)
        {
            CartItem item = items.get(i);

            if(item.getProduct().getId() == ProductId){
                item.getProduct().increaseStock(item.getQuantity());
                items.remove(i);

                IO.println("Product removed from the shopping cart.");
                return;
            }

        }
        IO.println("Product not found in shopping cart.");
    }

}
