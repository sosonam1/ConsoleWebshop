package org.example;

import java.util.ArrayList;

public class AuthenticationService {
    private ArrayList<User> users = new ArrayList<>();

    public AuthenticationService(){
        users.add(new User("admin", "admin123", Role.Admin));
        }




    public User findUser(String username)
    {
        for(User user : users)
        {
            if(user.getUsername().equalsIgnoreCase(username))
            {
                return user;
            }
        }
        return null;
    }

    public boolean register(String username, String password)
    {
        if(findUser(username)!= null)
        {
            IO.println("Username already exists.");
            return false;
        }

        users.add(new User(username, password, Role.Customer));
        IO.println("Account created successfully.");
        return true;
    }

    public User login(String username, String password)
    {
        User user = findUser(username);

        if(user != null && user.checkPassword(password))
        {
            return user;
        }

        IO.println("Invalid username or password.");
        return null;
    }

}

