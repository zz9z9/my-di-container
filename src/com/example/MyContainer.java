package com.example;

import com.example.customer.CustomerService;
import com.example.customer.CustomerServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class MyContainer {
    Map<String, Object> map = new HashMap<>();
    Map<Class, Object> map2 = new HashMap<>();

    public MyContainer(Class<?> clazz) {
        map.put("customerService", new CustomerServiceImpl());
        map2.put(CustomerService.class, new CustomerServiceImpl());
    }

    <T> T getBean(String name, Class<T> type) {
        Object obj = map.get(name);
        return (T) obj;
    }

    <T> T getBean(Class<T> type) {
        Object obj = map2.get(type);
        return (T) obj;
    }

    public static void main(String[] args) {
        MyContainer container = new MyContainer(AppConfig.class);
        CustomerService service = container.getBean("customerService", CustomerService.class);
        CustomerService service2 =  container.getBean(CustomerService.class);

        System.out.println("service :: "+service.getClass());

        service.test();
        service2.test();
    }
}
