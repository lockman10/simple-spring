package com.joker.spring.beans.factory.support;

import com.joker.spring.beans.BeanDefinition;
import com.joker.spring.beans.factory.BeanCreationException;
import com.joker.spring.beans.factory.config.ConfigurableBeanFactory;
import com.joker.spring.utils.ClassUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description:
 *
 * @author zgw
 * date 2019/9/14
 */
public class DefaultBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory, BeanDefinitionRegistry {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(64);

    private ClassLoader classLoader;

    public DefaultBeanFactory() {

    }

    @Override
    public void registerBeanDefinition(String beanId, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(beanId, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return this.beanDefinitionMap.get(beanId);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return null != this.classLoader ? this.classLoader : ClassUtil.getDefaultClassLoader();
    }

    @Override
    public Object getBean(String beanId) {
        BeanDefinition beanDefinition = this.getBeanDefinition(beanId);
        if (null == beanDefinition) {
            return null;
        }
        if (beanDefinition.isSingleton()) {
            Object singleton = this.getSingleton(beanId);
            if (null == singleton) {
                singleton = createBean(beanDefinition);
                this.registerSingleton(beanId, singleton);
            }
            return singleton;
        }
        return createBean(beanDefinition);
    }

    private Object createBean(BeanDefinition beanDefinition) {
        ClassLoader classLoader = this.getBeanClassLoader();
        String beanClassName = beanDefinition.getBeanClassName();
        try {
            Class<?> clazz = classLoader.loadClass(beanClassName);
            return clazz.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("create bean for "+ beanClassName +" failed",e);
        }
    }
}
