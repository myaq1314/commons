package org.czh.commons.constant;

/**
 * @author : czh
 * description : 时间 表达式 常量
 * date : 2021-05-06
 * email 916419307@qq.com
 */
public final class DateConstant {
    /*
        日期 + 时间 单独一个
     */
    public static String ONLY_YEAR() {
        return "yyyy";
    }

    public static String ONLY_MONTH() {
        return "MM";
    }

    public static String ONLY_DAY() {
        return "dd";
    }

    public static String ONLY_HOUR() {
        return "HH";
    }

    public static String ONLY_MINUTE() {
        return "mm";
    }

    public static String ONLY_SECOND() {
        return "ss";
    }

    public static String ONLY_MILLIS() {
        return "SSS";
    }

    /*
        日期 + 时间
     */
    public static String DATETIME_STANDARD() {
        return "yyyy-MM-dd HH:mm:ss";
    }

    public static String DATETIME_STANDARD_MILLIS() {
        return "yyyy-MM-dd HH:mm:ss SSS";
    }

    public static String DATETIME_SLASH() {
        return "yyyy/MM/dd HH:mm:ss";
    }

    public static String DATETIME_SLASH_MILLIS() {
        return "yyyy/MM/dd HH:mm:ss SSS";
    }

    public static String DATETIME_SIMPLE() {
        return "yyyyMMddHHmmss";
    }

    public static String DATETIME_SIMPLE_MILLIS() {
        return "yyyyMMddHHmmssSSS";
    }

    /*
        日期
     */

    public static String DATE_STANDARD() {
        return "yyyy-MM-dd";
    }

    public static String DATE_SLASH() {
        return "yyyy/MM/dd";
    }

    public static String DATE_SIMPLE() {
        return "yyyyMMdd";
    }

    /*
        时间
     */

    public static String TIME_STANDARD() {
        return "HH:mm:ss";
    }

    public static String TIME_STANDARD_MILLIS() {
        return "HH:mm:ss SSS";
    }

    public static String TIME_SIMPLE() {
        return "HHmmss";
    }

    public static String TIME_SIMPLE_MILLIS() {
        return "HHmmssSSS";
    }
}
