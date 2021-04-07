package com.example;

import com.example.customer.CustomerRepository;
import com.example.customer.CustomerService;
import com.example.customer.CustomerServiceImpl;
import com.example.customer.TemporaryCustomerRepository;
import com.example.planner.PlannerService;
import com.example.planner.PlannerServiceImpl;

public class AppConfig extends CommonConfig {

    public CustomerService customerService() {
        CustomerService customerService = new CustomerServiceImpl(customerRepository(), plannerService());
        return super.getBean(customerService);
    }

    public PlannerService plannerService() {
        PlannerService plannerService = new PlannerServiceImpl(customerRepository());
        return super.getBean(plannerService);
    }

    public CustomerRepository customerRepository() {
        CustomerRepository customerRepository = new TemporaryCustomerRepository();
        return super.getBean(customerRepository);
    }
}
