package com.joker.spring.beans.factory.support;

import com.joker.spring.beans.BeanDefinition;

/**
 * description:
 *
 * @author zgw
 * date 2019/9/14
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanId, BeanDefinition beanDefinition);

    BeanDefinition getBeanDefinition(String beanId);
}
