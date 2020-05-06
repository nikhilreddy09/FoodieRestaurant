package com.example.foodierestaurant;

import java.util.ArrayList;

public class Order {
    private ArrayList<String> names ;
    private String username;

    public ArrayList<String> getNames() {
        return names;
    }

    public void setNames(ArrayList<String> names) {
        this.names = names;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
