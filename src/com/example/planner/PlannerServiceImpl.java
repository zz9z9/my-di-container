package com.example.planner;

import com.example.customer.Customer;

public class PlannerServiceImpl implements PlannerService {

    @Override
    public void setDressTourPlan(Customer customer) {
        String customerName = customer.getName();
        System.out.println("안녕하세요 "+customerName+" 고객님. 드레스 투어 예약 완료되었습니다. 감사합니다");
    }
}
