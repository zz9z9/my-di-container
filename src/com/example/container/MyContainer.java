package com.example.container;

import com.example.exception.NoSuchBeanDefinitionException;
import com.example.exception.NoUniqueBeanDefinitionException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class MyContainer implements Container {

    private Map<String, Object> beanNameRegistry = new HashMap<>();
    private Map<Class, List<String>> beanTypeRegistry = new HashMap<>();

    public MyContainer(Class<?>... clazz) {
        this.registerBeans(clazz);
    }

    @Override
    public void registerBeans(Class<?>... clazz) {
        try {
            for (Class c : clazz) {
                Constructor<?> constructor = c.getConstructor(null);
                String configName = constructor.getName();
                Object configInstance = constructor.newInstance();

                beanNameRegistry.put(configName, configInstance);

                for (Method m : c.getDeclaredMethods()) {
                    Class beanType = m.getReturnType();
                    String beanName = m.getName();
                    Object bean = m.invoke(configInstance);
                    Class instanceType = bean.getClass();

                    List<String> beanNamesByType = beanTypeRegistry.getOrDefault(beanType, new ArrayList<>());
                    List<String> beanNamesByInstanceType = beanTypeRegistry.getOrDefault(instanceType, new ArrayList<>());

                    beanNamesByType.add(beanName);
                    beanNamesByInstanceType.add(beanName);

                    beanNameRegistry.put(beanName, bean);
                    beanTypeRegistry.put(beanType, beanNamesByType);
                    beanTypeRegistry.put(instanceType, beanNamesByInstanceType);
                }
            }
            System.out.println("beanNameRegistry :: "+beanNameRegistry);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getBean(String beanName) {
        Object bean = beanNameRegistry.get(beanName);
        if (bean == null) {
            throw new NoSuchBeanDefinitionException(beanName);
        }

        return bean;
    }

    @Override
    public <T> T getBean(String beanName, Class<T> type) {
        Object bean = beanNameRegistry.get(beanName);
        if (bean == null) {
            throw new NoSuchBeanDefinitionException(String.format("No bean named '%s' available", beanName));
        }

        return (T) bean;
    }

    @Override
    public <T> T getBean(Class<T> type) {
        List<String> beanNames = beanTypeRegistry.get(type);
        if (beanNames.size() > 1) {
            throw new NoUniqueBeanDefinitionException(type, beanNames);
        }
        String beanName = beanNames.get(0);

        return (T) beanNameRegistry.get(beanName);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        Set<String> keys = beanNameRegistry.keySet();
        String[] beanNames = new String[keys.size()];

        int idx = 0;
        for(String key : keys) {
            beanNames[idx++] = key;
        }

        return beanNames;
    }

    @Override
    public int getBeanDefinitionCount() {
        return beanNameRegistry.size();
    }
}
