package org.czh.commons.utils.sql.base;

import org.czh.commons.enums.parent.IColumnEnum;
import org.czh.commons.utils.sql.eo.ColumnEO;
import org.czh.commons_core.asserts.EmptyAssert;
import org.czh.commons_core.asserts.FlagAssert;
import org.czh.commons_core.utils.date.DateUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021/10/11
 * email : 916419307@qq.com
 */
@SuppressWarnings({"DuplicatedCode", "unused"})
public final class ValueUtil {

    /**
     * 拼装 对象 到 SQL，字段类型，不使用表别名调用
     */
    public static String splitNoAlias(Object obj) {
        return split(obj, false);
    }

    /**
     * 拼装 对象 到 SQL，字段类型，使用表别名调用
     */
    public static String splitAlias(Object obj) {
        return split(obj, true);
    }

    /**
     * 拼装 对象 到 SQL，字段类型，不使用表别名调用
     * 不支持null处理
     *
     * @param obj               对象，目前支持 字段类型（IColumnEnum/ColumnEO），数值、字符、布尔、日期等
     * @param whetherTableAlias 是否表别名调用列
     *                          当obj类型为字段类型（IColumnEnum/ColumnEO）时，是否使用表别名调用
     *                          true a.`age`
     *                          false `age`
     * @return 对象 转换成 SQL 字符串
     * 字段类型：TbExampleEnum.FIRST_NAME
     * 表别名调用时完整SQL：a.`first_name`；不使用表别名调用时完整SQL：`first_name`；
     * 字段类型：ColumnEO.one("first_name")
     * 表别名调用时完整SQL：a.`first_name`；不使用表别名调用时完整SQL：`first_name`；
     * 数值类型：int、Integer、long、Long、short Short、byte Byte、float Float、double Double、BigDecimal
     * 完整SQL：1、1、1、1、1、1、1、1、1.0、1.0、1.0
     * 字符类型：String、StringBuilder、StringBuffer、char、Character
     * 完整SQL：'abc'、'def'、'ghi'、'j'、'k'
     * 布尔类型：true、false、Boolean.TRUE、Boolean.False
     * 完整SQL：1、0、1、0
     * 日期类型：java.util.Date、java.time.LocalDateTime、java.time.LocalDate、java.time.LocalTime
     * 完整SQL：'2021-10-13 18:57:17'、'2021-10-13 18:57:30'、'2021-10-13'、'18:57:36'
     * 不支持类型：Collection集合、Map键值对、Array数组
     * 不支持细分：List、Set、Queue、HashMap、byte[]、、、、、、
     */
    private static String split(Object obj, boolean whetherTableAlias) {
        EmptyAssert.isNotNull(obj);

        // 字段字典
        if (obj instanceof IColumnEnum) {
            return whetherTableAlias ? ColumnUtil.splitDefaultAlias((IColumnEnum) obj) : ColumnUtil.split((IColumnEnum) obj);
        }

        // 字段实体
        if (obj instanceof ColumnEO) {
            return whetherTableAlias ? ColumnUtil.splitDefaultAlias((ColumnEO) obj) : ColumnUtil.split((ColumnEO) obj);
        }

        // 数值类型
        // 数值包装类型，Integer/Long/Short/Byte/Float/Double 18 / 4.5
        // 数值基本类型，int/long/short/byte/float/double/     23 / 6.932
        // 其它数值类型，BigDecimal 5.34
        if (obj instanceof Number) {
            return " " + obj + " ";
        }

        // 布尔类型 （Boolean，boolean）
        if (obj instanceof Boolean) {
            return (Boolean) obj ? " 1 " : " 0 ";
        }

        // 字符队列类型（String， StringBuilder， StringBuffer）
        if (obj instanceof CharSequence) {
            return " '" + ((CharSequence) obj).toString() + "' ";
        }

        // 字符类型 (Character，char)
        if (obj instanceof Character) {
            return " '" + obj + "' ";
        }

        // 日期类型
        if (obj instanceof Date) {
            return " '" + DateUtil.formatToText((Date) obj) + "' ";
        } else if (obj instanceof LocalDateTime) {
            return " '" + DateUtil.formatToText((LocalDateTime) obj) + "' ";
        } else if (obj instanceof LocalDate) {
            return " '" + DateUtil.formatToText((LocalDate) obj) + "' ";
        } else if (obj instanceof LocalTime) {
            return " '" + DateUtil.formatToText((LocalTime) obj) + "' ";
        }

        FlagAssert.isFalse(obj instanceof Collection, "入参不支持集合类型");
        FlagAssert.isFalse(obj instanceof Map, "入参不支持键值对类型");
        FlagAssert.isFalse(obj instanceof byte[], "入参不支持数组类型");
        FlagAssert.isFalse(obj instanceof short[], "入参不支持数组类型");
        FlagAssert.isFalse(obj instanceof int[], "入参不支持数组类型");
        FlagAssert.isFalse(obj instanceof long[], "入参不支持数组类型");
        FlagAssert.isFalse(obj instanceof float[], "入参不支持数组类型");
        FlagAssert.isFalse(obj instanceof double[], "入参不支持数组类型");
        FlagAssert.isFalse(obj instanceof boolean[], "入参不支持数组类型");
        FlagAssert.isFalse(obj instanceof char[], "入参不支持数组类型");
        FlagAssert.isFalse(obj instanceof Object[], "入参不支持数组类型");
        throw new IllegalStateException("未知类型");
    }
}
