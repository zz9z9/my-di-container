package com.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommonConfig {

    private static Map<String, Object> beanStore = new ConcurrentHashMap<>();

    public <T> T getBean(T instance) {

        String instanceName = instance.getClass().getName();
        T returnInstance;

        if(beanStore.get(instanceName)==null) {
            beanStore.put(instanceName, instance);
        }

        returnInstance = (T) beanStore.get(instanceName);

        return returnInstance;
    }
}
