package com.example.exception;

import com.example.util.StringUtils;

import java.util.List;

public class NoUniqueBeanDefinitionException extends RuntimeException{
    public NoUniqueBeanDefinitionException() {

    }

    public NoUniqueBeanDefinitionException(Class<?> type, List<String> beanNames) {
        super(String.format("No qualifying bean of type '%s' available: " +
                "expected single matching bean but found %d: %s", type.getName(), beanNames.size(), StringUtils.getCommaDelimitedString(beanNames)));
    }
}