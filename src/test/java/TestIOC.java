import com.joker.spring.beans.BeanDefinition;
import com.joker.spring.beans.factory.BeanCreationException;
import com.joker.spring.beans.factory.BeanDefinitionStoreException;
import com.joker.spring.beans.factory.XmlBeanDefinitionReader;
import com.joker.spring.beans.factory.support.DefaultBeanFactory;
import com.joker.spring.core.io.ClassPathResource;
import com.joker.spring.test.PetStoreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.InputStream;

/**
 * description:
 *
 * @author zgw
 * date 2019/9/14
 */
public class TestIOC {

    DefaultBeanFactory      factory = null;
    XmlBeanDefinitionReader reader  = null;

    @Before
    public void setUp(){
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);

    }

    @Test
    public void testClassPathResource() throws Exception{
        ClassPathResource classPathResource = new ClassPathResource("spring-bean.xml");
        String description = classPathResource.getDescription();
        InputStream inputStream = classPathResource.getInputStream();
        String content = getContent(inputStream);
        System.out.println(content);
        System.out.println(description);
    }

    @Test
    public void testCreateBean() {
        ClassPathResource resource = new ClassPathResource("spring-bean.xml");
        reader.loadBeanDefinations(resource);
        BeanDefinition bd = factory.getBeanDefinition("petStore");
        assertTrue(bd.isSingleton());
//        assertTrue(bd.isPrototype());
        assertEquals(BeanDefinition.SCOPE_DEFAULT, bd.getScope());
        assertEquals("com.joker.spring.test.PetStoreService", bd.getBeanClassName());
        PetStoreService petStore = (PetStoreService)factory.getBean("petStore");
        assertNotNull(petStore);
        PetStoreService petStore1 = (PetStoreService)factory.getBean("petStore");
        assertEquals(petStore, petStore1);

    }

    @Test
    public void testInvalidBean() {
        reader.loadBeanDefinations(new ClassPathResource("spring-bean.xml"));
        try {
            factory.getBean("invalidBean");
        }catch (BeanCreationException e) {
            return;
        }

        Assert.fail("expect BeanCreationException ");
    }

    @Test
    public void testInvalidXml() {
        try {
            reader.loadBeanDefinations(new ClassPathResource("spring-xxx.xml"));
        } catch (BeanDefinitionStoreException e) {
            return;
        }

        Assert.fail("exception BeanDefinitionStoreException");

    }

    private String getContent(InputStream in) throws Exception{
        byte[] bytes = new byte[1024];
        int length;
        while ((length = in.read(bytes))!=-1) {
             in.read(bytes, 0, length);
        }
        return new String(bytes,"utf-8");
    }


}
