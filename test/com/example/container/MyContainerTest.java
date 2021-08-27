package com.example.container;

import com.example.AppConfig;
import com.example.TestAppConfig;
import com.example.customer.CustomerRepository;
import com.example.customer.CustomerService;
import com.example.customer.CustomerServiceImpl;
import com.example.exception.NoSuchBeanDefinitionException;
import com.example.exception.NoUniqueBeanDefinitionException;
import com.example.planner.PlannerServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyContainerTest {

    Container container = new MyContainer(AppConfig.class);
    Container exceptionContainer = new MyContainer(TestAppConfig.class);

    @Test
    @DisplayName("컨테이너에서 주입해 준 CustomerService의 실제 구현체는 CustomerServiceImpl이다.")
    void getBean() {
        CustomerService service = container.getBean("customerService", CustomerService.class);
        assertEquals(CustomerServiceImpl.class, service.getClass());
    }

    @Test
    @DisplayName("다양한 getBean을 통해 bean을 가져올 수 있어야한다.")
    void getBeanVariousWay() {
        CustomerService service;

        service = container.getBean("customerService", CustomerService.class);
        assertEquals(CustomerServiceImpl.class, service.getClass());

        service = (CustomerService) container.getBean("customerService");
        assertEquals(CustomerServiceImpl.class, service.getClass());

        service = container.getBean(CustomerService.class);
        assertEquals(CustomerServiceImpl.class, service.getClass());
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면 중복 오류가 발생한다")
    void getDuplicatedBean()  {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> exceptionContainer.getBean(CustomerService.class));
    }

    @Test
    @DisplayName("컨테이너 생성시 설정파일에 기반하여 빈을 등록해야 한다.")
    void registerBeans() {
        for(String beanName : container.getBeanDefinitionNames()) {
            assertDoesNotThrow(() -> container.getBean(beanName));
        }
    }

    @Test
    @DisplayName("등록되지 않은 빈의 이름으로 조회시 오류가 발생해야 한다")
    void getBeanByInValidName() {
        assertThrows(NoSuchBeanDefinitionException.class, () -> exceptionContainer.getBean("strangerBean"));
    }

    @Test
    @DisplayName("config 파일 통해 빈으로 등록되는 객체들은 유일해야 한다")
    void checkSingletonInstance() {
        Container container = new MyContainer(AppConfig.class);

        CustomerServiceImpl customerService = container.getBean(CustomerServiceImpl.class);
        PlannerServiceImpl plannerService = container.getBean(PlannerServiceImpl.class);
        CustomerRepository customerRepository = container.getBean(CustomerRepository.class);

        assertSame(customerRepository, customerService.getCustomerRepository());
        assertSame(customerRepository, plannerService.getCustomerRepository());
    }

}
