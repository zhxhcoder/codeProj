package com.zhxh.codeproj.dynamicproxy;

import java.lang.reflect.Method;

/**
 * Invocation handler.
 *
 * @author zhxh 2018-03-01 20:05
 */
public interface InvocationHandler {

    void invoke(Object proxy, Method method, Object[] args);
}
