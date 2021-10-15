package org.czh.commons.utils.sql.base;

import org.czh.commons.entity.eo.BaseViewEO;
import org.czh.commons.enums.parent.IColumnEnum;
import org.czh.commons.enums.sql.SpecialKey;
import org.czh.commons.utils.sql.eo.ColumnEO;
import org.czh.commons_core.asserts.EmptyAssert;
import org.czh.commons_core.convertor.MapConvertor;
import org.czh.commons_core.validate.EmptyValidate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021/10/8
 * email 916419307@qq.com
 */
@SuppressWarnings({"unused", "DuplicatedCode"})
public final class OrderUtil {

    private static String ASC() {
        return " ASC ";
    }

    private static String DESC() {
        return " DESC ";
    }
    
    /*
      -----------------------------拼装 查询字段 SQL（装载到实体）-------------------------------
     */

    public static <EO extends BaseViewEO> void addAsc(final EO conditionEO, final String... columnNames) {
        EmptyAssert.isNotEmpty(columnNames);

        for (String columnName : columnNames) {
            addSQL(conditionEO, ColumnUtil.splitDefaultAlias(columnName) + ASC());
        }
    }

    public static <EO extends BaseViewEO> void addAsc(final EO conditionEO, final IColumnEnum... columnEnums) {
        EmptyAssert.isNotEmpty(columnEnums);

        for (IColumnEnum columnEnum : columnEnums) {
            addSQL(conditionEO, ColumnUtil.splitDefaultAlias(columnEnum) + ASC());
        }
    }

    public static <EO extends BaseViewEO> void addAsc(final EO conditionEO, final ColumnEO... columnEOS) {
        EmptyAssert.isNotEmpty(columnEOS);

        for (ColumnEO columnEO : columnEOS) {
            addSQL(conditionEO, ColumnUtil.splitDefaultAlias(columnEO) + ASC());
        }
    }

    public static <EO extends BaseViewEO> void addDesc(final EO conditionEO, final String... columnNames) {
        EmptyAssert.isNotEmpty(columnNames);

        for (String columnName : columnNames) {
            addSQL(conditionEO, ColumnUtil.splitDefaultAlias(columnName) + DESC());
        }
    }

    public static <EO extends BaseViewEO> void addDesc(final EO conditionEO, final IColumnEnum... columnEnums) {
        EmptyAssert.isNotEmpty(columnEnums);

        for (IColumnEnum columnEnum : columnEnums) {
            addSQL(conditionEO, ColumnUtil.splitDefaultAlias(columnEnum) + DESC());
        }
    }

    public static <EO extends BaseViewEO> void addDesc(final EO conditionEO, final ColumnEO... columnEOS) {
        EmptyAssert.isNotEmpty(columnEOS);

        for (ColumnEO columnEO : columnEOS) {
            addSQL(conditionEO, ColumnUtil.splitDefaultAlias(columnEO) + DESC());
        }
    }

    /*
      -----------------------------拼装 查询字段 SQL（装载到键值对）-------------------------------
     */

    public static void addAsc(final Map<String, Object> conditionMap, final String... columnNames) {
        EmptyAssert.isNotEmpty(columnNames);

        for (String columnName : columnNames) {
            addSQL(conditionMap, ColumnUtil.splitDefaultAlias(columnName) + ASC());
        }
    }

    public static void addAsc(final Map<String, Object> conditionMap, final IColumnEnum... columnEnums) {
        EmptyAssert.isNotEmpty(columnEnums);

        for (IColumnEnum columnEnum : columnEnums) {
            addSQL(conditionMap, ColumnUtil.splitDefaultAlias(columnEnum) + ASC());
        }
    }

    public static void addAsc(final Map<String, Object> conditionMap, final ColumnEO... columnEOS) {
        EmptyAssert.isNotEmpty(columnEOS);

        for (ColumnEO columnEO : columnEOS) {
            addSQL(conditionMap, ColumnUtil.splitDefaultAlias(columnEO) + ASC());
        }
    }

    public static void addDesc(final Map<String, Object> conditionMap, final String... columnNames) {
        EmptyAssert.isNotEmpty(columnNames);

        for (String columnName : columnNames) {
            addSQL(conditionMap, ColumnUtil.splitDefaultAlias(columnName) + DESC());
        }
    }

    public static void addDesc(final Map<String, Object> conditionMap, final IColumnEnum... columnEnums) {
        EmptyAssert.isNotEmpty(columnEnums);

        for (IColumnEnum columnEnum : columnEnums) {
            addSQL(conditionMap, ColumnUtil.splitDefaultAlias(columnEnum) + DESC());
        }
    }

    public static void addDesc(final Map<String, Object> conditionMap, final ColumnEO... columnEOS) {
        EmptyAssert.isNotEmpty(columnEOS);

        for (ColumnEO columnEO : columnEOS) {
            addSQL(conditionMap, ColumnUtil.splitDefaultAlias(columnEO) + DESC());
        }
    }

    /*
      -----------------------------拼装 查询字段 SQL（装载到可变的字符序列）-------------------------------
     */

    public static void appendAsc(final StringBuilder builder, final String... columnNames) {
        appendAsc(builder, false, columnNames);
    }

    public static void appendAsc(final StringBuilder builder, final IColumnEnum... columnEnums) {
        appendAsc(builder, false, columnEnums);
    }

    public static void appendAsc(final StringBuilder builder, final ColumnEO... columnEOS) {
        appendAsc(builder, false, columnEOS);
    }

    public static void appendDesc(final StringBuilder builder, final String... columnNames) {
        appendDesc(builder, false, columnNames);
    }

    public static void appendDesc(final StringBuilder builder, final IColumnEnum... columnEnums) {
        appendDesc(builder, false, columnEnums);
    }

    public static void appendDesc(final StringBuilder builder, final ColumnEO... columnEOS) {
        appendDesc(builder, false, columnEOS);
    }

    public static void appendAsc(final StringBuilder builder, boolean isTop, final String... columnNames) {
        EmptyAssert.isNotNull(builder);
        EmptyAssert.isNotEmpty(columnNames);

        for (String columnName : columnNames) {
            if (!isTop) {
                builder.append(" , ");
            }
            builder.append(ColumnUtil.splitDefaultAlias(columnName)).append(ASC());
            isTop = false;
        }
    }

    public static void appendAsc(final StringBuilder builder, boolean isTop, final IColumnEnum... columnEnums) {
        EmptyAssert.isNotNull(builder);
        EmptyAssert.isNotEmpty(columnEnums);

        for (IColumnEnum columnEnum : columnEnums) {
            if (!isTop) {
                builder.append(" , ");
            }
            builder.append(ColumnUtil.splitDefaultAlias(columnEnum)).append(ASC());
            isTop = false;
        }
    }

    public static void appendAsc(final StringBuilder builder, boolean isTop, final ColumnEO... columnEOS) {
        EmptyAssert.isNotNull(builder);
        EmptyAssert.isNotEmpty(columnEOS);

        for (ColumnEO columnEO : columnEOS) {
            if (!isTop) {
                builder.append(" , ");
            }
            builder.append(ColumnUtil.splitDefaultAlias(columnEO)).append(ASC());
            isTop = false;
        }
    }

    public static void appendDesc(final StringBuilder builder, boolean isTop, final String... columnNames) {
        EmptyAssert.isNotNull(builder);
        EmptyAssert.isNotEmpty(columnNames);

        for (String columnName : columnNames) {
            if (!isTop) {
                builder.append(" , ");
            }
            builder.append(ColumnUtil.splitDefaultAlias(columnName)).append(DESC());
            isTop = false;
        }
    }

    public static void appendDesc(final StringBuilder builder, boolean isTop, final IColumnEnum... columnEnums) {
        EmptyAssert.isNotNull(builder);
        EmptyAssert.isNotEmpty(columnEnums);

        for (IColumnEnum columnEnum : columnEnums) {
            if (!isTop) {
                builder.append(" , ");
            }
            builder.append(ColumnUtil.splitDefaultAlias(columnEnum)).append(DESC());
            isTop = false;
        }
    }

    public static void appendDesc(final StringBuilder builder, boolean isTop, final ColumnEO... columnEOS) {
        EmptyAssert.isNotNull(builder);
        EmptyAssert.isNotEmpty(columnEOS);

        for (ColumnEO columnEO : columnEOS) {
            if (!isTop) {
                builder.append(" , ");
            }
            builder.append(ColumnUtil.splitDefaultAlias(columnEO)).append(DESC());
            isTop = false;
        }
    }

    /*
      -----------------------------拼装 查询字段 SQL（不进行装载，直接返回）-------------------------------
     */
    public static List<String> splitAsc(final String... columnNames) {
        EmptyAssert.isNotEmpty(columnNames);

        List<String> orderSQLList = new ArrayList<>(columnNames.length);
        for (String columnName : columnNames) {
            orderSQLList.add(ColumnUtil.splitDefaultAlias(columnName) + ASC());
        }
        return orderSQLList;
    }

    public static List<String> splitAsc(final IColumnEnum... columnEnums) {
        EmptyAssert.isNotEmpty(columnEnums);

        List<String> orderSQLList = new ArrayList<>(columnEnums.length);
        for (IColumnEnum columnEnum : columnEnums) {
            orderSQLList.add(ColumnUtil.splitDefaultAlias(columnEnum) + ASC());
        }
        return orderSQLList;
    }

    public static List<String> splitAsc(final ColumnEO... columnEOS) {
        EmptyAssert.isNotEmpty(columnEOS);

        List<String> orderSQLList = new ArrayList<>(columnEOS.length);
        for (ColumnEO columnEO : columnEOS) {
            orderSQLList.add(ColumnUtil.splitDefaultAlias(columnEO) + ASC());
        }
        return orderSQLList;
    }

    public static List<String> splitDesc(final String... columnNames) {
        EmptyAssert.isNotEmpty(columnNames);

        List<String> orderSQLList = new ArrayList<>(columnNames.length);
        for (String columnName : columnNames) {
            orderSQLList.add(ColumnUtil.splitDefaultAlias(columnName) + DESC());
        }
        return orderSQLList;
    }

    public static List<String> splitDesc(final IColumnEnum... columnEnums) {
        EmptyAssert.isNotEmpty(columnEnums);

        List<String> orderSQLList = new ArrayList<>(columnEnums.length);
        for (IColumnEnum columnEnum : columnEnums) {
            orderSQLList.add(ColumnUtil.splitDefaultAlias(columnEnum) + DESC());
        }
        return orderSQLList;
    }

    public static List<String> splitDesc(final ColumnEO... columnEOS) {
        EmptyAssert.isNotEmpty(columnEOS);

        List<String> orderSQLList = new ArrayList<>(columnEOS.length);
        for (ColumnEO columnEO : columnEOS) {
            orderSQLList.add(ColumnUtil.splitDefaultAlias(columnEO) + DESC());
        }
        return orderSQLList;
    }

    /*
      -----------------------------拼装 查询字段函数 SQL（装载到实体）-------------------------------
     */

    /**
     * 拼装 空参函数
     * 示例 now() asc
     *
     * @param conditionEO 实体
     * @param func        函数 now
     * @param <EO>        实体类型
     */
    public static <EO extends BaseViewEO> void addAscFunc(final EO conditionEO, final String func) {
        addSQL(conditionEO, splitAscFunc(func));
    }

    /**
     * 拼装 一参函数
     * 示例 min(a.`age`) asc
     *
     * @param conditionEO 实体
     * @param func        函数 min
     * @param funcObj     函数入参 age 字段枚举
     * @param <EO>        实体类型
     */
    public static <EO extends BaseViewEO> void addAscFunc(final EO conditionEO,
                                                          final String func,
                                                          final Object funcObj) {
        addSQL(conditionEO, splitAscFunc(func, funcObj));
    }

    /**
     * 拼装 多参函数
     * 示例 concat(a.`name`, '-', a.`age`) asc
     *
     * @param conditionEO 实体
     * @param func        函数 concat
     * @param separator   分隔符 , 逗号字符串
     * @param funcObjs    函数入参 name 字段枚举，- 中横线字符串，age 字段枚举
     * @param <EO>        实体类型
     */
    public static <EO extends BaseViewEO> void addAscFunc(final EO conditionEO,
                                                          final String func,
                                                          final String separator,
                                                          final Object... funcObjs) {
        addSQL(conditionEO, splitAscFunc(func, separator, funcObjs));
    }

    /**
     * 拼装 空参函数
     * 示例 now() desc
     *
     * @param conditionEO 实体
     * @param func        函数 now
     * @param <EO>        实体类型
     */
    public static <EO extends BaseViewEO> void addDescFunc(final EO conditionEO, final String func) {
        addSQL(conditionEO, splitDescFunc(func));
    }

    /**
     * 拼装 一参函数
     * 示例 min(a.`age`) desc
     *
     * @param conditionEO 实体
     * @param func        函数 min
     * @param funcObj     函数入参 age 字段枚举
     * @param <EO>        实体类型
     */
    public static <EO extends BaseViewEO> void addDescFunc(final EO conditionEO,
                                                           final String func,
                                                           final Object funcObj) {
        addSQL(conditionEO, splitDescFunc(func, funcObj));
    }

    /**
     * 拼装 多参函数
     * 示例 concat(a.`name`, '-', a.`age`) desc
     *
     * @param conditionEO 实体
     * @param func        函数 concat
     * @param separator   分隔符 , 逗号字符串
     * @param funcObjs    函数入参 name 字段枚举，- 中横线字符串，age 字段枚举
     * @param <EO>        实体类型
     */
    public static <EO extends BaseViewEO> void addDescFunc(final EO conditionEO,
                                                           final String func,
                                                           final String separator,
                                                           final Object... funcObjs) {
        addSQL(conditionEO, splitDescFunc(func, separator, funcObjs));
    }

    /*
      -----------------------------拼装 查询字段函数 SQL（装载到键值对）-------------------------------
     */

    /**
     * 拼装 空参函数
     * 示例 now() asc
     *
     * @param conditionMap 键值对
     * @param func         函数 now
     */
    public static void addAscFunc(final Map<String, Object> conditionMap, final String func) {
        addSQL(conditionMap, splitAscFunc(func));
    }

    /**
     * 拼装 一参函数
     * 示例 min(a.`age`) asc
     *
     * @param conditionMap 键值对
     * @param func         函数 min
     * @param funcObj      函数入参 age 字段枚举
     */
    public static void addAscFunc(final Map<String, Object> conditionMap, final String func, final Object funcObj) {
        addSQL(conditionMap, splitAscFunc(func, funcObj));
    }

    /**
     * 拼装 多参函数
     * 示例 concat(a.`name`, '-', a.`age`) asc
     *
     * @param conditionMap 键值对
     * @param func         函数 concat
     * @param separator    分隔符 , 逗号字符串
     * @param funcObjs     函数入参 name 字段枚举，- 中横线字符串，age 字段枚举
     */
    public static void addAscFunc(final Map<String, Object> conditionMap,
                                  final String func,
                                  final String separator,
                                  final Object... funcObjs) {
        addSQL(conditionMap, splitAscFunc(func, separator, funcObjs));
    }

    /**
     * 拼装 空参函数
     * 示例 now() desc
     *
     * @param conditionMap 键值对
     * @param func         函数 now
     */
    public static void addDescFunc(final Map<String, Object> conditionMap, final String func) {
        addSQL(conditionMap, splitDescFunc(func));
    }

    /**
     * 拼装 一参函数
     * 示例 min(a.`age`) desc
     *
     * @param conditionMap 键值对
     * @param func         函数 min
     * @param funcObj      函数入参 age 字段枚举
     */
    public static void addDescFunc(final Map<String, Object> conditionMap, final String func, final Object funcObj) {
        addSQL(conditionMap, splitDescFunc(func, funcObj));
    }

    /**
     * 拼装 多参函数
     * 示例 concat(a.`name`, '-', a.`age`) desc
     *
     * @param conditionMap 键值对
     * @param func         函数 concat
     * @param separator    分隔符 , 逗号字符串
     * @param funcObjs     函数入参 name 字段枚举，- 中横线字符串，age 字段枚举
     */
    public static void addDescFunc(final Map<String, Object> conditionMap,
                                   final String func,
                                   final String separator,
                                   final Object... funcObjs) {
        addSQL(conditionMap, splitDescFunc(func, separator, funcObjs));
    }

    /*
      -----------------------------拼装 查询字段函数 SQL（装载到可变的字符序列）-------------------------------
     */

    /**
     * 拼装 空参函数
     * 示例 now() asc
     *
     * @param builder 可变的字符序列
     * @param func    函数 now
     */
    public static void appendAscFunc(final StringBuilder builder, final String func) {
        appendAscFunc(builder, false, func);
    }

    /**
     * 拼装 一参函数
     * 示例 min(a.`age`) asc
     *
     * @param builder 可变的字符序列
     * @param func    函数 min
     * @param funcObj 函数入参 age 字段枚举
     */
    public static void appendAscFunc(final StringBuilder builder,
                                     final String func,
                                     final Object funcObj) {
        appendAscFunc(builder, false, func, funcObj);
    }

    /**
     * 拼装 多参函数
     * 示例 concat(a.`name`, '-', a.`age`) asc
     *
     * @param builder   可变的字符序列
     * @param func      函数 concat
     * @param separator 分隔符 , 逗号字符串
     * @param funcObjs  函数入参 name 字段枚举，- 中横线字符串，age 字段枚举
     */
    public static void appendAscFunc(final StringBuilder builder,
                                     final String func,
                                     final String separator,
                                     final Object... funcObjs) {
        appendAscFunc(builder, false, func, separator, funcObjs);
    }

    /**
     * 拼装 空参函数
     * 示例 now() asc
     *
     * @param builder 可变的字符序列
     * @param func    函数 now
     */
    public static void appendAscFunc(final StringBuilder builder, boolean isTop, final String func) {
        EmptyAssert.isNotNull(builder);

        if (!isTop) {
            builder.append(" , ");
        }
        builder.append(splitAscFunc(func));
    }

    /**
     * 拼装 一参函数
     * 示例 min(a.`age`) asc
     *
     * @param builder 可变的字符序列
     * @param func    函数 min
     * @param funcObj 函数入参 age 字段枚举
     */
    public static void appendAscFunc(final StringBuilder builder,
                                     boolean isTop,
                                     final String func,
                                     final Object funcObj) {
        EmptyAssert.isNotNull(builder);

        if (!isTop) {
            builder.append(" , ");
        }
        builder.append(splitAscFunc(func, funcObj));
    }

    /**
     * 拼装 多参函数
     * 示例 concat(a.`name`, '-', a.`age`) asc
     *
     * @param builder   可变的字符序列
     * @param func      函数 concat
     * @param separator 分隔符 , 逗号字符串
     * @param funcObjs  函数入参 name 字段枚举，- 中横线字符串，age 字段枚举
     */
    public static void appendAscFunc(final StringBuilder builder,
                                     boolean isTop,
                                     final String func,
                                     final String separator,
                                     final Object... funcObjs) {
        EmptyAssert.isNotNull(builder);

        if (!isTop) {
            builder.append(" , ");
        }
        builder.append(splitAscFunc(func, separator, funcObjs));
    }

    /**
     * 拼装 空参函数
     * 示例 now() desc
     *
     * @param builder 可变的字符序列
     * @param func    函数 now
     */
    public static void appendDescFunc(final StringBuilder builder, final String func) {
        appendDescFunc(builder, false, func);
    }

    /**
     * 拼装 一参函数
     * 示例 min(a.`age`) desc
     *
     * @param builder 可变的字符序列
     * @param func    函数 min
     * @param funcObj 函数入参 age 字段枚举
     */
    public static void appendDescFunc(final StringBuilder builder,
                                      final String func,
                                      final Object funcObj) {
        appendDescFunc(builder, false, func, funcObj);
    }

    /**
     * 拼装 多参函数
     * 示例 concat(a.`name`, '-', a.`age`) desc
     *
     * @param builder   可变的字符序列
     * @param func      函数 concat
     * @param separator 分隔符 , 逗号字符串
     * @param funcObjs  函数入参 name 字段枚举，- 中横线字符串，age 字段枚举
     */
    public static void appendDescFunc(final StringBuilder builder,
                                      final String func,
                                      final String separator,
                                      final Object... funcObjs) {
        appendDescFunc(builder, false, func, separator, funcObjs);
    }

    /**
     * 拼装 空参函数
     * 示例 now() desc
     *
     * @param builder 可变的字符序列
     * @param func    函数 now
     */
    public static void appendDescFunc(final StringBuilder builder, boolean isTop, final String func) {
        EmptyAssert.isNotNull(builder);

        if (!isTop) {
            builder.append(" , ");
        }
        builder.append(splitDescFunc(func));
    }

    /**
     * 拼装 一参函数
     * 示例 min(a.`age`) desc
     *
     * @param builder 可变的字符序列
     * @param func    函数 min
     * @param funcObj 函数入参 age 字段枚举
     */
    public static void appendDescFunc(final StringBuilder builder,
                                      boolean isTop,
                                      final String func,
                                      final Object funcObj) {
        EmptyAssert.isNotNull(builder);

        if (!isTop) {
            builder.append(" , ");
        }
        builder.append(splitDescFunc(func, funcObj));
    }

    /**
     * 拼装 多参函数
     * 示例 concat(a.`name`, '-', a.`age`) desc
     *
     * @param builder   可变的字符序列
     * @param func      函数 concat
     * @param separator 分隔符 , 逗号字符串
     * @param funcObjs  函数入参 name 字段枚举，- 中横线字符串，age 字段枚举
     */
    public static void appendDescFunc(final StringBuilder builder,
                                      boolean isTop,
                                      final String func,
                                      final String separator,
                                      final Object... funcObjs) {
        EmptyAssert.isNotNull(builder);

        if (!isTop) {
            builder.append(" , ");
        }
        builder.append(splitDescFunc(func, separator, funcObjs));
    }

    /*
      -----------------------------拼装 查询字段函数 SQL（不进行装载，直接返回）-------------------------------
     */

    /**
     * 拼装 空参函数
     * 示例 now() asc
     *
     * @param func 函数 now
     */
    public static String splitAscFunc(final String func) {
        return splitAscFunc(func, null);
    }

    /**
     * 拼装 一参函数
     * 示例 min(a.`age`) asc
     *
     * @param func    函数 min
     * @param funcObj 函数入参 age 字段枚举
     */
    public static String splitAscFunc(final String func, final Object funcObj) {
        return splitAscFunc(func, null, funcObj);
    }

    /**
     * 拼装 多参函数
     * 示例 concat(a.`name`, '-', a.`age`) asc
     *
     * @param func      函数 concat
     * @param separator 分隔符 , 逗号字符串
     * @param funcObjs  函数入参 name 字段枚举/字段实体，- 中横线字符串，age 字段枚举/字段实体
     */
    public static String splitAscFunc(final String func, final String separator, final Object... funcObjs) {
        return FuncUtil.splitMore(func, separator, true, funcObjs) + ASC();
    }

    /**
     * 拼装 空参函数
     * 示例 now() asc
     *
     * @param func 函数 now
     */
    public static String splitDescFunc(final String func) {
        return splitDescFunc(func, null);
    }

    /**
     * 拼装 一参函数
     * 示例 min(a.`age`) asc
     *
     * @param func    函数 min
     * @param funcObj 函数入参 age 字段枚举
     */
    public static String splitDescFunc(final String func, final Object funcObj) {
        return splitDescFunc(func, null, funcObj);
    }

    /**
     * 拼装 多参函数
     * 示例 concat(a.`name`, '-', a.`age`) asc
     *
     * @param func      函数 concat
     * @param separator 分隔符 , 逗号字符串
     * @param funcObjs  函数入参 name 字段枚举/字段实体，- 中横线字符串，age 字段枚举/字段实体
     */
    public static String splitDescFunc(final String func, final String separator, final Object... funcObjs) {
        return FuncUtil.splitMore(func, separator, true, funcObjs) + DESC();
    }

    /*
      -----------------------------装载 查询字段 SQL-------------------------------
     */

    public static <EO extends BaseViewEO> void addSQL(final EO conditionEO, final String orderSQL) {
        EmptyAssert.isNotNull(conditionEO);
        EmptyAssert.isNotBlank(orderSQL);

        List<String> orderSQLList = conditionEO.getOrderSQLList();
        if (EmptyValidate.isNull(orderSQLList)) {
            orderSQLList = new ArrayList<>();
            conditionEO.setOrderSQLList(orderSQLList);
        }
        if (!orderSQLList.contains(orderSQL)) {
            orderSQLList.add(orderSQL);
        }
    }

    public static <EO extends BaseViewEO> void addSQLList(final EO conditionEO, final List<String> orderSQLS) {
        EmptyAssert.isNotNull(conditionEO);
        EmptyAssert.isNotEmpty(orderSQLS);

        List<String> orderSQLList = conditionEO.getOrderSQLList();
        if (EmptyValidate.isNull(orderSQLList)) {
            conditionEO.setOrderSQLList(orderSQLS);
            return;
        }

        for (String orderSQL : orderSQLS) {
            if (!orderSQLList.contains(orderSQL)) {
                orderSQLList.add(orderSQL);
            }
        }
    }

    public static void addSQL(final Map<String, Object> conditionMap, final String orderSQL) {
        EmptyAssert.isNotNull(conditionMap);
        EmptyAssert.isNotBlank(orderSQL);

        @SuppressWarnings("unchecked")
        List<String> orderSQLList = MapConvertor.convertToFirstValue(
                conditionMap,
                SpecialKey.orderSQLList.name(),
                obj -> (List<String>) obj
        );
        if (EmptyValidate.isNull(orderSQLList)) {
            orderSQLList = new ArrayList<>();
            conditionMap.put(SpecialKey.orderSQLList.name(), orderSQLList);
        }
        if (!orderSQLList.contains(orderSQL)) {
            orderSQLList.add(orderSQL);
        }

    }

    public static void addSQLList(final Map<String, Object> conditionMap, final List<String> orderSQLS) {
        EmptyAssert.isNotNull(conditionMap);
        EmptyAssert.isNotEmpty(orderSQLS);

        @SuppressWarnings("unchecked")
        List<String> orderSQLList = MapConvertor.convertToFirstValue(
                conditionMap,
                SpecialKey.orderSQLList.name(),
                obj -> (List<String>) obj
        );
        if (EmptyValidate.isNull(orderSQLList)) {
            conditionMap.put(SpecialKey.orderSQLList.name(), orderSQLS);
            return;
        }

        for (String orderSQL : orderSQLS) {
            if (!orderSQLList.contains(orderSQL)) {
                orderSQLList.add(orderSQL);
            }
        }
    }

}
