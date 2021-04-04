package com.example.customer;

public class Customer {

    int id;
    String name;
    boolean isAccompanyPlanner;

    public Customer(int id, String name, boolean isAccompanyPlanner) {
        this.id = id;
        this.name = name;
        this.isAccompanyPlanner = isAccompanyPlanner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAccompanyPlanner() {
        return isAccompanyPlanner;
    }

    public void setAccompanyPlanner(boolean accompanyPlanner) {
        isAccompanyPlanner = accompanyPlanner;
    }
}
