package com.example;

import com.example.customer.CustomerRepository;
import com.example.customer.CustomerService;
import com.example.customer.CustomerServiceImpl;
import com.example.customer.TemporaryCustomerRepository;
import com.example.planner.PlannerService;
import com.example.planner.PlannerServiceImpl;

public class AppConfig extends CommonConfig {
    public CustomerService customerService() {

        String beanName = "customerService";
        if(!isExist(beanName)) {
            CustomerService customerService = new CustomerServiceImpl(customerRepository(), plannerService());
            createBean(beanName, customerService);
        }

        return (CustomerService) getBean(beanName);
    }

    public PlannerService plannerService() {

        String beanName = "plannerService";
        if(!isExist(beanName)) {
            PlannerService plannerService = new PlannerServiceImpl(customerRepository());
            createBean(beanName, plannerService);
        }

        return (PlannerService) getBean(beanName);
    }

    public CustomerRepository customerRepository() {

        String beanName = "customerRepository";
        if(!isExist(beanName)) {
            CustomerRepository customerRepository = new TemporaryCustomerRepository();
            createBean(beanName, customerRepository);
        }

        return (CustomerRepository) getBean(beanName);
    }
}
