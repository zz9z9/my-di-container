package com.example.exception;

public class NoSuchBeanDefinitionException extends RuntimeException {
    public NoSuchBeanDefinitionException() {

    }

    public NoSuchBeanDefinitionException(String beanName) {
        super(String.format("No bean named '%s' available", beanName));
    }
}
