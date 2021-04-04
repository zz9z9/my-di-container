package com.example;

import com.example.customer.CustomerService;
import com.example.customer.CustomerServiceImpl;

public class TestAppConfig {
    public CustomerService customerService() {
        return new CustomerServiceImpl();
    }
}