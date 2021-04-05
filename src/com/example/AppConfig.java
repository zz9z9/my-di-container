package com.example;

import com.example.customer.CustomerService;
import com.example.customer.CustomerServiceImpl;
import com.example.planner.PlannerService;
import com.example.planner.PlannerServiceImpl;

public class AppConfig {
    public CustomerService customerService() {
        return new CustomerServiceImpl(plannerService());
    }

    public PlannerService plannerService() {
        return new PlannerServiceImpl();
    }
}
