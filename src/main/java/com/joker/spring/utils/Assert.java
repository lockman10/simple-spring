package com.joker.spring.utils;

/**
 * description:
 *
 * @author zgw
 * date 2019/9/14
 */
public abstract class Assert {

    public static void notNull(Object o, String message) {
        if (null == o) {
            throw new IllegalArgumentException(message);
        }
    }

}
