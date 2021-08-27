package org.czh.commons.validate;

import org.czh.commons.annotations.tag.NotBlankTag;

import java.math.BigDecimal;

/**
 * @author : czh
 * description : 数字 断言
 * date : 2021-04-28
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public final class NumAssert {

    public static void isPureNumber(@NotBlankTag final String text) {
        isPureNumber(text, "[Assertion failed] - The text must be pure numbers");
    }

    public static void isPureNumber(@NotBlankTag final String text, final String message) {
        if (!NumValidate.isPureNumber(text)) {
            throw new IllegalStateException(message);
        }
    }

    /**
     * 判断字符串为数字
     *
     * @param text 文本
     */
    public static void isNumber(final String text) {
        isNumber(text, "[Assertion failed] - this text argument must be a number");
    }

    public static void isNumber(final String text, final String message) {
        if (!NumValidate.isNumber(text)) {
            throw new IllegalStateException(message);
        }
    }

    /**
     * 判断字符串为整数
     *
     * @param text 数值文本
     */
    public static void isInt(final String text) {
        isInt(text, "[Assertion failed] - this text argument must be a int number");
    }

    public static void isInt(final String text, final String message) {
        if (!NumValidate.isInt(text)) {
            throw new IllegalStateException(message);
        }
    }

    /**
     * 判断字符串是否为浮点数
     * 不能用来校验整数 false
     *
     * @param text 数值文本
     */
    public static void isDouble(final String text) {
        isDouble(text, "[Assertion failed] - this text argument must be a double number");
    }

    public static void isDouble(final String text, final String message) {
        if (!NumValidate.isDouble(text)) {
            throw new IllegalStateException(message);
        }
    }

    /**
     * 校验是否是自然数 0、1、2
     * 0 - 无穷大
     *
     * @param num 数值 包装类
     */
    public static void isNaturalInt(final Integer num) {
        isNaturalInt(num, "[Assertion failed] - this text argument must be a natural number");
    }

    public static void isNaturalInt(final Integer num, final String message) {
        if (!NumValidate.isNaturalInt(num)) {
            throw new IllegalStateException(message);
        }
    }

    /**
     * 校验是否是负整数
     * -1 - 无穷小
     *
     * @param num 数值 包装类
     */
    public static void isNegativeInt(final Integer num) {
        isNegativeInt(num, "[Assertion failed] - this text argument must be a negative number");
    }

    public static void isNegativeInt(final Integer num, final String message) {
        if (!NumValidate.isNegativeInt(num)) {
            throw new IllegalStateException(message);
        }
    }

    /**
     * 验证是否是正整数 1、2、3
     * 1 - 无穷大
     *
     * @param num 数值包装类
     */
    public static void isPositiveInt(final Integer num) {
        isPositiveInt(num, "[Assertion failed] - this text argument must be a positive number");
    }

    public static void isPositiveInt(final Integer num, final String message) {
        if (!NumValidate.isPositiveInt(num)) {
            throw new IllegalStateException(message);
        }
    }

    /**
     * 校验是否是自然数 0、1、2
     * 0 - 无穷大
     *
     * @param num 数值 包装类
     */
    public static void isNaturalLong(final Long num) {
        isNaturalLong(num, "[Assertion failed] - this text argument must be a natural number");
    }

    public static void isNaturalLong(final Long num, final String message) {
        if (!NumValidate.isNaturalLong(num)) {
            throw new IllegalStateException(message);
        }
    }

    /**
     * 校验是否是负整数
     * -1 - 无穷小
     *
     * @param num 数值 包装类
     */
    public static void isNegativeLong(final Long num) {
        isNegativeLong(num, "[Assertion failed] - this text argument must be a negative number");
    }

    public static void isNegativeLong(final Long num, final String message) {
        if (!NumValidate.isNegativeLong(num)) {
            throw new IllegalStateException(message);
        }
    }

    /**
     * 验证是否是正整数 1、2、3
     * 1 - 无穷大
     *
     * @param num 数值包装类
     */
    public static void isPositiveLong(final Long num) {
        isPositiveLong(num, "[Assertion failed] - this text argument must be a positive number");
    }

    public static void isPositiveLong(final Long num, final String message) {
        if (!NumValidate.isPositiveLong(num)) {
            throw new IllegalStateException(message);
        }
    }

    public static void isMin(final Integer num, final Integer min) {
        isMin(num, min, "[Assertion failed] - This number should be greater than or equal to the minimum");
    }

    public static void isMin(final Integer num, final Integer min, final String message) {
        if (!NumValidate.isMin(num, min)) {
            throw new IllegalStateException(message);
        }
    }

    public static void isMin(final Long num, final Long min) {
        isMin(num, min, "[Assertion failed] - This number should be greater than or equal to the minimum");
    }

    public static void isMin(final Long num, final Long min, final String message) {
        if (!NumValidate.isMin(num, min)) {
            throw new IllegalStateException(message);
        }
    }

    public static void isMin(final BigDecimal num, final BigDecimal min) {
        isMin(num, min, "[Assertion failed] - This number should be greater than or equal to the minimum");
    }

    public static void isMin(final BigDecimal num, final BigDecimal min, final String message) {
        if (!NumValidate.isMin(num, min)) {
            throw new IllegalStateException(message);
        }
    }

    public static void isMax(final Integer num, final Integer max) {
        isMax(num, max, "[Assertion failed] - This number should be less than or equal to the maximum");
    }

    public static void isMax(final Integer num, final Integer max, final String message) {
        if (!NumValidate.isMax(num, max)) {
            throw new IllegalStateException(message);
        }
    }

    public static void isMax(final Long num, final Long max) {
        isMax(num, max, "[Assertion failed] - This number should be less than or equal to the maximum");
    }

    public static void isMax(final Long num, final Long max, final String message) {
        if (!NumValidate.isMax(num, max)) {
            throw new IllegalStateException(message);
        }
    }

    public static void isMax(final BigDecimal num, final BigDecimal max) {
        isMax(num, max, "[Assertion failed] - This number should be less than or equal to the maximum");
    }

    public static void isMax(final BigDecimal num, final BigDecimal max, final String message) {
        if (!NumValidate.isMax(num, max)) {
            throw new IllegalStateException(message);
        }
    }

    public static void isMinAndMax(final Integer num, final Integer min, final Integer max) {
        isMinAndMax(num, min, max, "[Assertion failed] - This number should be greater than or equal to the minimum and less than or equal to the maximum");
    }

    public static void isMinAndMax(final Integer num, final Integer min, final Integer max, final String message) {
        if (!NumValidate.isMinAndMax(num, min, max)) {
            throw new IllegalStateException(message);
        }
    }

    public static void isMinAndMax(final Long num, final Long min, final Long max) {
        isMinAndMax(num, min, max, "[Assertion failed] - This number should be greater than or equal to the minimum and less than or equal to the maximum");
    }

    public static void isMinAndMax(final Long num, final Long min, final Long max, final String message) {
        if (!NumValidate.isMinAndMax(num, min, max)) {
            throw new IllegalStateException(message);
        }
    }

    public static void isMinAndMax(final BigDecimal num, final BigDecimal min, final BigDecimal max) {
        isMinAndMax(num, min, max, "[Assertion failed] - This number should be greater than or equal to the minimum and less than or equal to the maximum");
    }

    public static void isMinAndMax(final BigDecimal num, final BigDecimal min, final BigDecimal max, final String message) {
        if (!NumValidate.isMinAndMax(num, min, max)) {
            throw new IllegalStateException(message);
        }
    }
}
