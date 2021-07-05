package org.czh.commons.utils.proxy;

import lombok.AllArgsConstructor;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : czh
 * description :
 * date : 2021-07-03
 * email 916419307@qq.com
 */
@AllArgsConstructor
public class DynamicProxy {

    protected final Object target;

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(
                this.target.getClass().getClassLoader(),
                this.target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    pre();
                    Object returnValue = invoke(target, method, args);
                    post();
                    return returnValue;
                }
        );
    }

    protected void pre() {

    }

    protected Object invoke(Object target, Method method, Object[] args) throws Throwable {
        return method.invoke(target, args);
    }

    protected void post() {

    }
}
