package org.czh.commons.utils.sql.base;

import org.czh.commons.entity.eo.BaseCommonEO;
import org.czh.commons.entity.eo.InsertEO;
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
public final class InsertUtil {

    /*
      -----------------------------拼装 插入SQL实体（装载到实体）-------------------------------
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
        addEO(conditionEO, new InsertEO(ColumnUtil.split(columnName), ValueUtil.splitAlias(columnValue)));
    }

    public static <EO extends BaseCommonEO> void addFunc(final EO conditionEO,
                                                         final IColumnEnum columnEnum,
                                                         final String func) {
        addEO(conditionEO, splitFunc(columnEnum, func));
    }

    public static <EO extends BaseCommonEO> void addFunc(final EO conditionEO,
                                                         final IColumnEnum columnEnum,
                                                         final String func,
                                                         final Object funcObj) {
        addEO(conditionEO, splitFunc(columnEnum, func, funcObj));
    }

    public static <EO extends BaseCommonEO> void addFunc(final EO conditionEO,
                                                         final IColumnEnum columnEnum,
                                                         final String func,
                                                         final String separator,
                                                         final Object... funcObjs) {
        addEO(conditionEO, splitFunc(columnEnum, func, separator, funcObjs));
    }

    public static <EO extends BaseCommonEO> void addFunc(final EO conditionEO,
                                                         final ColumnEO columnEO,
                                                         final String func) {
        addEO(conditionEO, splitFunc(columnEO, func));
    }

    public static <EO extends BaseCommonEO> void addFunc(final EO conditionEO,
                                                         final ColumnEO columnEO,
                                                         final String func,
                                                         final Object funcObj) {
        addEO(conditionEO, splitFunc(columnEO, func, funcObj));
    }

    public static <EO extends BaseCommonEO> void addFunc(final EO conditionEO,
                                                         final ColumnEO columnEO,
                                                         final String func,
                                                         final String separator,
                                                         final Object... funcObjs) {
        addEO(conditionEO, splitFunc(columnEO, func, separator, funcObjs));
    }

    public static <EO extends BaseCommonEO> void addFunc(final EO conditionEO,
                                                         final String columnName,
                                                         final String func) {
        addEO(conditionEO, splitFunc(columnName, func));
    }

    public static <EO extends BaseCommonEO> void addFunc(final EO conditionEO,
                                                         final String columnName,
                                                         final String func,
                                                         final Object funcObj) {
        addEO(conditionEO, splitFunc(columnName, func, funcObj));
    }

    public static <EO extends BaseCommonEO> void addFunc(final EO conditionEO,
                                                         final String columnName,
                                                         final String func,
                                                         final String separator,
                                                         final Object... funcObjs) {
        addEO(conditionEO, splitFunc(columnName, func, separator, funcObjs));
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
        addEO(conditionMap, new InsertEO(ColumnUtil.split(columnName), ValueUtil.splitAlias(columnValue)));
    }

    public static void addFunc(final Map<String, Object> conditionMap,
                               final IColumnEnum columnEnum,
                               final String func) {
        addEO(conditionMap, splitFunc(columnEnum, func));
    }

    public static void addFunc(final Map<String, Object> conditionMap,
                               final IColumnEnum columnEnum,
                               final String func,
                               final Object funcObj) {
        addEO(conditionMap, splitFunc(columnEnum, func, funcObj));
    }

    public static void addFunc(final Map<String, Object> conditionMap,
                               final IColumnEnum columnEnum,
                               final String func,
                               final String separator,
                               final Object... funcObjs) {
        addEO(conditionMap, splitFunc(columnEnum, func, separator, funcObjs));
    }

    public static void addFunc(final Map<String, Object> conditionMap,
                               final ColumnEO columnEO,
                               final String func) {
        addEO(conditionMap, splitFunc(columnEO, func));
    }

    public static void addFunc(final Map<String, Object> conditionMap,
                               final ColumnEO columnEO,
                               final String func,
                               final Object funcObj) {
        addEO(conditionMap, splitFunc(columnEO, func, funcObj));
    }

    public static void addFunc(final Map<String, Object> conditionMap,
                               final ColumnEO columnEO,
                               final String func,
                               final String separator,
                               final Object... funcObjs) {
        addEO(conditionMap, splitFunc(columnEO, func, separator, funcObjs));
    }

    public static void addFunc(final Map<String, Object> conditionMap,
                               final String columnName,
                               final String func) {
        addEO(conditionMap, splitFunc(columnName, func));
    }

    public static void addFunc(final Map<String, Object> conditionMap,
                               final String columnName,
                               final String func,
                               final Object funcObj) {
        addEO(conditionMap, splitFunc(columnName, func, funcObj));
    }

    public static void addFunc(final Map<String, Object> conditionMap,
                               final String columnName,
                               final String func,
                               final String separator,
                               final Object... funcObjs) {
        addEO(conditionMap, splitFunc(columnName, func, separator, funcObjs));
    }

    /*
      -----------------------------拼装 插入SQL实体（装载到可变的字符序列）-------------------------------
     */

    public static void append(final StringBuilder columnBuilder,
                              final StringBuilder valueBuilder,
                              final IColumnEnum columnEnum,
                              final Object columnValue) {
        append(columnBuilder, valueBuilder, false, columnEnum, columnValue);
    }

    public static void append(final StringBuilder columnBuilder,
                              final StringBuilder valueBuilder,
                              final ColumnEO columnEO,
                              final Object columnValue) {
        append(columnBuilder, valueBuilder, false, columnEO, columnValue);
    }

    public static void append(final StringBuilder columnBuilder,
                              final StringBuilder valueBuilder,
                              final String columnName,
                              final Object columnValue) {
        append(columnBuilder, valueBuilder, false, columnName, columnValue);
    }

    public static void append(final StringBuilder columnBuilder,
                              final StringBuilder valueBuilder,
                              final boolean isTop,
                              final IColumnEnum columnEnum,
                              final Object columnValue) {
        EmptyAssert.isNotNull(columnEnum);
        append(columnBuilder, valueBuilder, isTop, columnEnum.getColumn(), columnValue);
    }

    public static void append(final StringBuilder columnBuilder,
                              final StringBuilder valueBuilder,
                              final boolean isTop,
                              final ColumnEO columnEO,
                              final Object columnValue) {
        EmptyAssert.isNotNull(columnEO);
        append(columnBuilder, valueBuilder, isTop, columnEO.getColumn(), columnValue);
    }

    public static void append(final StringBuilder columnBuilder,
                              final StringBuilder valueBuilder,
                              final boolean isTop,
                              final String columnName,
                              final Object columnValue) {
        EmptyAssert.allNotNull(columnBuilder, valueBuilder);

        if (!isTop) {
            columnBuilder.append(" , ");
            valueBuilder.append(" , ");
        }
        columnBuilder.append(ColumnUtil.split(columnName));
        valueBuilder.append(ValueUtil.splitAlias(columnValue));
    }

    public static void appendFunc(final StringBuilder columnBuilder,
                                  final StringBuilder valueBuilder,
                                  final IColumnEnum columnEnum,
                                  final String func) {
        appendFunc(columnBuilder, valueBuilder, false, columnEnum, func);
    }

    public static void appendFunc(final StringBuilder columnBuilder,
                                  final StringBuilder valueBuilder,
                                  final IColumnEnum columnEnum,
                                  final String func,
                                  final Object funcObj) {
        appendFunc(columnBuilder, valueBuilder, false, columnEnum, func, funcObj);
    }

    public static void appendFunc(final StringBuilder columnBuilder,
                                  final StringBuilder valueBuilder,
                                  final IColumnEnum columnEnum,
                                  final String func,
                                  final String separator,
                                  final Object... funcObjs) {
        appendFunc(columnBuilder, valueBuilder, false, columnEnum, func, separator, funcObjs);
    }

    public static void appendFunc(final StringBuilder columnBuilder,
                                  final StringBuilder valueBuilder,
                                  final ColumnEO columnEO,
                                  final String func) {
        appendFunc(columnBuilder, valueBuilder, false, columnEO, func);
    }

    public static void appendFunc(final StringBuilder columnBuilder,
                                  final StringBuilder valueBuilder,
                                  final ColumnEO columnEO,
                                  final String func,
                                  final Object funcObj) {
        appendFunc(columnBuilder, valueBuilder, false, columnEO, func, funcObj);
    }

    public static void appendFunc(final StringBuilder columnBuilder,
                                  final StringBuilder valueBuilder,
                                  final ColumnEO columnEO,
                                  final String func,
                                  final String separator,
                                  final Object... funcObjs) {
        appendFunc(columnBuilder, valueBuilder, false, columnEO, func, separator, funcObjs);
    }

    public static void appendFunc(final StringBuilder columnBuilder,
                                  final StringBuilder valueBuilder,
                                  final String columnName,
                                  final String func) {
        appendFunc(columnBuilder, valueBuilder, false, columnName, func);
    }

    public static void appendFunc(final StringBuilder columnBuilder,
                                  final StringBuilder valueBuilder,
                                  final String columnName,
                                  final String func,
                                  final Object funcObj) {
        appendFunc(columnBuilder, valueBuilder, false, columnName, func, funcObj);
    }

    public static void appendFunc(final StringBuilder columnBuilder,
                                  final StringBuilder valueBuilder,
                                  final String columnName,
                                  final String func,
                                  final String separator,
                                  final Object... funcObjs) {
        appendFunc(columnBuilder, valueBuilder, false, columnName, func, separator, funcObjs);
    }

    public static void appendFunc(final StringBuilder columnBuilder,
                                  final StringBuilder valueBuilder,
                                  final boolean isTop,
                                  final IColumnEnum columnEnum,
                                  final String func) {
        appendFunc(columnBuilder, valueBuilder, isTop, columnEnum, func, "", (Object[]) null);
    }

    public static void appendFunc(final StringBuilder columnBuilder,
                                  final StringBuilder valueBuilder,
                                  final boolean isTop,
                                  final IColumnEnum columnEnum,
                                  final String func,
                                  final Object funcObj) {
        appendFunc(columnBuilder, valueBuilder, isTop, columnEnum, func, "", funcObj);
    }

    public static void appendFunc(final StringBuilder columnBuilder,
                                  final StringBuilder valueBuilder,
                                  final boolean isTop,
                                  final IColumnEnum columnEnum,
                                  final String func,
                                  final String separator,
                                  final Object... funcObjs) {
        EmptyAssert.isNotNull(columnEnum);
        appendFunc(columnBuilder, valueBuilder, isTop, columnEnum.getColumn(), func, separator, funcObjs);
    }

    public static void appendFunc(final StringBuilder columnBuilder,
                                  final StringBuilder valueBuilder,
                                  final boolean isTop,
                                  final ColumnEO columnEO,
                                  final String func) {
        appendFunc(columnBuilder, valueBuilder, isTop, columnEO, func, "", (Object[]) null);
    }

    public static void appendFunc(final StringBuilder columnBuilder,
                                  final StringBuilder valueBuilder,
                                  final boolean isTop,
                                  final ColumnEO columnEO,
                                  final String func,
                                  final Object funcObj) {
        appendFunc(columnBuilder, valueBuilder, isTop, columnEO, func, "", funcObj);
    }

    public static void appendFunc(final StringBuilder columnBuilder,
                                  final StringBuilder valueBuilder,
                                  final boolean isTop,
                                  final ColumnEO columnEO,
                                  final String func,
                                  final String separator,
                                  final Object... funcObjs) {
        EmptyAssert.isNotNull(columnEO);
        appendFunc(columnBuilder, valueBuilder, isTop, columnEO.getColumn(), func, separator, funcObjs);
    }

    public static void appendFunc(final StringBuilder columnBuilder,
                                  final StringBuilder valueBuilder,
                                  final boolean isTop,
                                  final String columnName,
                                  final String func) {
        appendFunc(columnBuilder, valueBuilder, isTop, columnName, func, "", (Object[]) null);
    }

    public static void appendFunc(final StringBuilder columnBuilder,
                                  final StringBuilder valueBuilder,
                                  final boolean isTop,
                                  final String columnName,
                                  final String func,
                                  final Object funcObj) {
        appendFunc(columnBuilder, valueBuilder, isTop, columnName, func, "", funcObj);
    }

    public static void appendFunc(final StringBuilder columnBuilder,
                                  final StringBuilder valueBuilder,
                                  final boolean isTop,
                                  final String columnName,
                                  final String func,
                                  final String separator,
                                  final Object... funcObjs) {
        EmptyAssert.allNotNull(columnBuilder, valueBuilder);
        if (!isTop) {
            columnBuilder.append(" , ");
            valueBuilder.append(" , ");
        }
        columnBuilder.append(ColumnUtil.split(columnName));
        valueBuilder.append(FuncUtil.splitMore(func, separator, false, funcObjs));
    }

    /*
      -----------------------------拼装 插入SQL实体-------------------------------
     */

    public static InsertEO split(final IColumnEnum columnEnum, final Object columnValue) {
        EmptyAssert.isNotNull(columnEnum);
        return split(columnEnum.getColumn(), columnValue);
    }

    public static InsertEO split(final ColumnEO columnEO, final Object columnValue) {
        EmptyAssert.isNotNull(columnEO);
        return split(columnEO.getColumn(), columnValue);
    }

    public static InsertEO split(final String columnName, final Object columnValue) {
        return new InsertEO(ColumnUtil.split(columnName), ValueUtil.splitAlias(columnValue));
    }

    public static InsertEO splitFunc(final IColumnEnum columnEnum, final String func) {
        return splitFunc(columnEnum, func, "", (Object[]) null);
    }

    public static InsertEO splitFunc(final IColumnEnum columnEnum, final String func, final Object funcObj) {
        return splitFunc(columnEnum, func, "", funcObj);
    }

    public static InsertEO splitFunc(final IColumnEnum columnEnum,
                                     final String func,
                                     final String separator,
                                     final Object... funcObjs) {
        EmptyAssert.isNotNull(columnEnum);
        return splitFunc(columnEnum.getColumn(), func, separator, funcObjs);
    }

    public static InsertEO splitFunc(final ColumnEO columnEO, final String func) {
        return splitFunc(columnEO, func, "", (Object[]) null);
    }

    public static InsertEO splitFunc(final ColumnEO columnEO, final String func, final Object funcObj) {
        return splitFunc(columnEO, func, "", funcObj);
    }

    public static InsertEO splitFunc(final ColumnEO columnEO,
                                     final String func,
                                     final String separator,
                                     final Object... funcObjs) {
        EmptyAssert.isNotNull(columnEO);
        return splitFunc(columnEO.getColumn(), func, separator, funcObjs);
    }

    public static InsertEO splitFunc(final String columnName, final String func) {
        return splitFunc(columnName, func, "", (Object[]) null);
    }

    public static InsertEO splitFunc(final String columnName, final String func, final Object funcObj) {
        return splitFunc(columnName, func, "", funcObj);
    }

    public static InsertEO splitFunc(final String columnName,
                                     final String func,
                                     final String separator,
                                     final Object... funcObjs) {
        return new InsertEO(ColumnUtil.split(columnName), FuncUtil.splitMore(func, separator, false, funcObjs));
    }

    /*
      -----------------------------装载 插入SQL实体-------------------------------
     */

    public static <EO extends BaseCommonEO> void addEO(final EO conditionEO, final InsertEO insertEO) {
        EmptyAssert.allNotNull(conditionEO, insertEO);

        List<InsertEO> insertSQLEOLList = conditionEO.getInsertSQLEOLList();
        if (EmptyValidate.isNull(insertSQLEOLList)) {
            insertSQLEOLList = new ArrayList<>();
            conditionEO.setInsertSQLEOLList(insertSQLEOLList);
        }
        if (!insertSQLEOLList.contains(insertEO)) {
            insertSQLEOLList.add(insertEO);
        }
    }

    public static <EO extends BaseCommonEO> void addEOList(final EO conditionEO, final List<InsertEO> insertEOS) {
        EmptyAssert.isNotNull(conditionEO);
        EmptyAssert.isNotEmpty(insertEOS);

        List<InsertEO> insertSQLEOLList = conditionEO.getInsertSQLEOLList();
        if (EmptyValidate.isNull(insertSQLEOLList)) {
            conditionEO.setInsertSQLEOLList(insertEOS);
            return;
        }

        for (InsertEO insertEO : insertEOS) {
            if (!insertSQLEOLList.contains(insertEO)) {
                insertSQLEOLList.add(insertEO);
            }
        }
    }

    public static void addEO(final Map<String, Object> conditionMap, final InsertEO insertEO) {
        EmptyAssert.allNotNull(conditionMap, insertEO);

        @SuppressWarnings("unchecked")
        List<InsertEO> insertSQLEOLList = MapConvertor.convertToFirstValue(
                conditionMap,
                SpecialKey.insertSQLEOLList.name(),
                obj -> (List<InsertEO>) obj
        );
        if (EmptyValidate.isNull(insertSQLEOLList)) {
            insertSQLEOLList = new ArrayList<>();
            conditionMap.put(SpecialKey.insertSQLEOLList.name(), insertSQLEOLList);
        }
        if (!insertSQLEOLList.contains(insertEO)) {
            insertSQLEOLList.add(insertEO);
        }
    }

    public static void addEOList(final Map<String, Object> conditionMap, final List<InsertEO> insertEOS) {
        EmptyAssert.isNotNull(conditionMap);
        EmptyAssert.isNotEmpty(insertEOS);

        @SuppressWarnings("unchecked")
        List<InsertEO> insertSQLEOLList = MapConvertor.convertToFirstValue(
                conditionMap,
                SpecialKey.insertSQLEOLList.name(),
                obj -> (List<InsertEO>) obj
        );
        if (EmptyValidate.isNull(insertSQLEOLList)) {
            conditionMap.put(SpecialKey.insertSQLEOLList.name(), insertEOS);
            return;
        }

        for (InsertEO insertEO : insertEOS) {
            if (!insertSQLEOLList.contains(insertEO)) {
                insertSQLEOLList.add(insertEO);
            }
        }
    }
}
