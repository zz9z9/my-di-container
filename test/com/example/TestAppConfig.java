package com.example;

import com.example.customer.CustomerService;
import com.example.customer.CustomerServiceImpl;
import com.example.planner.PlannerServiceImpl;

public class TestAppConfig {
    public CustomerService customerService() {
        return new CustomerServiceImpl(new PlannerServiceImpl());
    }

    public CustomerService customerService2() {
        return new CustomerServiceImpl(new PlannerServiceImpl());
    }
}