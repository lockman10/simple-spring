package com.joker.spring.beans.factory.config;

import com.joker.spring.beans.factory.BeanFactory;

/**
 * description:
 *
 * @author zgw
 * date 2019/9/14
 */
public interface ConfigurableBeanFactory extends BeanFactory {

    void setBeanClassLoader(ClassLoader classLoader);

    ClassLoader getBeanClassLoader();

}
