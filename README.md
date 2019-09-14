# simple-spring
hand writing simply spring

## ICO实现
1. 解析xml，生成BeanDefinition  
    使用dom4j解析xml，将配置的bean转换成BeanDefinition放入map中
2. 生成Bean的实例对象  
    根据类全名反射生成实例，如果是单例怎存于map中，下次获取直接从map中获取
