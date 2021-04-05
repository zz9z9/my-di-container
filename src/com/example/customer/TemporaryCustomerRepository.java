package com.example.customer;

import java.util.HashMap;
import java.util.Map;

public class TemporaryCustomerRepository implements CustomerRepository{

    private static Map<Integer, Customer> store = new HashMap<>();

    @Override
    public void save(int customerId, Customer customer) {
        store.put(customerId, customer);
    }

    @Override
    public Customer findById(int customerId) {
        return store.get(customerId);
    }
}
