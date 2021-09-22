package org.czh.commons.utils;

import org.czh.commons.validate.EmptyAssert;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.stream.IntStream;

/**
 * @author : czh
 * description :
 * date : 2021-05-21
 * email 916419307@qq.com
 */
public final class ConstructorUtil {

    /**
     * 本方法 只支持 ，公共的无参构造
     */
    public static <T> T newInstance(Class<T> clazz) {
        EmptyAssert.isNotNull(clazz);

        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("构建无参实体异常");
        }
    }

    /**
     * 本方法 支持 私有、受保护、默认、公共 的 有参，有参构造
     */
    public static <T> T parameterInstance(Class<T> clazz, Object... initargs) {
        EmptyAssert.isNotNull(clazz);

        Class<?>[] parameterTypes = new Class<?>[initargs.length];
        IntStream.range(0, initargs.length).forEach(i -> {
            EmptyAssert.isNotNull(initargs[i], "参数不能为空，无法判断");
            parameterTypes[i] = initargs[i].getClass();
        });
        return newInstance(newConstructor(clazz, parameterTypes), initargs);
    }

    /**
     * 本方法 支持 私有、受保护、默认、公共 的 有参，无参构造
     */
    public static <T> T parameterlessInstance(Class<T> clazz) {
        EmptyAssert.isNotNull(clazz);
        return newInstance(newConstructor(clazz));
    }

    public static <T> T newInstance(Constructor<T> constructor, Object... initargs) {
        EmptyAssert.isNotNull(constructor);

        try {
            return constructor.newInstance(initargs);
        } catch (Exception e) {
            throw new RuntimeException("构建实体异常");
        }
    }

    public static <T> Constructor<T> newConstructor(Class<T> clazz,
                                                    Class<?>... parameterTypes) {
        EmptyAssert.isNotNull(clazz);

        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor(parameterTypes);
            if (!Modifier.isPublic(constructor.getModifiers())) {
                constructor.setAccessible(true);
            }
            return constructor;
        } catch (Exception e) {
            throw new RuntimeException("构建有参构造器异常");
        }
    }
}
