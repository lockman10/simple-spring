package com.joker.spring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * description:统一资源抽象-可以支持多种方式读取资源
 *
 * @author zgw
 * date 2019/9/14
 */
public interface Resource {

    /**
     * 获取资源流
     */
    InputStream getInputStream() throws IOException;

    /**
     * 描述资源位置
     */
    String getDescription();
}
