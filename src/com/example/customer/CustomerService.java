package com.example.customer;

public interface CustomerService {

    void join(Customer customer);

    Customer findCustomer(int customerId);

    void requestDressTour(Customer customer);
}
