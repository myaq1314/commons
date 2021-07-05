package org.czh.commons.validate;

/**
 * @author : czh
 * description : 布尔值 断言
 * date : 2021-04-28
 * email 916419307@qq.com
 */
public final class FlagAssert {

    /*
      -----------------------------boolean assert-------------------------------
     */

    /**
     * 校验 对象 是否为true
     *
     * @param expression boolean 对象
     */
    public static void isTrue(final boolean expression) {
        isTrue(expression, "[Assertion failed] - this expression argument must be true");
    }

    public static void isTrue(final boolean expression, final String message) {
        if (!expression) {
            throw new RuntimeException(message);
        }
    }

    public static void isTrue(final Boolean flag) {
        isTrue(flag, "[Assertion failed] - this expression argument must be true");
    }

    public static void isTrue(final Boolean flag, final String message) {
        if (FlagValidate.isFalse(flag)) {
            throw new RuntimeException(message);
        }
    }

    /**
     * 校验 对象 是否为false
     *
     * @param expression boolean 对象
     */
    public static void isFalse(final boolean expression) {
        isFalse(expression, "[Assertion failed] - this expression argument must not be true");
    }

    public static void isFalse(final boolean expression, final String message) {
        if (expression) {
            throw new RuntimeException(message);
        }
    }

    public static void isFalse(final Boolean flag) {
        isFalse(flag, "[Assertion failed] - this expression argument must not be true");
    }

    public static void isFalse(final Boolean flag, final String message) {
        if (FlagValidate.isTrue(flag)) {
            throw new RuntimeException(message);
        }
    }
}
