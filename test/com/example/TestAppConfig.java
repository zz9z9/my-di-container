package com.example;

import com.example.customer.CustomerRepository;
import com.example.customer.CustomerService;
import com.example.customer.CustomerServiceImpl;
import com.example.customer.TemporaryCustomerRepository;
import com.example.planner.PlannerService;
import com.example.planner.PlannerServiceImpl;

public class TestAppConfig {
    public CustomerService customerService() {
        return new CustomerServiceImpl(customerRepository(), plannerService());
    }

    public CustomerService customerService2() {
        return new CustomerServiceImpl(customerRepository(), plannerService());
    }

    public PlannerService plannerService() {
        return new PlannerServiceImpl(customerRepository());
    }

    public CustomerRepository customerRepository() {
        return new TemporaryCustomerRepository();
    }
}