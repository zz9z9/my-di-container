package com.example.customer;

import com.example.planner.PlannerService;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PlannerService plannerService;

    public CustomerServiceImpl(CustomerRepository customerRepository, PlannerService plannerService) {
        this.customerRepository = customerRepository;
        this.plannerService = plannerService;
    }

    @Override
    public void join(Customer customer) {
        customerRepository.save(customer.getId(), customer);
    }

    @Override
    public Customer findCustomer(int customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public void requestDressTour(Customer customer) {
        if(findCustomer(customer.getId())!=null) {
            plannerService.setDressTourPlan(customer);
        } else {
            System.out.println("등록되지 않은 고객입니다");
        }
    }

    // 싱글톤 객체로 생성되는지 테스트하기 위한 메서드
    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }
}
