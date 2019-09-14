package com.joker.spring.beans.factory;

import com.joker.spring.beans.BeanDefinition;
import com.joker.spring.beans.factory.support.BeanDefinitionRegistry;
import com.joker.spring.beans.factory.support.GenericBeanDefinition;
import com.joker.spring.core.io.Resource;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * description:
 *
 * @author zgw
 * date 2019/9/14
 */
public class XmlBeanDefinitionReader {

    public static final String ID_ATTRIBUTE = "id";

    public static final String CLASS_ATTRIBUTE ="class";

    public static final String SCOPE_ATTRIBUTE = "scope";

    BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }


    public void loadBeanDefinations(Resource resource) {
        InputStream in = null;
        try {
            in =  resource.getInputStream();
            SAXReader reader = new SAXReader();
            Document doc = reader.read(in);
            Element root = doc.getRootElement();
            Iterator<Element> iterator = root.elementIterator();
            while (iterator.hasNext()) {
                Element ele = iterator.next();
                String id = ele.attributeValue(ID_ATTRIBUTE);
                String className = ele.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition beanDefinition = new GenericBeanDefinition(id, className);
                if (null != ele.attributeValue(SCOPE_ATTRIBUTE)) {
                    beanDefinition.setScope(ele.attributeValue(SCOPE_ATTRIBUTE));
                }
                this.registry.registerBeanDefinition(id, beanDefinition);
            }

        } catch (Exception e) {
           throw new BeanDefinitionStoreException("IOException parsing XML document from " + resource.getDescription(),e);
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
