package com.example.customer;

import com.example.planner.PlannerService;
import com.example.planner.PlannerServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceTest {

    CustomerRepository customerRepository = new TemporaryCustomerRepository();
    PlannerService plannerService = new PlannerServiceImpl(customerRepository);
    CustomerService customerService = new CustomerServiceImpl(customerRepository, plannerService);

    @Test
    @DisplayName("새로운 고객 등록이 정상적으로 되는지 확인")
    void registerCustomer() {
        Customer newCustomer = new Customer(1, "이재윤", new ArrayList<>());
        customerService.join(newCustomer);

        Customer getCustomer = customerService.findCustomer(newCustomer.getId());
        assertSame(newCustomer, getCustomer);
    }

    @Test
    @DisplayName("등록되지 않은 고객 조회시 null 리턴")
    void checkNotRegisteredCustomer() {
        assertSame(null, customerService.findCustomer(123));
    }
}
