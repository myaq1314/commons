package org.czh.commons.validate;

/**
 * @author : czh
 * description : 枚举 判断
 * date : 2021-04-29
 * email 916419307@qq.com
 */
public final class EnumValidate {

    /**
     * 验证是否 是 枚举类
     */
    public static boolean isEnum(final Class<?> clazz) {
        EmptyAssert.isNotNull(clazz);
        return clazz.isEnum();
    }

    /**
     * 验证是否 不是 枚举类
     */
    public static boolean isNotEnum(final Class<?> clazz) {
        EmptyAssert.isNotNull(clazz);
        return !clazz.isEnum();
    }
}
