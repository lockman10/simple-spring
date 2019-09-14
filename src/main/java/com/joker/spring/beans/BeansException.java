package com.joker.spring.beans;

/**
 * description:
 *
 * @author zgw
 * date 2019/9/14
 */
public class BeansException extends RuntimeException{

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg,Throwable cause) {
        super(msg,cause);
    }
}
