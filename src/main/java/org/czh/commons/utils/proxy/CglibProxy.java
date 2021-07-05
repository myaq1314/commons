package org.czh.commons.utils.proxy;

import lombok.AllArgsConstructor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author : czh
 * description :
 * date : 2021-07-03
 * email 916419307@qq.com
 */
@AllArgsConstructor
public class CglibProxy implements MethodInterceptor {

    private final Object target;

    // 为目标对象创建一个代理对象
    public Object getProxyInstance() {
        // 工具类
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(target.getClass());
        // 设置回调函数
        enhancer.setCallback(this);
        // 创建子类
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        pre();
        Object returnValue = invoke(target, method, args);
        post();
        return returnValue;
    }

    protected void pre() {

    }

    protected Object invoke(Object target, Method method, Object[] args) throws Throwable {
        return method.invoke(target, args);
    }

    protected void post() {

    }
}
