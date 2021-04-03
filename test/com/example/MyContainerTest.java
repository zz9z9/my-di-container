package com.example;

import com.example.customer.CustomerService;
import com.example.customer.CustomerServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyContainerTest {
    @Test
    @DisplayName("컨테이너에서 주입해 준 CustomerService의 실제 구현체는 CustomerServiceImpl이다.")
    void testGetBean() {
        MyContainer container = new MyContainer(AppConfig.class);
        CustomerService service = container.getBean("customerService", CustomerService.class);

        assertEquals(CustomerServiceImpl.class, service.getClass());
    }
}
