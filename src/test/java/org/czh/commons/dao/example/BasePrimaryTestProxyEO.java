package org.czh.commons.dao.example;

import lombok.AllArgsConstructor;
import org.czh.commons.entity.IBaseEntity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021-07-03
 * email 916419307@qq.com
 */
@AllArgsConstructor
public class BasePrimaryTestProxyEO<T> implements InvocationHandler, IBaseEntity {

    private static final long serialVersionUID = -77260468501806011L;

    private final Class<T> mapperInterface;

    @SuppressWarnings("RedundantThrows")
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(mapperInterface.getName());
        System.out.println(method.getName());
        if (method.getName().equals("toString")) {
            return proxy.getClass().getName();
        } else if (method.getName().startsWith("insert")) {
            return 1;
        } else if (method.getName().startsWith("update")) {
            return 1;
        } else if (method.getName().startsWith("get")) {
            return 1;
        } else if (method.getName().startsWith("query")) {
            List<Object> resultList = new ArrayList<>();
            Collections.addAll(resultList, args[0]);
            return resultList;
        }
        return 0;
    }
}
