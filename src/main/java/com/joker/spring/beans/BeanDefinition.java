package com.joker.spring.beans;

/**
 * description:BeanDefinition抽象
 * 1. 生成实例的范围单例和多例
 * 2. 判断实例的范围
 * 3. 获取实例的范围
 * 4. 设置实例范围
 * 5. 获取class的名称
 * @author zgw
 * date 2019/9/14
 */
public interface BeanDefinition {

    String SCOPE_SINGLETON="singleton";

    String SCOPE_PROTOTYPE = "prototype";

    String SCOPE_DEFAULT = "";

    boolean isSingleton();

    boolean isPrototype();

    String getScope();

    void setScope(String scope);

    String getBeanClassName();




}
