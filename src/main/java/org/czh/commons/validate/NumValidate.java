package org.czh.commons.validate;

import java.math.BigDecimal;

/**
 * @author : czh
 * description : 数字 判断
 * date : 2021-04-28
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public final class NumValidate {

    /**
     * 判断是否是纯数字
     */
    public static boolean isPureNumber(final String text) {
        EmptyAssert.isNotBlank(text);
        return text.matches("^[0-9]*$");
    }

    /**
     * 判断字符串为数字
     *
     * @param text 文本
     * @return true 数字
     */
    public static boolean isNumber(final String text) {
        return isInt(text) || isDouble(text);
    }

    /**
     * 判断字符串为整数
     *
     * @param text 数值文本
     * @return true 整数
     */
    public static boolean isInt(final String text) {
        return EmptyValidate.isNotBlank(text)
                && text.matches("^-?[1-9]\\d*$");
    }

    /**
     * 判断字符串是否为浮点数
     * 不能用来校验整数 false
     *
     * @param text 数值文本
     * @return true 小数
     */
    public static boolean isDouble(final String text) {
        return EmptyValidate.isNotBlank(text)
                && text.matches("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$");
    }

    /**
     * 校验是否是自然数
     * 0 - 无穷大
     *
     * @param num 数值 包装类
     * @return true 自然数
     */
    public static boolean isNaturalInt(final Integer num) {
        return EmptyValidate.isNotNull(num) && num >= 0;
    }

    /**
     * 校验是否是负整数
     * -1 - 无穷小
     *
     * @param num 数值 包装类
     * @return true 负整数
     */
    public static boolean isNegativeInt(final Integer num) {
        return EmptyValidate.isNotNull(num) && num < 0;
    }

    /**
     * 验证是否是正整数
     * 1 - 无穷大
     *
     * @param num 数值包装类
     * @return true 正整数
     */
    public static boolean isPositiveInt(final Integer num) {
        return EmptyValidate.isNotNull(num) && num > 0;
    }

    /**
     * 校验是否是自然数
     * 0 - 无穷大
     *
     * @param num 数值 包装类
     * @return true 自然数
     */
    public static boolean isNaturalLong(final Long num) {
        return EmptyValidate.isNotNull(num) && num >= 0L;
    }

    /**
     * 校验是否是负整数
     * -1 - 无穷小
     *
     * @param num 数值 包装类
     * @return true 负整数
     */
    public static boolean isNegativeLong(final Long num) {
        return EmptyValidate.isNotNull(num) && num < 0L;
    }

    /**
     * 验证是否是正整数
     * 1 - 无穷大
     *
     * @param num 数值包装类
     * @return true 正整数
     */
    public static boolean isPositiveLong(final Long num) {
        return EmptyValidate.isNotNull(num) && num > 0L;
    }

    public static boolean isMin(final Integer num, final Integer min) {
        EmptyAssert.allNotNull(num, min);
        return num >= min;
    }

    public static boolean isMin(final Long num, final Long min) {
        EmptyAssert.allNotNull(num, min);
        return num >= min;
    }

    public static boolean isMin(final BigDecimal num, final BigDecimal min) {
        EmptyAssert.allNotNull(num, min);
        return num.compareTo(min) >= 0;
    }

    public static boolean isMax(final Integer num, final Integer max) {
        EmptyAssert.allNotNull(num, max);
        return num >= max;
    }

    public static boolean isMax(final Long num, final Long max) {
        EmptyAssert.allNotNull(num, max);
        return num <= max;
    }

    public static boolean isMax(final BigDecimal num, final BigDecimal max) {
        EmptyAssert.allNotNull(num, max);
        return num.compareTo(max) <= 0;
    }

    public static boolean isMinAndMax(final Integer num, final Integer min, final Integer max) {
        EmptyAssert.allNotNull(num, min, max);
        return num >= min && num <= max;
    }

    public static boolean isMinAndMax(final Long num, final Long min, final Long max) {
        EmptyAssert.allNotNull(num, min, max);
        return num >= min && num <= max;
    }

    public static boolean isMinAndMax(final BigDecimal num, final BigDecimal min, final BigDecimal max) {
        EmptyAssert.allNotNull(num, min, max);
        return num.compareTo(min) >= 0 && num.compareTo(max) <= 0;
    }
}
