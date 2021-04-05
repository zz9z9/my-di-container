package com.example.planner;

import com.example.customer.Customer;
import com.example.customer.CustomerRepository;

import java.util.List;

public class PlannerServiceImpl implements PlannerService {

    CustomerRepository customerRepository;

    public PlannerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void setDressTourPlan(Customer customer) {
        int id = customer.getId();
        String customerName = customer.getName();
        List<String> schedule = customer.getSchedule();

        schedule.add("dress tour");
        customer.setSchedule(schedule);

        customerRepository.save(id, customer);

        System.out.println("안녕하세요 "+customerName+" 고객님. 드레스 투어 예약 완료되었습니다. 감사합니다\n");
    }
}
