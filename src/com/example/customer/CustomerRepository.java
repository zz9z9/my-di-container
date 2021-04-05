package com.example.customer;

public interface CustomerRepository {

    void save(int customerId, Customer customer);

    Customer findById(int customerId);
}
