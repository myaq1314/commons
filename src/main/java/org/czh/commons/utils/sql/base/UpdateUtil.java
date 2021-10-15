package org.czh.commons.utils.sql.base;

import org.czh.commons.entity.eo.BaseCommonEO;
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
public final class UpdateUtil {
    
    /*
      -----------------------------拼装 更新SQL实体（装载到实体）-------------------------------
     */

    public static <EO extends BaseCommonEO> void add(final EO conditionEO,
                                                     final IColumnEnum columnEnum,
                                                     final Object columnValue) {
        EmptyAssert.isNotNull(columnEnum);
        add(conditionEO, columnEnum.getColumn(), columnValue);
    }

    public static <EO extends BaseCommonEO> void add(final EO conditionEO,
                                                     final ColumnEO columnEO,
                                                     final Object columnValue) {
        EmptyAssert.isNotNull(columnEO);
        add(conditionEO, columnEO.getColumn(), columnValue);
    }

    public static <EO extends BaseCommonEO> void add(final EO conditionEO,
                                                     final String columnName,
                                                     final Object columnValue) {
        EmptyAssert.isNotBlank(columnName);
        EmptyAssert.isNotNull(columnValue);
        addSQL(conditionEO,
               String.format(" %s = %s ", ColumnUtil.splitDefaultAlias(columnName), ValueUtil.splitAlias(columnValue))
        );
    }

    public static <EO extends BaseCommonEO> void addFunc(final EO conditionEO,
                                                         final IColumnEnum columnEnum,
                                                         final String func) {
        addSQL(conditionEO, splitFunc(columnEnum, func));
    }

    public static <EO extends BaseCommonEO> void addFunc(final EO conditionEO,
                                                         final IColumnEnum columnEnum,
                                                         final String func,
                                                         final Object funcObj) {
        addSQL(conditionEO, splitFunc(columnEnum, func, funcObj));
    }

    public static <EO extends BaseCommonEO> void addFunc(final EO conditionEO,
                                                         final IColumnEnum columnEnum,
                                                         final String func,
                                                         final String separator,
                                                         final Object... funcObjs) {
        addSQL(conditionEO, splitFunc(columnEnum, func, separator, funcObjs));
    }

    public static <EO extends BaseCommonEO> void addFunc(final EO conditionEO,
                                                         final ColumnEO columnEO,
                                                         final String func) {
        addSQL(conditionEO, splitFunc(columnEO, func));
    }

    public static <EO extends BaseCommonEO> void addFunc(final EO conditionEO,
                                                         final ColumnEO columnEO,
                                                         final String func,
                                                         final Object funcObj) {
        addSQL(conditionEO, splitFunc(columnEO, func, funcObj));
    }

    public static <EO extends BaseCommonEO> void addFunc(final EO conditionEO,
                                                         final ColumnEO columnEO,
                                                         final String func,
                                                         final String separator,
                                                         final Object... funcObjs) {
        addSQL(conditionEO, splitFunc(columnEO, func, separator, funcObjs));
    }

    public static <EO extends BaseCommonEO> void addFunc(final EO conditionEO,
                                                         final String columnName,
                                                         final String func) {
        addSQL(conditionEO, splitFunc(columnName, func));
    }

    public static <EO extends BaseCommonEO> void addFunc(final EO conditionEO,
                                                         final String columnName,
                                                         final String func,
                                                         final Object funcObj) {
        addSQL(conditionEO, splitFunc(columnName, func, funcObj));
    }

    public static <EO extends BaseCommonEO> void addFunc(final EO conditionEO,
                                                         final String columnName,
                                                         final String func,
                                                         final String separator,
                                                         final Object... funcObjs) {
        addSQL(conditionEO, splitFunc(columnName, func, separator, funcObjs));
    }

    /*
      -----------------------------拼装 插入SQL实体（装载到键值对）-------------------------------
     */

    public static void add(final Map<String, Object> conditionMap,
                           final IColumnEnum columnEnum,
                           final Object columnValue) {
        EmptyAssert.isNotNull(columnEnum);
        add(conditionMap, columnEnum.getColumn(), columnValue);
    }

    public static void add(final Map<String, Object> conditionMap,
                           final ColumnEO columnEO,
                           final Object columnValue) {
        EmptyAssert.isNotNull(columnEO);
        add(conditionMap, columnEO.getColumn(), columnValue);
    }

    public static void add(final Map<String, Object> conditionMap,
                           final String columnName,
                           final Object columnValue) {
        addSQL(conditionMap,
               String.format(" %s = %s ", ColumnUtil.splitDefaultAlias(columnName), ValueUtil.splitAlias(columnValue))
        );
    }

    public static void addFunc(final Map<String, Object> conditionMap,
                               final IColumnEnum columnEnum,
                               final String func) {
        addSQL(conditionMap, splitFunc(columnEnum, func));
    }

    public static void addFunc(final Map<String, Object> conditionMap,
                               final IColumnEnum columnEnum,
                               final String func,
                               final Object funcObj) {
        addSQL(conditionMap, splitFunc(columnEnum, func, funcObj));
    }

    public static void addFunc(final Map<String, Object> conditionMap,
                               final IColumnEnum columnEnum,
                               final String func,
                               final String separator,
                               final Object... funcObjs) {
        addSQL(conditionMap, splitFunc(columnEnum, func, separator, funcObjs));
    }

    public static void addFunc(final Map<String, Object> conditionMap,
                               final ColumnEO columnEO,
                               final String func) {
        addSQL(conditionMap, splitFunc(columnEO, func));
    }

    public static void addFunc(final Map<String, Object> conditionMap,
                               final ColumnEO columnEO,
                               final String func,
                               final Object funcObj) {
        addSQL(conditionMap, splitFunc(columnEO, func, funcObj));
    }

    public static void addFunc(final Map<String, Object> conditionMap,
                               final ColumnEO columnEO,
                               final String func,
                               final String separator,
                               final Object... funcObjs) {
        addSQL(conditionMap, splitFunc(columnEO, func, separator, funcObjs));
    }

    public static void addFunc(final Map<String, Object> conditionMap,
                               final String columnName,
                               final String func) {
        addSQL(conditionMap, splitFunc(columnName, func));
    }

    public static void addFunc(final Map<String, Object> conditionMap,
                               final String columnName,
                               final String func,
                               final Object funcObj) {
        addSQL(conditionMap, splitFunc(columnName, func, funcObj));
    }

    public static void addFunc(final Map<String, Object> conditionMap,
                               final String columnName,
                               final String func,
                               final String separator,
                               final Object... funcObjs) {
        addSQL(conditionMap, splitFunc(columnName, func, separator, funcObjs));
    }

    /*
      -----------------------------拼装 插入SQL实体（装载到可变的字符序列）-------------------------------
     */

    public static void append(final StringBuilder builder,
                              final IColumnEnum columnEnum,
                              final Object columnValue) {
        append(builder, false, columnEnum, columnValue);
    }

    public static void append(final StringBuilder builder,
                              final ColumnEO columnEO,
                              final Object columnValue) {
        append(builder, false, columnEO, columnValue);
    }

    public static void append(final StringBuilder builder,
                              final String columnName,
                              final Object columnValue) {
        append(builder, false, columnName, columnValue);
    }

    public static void append(final StringBuilder builder,
                              final boolean isTop,
                              final IColumnEnum columnEnum,
                              final Object columnValue) {
        EmptyAssert.isNotNull(columnEnum);
        append(builder, isTop, columnEnum.getColumn(), columnValue);
    }

    public static void append(final StringBuilder builder,
                              final boolean isTop,
                              final ColumnEO columnEO,
                              final Object columnValue) {
        EmptyAssert.isNotNull(columnEO);
        append(builder, isTop, columnEO.getColumn(), columnValue);
    }

    public static void append(final StringBuilder builder,
                              final boolean isTop,
                              final String columnName,
                              final Object columnValue) {
        EmptyAssert.allNotNull(builder);

        if (!isTop) {
            builder.append(" , ");
        }
        builder.append(" ")
               .append(ColumnUtil.splitDefaultAlias(columnName))
               .append(" = ")
               .append(ValueUtil.splitAlias(columnValue))
               .append(" ");
    }

    public static void appendFunc(final StringBuilder builder,
                                  final boolean isTop,
                                  final IColumnEnum columnEnum,
                                  final String func) {
        appendFunc(builder, isTop, columnEnum, func, "", (Object[]) null);
    }

    public static void appendFunc(final StringBuilder builder,
                                  final boolean isTop,
                                  final IColumnEnum columnEnum,
                                  final String func,
                                  final Object funcObj) {
        appendFunc(builder, isTop, columnEnum, func, "", funcObj);
    }

    public static void appendFunc(final StringBuilder builder,
                                  final boolean isTop,
                                  final IColumnEnum columnEnum,
                                  final String func,
                                  final String separator,
                                  final Object... funcObjs) {
        EmptyAssert.isNotNull(columnEnum);
        appendFunc(builder, isTop, columnEnum.getColumn(), func, separator, funcObjs);
    }

    public static void appendFunc(final StringBuilder builder,
                                  final boolean isTop,
                                  final ColumnEO columnEO,
                                  final String func) {
        appendFunc(builder, isTop, columnEO, func, "", (Object[]) null);
    }

    public static void appendFunc(final StringBuilder builder,
                                  final boolean isTop,
                                  final ColumnEO columnEO,
                                  final String func,
                                  final Object funcObj) {
        appendFunc(builder, isTop, columnEO, func, "", funcObj);
    }

    public static void appendFunc(final StringBuilder builder,
                                  final boolean isTop,
                                  final ColumnEO columnEO,
                                  final String func,
                                  final String separator,
                                  final Object... funcObjs) {
        EmptyAssert.isNotNull(columnEO);
        appendFunc(builder, isTop, columnEO.getColumn(), func, separator, funcObjs);
    }

    public static void appendFunc(final StringBuilder builder,
                                  final boolean isTop,
                                  final String columnName,
                                  final String func) {
        appendFunc(builder, isTop, columnName, func, "", (Object[]) null);
    }

    public static void appendFunc(final StringBuilder builder,
                                  final boolean isTop,
                                  final String columnName,
                                  final String func,
                                  final Object funcObj) {
        appendFunc(builder, isTop, columnName, func, "", funcObj);
    }

    public static void appendFunc(final StringBuilder builder,
                                  final boolean isTop,
                                  final String columnName,
                                  final String func,
                                  final String separator,
                                  final Object... funcObjs) {
        EmptyAssert.isNotNull(builder);
        if (!isTop) {
            builder.append(" , ");
        }
        builder.append(" ")
               .append(ColumnUtil.splitDefaultAlias(columnName))
               .append(" = ")
               .append(FuncUtil.splitMore(func, separator, true, funcObjs))
               .append(" ");
    }

    /*
      -----------------------------拼装 插入SQL实体-------------------------------
     */

    public static String split(final IColumnEnum columnEnum, final Object columnValue) {
        EmptyAssert.isNotNull(columnEnum);
        return split(columnEnum.getColumn(), columnValue);
    }

    public static String split(final ColumnEO columnEO, final Object columnValue) {
        EmptyAssert.isNotNull(columnEO);
        return split(columnEO.getColumn(), columnValue);
    }

    public static String split(final String columnName, final Object columnValue) {
        return String.format(" %s = %s ", ColumnUtil.splitDefaultAlias(columnName), ValueUtil.splitAlias(columnValue));
    }

    public static String splitFunc(final IColumnEnum columnEnum, final String func) {
        return splitFunc(columnEnum, func, "", (Object[]) null);
    }

    public static String splitFunc(final IColumnEnum columnEnum, final String func, final Object funcObj) {
        return splitFunc(columnEnum, func, "", funcObj);
    }

    public static String splitFunc(final IColumnEnum columnEnum,
                                   final String func,
                                   final String separator,
                                   final Object... funcObjs) {
        EmptyAssert.isNotNull(columnEnum);
        return splitFunc(columnEnum.getColumn(), func, separator, funcObjs);
    }

    public static String splitFunc(final ColumnEO columnEO, final String func) {
        return splitFunc(columnEO, func, "", (Object[]) null);
    }

    public static String splitFunc(final ColumnEO columnEO, final String func, final Object funcObj) {
        return splitFunc(columnEO, func, "", funcObj);
    }

    public static String splitFunc(final ColumnEO columnEO,
                                   final String func,
                                   final String separator,
                                   final Object... funcObjs) {
        EmptyAssert.isNotNull(columnEO);
        return splitFunc(columnEO.getColumn(), func, separator, funcObjs);
    }

    public static String splitFunc(final String columnName, final String func) {
        return splitFunc(columnName, func, "", (Object[]) null);
    }

    public static String splitFunc(final String columnName, final String func, final Object funcObj) {
        return splitFunc(columnName, func, "", funcObj);
    }

    public static String splitFunc(final String columnName,
                                   final String func,
                                   final String separator,
                                   final Object... funcObjs) {
        return String.format(" %s = %s ",
                             ColumnUtil.splitDefaultAlias(columnName),
                             FuncUtil.splitMore(func, separator, true, funcObjs)
        );
    }

    /*
      -----------------------------装载 插入SQL实体-------------------------------
     */

    public static <EO extends BaseCommonEO> void addSQL(final EO conditionEO, final String updateSQL) {
        EmptyAssert.allNotNull(conditionEO);
        EmptyAssert.isNotBlank(updateSQL);

        List<String> updateSQLList = conditionEO.getUpdateSQLList();
        if (EmptyValidate.isNull(updateSQLList)) {
            updateSQLList = new ArrayList<>();
            conditionEO.setUpdateSQLList(updateSQLList);
        }
        if (!updateSQLList.contains(updateSQL)) {
            updateSQLList.add(updateSQL);
        }
    }

    public static <EO extends BaseCommonEO> void addSQLList(final EO conditionEO, final List<String> updateSQLS) {
        EmptyAssert.isNotNull(conditionEO);
        EmptyAssert.isNotEmpty(updateSQLS);

        List<String> updateSQLList = conditionEO.getUpdateSQLList();
        if (EmptyValidate.isNull(updateSQLList)) {
            conditionEO.setUpdateSQLList(updateSQLS);
            return;
        }

        for (String updateSQL : updateSQLS) {
            if (!updateSQLList.contains(updateSQL)) {
                updateSQLList.add(updateSQL);
            }
        }
    }

    public static void addSQL(final Map<String, Object> conditionMap, final String updateSQL) {
        EmptyAssert.allNotNull(conditionMap);
        EmptyAssert.isNotBlank(updateSQL);

        @SuppressWarnings("unchecked")
        List<String> updateSQLList = MapConvertor.convertToFirstValue(
                conditionMap,
                SpecialKey.updateSQLList.name(),
                obj -> (List<String>) obj
        );
        if (EmptyValidate.isNull(updateSQLList)) {
            updateSQLList = new ArrayList<>();
            conditionMap.put(SpecialKey.updateSQLList.name(), updateSQLList);
        }
        if (!updateSQLList.contains(updateSQL)) {
            updateSQLList.add(updateSQL);
        }
    }

    public static void addSQLList(final Map<String, Object> conditionMap, final List<String> updateSQLS) {
        EmptyAssert.isNotNull(conditionMap);
        EmptyAssert.isNotEmpty(updateSQLS);

        @SuppressWarnings("unchecked")
        List<String> updateSQLList = MapConvertor.convertToFirstValue(
                conditionMap,
                SpecialKey.updateSQLList.name(),
                obj -> (List<String>) obj
        );
        if (EmptyValidate.isNull(updateSQLList)) {
            conditionMap.put(SpecialKey.updateSQLList.name(), updateSQLS);
            return;
        }

        for (String updateSQL : updateSQLS) {
            if (!updateSQLList.contains(updateSQL)) {
                updateSQLList.add(updateSQL);
            }
        }
    }
}
