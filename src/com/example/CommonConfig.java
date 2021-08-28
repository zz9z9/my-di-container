package com.example;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CommonConfig {

    private static Map<String, Object> beanStore = new ConcurrentHashMap<>();
    private static Set<String> beanNames = ConcurrentHashMap.newKeySet();

    public Object getBean(String beanName) {
        return beanStore.get(beanName);
    }

    public <T> void createBean(String beanName, T instance) {
        beanStore.put(beanName, instance);
        beanNames.add(beanName);
    }

    public boolean isExist(String beanName) {
        return beanNames.contains(beanName);
    }
}
