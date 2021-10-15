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
 * date : 2021/9/29
 * email 916419307@qq.com
 */
@SuppressWarnings({"unused", "DuplicatedCode"})
public final class SelectUtil {

    /*
      -----------------------------拼装 查询字段 SQL（装载到实体）-------------------------------
     */

    /**
     * 拼装 查询字段 SQL（装载到实体）
     * SQL示例：a.`age`
     *
     * @param conditionEO 数据实体
     * @param columnNames 字段名称数组
     * @see org.czh.commons.entity.eo.BaseViewEO.selectSQLList
     */
    public static <EO extends BaseViewEO> void add(final EO conditionEO, final String... columnNames) {
        EmptyAssert.isNotEmpty(columnNames);

        for (String columnName : columnNames) {
            addSQL(conditionEO, ColumnUtil.splitDefaultAlias(columnName));
        }
    }

    /**
     * 拼装 查询字段 SQL（装载到实体）
     * SQL示例：a.`age`
     *
     * @param conditionEO 数据实体
     * @param columnNames 字段字典数组
     * @see org.czh.commons.entity.eo.BaseViewEO.selectSQLList
     */
    public static <EO extends BaseViewEO> void add(final EO conditionEO, final IColumnEnum... columnEnums) {
        EmptyAssert.isNotEmpty(columnEnums);

        for (IColumnEnum columnEnum : columnEnums) {
            addSQL(conditionEO, ColumnUtil.splitDefaultAlias(columnEnum));
        }
    }

    /**
     * 拼装 查询字段 SQL（装载到实体）
     * SQL示例：a.`age`
     *
     * @param conditionEO 数据实体
     * @param columnNames 字段实体数组
     * @see org.czh.commons.entity.eo.BaseViewEO.selectSQLList
     */
    public static <EO extends BaseViewEO> void add(final EO conditionEO, final ColumnEO... columnEOS) {
        EmptyAssert.isNotEmpty(columnEOS);

        for (ColumnEO columnEO : columnEOS) {
            addSQL(conditionEO, ColumnUtil.splitDefaultAlias(columnEO));
        }
    }

    /*
      -----------------------------拼装 查询字段 SQL（装载到键值对）-------------------------------
     */

    /**
     * 拼装 查询字段 SQL（装载到键值对）
     * SQL示例：a.`age`
     *
     * @param conditionMap 键值对
     * @param columnNames  字段名称数组
     * @see org.czh.commons.enums.sql.SpecialKey.selectSQLList 特殊Key值
     */
    public static void add(final Map<String, Object> conditionMap, final String... columnNames) {
        EmptyAssert.isNotEmpty(columnNames);

        for (String columnName : columnNames) {
            addSQL(conditionMap, ColumnUtil.splitDefaultAlias(columnName));
        }
    }

    /**
     * 拼装 查询字段 SQL（装载到键值对）
     * SQL示例：a.`age`
     *
     * @param conditionMap 键值对
     * @param columnNames  字段字典数组
     * @see org.czh.commons.enums.sql.SpecialKey.selectSQLList 特殊Key值
     */
    public static void add(final Map<String, Object> conditionMap, final IColumnEnum... columnEnums) {
        EmptyAssert.isNotEmpty(columnEnums);

        for (IColumnEnum columnEnum : columnEnums) {
            addSQL(conditionMap, ColumnUtil.splitDefaultAlias(columnEnum));
        }
    }

    /**
     * 拼装 查询字段 SQL（装载到键值对）
     * SQL示例：a.`age`
     *
     * @param conditionMap 键值对
     * @param columnNames  字段实体数组
     * @see org.czh.commons.enums.sql.SpecialKey.selectSQLList 特殊Key值
     */
    public static void add(final Map<String, Object> conditionMap, final ColumnEO... columnEOS) {
        EmptyAssert.isNotEmpty(columnEOS);

        for (ColumnEO columnEO : columnEOS) {
            addSQL(conditionMap, ColumnUtil.splitDefaultAlias(columnEO));
        }
    }

    /*
      -----------------------------拼装 查询字段 SQL（装载到可变的字符序列）-------------------------------
     */

    /**
     * 拼装查询字段SQL，装载到可变的字符序列
     * SQL示例：a.`age`
     */
    public static void append(final StringBuilder builder, final String... columnNames) {
        append(builder, false, columnNames);
    }

    /**
     * 拼装查询字段SQL，装载到可变的字符序列
     * SQL示例：a.`age`
     */
    public static void append(final StringBuilder builder, final IColumnEnum... columnEnums) {
        append(builder, false, columnEnums);
    }

    /**
     * 拼装查询字段SQL，装载到可变的字符序列
     * SQL示例：a.`age`
     */
    public static void append(final StringBuilder builder, final ColumnEO... columnEOS) {
        append(builder, false, columnEOS);
    }

    /**
     * 拼装查询字段SQL，装载到可变的字符序列
     * SQL示例：a.`age`
     */
    public static void append(final StringBuilder builder, boolean isTop, final String... columnNames) {
        EmptyAssert.isNotNull(builder);
        EmptyAssert.isNotEmpty(columnNames);

        for (String columnName : columnNames) {
            if (!isTop) {
                builder.append(", ");
            }
            builder.append(ColumnUtil.splitDefaultAlias(columnName));
            isTop = false;
        }
    }

    /**
     * 拼装查询字段SQL，装载到可变的字符序列
     * SQL示例：a.`age`
     */
    public static void append(final StringBuilder builder, boolean isTop, final IColumnEnum... columnEnums) {
        EmptyAssert.isNotNull(builder);
        EmptyAssert.isNotEmpty(columnEnums);

        for (IColumnEnum columnEnum : columnEnums) {
            if (!isTop) {
                builder.append(", ");
            }
            builder.append(ColumnUtil.splitDefaultAlias(columnEnum));
            isTop = false;
        }
    }

    /**
     * 拼装查询字段SQL，装载到可变的字符序列
     * SQL示例：a.`age`
     */
    public static void append(final StringBuilder builder, boolean isTop, final ColumnEO... columnEOS) {
        EmptyAssert.isNotNull(builder);
        EmptyAssert.isNotEmpty(columnEOS);

        for (ColumnEO columnEO : columnEOS) {
            if (!isTop) {
                builder.append(", ");
            }
            builder.append(ColumnUtil.splitDefaultAlias(columnEO));
            isTop = false;
        }
    }

    /*
      -----------------------------拼装 查询字段 SQL（不进行装载，直接返回）-------------------------------
     */

    /**
     * 拼装查询字段SQL
     * SQL示例：a.`age`
     */
    public static List<String> split(final String... columnNames) {
        EmptyAssert.isNotEmpty(columnNames);

        List<String> selectSQLList = new ArrayList<>(columnNames.length);
        for (String columnName : columnNames) {
            selectSQLList.add(ColumnUtil.splitDefaultAlias(columnName));
        }
        return selectSQLList;
    }

    /**
     * 拼装查询字段SQL
     * SQL示例：a.`age`
     */
    public static List<String> split(final IColumnEnum... columnEnums) {
        EmptyAssert.isNotEmpty(columnEnums);

        List<String> selectSQLList = new ArrayList<>(columnEnums.length);
        for (IColumnEnum columnEnum : columnEnums) {
            selectSQLList.add(ColumnUtil.splitDefaultAlias(columnEnum));
        }
        return selectSQLList;
    }

    /**
     * 拼装查询字段SQL
     * SQL示例：a.`age`
     */
    public static List<String> split(final ColumnEO... columnEOS) {
        EmptyAssert.isNotEmpty(columnEOS);

        List<String> selectSQLList = new ArrayList<>(columnEOS.length);
        for (ColumnEO columnEO : columnEOS) {
            selectSQLList.add(ColumnUtil.splitDefaultAlias(columnEO));
        }
        return selectSQLList;
    }

    /*
      -----------------------------拼装 查询字段函数 SQL（装载到实体）-------------------------------
     */

    /**
     * 拼装 空参函数
     * 示例 now() as `nowDate`
     *
     * @param conditionEO 实体
     * @param func        函数 now
     * @param aliasColumn 别名 nowDate 字段枚举
     * @param <EO>        实体类型
     */
    public static <EO extends BaseViewEO> void addFunc(final EO conditionEO,
                                                       final String func,
                                                       final IColumnEnum aliasColumn) {
        addSQL(conditionEO, splitFunc(func, aliasColumn));
    }

    /**
     * 拼装 一参函数
     * 示例 min(a.`age`) as `age`
     *
     * @param conditionEO 实体
     * @param func        函数 min
     * @param aliasColumn 别名 age 字段枚举
     * @param funcObj     函数入参 age 字段枚举
     * @param <EO>        实体类型
     */
    public static <EO extends BaseViewEO> void addFunc(final EO conditionEO,
                                                       final String func,
                                                       final IColumnEnum aliasColumn,
                                                       final Object funcObj) {
        addSQL(conditionEO, splitFunc(func, aliasColumn, funcObj));
    }

    /**
     * 拼装 多参函数
     * 示例 concat(a.`name`, '-', a.`age`) as `name`
     *
     * @param conditionEO 实体
     * @param func        函数 concat
     * @param aliasColumn 别名 name 字段枚举
     * @param separator   分隔符 , 逗号字符串
     * @param funcObjs    函数入参 name 字段枚举，- 中横线字符串，age 字段枚举
     * @param <EO>        实体类型
     */
    public static <EO extends BaseViewEO> void addFunc(final EO conditionEO,
                                                       final String func,
                                                       final IColumnEnum aliasColumn,
                                                       final String separator,
                                                       final Object... funcObjs) {
        addSQL(conditionEO, splitFunc(func, aliasColumn, separator, funcObjs));
    }

    /**
     * 拼装 空参函数
     * 示例 now() as `nowDate`
     *
     * @param conditionEO 实体
     * @param func        函数 now
     * @param aliasColumn 别名 nowDate 字段实体
     * @param <EO>        实体类型
     */
    public static <EO extends BaseViewEO> void addFunc(final EO conditionEO,
                                                       final String func,
                                                       final ColumnEO aliasColumn) {
        addSQL(conditionEO, splitFunc(func, aliasColumn));
    }

    /**
     * 拼装 一参函数
     * 示例 min(a.`age`) as `age`
     *
     * @param conditionEO 实体
     * @param func        函数 min
     * @param aliasColumn 别名 age 字段实体
     * @param funcObj     函数入参 age 字段实体
     * @param <EO>        实体类型
     */
    public static <EO extends BaseViewEO> void addFunc(final EO conditionEO,
                                                       final String func,
                                                       final ColumnEO aliasColumn,
                                                       final Object funcObj) {
        addSQL(conditionEO, splitFunc(func, aliasColumn, funcObj));
    }

    /**
     * 拼装 多参函数
     * 示例 concat(a.`name`, '-', a.`age`) as `name`
     *
     * @param conditionEO 实体
     * @param func        函数 concat
     * @param aliasColumn 别名 name 字段实体
     * @param separator   分隔符 , 逗号字符串
     * @param funcObjs    函数入参 name 字段实体，- 中横线字符串，age 字段实体
     * @param <EO>        实体类型
     */
    public static <EO extends BaseViewEO> void addFunc(final EO conditionEO,
                                                       final String func,
                                                       final ColumnEO aliasColumn,
                                                       final String separator,
                                                       final Object... funcObjs) {
        addSQL(conditionEO, splitFunc(func, aliasColumn, separator, funcObjs));
    }

    /**
     * 拼装 空参函数
     * 示例 now() as `nowDate`
     *
     * @param conditionEO 实体
     * @param func        函数 now
     * @param aliasColumn 别名 nowDate 字段字符串
     * @param <EO>        实体类型
     */
    public static <EO extends BaseViewEO> void addFunc(final EO conditionEO,
                                                       final String func,
                                                       final String aliasColumn) {
        addSQL(conditionEO, splitFunc(func, aliasColumn));
    }

    /**
     * 拼装 一参函数
     * 示例 min(a.`age`) as `age`
     *
     * @param conditionEO 实体
     * @param func        函数 min
     * @param aliasColumn 别名 age 字段字符串
     * @param funcObj     函数入参 age 字段枚举/字段实体
     * @param <EO>        实体类型
     */
    public static <EO extends BaseViewEO> void addFunc(final EO conditionEO,
                                                       final String func,
                                                       final String aliasColumn,
                                                       final Object funcObj) {
        addSQL(conditionEO, splitFunc(func, aliasColumn, funcObj));
    }

    /**
     * 拼装 多参函数
     * 示例 concat(a.`name`, '-', a.`age`) as `name`
     *
     * @param conditionEO 实体
     * @param func        函数 concat
     * @param aliasColumn 别名 name 字段字符串
     * @param separator   分隔符 , 逗号字符串
     * @param funcObjs    函数入参 name 字段枚举/字段实体，- 中横线字符串，age 字段枚举/字段实体
     * @param <EO>        实体类型
     */
    public static <EO extends BaseViewEO> void addFunc(final EO conditionEO,
                                                       final String func,
                                                       final String aliasColumn,
                                                       final String separator,
                                                       final Object... funcObjs) {
        addSQL(conditionEO, splitFunc(func, aliasColumn, separator, funcObjs));
    }

    /*
      -----------------------------拼装 查询字段函数 SQL（装载到键值对）-------------------------------
     */

    /**
     * 拼装 空参函数
     * 示例 now() as `nowDate`
     *
     * @param conditionMap 键值对
     * @param func         函数 now
     * @param aliasColumn  别名 nowDate 字段枚举
     */
    public static void addFunc(final Map<String, Object> conditionMap,
                               final String func,
                               final IColumnEnum aliasColumn) {
        addSQL(conditionMap, splitFunc(func, aliasColumn));
    }

    /**
     * 拼装 一参函数
     * 示例 min(a.`age`) as `age`
     *
     * @param conditionMap 键值对
     * @param func         函数 min
     * @param aliasColumn  别名 age 字段枚举
     * @param funcObj      函数入参 age 字段枚举
     */
    public static void addFunc(final Map<String, Object> conditionMap,
                               final String func,
                               final IColumnEnum aliasColumn,
                               final Object funcObj) {
        addSQL(conditionMap, splitFunc(func, aliasColumn, funcObj));
    }

    /**
     * 拼装 多参函数
     * 示例 concat(a.`name`, '-', a.`age`) as `name`
     *
     * @param conditionMap 键值对
     * @param func         函数 concat
     * @param aliasColumn  别名 name 字段枚举
     * @param separator    分隔符 , 逗号字符串
     * @param funcObjs     函数入参 name 字段枚举，- 中横线字符串，age 字段枚举
     */
    public static void addFunc(final Map<String, Object> conditionMap,
                               final String func,
                               final IColumnEnum aliasColumn,
                               final String separator,
                               final Object... funcObjs) {
        addSQL(conditionMap, splitFunc(func, aliasColumn, separator, funcObjs));
    }

    /**
     * 拼装 空参函数
     * 示例 now() as `nowDate`
     *
     * @param conditionMap 键值对
     * @param func         函数 now
     * @param aliasColumn  别名 nowDate 字段实体
     */
    public static void addFunc(final Map<String, Object> conditionMap,
                               final String func,
                               final ColumnEO aliasColumn) {
        addSQL(conditionMap, splitFunc(func, aliasColumn));
    }

    /**
     * 拼装 一参函数
     * 示例 min(a.`age`) as `age`
     *
     * @param conditionMap 键值对
     * @param func         函数 min
     * @param aliasColumn  别名 age 字段实体
     * @param funcObj      函数入参 age 字段实体
     */
    public static void addFunc(final Map<String, Object> conditionMap,
                               final String func,
                               final ColumnEO aliasColumn,
                               final Object funcObj) {
        addSQL(conditionMap, splitFunc(func, aliasColumn, funcObj));
    }

    /**
     * 拼装 多参函数
     * 示例 concat(a.`name`, '-', a.`age`) as `name`
     *
     * @param conditionMap 键值对
     * @param func         函数 concat
     * @param aliasColumn  别名 name 字段实体
     * @param separator    分隔符 , 逗号字符串
     * @param funcObjs     函数入参 name 字段实体，- 中横线字符串，age 字段实体
     */
    public static void addFunc(final Map<String, Object> conditionMap,
                               final String func,
                               final ColumnEO aliasColumn,
                               final String separator,
                               final Object... funcObjs) {
        addSQL(conditionMap, splitFunc(func, aliasColumn, separator, funcObjs));
    }

    /**
     * 拼装 空参函数
     * 示例 now() as `nowDate`
     *
     * @param conditionMap 键值对
     * @param func         函数 now
     * @param aliasColumn  别名 nowDate 字段字符串
     */
    public static void addFunc(final Map<String, Object> conditionMap,
                               final String func,
                               final String aliasColumn) {
        addSQL(conditionMap, splitFunc(func, aliasColumn));
    }

    /**
     * 拼装 一参函数
     * 示例 min(a.`age`) as `age`
     *
     * @param conditionMap 键值对
     * @param func         函数 min
     * @param aliasColumn  别名 age 字段字符串
     * @param funcObj      函数入参 age 字段枚举/字段实体
     */
    public static void addFunc(final Map<String, Object> conditionMap,
                               final String func,
                               final String aliasColumn,
                               final Object funcObj) {
        addSQL(conditionMap, splitFunc(func, aliasColumn, funcObj));
    }

    /**
     * 拼装 多参函数
     * 示例 concat(a.`name`, '-', a.`age`) as `name`
     *
     * @param conditionMap 键值对
     * @param func         函数 concat
     * @param aliasColumn  别名 name 字段字符串
     * @param separator    分隔符 , 逗号字符串
     * @param funcObjs     函数入参 name 字段枚举/字段实体，- 中横线字符串，age 字段枚举/字段实体
     */
    public static void addFunc(final Map<String, Object> conditionMap,
                               final String func,
                               final String aliasColumn,
                               final String separator,
                               final Object... funcObjs) {
        addSQL(conditionMap, splitFunc(func, aliasColumn, separator, funcObjs));
    }

    /*
      -----------------------------拼装 查询字段函数 SQL（装载到可变的字符序列）-------------------------------
     */

    /**
     * 拼装 空参函数
     * 示例 now() as `nowDate`
     *
     * @param builder     可变的字符序列
     * @param func        函数 now
     * @param aliasColumn 别名 nowDate 字段枚举
     */
    public static void appendFunc(final StringBuilder builder,
                                  final String func,
                                  final IColumnEnum aliasColumn) {
        appendFunc(builder, false, func, aliasColumn);
    }

    /**
     * 拼装 一参函数
     * 示例 min(a.`age`) as `age`
     *
     * @param builder     可变的字符序列
     * @param func        函数 min
     * @param aliasColumn 别名 age 字段枚举
     * @param funcObj     函数入参 age 字段枚举
     */
    public static void appendFunc(final StringBuilder builder,
                                  final String func,
                                  final IColumnEnum aliasColumn,
                                  final Object funcObj) {
        appendFunc(builder, false, func, aliasColumn, funcObj);
    }

    /**
     * 拼装 多参函数
     * 示例 concat(a.`name`, '-', a.`age`) as `name`
     *
     * @param builder     可变的字符序列
     * @param func        函数 concat
     * @param aliasColumn 别名 name 字段枚举
     * @param separator   分隔符 , 逗号字符串
     * @param funcObjs    函数入参 name 字段枚举，- 中横线字符串，age 字段枚举
     */
    public static void appendFunc(final StringBuilder builder,
                                  final String func,
                                  final IColumnEnum aliasColumn,
                                  final String separator,
                                  final Object... funcObjs) {
        appendFunc(builder, false, func, aliasColumn, separator, funcObjs);
    }

    public static void appendFunc(final StringBuilder builder,
                                  boolean isTop,
                                  final String func,
                                  final IColumnEnum aliasColumn) {
        EmptyAssert.isNotNull(builder);

        if (!isTop) {
            builder.append(", ");
        }
        builder.append(splitFunc(func, aliasColumn));
    }

    /**
     * 拼装 一参函数
     * 示例 min(a.`age`) as `age`
     *
     * @param builder     可变的字符序列
     * @param isTop       是否是开头位置
     * @param func        函数 min
     * @param aliasColumn 别名 age 字段枚举
     * @param funcObj     函数入参 age 字段枚举
     */
    public static void appendFunc(final StringBuilder builder,
                                  boolean isTop,
                                  final String func,
                                  final IColumnEnum aliasColumn,
                                  final Object funcObj) {
        EmptyAssert.isNotNull(builder);

        if (!isTop) {
            builder.append(", ");
        }
        builder.append(splitFunc(func, aliasColumn, funcObj));
    }

    /**
     * 拼装 多参函数
     * 示例 concat(a.`name`, '-', a.`age`) as `name`
     *
     * @param builder     可变的字符序列
     * @param isTop       是否是开头位置
     * @param func        函数 concat
     * @param aliasColumn 别名 name 字段枚举
     * @param separator   分隔符 , 逗号字符串
     * @param funcObjs    函数入参 name 字段枚举，- 中横线字符串，age 字段枚举
     */
    public static void appendFunc(final StringBuilder builder,
                                  boolean isTop,
                                  final String func,
                                  final IColumnEnum aliasColumn,
                                  final String separator,
                                  final Object... funcObjs) {
        EmptyAssert.isNotNull(builder);

        if (!isTop) {
            builder.append(", ");
        }
        builder.append(splitFunc(func, aliasColumn, separator, funcObjs));
    }

    /**
     * 拼装 空参函数
     * 示例 now() as `nowDate`
     *
     * @param builder     可变的字符序列
     * @param func        函数 now
     * @param aliasColumn 别名 nowDate 字段实体
     */
    public static void appendFunc(final StringBuilder builder,
                                  final String func,
                                  final ColumnEO aliasColumn) {
        appendFunc(builder, false, func, aliasColumn);
    }

    /**
     * 拼装 一参函数
     * 示例 min(a.`age`) as `age`
     *
     * @param builder     可变的字符序列
     * @param func        函数 min
     * @param aliasColumn 别名 age 字段实体
     * @param funcObj     函数入参 age 字段实体
     */
    public static void appendFunc(final StringBuilder builder,
                                  final String func,
                                  final ColumnEO aliasColumn,
                                  final Object funcObj) {
        appendFunc(builder, false, func, aliasColumn, funcObj);
    }

    /**
     * 拼装 多参函数
     * 示例 concat(a.`name`, '-', a.`age`) as `name`
     *
     * @param builder     可变的字符序列
     * @param func        函数 concat
     * @param aliasColumn 别名 name 字段实体
     * @param separator   分隔符 , 逗号字符串
     * @param funcObjs    函数入参 name 字段实体，- 中横线字符串，age 字段实体
     */
    public static void appendFunc(final StringBuilder builder,
                                  final String func,
                                  final ColumnEO aliasColumn,
                                  final String separator,
                                  final Object... funcObjs) {
        appendFunc(builder, false, func, aliasColumn, separator, funcObjs);
    }

    /**
     * 拼装 空参函数
     * 示例 now() as `nowDate`
     *
     * @param builder     可变的字符序列
     * @param isTop       是否是开头位置
     * @param func        函数 now
     * @param aliasColumn 别名 nowDate 字段实体
     */
    public static void appendFunc(final StringBuilder builder,
                                  boolean isTop,
                                  final String func,
                                  final ColumnEO aliasColumn) {
        EmptyAssert.isNotNull(builder);

        if (!isTop) {
            builder.append(", ");
        }
        builder.append(splitFunc(func, aliasColumn));
    }

    /**
     * 拼装 一参函数
     * 示例 min(a.`age`) as `age`
     *
     * @param builder     可变的字符序列
     * @param isTop       是否是开头位置
     * @param func        函数 min
     * @param aliasColumn 别名 age 字段实体
     * @param funcObj     函数入参 age 字段实体
     */
    public static void appendFunc(final StringBuilder builder,
                                  boolean isTop,
                                  final String func,
                                  final ColumnEO aliasColumn,
                                  final Object funcObj) {
        EmptyAssert.isNotNull(builder);

        if (!isTop) {
            builder.append(", ");
        }
        builder.append(splitFunc(func, aliasColumn, funcObj));
    }

    /**
     * 拼装 多参函数
     * 示例 concat(a.`name`, '-', a.`age`) as `name`
     *
     * @param builder     可变的字符序列
     * @param isTop       是否是开头位置
     * @param func        函数 concat
     * @param aliasColumn 别名 name 字段实体
     * @param separator   分隔符 , 逗号字符串
     * @param funcObjs    函数入参 name 字段实体，- 中横线字符串，age 字段实体
     */
    public static void appendFunc(final StringBuilder builder,
                                  boolean isTop,
                                  final String func,
                                  final ColumnEO aliasColumn,
                                  final String separator,
                                  final Object... funcObjs) {
        EmptyAssert.isNotNull(builder);

        if (!isTop) {
            builder.append(", ");
        }
        builder.append(splitFunc(func, aliasColumn, separator, funcObjs));
    }

    /**
     * 拼装 空参函数
     * 示例 now() as `nowDate`
     *
     * @param builder     可变的字符序列
     * @param func        函数 now
     * @param aliasColumn 别名 nowDate 字段字符串
     */
    public static void appendFunc(final StringBuilder builder,
                                  final String func,
                                  final String aliasColumn) {
        appendFunc(builder, false, func, aliasColumn);
    }

    /**
     * 拼装 一参函数
     * 示例 min(a.`age`) as `age`
     *
     * @param builder     可变的字符序列
     * @param func        函数 min
     * @param aliasColumn 别名 age 字段字符串
     * @param funcObj     函数入参 age 字段枚举/字段实体
     */
    public static void appendFunc(final StringBuilder builder,
                                  final String func,
                                  final String aliasColumn,
                                  final Object funcObj) {
        appendFunc(builder, false, func, aliasColumn, funcObj);
    }

    /**
     * 拼装 多参函数
     * 示例 concat(a.`name`, '-', a.`age`) as `name`
     *
     * @param builder     可变的字符序列
     * @param func        函数 concat
     * @param aliasColumn 别名 name 字段字符串
     * @param separator   分隔符 , 逗号字符串
     * @param funcObjs    函数入参 name 字段枚举/字段实体，- 中横线字符串，age 字段枚举/字段实体
     */
    public static void appendFunc(final StringBuilder builder,
                                  final String func,
                                  final String aliasColumn,
                                  final String separator,
                                  final Object... funcObjs) {
        appendFunc(builder, false, func, aliasColumn, separator, funcObjs);
    }

    /**
     * 拼装 空参函数
     * 示例 now() as `nowDate`
     *
     * @param builder     可变的字符序列
     * @param isTop       是否是开头位置
     * @param func        函数 now
     * @param aliasColumn 别名 nowDate 字段字符串
     */
    public static void appendFunc(final StringBuilder builder,
                                  boolean isTop,
                                  final String func,
                                  final String aliasColumn) {
        EmptyAssert.isNotNull(builder);

        if (!isTop) {
            builder.append(", ");
        }
        builder.append(splitFunc(func, aliasColumn));
    }

    /**
     * 拼装 一参函数
     * 示例 min(a.`age`) as `age`
     *
     * @param builder     可变的字符序列
     * @param isTop       是否是开头位置
     * @param func        函数 min
     * @param aliasColumn 别名 age 字段字符串
     * @param funcObj     函数入参 age 字段枚举/字段实体
     */
    public static void appendFunc(final StringBuilder builder,
                                  boolean isTop,
                                  final String func,
                                  final String aliasColumn,
                                  final Object funcObj) {
        EmptyAssert.isNotNull(builder);

        if (!isTop) {
            builder.append(", ");
        }
        builder.append(splitFunc(func, aliasColumn, funcObj));
    }

    /**
     * 拼装 多参函数
     * 示例 concat(a.`name`, '-', a.`age`) as `name`
     *
     * @param builder     可变的字符序列
     * @param isTop       是否是开头位置
     * @param func        函数 concat
     * @param aliasColumn 别名 name 字段字符串
     * @param separator   分隔符 , 逗号字符串
     * @param funcObjs    函数入参 name 字段枚举/字段实体，- 中横线字符串，age 字段枚举/字段实体
     */
    public static void appendFunc(final StringBuilder builder,
                                  boolean isTop,
                                  final String func,
                                  final String aliasColumn,
                                  final String separator,
                                  final Object... funcObjs) {
        EmptyAssert.isNotNull(builder);

        if (!isTop) {
            builder.append(", ");
        }
        builder.append(splitFunc(func, aliasColumn, separator, funcObjs));
    }

    /*
      -----------------------------拼装 查询字段函数 SQL（不进行装载，直接返回）-------------------------------
     */

    /**
     * 拼装 空参函数
     * 示例 now() as `nowDate`
     *
     * @param func        函数 now
     * @param aliasColumn 别名 nowDate 字段枚举
     */
    public static String splitFunc(final String func, final IColumnEnum aliasColumn) {
        return splitFunc(func, aliasColumn, null);
    }

    /**
     * 拼装 一参函数
     * 示例 min(a.`age`) as `age`
     *
     * @param func        函数 min
     * @param aliasColumn 别名 age 字段枚举
     * @param funcObj     函数入参 age 字段枚举
     */
    public static String splitFunc(final String func, final IColumnEnum aliasColumn, final Object funcObj) {
        return splitFunc(func, aliasColumn, null, funcObj);
    }

    /**
     * 拼装 多参函数
     * 示例 concat(a.`name`, '-', a.`age`) as `name`
     *
     * @param func        函数 concat
     * @param aliasColumn 别名 name 字段枚举
     * @param separator   分隔符 , 逗号字符串
     * @param funcObjs    函数入参 name 字段枚举，- 中横线字符串，age 字段枚举
     */
    public static String splitFunc(final String func,
                                   final IColumnEnum aliasColumn,
                                   final String separator,
                                   final Object... funcObjs) {
        EmptyAssert.isNotNull(aliasColumn);
        return splitFunc(func, aliasColumn.getColumn(), separator, funcObjs);
    }

    /**
     * 拼装 空参函数
     * 示例 now() as `nowDate`
     *
     * @param func        函数 now
     * @param aliasColumn 别名 nowDate 字段实体
     */
    public static String splitFunc(final String func, final ColumnEO aliasColumn) {
        return splitFunc(func, aliasColumn, null);
    }

    /**
     * 拼装 一参函数
     * 示例 min(a.`age`) as `age`
     *
     * @param func        函数 min
     * @param aliasColumn 别名 age 字段实体
     * @param funcObj     函数入参 age 字段实体
     */
    public static String splitFunc(final String func, final ColumnEO aliasColumn, final Object funcObj) {
        return splitFunc(func, aliasColumn, null, funcObj);
    }

    /**
     * 拼装 多参函数
     * 示例 concat(a.`name`, '-', a.`age`) as `name`
     *
     * @param func        函数 concat
     * @param aliasColumn 别名 name 字段实体
     * @param separator   分隔符 , 逗号字符串
     * @param funcObjs    函数入参 name 字段实体，- 中横线字符串，age 字段实体
     */
    public static String splitFunc(final String func,
                                   final ColumnEO aliasColumn,
                                   final String separator,
                                   final Object... funcObjs) {
        EmptyAssert.isNotNull(aliasColumn);
        return splitFunc(func, aliasColumn.getColumn(), separator, funcObjs);
    }

    /**
     * 拼装 空参函数
     * 示例 now() as `nowDate`
     *
     * @param func        函数 now
     * @param aliasColumn 别名 nowDate 字段字符串
     */
    public static String splitFunc(final String func, final String aliasColumn) {
        return splitFunc(func, aliasColumn, null);
    }

    /**
     * 拼装 一参函数
     * 示例 min(a.`age`) as `age`
     *
     * @param func        函数 min
     * @param aliasColumn 别名 age 字段字符串
     * @param funcObj     函数入参 age 字段枚举/字段实体
     */
    public static String splitFunc(final String func, final String aliasColumn, final Object funcObj) {
        return splitFunc(func, aliasColumn, null, funcObj);
    }

    /**
     * 拼装 多参函数
     * 示例 concat(a.`name`, '-', a.`age`) as `name`
     *
     * @param func        函数 concat
     * @param aliasColumn 别名 name 字段字符串
     * @param separator   分隔符 , 逗号字符串
     * @param funcObjs    函数入参 name 字段枚举/字段实体，- 中横线字符串，age 字段枚举/字段实体
     */
    public static String splitFunc(final String func,
                                   final String aliasColumn,
                                   final String separator,
                                   final Object... funcObjs) {
        EmptyAssert.isNotNull(aliasColumn);
        return FuncUtil.splitSelectAliasMore(func, aliasColumn, separator, true, funcObjs);
    }

    /*
      -----------------------------装载 查询字段 SQL-------------------------------
     */

    public static <EO extends BaseViewEO> void addSQL(final EO conditionEO, final String selectSQL) {
        EmptyAssert.isNotNull(conditionEO);
        EmptyAssert.isNotBlank(selectSQL);

        List<String> selectSQLList = conditionEO.getSelectSQLList();
        if (EmptyValidate.isNull(selectSQLList)) {
            selectSQLList = new ArrayList<>();
            conditionEO.setSelectSQLList(selectSQLList);
        }
        if (!selectSQLList.contains(selectSQL)) {
            selectSQLList.add(selectSQL);
        }
    }

    public static <EO extends BaseViewEO> void addSQLList(final EO conditionEO, final List<String> selectSQLS) {
        EmptyAssert.isNotNull(conditionEO);
        EmptyAssert.isNotEmpty(selectSQLS);

        List<String> selectSQLList = conditionEO.getSelectSQLList();
        if (EmptyValidate.isNull(selectSQLList)) {
            conditionEO.setSelectSQLList(selectSQLS);
            return;
        }

        for (String selectSQL : selectSQLS) {
            if (!selectSQLList.contains(selectSQL)) {
                selectSQLList.add(selectSQL);
            }
        }
    }

    public static void addSQL(final Map<String, Object> conditionMap, final String selectSQL) {
        EmptyAssert.isNotNull(conditionMap);
        EmptyAssert.isNotBlank(selectSQL);

        @SuppressWarnings("unchecked")
        List<String> selectSQLList = MapConvertor.convertToFirstValue(
                conditionMap,
                SpecialKey.selectSQLList.name(),
                obj -> (List<String>) obj
        );
        if (EmptyValidate.isNull(selectSQLList)) {
            selectSQLList = new ArrayList<>();
            conditionMap.put(SpecialKey.selectSQLList.name(), selectSQLList);
        }
        if (!selectSQLList.contains(selectSQL)) {
            selectSQLList.add(selectSQL);
        }

    }

    public static void addSQLList(final Map<String, Object> conditionMap, final List<String> selectSQLS) {
        EmptyAssert.isNotNull(conditionMap);
        EmptyAssert.isNotEmpty(selectSQLS);

        @SuppressWarnings("unchecked")
        List<String> selectSQLList = MapConvertor.convertToFirstValue(
                conditionMap,
                SpecialKey.selectSQLList.name(),
                obj -> (List<String>) obj
        );
        if (EmptyValidate.isNull(selectSQLList)) {
            conditionMap.put(SpecialKey.selectSQLList.name(), selectSQLS);
            return;
        }

        for (String selectSQL : selectSQLS) {
            if (!selectSQLList.contains(selectSQL)) {
                selectSQLList.add(selectSQL);
            }
        }
    }
}
