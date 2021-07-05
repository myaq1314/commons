package org.czh.commons.utils;

import org.czh.commons.enums.parent.IColumnEnum;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;
import org.czh.commons.validate.FlagAssert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Date;

/**
 * @author : czh
 * description :
 * date : 2021-06-22
 * email 916419307@qq.com
 */
public final class SqlJointUtil {

    public static String getFunctionSeparator() {
        return ",";
    }

    public static String convertSelectFunctionSql(String function, IColumnEnum aliasColumn, Object... objs) {
        EmptyAssert.isNotNull(aliasColumn);
        return convertSelectFunctionSql(function, aliasColumn.getColumn(), objs);
    }

    public static String convertSelectFunctionSql(String function, String alias, Object... objs) {
        return convertSelectFunctionSql(function, alias, getFunctionSeparator(), objs);
    }

    public static String convertSelectFunctionSql(String function,
                                                  IColumnEnum aliasColumn,
                                                  String separator,
                                                  Object... objs) {
        EmptyAssert.isNotNull(aliasColumn);
        return convertSelectFunctionSql(function, aliasColumn.getColumn(), separator, objs);
    }

    public static String convertSelectFunctionSql(String function,
                                                  String alias,
                                                  String separator,
                                                  Object... objs) {
        EmptyAssert.allNotNull(function);
        EmptyAssert.isNotBlank(alias);

        if (EmptyValidate.isNotEmpty(objs)) {
            return String.format(" %s(%s) AS `%s` ", function, convertFunctionBodySql(separator, objs), alias);
        } else {
            return String.format(" %s() AS `%s` ", function, alias);
        }
    }

    public static String convertFunctionSql(String function, Object... objs) {
        return convertFunctionSql(function, getFunctionSeparator(), objs);
    }

    public static String convertFunctionSql(String function, String separator, Object... objs) {
        EmptyAssert.isNotNull(function);
        if (EmptyValidate.isNotEmpty(objs)) {
            return String.format(" %s(%s) ", function, convertFunctionBodySql(separator, objs));
        } else {
            return String.format(" %s() ", function);
        }
    }

    @SuppressWarnings("unused")
    public static String convertFunctionBodySql(Object... objs) {
        return convertFunctionBodySql(getFunctionSeparator(), objs);
    }

    public static String convertFunctionBodySql(String separator, Object... objs) {
        EmptyAssert.isNotNull(separator);

        StringBuilder builder = new StringBuilder();
        for (Object obj : objs) {
            EmptyAssert.isNotNull(obj);
            if (obj instanceof Collection) {
                //noinspection rawtypes
                for (Object objChild : (Collection) obj) {
                    builder.append(SqlJointUtil.objConvertSql(objChild)).append(separator);
                }
            } else {
                builder.append(SqlJointUtil.objConvertSql(obj)).append(separator);
            }
        }
        return builder.substring(0, builder.length() - separator.length());
    }

    public static String objConvertSql(Object obj) {
        // 不支持 null
        EmptyAssert.isNotNull(obj);
        // 不支持 集合
        FlagAssert.isTrue(!(obj instanceof Collection));

        // 字段字典枚举 a.`id`
        if (obj instanceof IColumnEnum) {
            return columnEnumConvertSql((IColumnEnum) obj);
        }

        // 数值包装类型，Integer/Long/Short/Byte/Float/Double 18 / 4.5
        // 数值基本类型，int/long/short/byte/float/double/     23 / 6.932
        // 其它数值类型，BigDecimal 5.34
        if (obj instanceof Number) {
            return numberConvertSql((Number) obj);
        }

        // 字符串、单个字符 'abc' / '1'
        if (obj instanceof String || obj instanceof Character) {
            return charsetConvertSql(obj);
        }

        // 日期 '2021-06-22'
        // 时间 '2021-06-22 17:32:52'
        String result = dateConvertSql(obj);
        if (EmptyValidate.isNotNull(result)) {
            return result;
        }

        // 其它类型
        return " '" + obj.toString() + "' ";
    }

    public static String columnEnumConvertSql(IColumnEnum columnEnum) {
        EmptyAssert.isNotNull(columnEnum);
        return columnNameConvertSql(columnEnum.getColumn());
    }

    public static String columnNameConvertSql(String columnName) {
        EmptyAssert.isNotBlank(columnName);
        return String.format(" a.`%s` ", columnName);
    }

    private static String numberConvertSql(Number number) {
        return " " + number + " ";
    }

    private static String charsetConvertSql(Object obj) {
        return " '" + obj.toString() + "' ";
    }

    private static String dateConvertSql(Object obj) {
        StringBuilder builder = new StringBuilder(" '");
        if (obj instanceof Date) {
            builder.append(DateUtil.formatToText((Date) obj));
        } else if (obj instanceof LocalDateTime) {
            builder.append(DateUtil.formatToText((LocalDateTime) obj));
        } else if (obj instanceof LocalDate) {
            builder.append(DateUtil.formatToText((LocalDate) obj));
        } else if (obj instanceof LocalTime) {
            builder.append(DateUtil.formatToText((LocalTime) obj));
        } else {
            return null;
        }
        builder.append("' ");
        return builder.toString();
    }
}
