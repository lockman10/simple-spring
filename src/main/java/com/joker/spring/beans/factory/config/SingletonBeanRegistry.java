package com.joker.spring.beans.factory.config;

/**
 * description:
 *
 * @author zgw
 * date 2019/9/14
 */
public interface SingletonBeanRegistry {

    void registerSingleton(String beanName, Object singletonObject);

    Object getSingleton(String beanName);
}
