package org.czh.commons.utils;

import org.czh.commons.validate.EmptyAssert;

import java.util.regex.Pattern;

/**
 * @author : czh
 * description :
 * date : 2021-07-05
 * email 916419307@qq.com
 */
public final class NumUtil {

    public static Integer matchNum(String text) {
        EmptyAssert.isNotBlank(text);
        String num = Pattern.compile("[^0-9]").matcher(text).replaceAll("");
        EmptyAssert.isNotBlank(num, "字符串没有提取到数字");

        try {
            return Integer.parseInt(num);
        } catch (NumberFormatException e) {
            throw new RuntimeException("字符串提取数字失败", e);
        }
    }

    public static String trimNum(String text) {
        EmptyAssert.isNotNull(text);
        return Pattern.compile("[\\d]").matcher(text).replaceAll("").trim();
    }

}
