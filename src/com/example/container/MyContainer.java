package com.example.container;

import com.example.AppConfig;
import com.example.customer.CustomerService;
import com.example.customer.CustomerServiceImpl;
import com.example.exception.NoSuchBeanDefinitionException;
import com.example.exception.NoUniqueBeanDefinitionException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyContainer implements Container {

    Map<String, Object> beanRegistry = new HashMap<>();
    Map<Class, List<Object>> map2 = new HashMap<>();

    public MyContainer(Class<?>... clazz) {
        List<Object> li = new ArrayList<>();
        li.add(new CustomerServiceImpl());
        li.add(new CustomerServiceImpl());
        this.registerBeans(clazz);

        map2.put(CustomerService.class, li);
    }

    @Override
    public void registerBeans(Class<?>... clazz) {
        try {
            for (Class c : clazz) {
                Constructor<?> constructor = c.getConstructor(null);
                String configName = constructor.getName();
                Object configInstance = constructor.newInstance();

                beanRegistry.put(configName, configInstance);

                for (Method m : c.getDeclaredMethods()) {
                    String beanName = m.getName();
                    Object bean = m.invoke(constructor.newInstance());

                    beanRegistry.put(beanName, bean);
                }
            }
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
    public Object getBean(String beanName) throws NoSuchBeanDefinitionException {
        Object bean = beanRegistry.get(beanName);
        if (bean == null) {
            throw new NoSuchBeanDefinitionException();
        }

        return bean;
    }

    @Override
    public <T> T getBean(String name, Class<T> type) throws NoSuchBeanDefinitionException {
        Object bean = beanRegistry.get(name);
        if (bean == null) {
            throw new NoSuchBeanDefinitionException();
        }

        return (T) bean;
    }

    @Override
    public <T> T getBean(Class<T> type) throws NoUniqueBeanDefinitionException {
        List<Object> obj = map2.get(type);
        if (obj.size() > 1) {
            throw new NoUniqueBeanDefinitionException();
        }

        return (T) obj;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return new String[0];
    }

    @Override
    public int getBeanDefinitionCount() {
        return 1;
    }
}
