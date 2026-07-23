package org.example;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<Order> orders = new ArrayList<>();
    private Role role;

    public User(String username, String password, Role role)
    {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername()
    {
        return username;
    }

    public Role getRole(){
        return role;
    }

    public boolean checkPassword(String enteredPassword)
    {
        return password.equals(enteredPassword);
    }

    public void addOrder(Order order)
    {
        orders.add(order);
    }

    public void showOrderHistory(){
        if(orders.isEmpty()){
            IO.println("You have no previous orders.");
            return;
        }

        IO.println("\n======= ORDER HISTORY ======");

        for (Order order : orders) {
            IO.println(
                    "Order #" + order.getOrderId()
                            + " - €" + order.getTotal()
            );
        }

        IO.println("\n=======ORDER HISTORY ======");
    }
}
