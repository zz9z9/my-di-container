package com.example.container;

import com.example.exception.NoSuchBeanDefinitionException;
import com.example.exception.NoUniqueBeanDefinitionException;

public interface DIContainer {
    void registerBeans(Class<?>... clazz);

    Object getBean(String beanName) throws NoSuchBeanDefinitionException;

    <T> T getBean(String beanName, Class<T> beanType) throws NoSuchBeanDefinitionException;

    <T> T getBean(Class<T> beanType) throws NoUniqueBeanDefinitionException;

    String[] getBeanDefinitionNames();

    int getBeanDefinitionCount();
}
