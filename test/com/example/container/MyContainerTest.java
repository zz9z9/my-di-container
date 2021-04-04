package com.example.container;

import com.example.AppConfig;
import com.example.TestAppConfig;
import com.example.customer.CustomerService;
import com.example.customer.CustomerServiceImpl;
import com.example.exception.NoSuchBeanDefinitionException;
import com.example.exception.NoUniqueBeanDefinitionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyContainerTest {
    @Test
    @DisplayName("컨테이너에서 주입해 준 CustomerService의 실제 구현체는 CustomerServiceImpl이다.")
    void testGetBean() throws NoSuchBeanDefinitionException {
        Container container = new MyContainer(AppConfig.class);
        CustomerService service = container.getBean("customerService", CustomerService.class);

        assertEquals(CustomerServiceImpl.class, service.getClass());
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면 중복 오류가 발생한다")
    void testNoUniqueBean()  {
        Container container = new MyContainer(AppConfig.class);
        assertThrows(NoUniqueBeanDefinitionException.class, () -> container.getBean(CustomerService.class));
    }

    @Test
    @DisplayName("컨테이너 생성시 설정파일에 기반하여 빈을 등록해야 한다.")
    void registerBeans() {
        Container container = new MyContainer(TestAppConfig.class);

        assertEquals(1, container.getBeanDefinitionCount());

        for(String beanName : container.getBeanDefinitionNames()) {
            assertDoesNotThrow(() -> container.getBean(beanName));
        }
    }
}
