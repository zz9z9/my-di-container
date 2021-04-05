package com.example;

import com.example.container.Container;
import com.example.container.MyContainer;
import com.example.customer.Customer;
import com.example.customer.CustomerService;
import com.example.exception.NoSuchBeanDefinitionException;

import java.util.ArrayList;
import java.util.List;

public class MyApp {
    public static void main(String[] args) throws NoSuchBeanDefinitionException {
        Container container = new MyContainer(AppConfig.class);
        CustomerService customerService = container.getBean("customerService", CustomerService.class);
        List<String> schedule = new ArrayList<>();
        Customer newCustomer = new Customer(1, "이재윤", schedule);

        customerService.join(newCustomer);
        customerService.requestDressTour(newCustomer);

        System.out.println("나의 스케줄 : "+customerService.findCustomer(newCustomer.getId()).getSchedule());
    }
}
