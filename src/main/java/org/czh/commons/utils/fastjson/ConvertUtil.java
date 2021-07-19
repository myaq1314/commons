package org.czh.commons.utils.fastjson;

import org.czh.commons.utils.DateUtil;
import org.czh.commons.validate.EmptyValidate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

/**
 * @author : czh
 * description :
 * date : 2021-07-19
 * email 916419307@qq.com
 */
public final class ConvertUtil {

    /*
      -----------------------------date and text-------------------------------
     */

    public static String dateToText(Date source, String format) {
        if (EmptyValidate.isNull(source)) {
            return null;
        }

        if (EmptyValidate.isNotBlank(format)) {
            return DateUtil.formatToText(source, format);
        } else {
            return DateUtil.formatToText(source);
        }
    }

    public static Date textToDate(String source, String format) {
        if (EmptyValidate.isNull(source)) {
            return null;
        }

        if (EmptyValidate.isNotBlank(format)) {
            return DateUtil.parseToDate(source, format);
        } else {
            return DateUtil.parseToDate(source);
        }
    }

    /*
      -----------------------------decimal and long-------------------------------
     */

    public static Long decimalToLong(BigDecimal source, String format) {
        if (EmptyValidate.isNull(source)) {
            return null;
        }

        BigDecimal rate;
        if (EmptyValidate.isNotEmpty(format)) {
            rate = new BigDecimal(format.trim());
        } else {
            rate = new BigDecimal(100);
        }
        return source.multiply(rate).longValue();
    }

    public static BigDecimal longToDecimal(Long source, String format) {
        if (EmptyValidate.isNull(source)) {
            return null;
        }

        BigDecimal rate;
        if (EmptyValidate.isNotEmpty(format)) {
            rate = new BigDecimal(format.trim());
        } else {
            rate = new BigDecimal(100);
        }
        return new BigDecimal(source).divide(rate, format.trim().length() - 1, RoundingMode.DOWN).stripTrailingZeros();
    }

    /*
      -----------------------------object and text-------------------------------
     */

    public static String objectToText(Object source, String format) {
        if (EmptyValidate.isNull(source)) {
            return null;
        }

        return source.toString();
    }

    public static Short textToShort(String source, String format) {
        if (EmptyValidate.isNull(source)) {
            return null;
        }
        return Short.parseShort(source);
    }

    public static Integer textToInteger(String source, String format) {
        if (EmptyValidate.isNull(source)) {
            return null;
        }
        return Integer.parseInt(source);
    }

    public static Long textToLong(String source, String format) {
        if (EmptyValidate.isNull(source)) {
            return null;
        }
        return Long.parseLong(source);
    }
}
