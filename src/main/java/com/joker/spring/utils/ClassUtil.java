package com.joker.spring.utils;


/**
 * description:
 *
 * @author zgw
 * date 2019/9/14
 */
public class ClassUtil {

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable e) {
            System.out.println("Cannot access thread context ClassLoader");
        }

        if (null == cl) {
            // No thread context class loader -> use class loader of this class
            cl = ClassUtil.class.getClassLoader();
            if (null == cl) {
                // getClassLoader() returning null indicates the bootstrap ClassLoader
                try {
                    cl = ClassLoader.getSystemClassLoader();
                } catch (Throwable e) {
                    // Cannot access system ClassLoader - oh well, maybe the caller can live with null.
                }

            }
        }
        return cl;
    }
}
