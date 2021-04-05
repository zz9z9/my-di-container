package com.example.customer;

import com.example.planner.PlannerService;

public class CustomerServiceImpl implements CustomerService{

    PlannerService plannerService;

    public CustomerServiceImpl(PlannerService plannerService) {
        this.plannerService = plannerService;
    }

    @Override
    public void requestDressTour(Customer customer) {
        plannerService.setDressTourPlan(customer);
    }
}
