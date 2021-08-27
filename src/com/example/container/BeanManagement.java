package com.example.container;

public interface BeanManagement {
    void registerBeans(Class<?>... clazz);

    Object getBean(String beanName);

    <T> T getBean(String beanName, Class<T> beanType);

    <T> T getBean(Class<T> beanType);

    String[] getBeanDefinitionNames();

    int getBeanDefinitionCount();
}
