package com.example;

import com.example.customer.CustomerService;
import com.example.customer.CustomerServiceImpl;
import com.example.exception.NoUniqueBeanDefinitionException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyContainer {
    Map<String, Object> map = new HashMap<>();
    Map<Class, List<Object>> map2 = new HashMap<>();

    public MyContainer(Class<?> clazz) {
        List<Object> li = new ArrayList<>();
        li.add(new CustomerServiceImpl());
        li.add(new CustomerServiceImpl());

        map.put("customerService", new CustomerServiceImpl());
        map2.put(CustomerService.class, li);
    }

    <T> T getBean(String name, Class<T> type) {
        Object obj = map.get(name);
        return (T) obj;
    }

    <T> T getBean(Class<T> type) throws NoUniqueBeanDefinitionException {
        List<Object> obj = map2.get(type);
        if(obj.size() > 1) {
            throw new NoUniqueBeanDefinitionException();
        }

        return (T) obj;
    }

    public static void main(String[] args) throws NoUniqueBeanDefinitionException {
        MyContainer container = new MyContainer(AppConfig.class);
        CustomerService service = container.getBean("customerService", CustomerService.class);
        CustomerService service2 =  container.getBean(CustomerService.class);

        System.out.println("service :: "+service.getClass());

        service.test();
        service2.test();
    }
}
