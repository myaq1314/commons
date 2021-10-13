package org.czh.commons.utils.sql.base;

import org.czh.commons.enums.parent.IColumnEnum;
import org.czh.commons.utils.sql.constant.SqlConstant;
import org.czh.commons.utils.sql.eo.ColumnEO;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;

/**
 * @author : czh
 * description :
 * date : 2021/9/29
 * email 916419307@qq.com
 */
@SuppressWarnings({"unused", "DuplicatedCode"})
public final class ColumnUtil {

    /*
      -----------------------------拼装 列名 SQL(字典格式 列名入参)-------------------------------
     */

    /**
     * 拼装 列名 SQL(字典格式 列名入参)
     * 不使用 表名别名 调用
     *
     * @param columnEnum 字典格式字段，类似 name
     * @return 字段的完整SQL：`name`
     */
    public static String split(IColumnEnum columnEnum) {
        return splitAlias(columnEnum, SqlConstant.NO_TABLE_ALIAS);
    }

    /**
     * 拼装 列名 SQL(字典格式 列名入参)
     * 使用 默认表名别名调用，默认表名别名为 a
     *
     * @param columnEnum 字典格式字段，类似 name
     * @return 字段的完整SQL：a.`name`
     */
    public static String splitDefaultAlias(IColumnEnum columnEnum) {
        return splitAlias(columnEnum, SqlConstant.DEFAULT_TABLE_ALIAS);
    }

    /**
     * 拼装 列名 SQL(字典格式 列名入参)
     * 使用 指定表名别名调用
     *
     * @param columnEnum 字典格式字段，类似 name
     * @param alias      表名别名，类似 abc
     * @return 字段的完整SQL：abc.`name`
     */
    public static String splitAlias(IColumnEnum columnEnum, String alias) {
        EmptyAssert.isNotNull(columnEnum);
        return splitAlias(columnEnum.getColumn(), alias);
    }

    /*
      -----------------------------拼装 列名 SQL(实体格式 列名入参)-------------------------------
     */

    /**
     * 拼装 列名 SQL(实体格式 列名入参)
     * 不使用 表名别名 调用
     *
     * @param columnEO 实体格式字段，类似 name
     * @return 字段的完整SQL：`name`
     */
    public static String split(ColumnEO columnEO) {
        return splitAlias(columnEO, SqlConstant.NO_TABLE_ALIAS);
    }

    /**
     * 拼装 列名 SQL(实体格式 列名入参)
     * 使用 默认表名别名调用，默认表名别名为 a
     *
     * @param columnEO 实体格式字段，类似 name
     * @return 字段的完整SQL：a.`name`
     */
    public static String splitDefaultAlias(ColumnEO columnEO) {
        return splitAlias(columnEO, SqlConstant.DEFAULT_TABLE_ALIAS);
    }

    /**
     * 拼装 列名 SQL(字典格式 列名入参)
     * 使用 指定表名别名调用
     *
     * @param columnEO 实体格式字段，类似 name
     * @param alias    表名别名，类似 abc
     * @return 字段的完整SQL：abc.`name`
     */
    public static String splitAlias(ColumnEO columnEO, String alias) {
        EmptyAssert.isNotNull(columnEO);
        return splitAlias(columnEO.getColumn(), alias);
    }

    /*
      -----------------------------拼装 列名 SQL(字符串格式 列名入参)-------------------------------
     */

    /**
     * 拼装 列名 SQL(字符串格式 列名入参)
     * 不使用 表名别名 调用
     *
     * @param columnName 字符串格式字段，类似 name
     * @return 字段的完整SQL：`name`
     */
    public static String split(String columnName) {
        return splitAlias(columnName, SqlConstant.NO_TABLE_ALIAS);
    }

    /**
     * 拼装 列名 SQL(实体格式 列名入参)
     * 使用 默认表名别名调用，默认表名别名为 a
     *
     * @param columnName 字符串格式字段，类似 name
     * @return 字段的完整SQL：a.`name`
     */
    public static String splitDefaultAlias(String columnName) {
        return splitAlias(columnName, SqlConstant.DEFAULT_TABLE_ALIAS);
    }

    /**
     * 拼装 列名 SQL(字典格式 列名入参)
     * 使用 指定表名别名调用
     *
     * @param columnName 字符串格式字段，类似 name
     * @param alias      表名别名，类似 abc
     * @return 字段的完整SQL：abc.`name`
     */
    public static String splitAlias(String columnName, String alias) {
        EmptyAssert.isNotBlank(columnName);

        if (EmptyValidate.isBlank(alias)) {
            // 完整SQL：无别名：   `name`
            return " `" + columnName + "` ";
        } else {
            // 完整SQL：有别名：   a.`name`
            return " " + alias + ".`" + columnName + "` ";
        }
    }

    /*
      -----------------------------追加 列名 SQL(字典格式 列名入参)-------------------------------
     */

    /**
     * 追加 列名 SQL(字典格式 列名入参)
     * 不使用 表名别名 调用
     *
     * @param columnEnum 字典格式字段，类似 name
     * @param builder    可变的字符序列，追加结果：字段的完整SQL：`name`
     */
    public static void append(IColumnEnum columnEnum, StringBuilder builder) {
        appendAlias(columnEnum, SqlConstant.NO_TABLE_ALIAS, builder);
    }

    /**
     * 追加 列名 SQL(字典格式 列名入参)
     * 使用 默认表名别名调用，默认表名别名为 a
     *
     * @param columnEnum 字典格式字段，类似 name
     * @param builder    可变的字符序列，追加结果：字段的完整SQL：a.`name`
     */
    public static void appendDefaultAlias(IColumnEnum columnEnum, StringBuilder builder) {
        appendAlias(columnEnum, SqlConstant.DEFAULT_TABLE_ALIAS, builder);
    }

    /**
     * 追加 列名 SQL(字典格式 列名入参)
     * 使用 指定表名别名调用
     *
     * @param columnEnum 字典格式字段，类似 name
     * @param alias      表名别名，类似 abc
     * @param builder    可变的字符序列，追加结果：字段的完整SQL：abc.`name`
     */
    public static void appendAlias(IColumnEnum columnEnum, String alias, StringBuilder builder) {
        EmptyAssert.isNotNull(columnEnum);
        appendAlias(columnEnum.getColumn(), alias, builder);
    }

    /*
      -----------------------------追加 列名 SQL(实体格式 列名入参)-------------------------------
     */

    /**
     * 追加 列名 SQL(实体格式 列名入参)
     * 不使用 表名别名 调用
     *
     * @param columnEO 实体格式字段，类似 name
     * @param builder  可变的字符序列，追加结果：字段的完整SQL：`name`
     */
    public static void append(ColumnEO columnEO, StringBuilder builder) {
        appendAlias(columnEO, SqlConstant.NO_TABLE_ALIAS, builder);
    }

    /**
     * 追加 列名 SQL(实体格式 列名入参)
     * 使用 默认表名别名调用，默认表名别名为 a
     *
     * @param columnEO 实体格式字段，类似 name
     * @param builder  可变的字符序列，追加结果：字段的完整SQL：a.`name`
     */
    public static void appendDefaultAlias(ColumnEO columnEO, StringBuilder builder) {
        appendAlias(columnEO, SqlConstant.DEFAULT_TABLE_ALIAS, builder);
    }

    /**
     * 追加 列名 SQL(实体格式 列名入参)
     * 使用 指定表名别名调用
     *
     * @param columnEO 实体格式字段，类似 name
     * @param alias    表名别名，类似 abc
     * @param builder  可变的字符序列，追加结果：字段的完整SQL：abc.`name`
     */
    public static void appendAlias(ColumnEO columnEO, String alias, StringBuilder builder) {
        EmptyAssert.isNotNull(columnEO);
        appendAlias(columnEO.getColumn(), alias, builder);
    }

    /*
      -----------------------------追加 列名 SQL(字符串格式 列名入参)-------------------------------
     */

    /**
     * 追加 列名 SQL(字符串格式 列名入参)
     * 不使用 表名别名 调用
     *
     * @param columnName 字符串格式字段，类似 name
     * @param builder    可变的字符序列，追加结果：字段的完整SQL：`name`
     */
    public static void append(String columnName, StringBuilder builder) {
        appendAlias(columnName, SqlConstant.NO_TABLE_ALIAS, builder);
    }

    /**
     * 追加 列名 SQL(字符串格式 列名入参)
     * 使用 默认表名别名调用，默认表名别名为 a
     *
     * @param columnName 字符串格式字段，类似 name
     * @param builder    可变的字符序列，追加结果：字段的完整SQL：a.`name`
     */
    public static void appendDefaultAlias(String columnName, StringBuilder builder) {
        appendAlias(columnName, SqlConstant.DEFAULT_TABLE_ALIAS, builder);
    }

    /**
     * 追加 列名 SQL(字符串格式 列名入参)
     * 使用 指定表名别名调用
     *
     * @param columnName 字符串格式字段，类似 name
     * @param alias      表名别名，类似 abc
     * @param builder    可变的字符序列，追加结果：字段的完整SQL：abc.`name`
     */
    public static void appendAlias(String columnName, String alias, StringBuilder builder) {
        EmptyAssert.isNotBlank(columnName);
        EmptyAssert.isNotNull(builder);

        // 完整SQL：有别名：   a.`name`
        // 完整SQL：无别名：   `name`
        builder.append(" ");
        if (EmptyValidate.isNotBlank(alias)) {
            builder.append(alias).append(".");
        }
        builder.append("`").append(columnName).append("` ");
    }
}
