package org.czh.commons.utils.sql.base;

import org.czh.commons.entity.eo.BaseViewEO;
import org.czh.commons.enums.sql.SpecialKey;
import org.czh.commons.utils.sql.annotations.Table;
import org.czh.commons.utils.sql.constant.SqlConstant;
import org.czh.commons_core.asserts.EmptyAssert;
import org.czh.commons_core.asserts.FlagAssert;
import org.czh.commons_core.convertor.MapConvertor;
import org.czh.commons_core.validate.EmptyValidate;

import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021/9/29
 * email 916419307@qq.com
 */
@SuppressWarnings({"unused", "DuplicatedCode"})
public final class TableUtil {

    /*
      -----------------------------拼装 表名 SQL(数据实体格式 表名入参)-------------------------------
     */

    /**
     * 拼装 表名 SQL(数据实体格式 表名入参)
     * 不设置 表别名
     *
     * @param conditionEO 数据实体格式，
     *                    tableNameSQL属性，设置动态表名；优先级较高；类似 tb_example_2021
     *                    <code>@Table</code>标记在类上的注解，设置原始表名；优先级低；类似 tb_example
     * @return 表名的完整SQL：`tb_example_2021`
     * @see org.czh.commons.entity.eo.BaseViewEO.tableNameSQL
     * @see org.czh.commons.utils.sql.annotations.Table
     */
    public static <EO extends BaseViewEO> String split(EO conditionEO) {
        return splitAlias(conditionEO, SqlConstant.NO_TABLE_ALIAS);
    }

    /**
     * 拼装 表名 SQL(数据实体格式 表名入参)
     * 设置表别名为默认表别名 a
     *
     * @param conditionEO 数据实体格式，
     *                    tableNameSQL属性，设置动态表名；优先级较高；类似 tb_example_2021
     *                    <code>@Table</code>标记在类上的注解，设置原始表名；优先级低；类似 tb_example
     * @return 表名的完整SQL：`tb_example_2021` AS a
     * @see org.czh.commons.entity.eo.BaseViewEO.tableNameSQL
     * @see org.czh.commons.utils.sql.annotations.Table
     */
    public static <EO extends BaseViewEO> String splitDefaultAlias(EO conditionEO) {
        return splitAlias(conditionEO, SqlConstant.DEFAULT_TABLE_ALIAS);
    }

    /**
     * 拼装 表名 SQL(数据实体格式 表名入参)
     * 设置表别名为指定表别名
     *
     * @param conditionEO 数据实体格式，
     *                    tableNameSQL属性，设置动态表名；优先级较高；类似 tb_example_2021
     *                    <code>@Table</code>标记在类上的注解，设置原始表名；优先级低；类似 tb_example
     * @param tableAlias  指定表别名，类似 abc
     * @return 表名的完整SQL：`tb_example_2021` AS abc
     * @see org.czh.commons.entity.eo.BaseViewEO.tableNameSQL
     * @see org.czh.commons.utils.sql.annotations.Table
     */
    public static <EO extends BaseViewEO> String splitAlias(EO conditionEO, String tableAlias) {
        EmptyAssert.isNotNull(conditionEO);

        String tableName;
        if (EmptyValidate.isBlank(tableName = conditionEO.getTableNameSQL())) {
            Table table = conditionEO.getClass().getAnnotation(Table.class);
            EmptyAssert.isNotNull(table, "实体中需要添加Table注解");
            tableName = table.name();
        }
        return splitAlias(tableName, tableAlias);
    }

    /*
      -----------------------------拼装 表名 SQL(Map格式 表名入参)-------------------------------
     */

    /**
     * 拼装 表名 SQL(Map格式 表名入参)
     * 不设置 表别名
     *
     * @param conditionMap Map格式，tableNameSQL Key，设置表名；类似 tb_example
     * @return 表名的完整SQL：`tb_example`
     * @see org.czh.commons.enums.sql.SpecialKey.tableNameSQL 特殊Key值
     */
    public static String split(Map<String, Object> conditionMap) {
        return splitAlias(conditionMap, SqlConstant.NO_TABLE_ALIAS);
    }

    /**
     * 拼装 表名 SQL(Map格式 表名入参)
     * 设置表别名为默认表别名 a
     *
     * @param conditionMap Map格式，tableNameSQL Key，设置表名；类似 tb_example
     * @return 表名的完整SQL：`tb_example` AS a
     * @see org.czh.commons.enums.sql.SpecialKey.tableNameSQL 特殊Key值
     */
    public static String splitDefaultAlias(Map<String, Object> conditionMap) {
        return splitAlias(conditionMap, SqlConstant.DEFAULT_TABLE_ALIAS);
    }

    /**
     * 拼装 表名 SQL(Map格式 表名入参)
     * 设置表别名为指定表别名
     *
     * @param conditionMap Map格式，tableNameSQL Key，设置表名；类似 tb_example
     * @param tableAlias   指定表别名，类似 abc
     * @return 表名的完整SQL：`tb_example` AS abc
     * @see org.czh.commons.enums.sql.SpecialKey.tableNameSQL 特殊Key值
     */
    public static String splitAlias(Map<String, Object> conditionMap, String tableAlias) {
        EmptyAssert.isNotEmpty(conditionMap);
        FlagAssert.isTrue(
                conditionMap.containsKey(SpecialKey.tableNameSQL.name()),
                "要拼接表名SQL，conditionMap键值对 必须包含 tableNameSQL Key"
        );

        String tableName = MapConvertor
                .convertToFirstValue(conditionMap, SpecialKey.tableNameSQL.name(), o -> (String) o);
        return splitAlias(tableName, tableAlias);
    }

    /*
      -----------------------------拼装 表名 SQL(字符串格式 表名入参)-------------------------------
     */

    /**
     * 拼装 表名 SQL(字符串格式 表名入参)
     * 不设置 表别名
     *
     * @param tableName 字符串格式表名，类似 tb_example
     * @return 表名的完整SQL：`tb_example`
     */
    public static String split(String tableName) {
        return splitAlias(tableName, SqlConstant.NO_TABLE_ALIAS);
    }

    /**
     * 拼装 表名 SQL(字符串格式 表名入参)
     * 设置表别名为默认表别名 a
     *
     * @param tableName 字符串格式表名，类似 tb_example
     * @return 表名的完整SQL：`tb_example` AS a
     */
    public static String splitDefaultAlias(String tableName) {
        return splitAlias(tableName, SqlConstant.DEFAULT_TABLE_ALIAS);
    }

    /**
     * 拼装 表名 SQL(字符串格式 表名入参)
     * 设置表别名为指定表别名
     *
     * @param tableName  字符串格式表名，类似 tb_example
     * @param tableAlias 指定表别名，类似 abc
     * @return 表名的完整SQL：`tb_example` AS abc
     */
    public static String splitAlias(String tableName, String tableAlias) {
        EmptyAssert.isNotBlank(tableName);

        if (EmptyValidate.isNotBlank(tableAlias)) {
            // 完整SQL：有别名：   `tb_example_mysql_info` AS a
            return " `" + tableName + "` AS `" + tableAlias + "` ";
        } else {
            // 完整SQL：无别名：   `tb_example_mysql_info`
            return " `" + tableName + "` ";
        }
    }

    /*
      -----------------------------追加 表名 SQL(数据实体格式 表名入参)-------------------------------
     */

    /**
     * 追加 表名 SQL(数据实体格式 表名入参)
     * 不设置 表别名
     *
     * @param conditionEO 数据实体格式，
     *                    tableNameSQL属性，设置动态表名；优先级较高；类似 tb_example_2021
     *                    <code>@Table</code>标记在类上的注解，设置原始表名；优先级低；类似 tb_example
     * @param builder     可变的字符序列，追加结果：表名的完整SQL：`tb_example_2021`
     * @see org.czh.commons.entity.eo.BaseViewEO.tableNameSQL
     * @see org.czh.commons.utils.sql.annotations.Table
     */
    public static <EO extends BaseViewEO> void append(EO conditionEO, StringBuilder builder) {
        appendAlias(conditionEO, SqlConstant.NO_TABLE_ALIAS, builder);
    }

    /**
     * 追加 表名 SQL(数据实体格式 表名入参)
     * 设置表别名为默认表别名 a
     *
     * @param conditionEO 数据实体格式，
     *                    tableNameSQL属性，设置动态表名；优先级较高；类似 tb_example_2021
     *                    <code>@Table</code>标记在类上的注解，设置原始表名；优先级低；类似 tb_example
     * @param builder     可变的字符序列，追加结果：表名的完整SQL：`tb_example_2021` AS a
     * @see org.czh.commons.entity.eo.BaseViewEO.tableNameSQL
     * @see org.czh.commons.utils.sql.annotations.Table
     */
    public static <EO extends BaseViewEO> void appendDefaultAlias(EO conditionEO, StringBuilder builder) {
        appendAlias(conditionEO, SqlConstant.DEFAULT_TABLE_ALIAS, builder);
    }

    /**
     * 追加 表名 SQL(数据实体格式 表名入参)
     * 设置表别名为指定表别名
     *
     * @param conditionEO 数据实体格式，
     *                    tableNameSQL属性，设置动态表名；优先级较高；类似 tb_example_2021
     *                    <code>@Table</code>标记在类上的注解，设置原始表名；优先级低；类似 tb_example
     * @param tableAlias  指定表别名，类似 abc
     * @param builder     可变的字符序列，追加结果：表名的完整SQL：`tb_example_2021` AS abc
     * @see org.czh.commons.entity.eo.BaseViewEO.tableNameSQL
     * @see org.czh.commons.utils.sql.annotations.Table
     */
    public static <EO extends BaseViewEO> void appendAlias(EO conditionEO, String tableAlias, StringBuilder builder) {
        EmptyAssert.isNotNull(conditionEO);

        String tableName;
        if (EmptyValidate.isBlank(tableName = conditionEO.getTableNameSQL())) {
            Table table = conditionEO.getClass().getAnnotation(Table.class);
            EmptyAssert.isNotNull(table, "实体中需要添加Table注解");
            tableName = table.name();
        }
        appendAlias(tableName, tableAlias, builder);
    }

    /*
      -----------------------------追加 表名 SQL(Map格式 表名入参)-------------------------------
     */

    /**
     * 追加 表名 SQL(Map格式 表名入参)
     * 不设置 表别名
     *
     * @param conditionMap Map格式，tableNameSQL Key，设置表名；类似 tb_example
     * @param builder      可变的字符序列，追加结果：表名的完整SQL：`tb_example`
     * @see org.czh.commons.enums.sql.SpecialKey.tableNameSQL 特殊Key值
     */
    public static void append(Map<String, Object> conditionMap, StringBuilder builder) {
        appendAlias(conditionMap, SqlConstant.NO_TABLE_ALIAS, builder);
    }

    /**
     * 追加 表名 SQL(Map格式 表名入参)
     * 设置表别名为默认表别名 a
     *
     * @param conditionMap Map格式，tableNameSQL Key，设置表名；类似 tb_example
     * @param builder      可变的字符序列，追加结果：表名的完整SQL：`tb_example` AS a
     * @see org.czh.commons.enums.sql.SpecialKey.tableNameSQL 特殊Key值
     */
    public static void appendDefaultAlias(Map<String, Object> conditionMap, StringBuilder builder) {
        appendAlias(conditionMap, SqlConstant.DEFAULT_TABLE_ALIAS, builder);
    }

    /**
     * 追加 表名 SQL(Map格式 表名入参)
     * 设置表别名为指定表别名
     *
     * @param conditionMap Map格式，tableNameSQL Key，设置表名；类似 tb_example
     * @param tableAlias   指定表别名，类似 abc
     * @param builder      可变的字符序列，追加结果：表名的完整SQL：`tb_example` AS abc
     * @see org.czh.commons.enums.sql.SpecialKey.tableNameSQL 特殊Key值
     */
    public static void appendAlias(Map<String, Object> conditionMap, String tableAlias, StringBuilder builder) {
        EmptyAssert.isNotEmpty(conditionMap);
        FlagAssert.isTrue(
                conditionMap.containsKey(SpecialKey.tableNameSQL.name()),
                "要拼接表名SQL，conditionMap键值对必须包含tableNameSQL Key"
        );

        String tableName = MapConvertor
                .convertToFirstValue(conditionMap, SpecialKey.tableNameSQL.name(), o -> (String) o);
        appendAlias(tableName, tableAlias, builder);
    }

    /*
      -----------------------------追加 表名 SQL(字符串格式 表名入参)-------------------------------
     */

    /**
     * 追加 表名 SQL(字符串格式 表名入参)
     * 不设置 表别名
     *
     * @param tableName 字符串格式表名，类似 tb_example
     * @param builder   可变的字符序列，追加结果：表名的完整SQL：`tb_example`
     */
    public static void append(String tableName, StringBuilder builder) {
        appendAlias(tableName, SqlConstant.NO_TABLE_ALIAS, builder);
    }

    /**
     * 拼装 表名 SQL(字符串格式 表名入参)
     * 设置表别名为默认表别名 a
     *
     * @param tableName 字符串格式表名，类似 tb_example
     * @param builder   可变的字符序列，追加结果：表名的完整SQL：`tb_example` AS a
     */
    public static void appendDefaultAlias(String tableName, StringBuilder builder) {
        appendAlias(tableName, SqlConstant.DEFAULT_TABLE_ALIAS, builder);
    }

    /**
     * 拼装 表名 SQL(字符串格式 表名入参)
     * 设置表别名为指定表别名
     *
     * @param tableName  字符串格式表名，类似 tb_example
     * @param tableAlias 指定表别名，类似 abc
     * @param builder    可变的字符序列，追加结果：表名的完整SQL：`tb_example` AS abc
     */
    public static void appendAlias(String tableName, String tableAlias, StringBuilder builder) {
        EmptyAssert.isNotBlank(tableName);
        EmptyAssert.isNotNull(builder);

        // 完整SQL：有别名：   `tb_example_mysql_info` AS a
        // 完整SQL：无别名：   `tb_example_mysql_info`
        builder.append(" `").append(tableName).append("` ");
        if (EmptyValidate.isNotBlank(tableAlias)) {
            builder.append("AS `").append(tableAlias).append("` ");
        }
    }
}
