package com.example.customer;

import java.util.List;

public class Customer {

    int id;
    String name;
    List<String> schedule;

    public Customer(int id, String name, List<String> schedule) {
        this.id = id;
        this.name = name;
        this.schedule = schedule;
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

    public List<String> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<String> schedule) {
        this.schedule = schedule;
    }
}
