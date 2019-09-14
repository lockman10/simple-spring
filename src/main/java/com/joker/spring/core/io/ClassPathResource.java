package com.joker.spring.core.io;

import com.joker.spring.utils.ClassUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * description:从classpath路径读取资源
 *
 * @author zgw
 * date 2019/9/14
 */
public class ClassPathResource implements Resource {

    private String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        this.path = path;
        this.classLoader = Objects.isNull(classLoader) ? ClassUtil.getDefaultClassLoader() : classLoader;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = classLoader.getResourceAsStream(path);
        if (null == inputStream) {
            throw new FileNotFoundException(path + "cannot open");
        }
        return inputStream;
    }

    @Override
    public String getDescription() {
        return this.path;
    }
}
