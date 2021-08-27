package org.czh.commons.validate;

/**
 * @author : czh
 * description : 枚举 断言
 * date : 2021-04-29
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public final class EnumAssert {

    /**
     * 验证是否 是 枚举类
     */
    public static void isEnum(final Class<?> clazz) {
        isEnum(clazz, "[Assertion failed] - this clazz argument must be a enum class");
    }

    public static void isEnum(final Class<?> clazz, final String message) {
        if (EnumValidate.isNotEnum(clazz)) {
            throw new IllegalStateException(message);
        }
    }

    /**
     * 验证是否 不是 枚举类
     */
    public static void isNotEnum(final Class<?> clazz) {
        isNotEnum(clazz, "[Assertion failed] - this clazz argument must not be a enum class");
    }

    public static void isNotEnum(final Class<?> clazz, final String message) {
        if (EnumValidate.isEnum(clazz)) {
            throw new IllegalStateException(message);
        }
    }
}
