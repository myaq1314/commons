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
 * date : 2021/9/30
 * email 916419307@qq.com
 */
@SuppressWarnings({"unused", "DuplicatedCode"})
public final class GroupUtil {

    /*
      -----------------------------拼装 字段 分组 SQL（装载到实体）-------------------------------
     */

    public static <EO extends BaseViewEO> void add(final EO conditionEO, final String... columnNames) {
        EmptyAssert.isNotEmpty(columnNames);

        for (String columnName : columnNames) {
            addSQL(conditionEO, ColumnUtil.splitDefaultAlias(columnName));
        }
    }

    public static <EO extends BaseViewEO> void add(final EO conditionEO, final IColumnEnum... columnEnums) {
        EmptyAssert.isNotEmpty(columnEnums);

        for (IColumnEnum columnEnum : columnEnums) {
            addSQL(conditionEO, ColumnUtil.splitDefaultAlias(columnEnum));
        }
    }

    public static <EO extends BaseViewEO> void add(final EO conditionEO, final ColumnEO... columnEOS) {
        EmptyAssert.isNotEmpty(columnEOS);

        for (ColumnEO columnEO : columnEOS) {
            addSQL(conditionEO, ColumnUtil.splitDefaultAlias(columnEO));
        }
    }

    /*
      -----------------------------拼装 查询字段 SQL（装载到键值对）-------------------------------
     */

    public static void add(final Map<String, Object> conditionMap, final String... columnNames) {
        EmptyAssert.isNotEmpty(columnNames);

        for (String columnName : columnNames) {
            addSQL(conditionMap, ColumnUtil.splitDefaultAlias(columnName));
        }
    }

    public static void add(final Map<String, Object> conditionMap, final IColumnEnum... columnEnums) {
        EmptyAssert.isNotEmpty(columnEnums);

        for (IColumnEnum columnEnum : columnEnums) {
            addSQL(conditionMap, ColumnUtil.splitDefaultAlias(columnEnum));
        }
    }

    public static void add(final Map<String, Object> conditionMap, final ColumnEO... columnEOS) {
        EmptyAssert.isNotEmpty(columnEOS);

        for (ColumnEO columnEO : columnEOS) {
            addSQL(conditionMap, ColumnUtil.splitDefaultAlias(columnEO));
        }
    }

    /*
      -----------------------------拼装 查询字段 SQL（装载到可变的字符序列）-------------------------------
     */

    public static void append(final StringBuilder builder, final String... columnNames) {
        append(builder, false, columnNames);
    }

    public static void append(final StringBuilder builder, final IColumnEnum... columnEnums) {
        append(builder, false, columnEnums);
    }

    public static void append(final StringBuilder builder, final ColumnEO... columnEOS) {
        append(builder, false, columnEOS);
    }

    public static void append(final StringBuilder builder, boolean isTop, final String... columnNames) {
        EmptyAssert.isNotNull(builder);
        EmptyAssert.isNotEmpty(columnNames);

        for (String columnName : columnNames) {
            if (!isTop) {
                builder.append(" , ");
            }
            builder.append(ColumnUtil.splitDefaultAlias(columnName));
            isTop = false;
        }
    }

    public static void append(final StringBuilder builder, boolean isTop, final IColumnEnum... columnEnums) {
        EmptyAssert.isNotNull(builder);
        EmptyAssert.isNotEmpty(columnEnums);

        for (IColumnEnum columnEnum : columnEnums) {
            if (!isTop) {
                builder.append(" , ");
            }
            builder.append(ColumnUtil.splitDefaultAlias(columnEnum));
            isTop = false;
        }
    }

    public static void append(final StringBuilder builder, boolean isTop, final ColumnEO... columnEOS) {
        EmptyAssert.isNotNull(builder);
        EmptyAssert.isNotEmpty(columnEOS);

        for (ColumnEO columnEO : columnEOS) {
            if (!isTop) {
                builder.append(" , ");
            }
            builder.append(ColumnUtil.splitDefaultAlias(columnEO));
            isTop = false;
        }
    }

    /*
      -----------------------------拼装 查询字段 SQL（不进行装载，直接返回）-------------------------------
     */
    public static List<String> split(final String... columnNames) {
        EmptyAssert.isNotEmpty(columnNames);

        List<String> groupSQLList = new ArrayList<>(columnNames.length);
        for (String columnName : columnNames) {
            String groupSQL = ColumnUtil.splitDefaultAlias(columnName);
            if (!groupSQLList.contains(groupSQL)) {
                groupSQLList.add(groupSQL);
            }
        }
        return groupSQLList;
    }

    public static List<String> split(final IColumnEnum... columnEnums) {
        EmptyAssert.isNotEmpty(columnEnums);

        List<String> groupSQLList = new ArrayList<>(columnEnums.length);
        for (IColumnEnum columnEnum : columnEnums) {
            String groupSQL = ColumnUtil.splitDefaultAlias(columnEnum);
            if (!groupSQLList.contains(groupSQL)) {
                groupSQLList.add(groupSQL);
            }
        }
        return groupSQLList;
    }

    public static List<String> split(final ColumnEO... columnEOS) {
        EmptyAssert.isNotEmpty(columnEOS);

        List<String> groupSQLList = new ArrayList<>(columnEOS.length);
        for (ColumnEO columnEO : columnEOS) {
            String groupSQL = ColumnUtil.splitDefaultAlias(columnEO);
            if (!groupSQLList.contains(groupSQL)) {
                groupSQLList.add(groupSQL);
            }
        }
        return groupSQLList;
    }

    /*
      -----------------------------拼装 函数 分组 SQL（装载到实体）-------------------------------
     */

    /**
     * 拼装 空参函数
     * 示例 now()
     *
     * @param conditionEO 实体
     * @param func        函数 now
     * @param <EO>        实体类型
     */
    public static <EO extends BaseViewEO> void addFunc(final EO conditionEO, final String func) {
        addSQL(conditionEO, splitFunc(func));
    }

    /**
     * 拼装 一参函数
     * 示例 min(a.`age`)
     *
     * @param conditionEO 实体
     * @param func        函数 min
     * @param funcObj     函数入参 age 字段枚举
     * @param <EO>        实体类型
     */
    public static <EO extends BaseViewEO> void addFunc(final EO conditionEO, final String func, final Object funcObj) {
        addSQL(conditionEO, splitFunc(func, funcObj));
    }

    /**
     * 拼装 多参函数
     * 示例 concat(a.`name`, '-', a.`age`)
     *
     * @param conditionEO 实体
     * @param func        函数 concat
     * @param separator   分隔符 , 逗号字符串
     * @param funcObjs    函数入参 name 字段枚举，- 中横线字符串，age 字段枚举
     * @param <EO>        实体类型
     */
    public static <EO extends BaseViewEO> void addFunc(final EO conditionEO,
                                                       final String func,
                                                       final String separator,
                                                       final Object... funcObjs) {
        addSQL(conditionEO, splitFunc(func, separator, funcObjs));
    }

    /*
      -----------------------------拼装 函数 分组 SQL（装载到键值对）-------------------------------
     */

    /**
     * 拼装 空参函数
     * 示例 now()
     *
     * @param conditionMap 键值对
     * @param func         函数 now
     */
    public static void addFunc(final Map<String, Object> conditionMap, final String func) {
        addSQL(conditionMap, splitFunc(func));
    }

    /**
     * 拼装 一参函数
     * 示例 min(a.`age`)
     *
     * @param conditionMap 键值对
     * @param func         函数 min
     * @param funcObj      函数入参 age 字段枚举
     */
    public static void addFunc(final Map<String, Object> conditionMap, final String func, final Object funcObj) {
        addSQL(conditionMap, splitFunc(func, funcObj));
    }

    /**
     * 拼装 多参函数
     * 示例 concat(a.`name`, '-', a.`age`)
     *
     * @param conditionMap 键值对
     * @param func         函数 concat
     * @param separator    分隔符 , 逗号字符串
     * @param funcObjs     函数入参 name 字段枚举，- 中横线字符串，age 字段枚举
     */
    public static void addFunc(final Map<String, Object> conditionMap,
                               final String func,
                               final String separator,
                               final Object... funcObjs) {
        addSQL(conditionMap, splitFunc(func, separator, funcObjs));
    }

    /*
      -----------------------------拼装 函数 分组 SQL（装载到可变的字符序列）-------------------------------
     */

    /**
     * 拼装 空参函数
     * 示例 now()
     *
     * @param builder 装载到可变的字符序列
     * @param func    函数 now
     */
    public static void appendFunc(final StringBuilder builder, final String func) {
        appendFunc(builder, false, func);
    }

    /**
     * 拼装 一参函数
     * 示例 min(a.`age`)
     *
     * @param builder 装载到可变的字符序列
     * @param func    函数 min
     * @param funcObj 函数入参 age 字段枚举
     */
    public static void appendFunc(final StringBuilder builder,
                                  final String func,
                                  final Object funcObj) {
        appendFunc(builder, false, func, funcObj);
    }

    /**
     * 拼装 多参函数
     * 示例 concat(a.`name`, '-', a.`age`)
     *
     * @param builder   装载到可变的字符序列
     * @param func      函数 concat
     * @param separator 分隔符 , 逗号字符串
     * @param funcObjs  函数入参 name 字段枚举，- 中横线字符串，age 字段枚举
     */
    public static void appendFunc(final StringBuilder builder,
                                  final String func,
                                  final String separator,
                                  final Object... funcObjs) {
        appendFunc(builder, false, func, separator, funcObjs);
    }

    public static void appendFunc(final StringBuilder builder, final boolean isTop, final String func) {
        EmptyAssert.isNotNull(builder);

        if (!isTop) {
            builder.append(" , ");
        }
        builder.append(splitFunc(func));
    }

    /**
     * 拼装 一参函数
     * 示例 min(a.`age`)
     *
     * @param builder 装载到可变的字符序列
     * @param func    函数 min
     * @param funcObj 函数入参 age 字段枚举
     */
    public static void appendFunc(final StringBuilder builder,
                                  final boolean isTop,
                                  final String func,
                                  final Object funcObj) {
        EmptyAssert.isNotNull(builder);

        if (!isTop) {
            builder.append(" , ");
        }
        builder.append(splitFunc(func, funcObj));
    }

    /**
     * 拼装 多参函数
     * 示例 concat(a.`name`, '-', a.`age`)
     *
     * @param builder   装载到可变的字符序列
     * @param func      函数 concat
     * @param separator 分隔符 , 逗号字符串
     * @param funcObjs  函数入参 name 字段枚举，- 中横线字符串，age 字段枚举
     */
    public static void appendFunc(final StringBuilder builder,
                                  final boolean isTop,
                                  final String func,
                                  final String separator,
                                  final Object... funcObjs) {
        EmptyAssert.isNotNull(builder);

        if (!isTop) {
            builder.append(" , ");
        }
        builder.append(splitFunc(func, separator, funcObjs));
    }

    /*
      -----------------------------拼装 函数 分组 SQL（不进行装载，直接返回）-------------------------------
     */

    /**
     * 拼装 空参函数
     * 示例 now()
     *
     * @param func 函数 now
     */
    public static String splitFunc(final String func) {
        return FuncUtil.split(func);
    }

    /**
     * 拼装 一参函数
     * 示例 min(a.`age`)
     *
     * @param func    函数 min
     * @param funcObj 函数入参 age 字段枚举
     */
    public static String splitFunc(final String func, final Object funcObj) {
        return FuncUtil.splitOne(func, Boolean.TRUE, funcObj);
    }

    /**
     * 拼装 多参函数
     * 示例 concat(a.`name`, '-', a.`age`)
     *
     * @param func      函数 concat
     * @param separator 分隔符 , 逗号字符串
     * @param funcObjs  函数入参 name 字段枚举，- 中横线字符串，age 字段枚举
     */
    public static String splitFunc(final String func, final String separator, final Object... funcObjs) {
        return FuncUtil.splitMore(func, separator, Boolean.TRUE, funcObjs);
    }

    /*
      -----------------------------装载 分组 SQL-------------------------------
     */

    public static <EO extends BaseViewEO> void addSQL(final EO conditionEO, final String groupSQL) {
        EmptyAssert.isNotNull(conditionEO);
        EmptyAssert.isNotBlank(groupSQL);

        List<String> groupSQLList = conditionEO.getGroupSQLList();
        if (EmptyValidate.isNull(groupSQLList)) {
            groupSQLList = new ArrayList<>();
            conditionEO.setGroupSQLList(groupSQLList);
        }
        if (!groupSQLList.contains(groupSQL)) {
            groupSQLList.add(groupSQL);
        }
    }

    public static <EO extends BaseViewEO> void addSQLList(final EO conditionEO, final List<String> groupSQLS) {
        EmptyAssert.isNotNull(conditionEO);
        EmptyAssert.isNotEmpty(groupSQLS);

        List<String> groupSQLList = conditionEO.getGroupSQLList();
        if (EmptyValidate.isNull(groupSQLList)) {
            conditionEO.setGroupSQLList(groupSQLS);
            return;
        }

        for (String groupSQL : groupSQLS) {
            if (!groupSQLList.contains(groupSQL)) {
                groupSQLList.add(groupSQL);
            }
        }
    }

    public static void addSQL(final Map<String, Object> conditionMap, final String groupSQL) {
        EmptyAssert.isNotNull(conditionMap);
        EmptyAssert.isNotBlank(groupSQL);

        @SuppressWarnings("unchecked")
        List<String> groupSQLList = MapConvertor.convertToFirstValue(
                conditionMap,
                SpecialKey.groupSQLList.name(),
                obj -> (List<String>) obj
        );
        if (EmptyValidate.isNull(groupSQLList)) {
            groupSQLList = new ArrayList<>();
            conditionMap.put(SpecialKey.groupSQLList.name(), groupSQLList);
        }
        if (!groupSQLList.contains(groupSQL)) {
            groupSQLList.add(groupSQL);
        }

    }

    public static void addSQLList(final Map<String, Object> conditionMap, final List<String> groupSQLS) {
        EmptyAssert.isNotNull(conditionMap);
        EmptyAssert.isNotEmpty(groupSQLS);

        @SuppressWarnings("unchecked")
        List<String> groupSQLList = MapConvertor.convertToFirstValue(
                conditionMap,
                SpecialKey.groupSQLList.name(),
                obj -> (List<String>) obj
        );
        if (EmptyValidate.isNull(groupSQLList)) {
            conditionMap.put(SpecialKey.groupSQLList.name(), groupSQLS);
            return;
        }

        for (String groupSQL : groupSQLS) {
            if (!groupSQLList.contains(groupSQL)) {
                groupSQLList.add(groupSQL);
            }
        }
    }
}
