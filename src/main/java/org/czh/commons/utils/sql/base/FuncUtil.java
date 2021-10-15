package org.czh.commons.utils.sql.base;

import org.czh.commons.utils.sql.constant.SqlConstant;
import org.czh.commons_core.asserts.EmptyAssert;
import org.czh.commons_core.validate.EmptyValidate;

/**
 * @author : czh
 * description :
 * date : 2021/9/30
 * email 916419307@qq.com
 */
@SuppressWarnings({"unused", "DuplicatedCode"})
public final class FuncUtil {

    /*
      --------------拼装 函数，格式 function()/function(parameter)/function(parameters)--------------
     */

    /**
     * 拼装 空参函数，格式 function()
     *
     * @param func 函数名，类似 now 获取当前时间；类似 pi 获取圆周率
     * @return 拼装函数完整SQL：now()、pi()
     */
    public static String split(String func) {
        return splitMore(func, SqlConstant.NO_SELECT_ALIAS, SqlConstant.NO_TABLE_ALIAS_FLAG);
    }

    /**
     * 拼装 一参函数，格式 function(parameter)
     *
     * @param func              函数名，类似 min 获取最小值；类似 count 获取数量
     * @param whetherTableAlias 是否表别名调用列
     *                          当obj类型为字段类型（IColumnEnum/ColumnEO）时，是否使用表别名调用
     *                          true a.`age`
     *                          false `age`
     * @param funcObj           参数，类似字段类型（IColumnEnum/ColumnEO），数值、字符、布尔、日期等
     * @return 拼装函数完整SQL：min(a.`age`)、count(1)
     */
    public static String splitOne(String func, boolean whetherTableAlias, Object funcObj) {
        EmptyAssert.isNotNull(funcObj);
        return splitMore(func, SqlConstant.NO_SELECT_ALIAS, whetherTableAlias, funcObj);
    }

    /**
     * 拼装 多参函数，格式 function(parameters)
     *
     * @param func              函数名，类似 group_concat 分组基础上拼装字符串；substr 截取字符串；可为 null
     * @param separator         分隔符，一般为 逗号, 涉及到计算时，可以为 加减乘除符号
     * @param whetherTableAlias 是否表别名调用列
     *                          当obj类型为字段类型（IColumnEnum/ColumnEO）时，是否使用表别名调用
     *                          true a.`name`
     *                          false `name`
     * @param funcObjs          多个参数，自动过滤null值参数，参数类型为类似字段类型（IColumnEnum/ColumnEO），数值、字符、布尔、日期等
     * @return 拼装函数完整SQL：group_concat(a.`name`, '-')、substr(a.`name`, 0, 1)
     */
    public static String splitMore(String func, String separator, boolean whetherTableAlias, Object... funcObjs) {
        return splitSelectAliasMore(func, SqlConstant.NO_SELECT_ALIAS, separator, whetherTableAlias, funcObjs);
    }


    /*
      --------------拼装 查询函数，格式 function()/function(parameter)/function(parameters) AS selectAlias--------------
     */

    /**
     * 拼装 查询 空参函数，格式 function() AS selectAlias
     *
     * @param func        函数名，类似 now 获取当前时间；类似 pi 获取圆周率
     * @param selectAlias 指定查询别名，类似 date；pi
     * @return 拼装函数完整SQL：now() AS `date`、pi() AS `pi`
     */
    public static String splitSelectAlias(String func, String selectAlias) {
        return splitSelectAliasMore(func, selectAlias, SqlConstant.NO_SEPARATOR, SqlConstant.NO_TABLE_ALIAS_FLAG);
    }

    /**
     * 拼装 查询 一参函数，格式 function(parameter) AS selectAlias
     *
     * @param func              函数名，类似 min 获取最小值；类似 count 获取数量
     * @param selectAlias       指定查询别名，类似 age；count
     * @param whetherTableAlias 是否表别名调用列
     *                          当obj类型为字段类型（IColumnEnum/ColumnEO）时，是否使用表别名调用
     *                          true a.`name`
     *                          false `name`
     * @param funcObj           参数，类似字段类型（IColumnEnum/ColumnEO），数值、字符、布尔、日期等
     * @return 拼装函数完整SQL：min(a.`age`) AS `age`、count(1) AS `count`
     */
    public static String splitSelectAliasOne(String func,
                                             String selectAlias,
                                             boolean whetherTableAlias,
                                             Object funcObj) {
        EmptyAssert.isNotNull(funcObj);
        return splitSelectAliasMore(func, selectAlias, SqlConstant.NO_SEPARATOR, whetherTableAlias, funcObj);
    }

    /**
     * 拼装 查询 多参函数，格式 function(parameters) AS selectAlias
     *
     * @param func              函数名，类似 group_concat 分组基础上拼装字符串；substr 截取字符串；可为 null
     * @param selectAlias       指定查询别名，类似 name；name；birthday_year
     * @param separator         分隔符，一般为 逗号, 涉及到计算时，可以为 加减乘除符号
     * @param whetherTableAlias 是否表别名调用列
     *                          当obj类型为字段类型（IColumnEnum/ColumnEO）时，是否使用表别名调用
     *                          true a.`name`
     *                          false `name`
     * @param funcObjs          多个参数，自动过滤null值参数，参数类型为类似字段类型（IColumnEnum/ColumnEO），数值、字符、布尔、日期等
     * @return 拼装函数完整SQL：concat(a.`name`, '-') AS `name`、substr(a.`name`, 0, 1) AS `name`、a.`year` - a.`age` AS `birthday_year`
     */
    public static String splitSelectAliasMore(String func,
                                              String selectAlias,
                                              String separator,
                                              boolean whetherTableAlias,
                                              Object... funcObjs) {
        separator = EmptyValidate.isNull(separator) ? SqlConstant.EMPTY_STRING : separator;

        // 函数名
        StringBuilder builder = new StringBuilder(SqlConstant.SPACE_STRING);
        if (EmptyValidate.isNotNull(func)) {
            builder.append(func).append(SqlConstant.LEFT_PARENTHESES);
        }

        // 函数体
        if (EmptyValidate.isNotEmpty(funcObjs)) {
            for (int i = 0; i < funcObjs.length; i++) {
                Object funcObj = funcObjs[i];
                if (EmptyValidate.isNull(funcObj)) {
                    continue;
                }

                if (whetherTableAlias) {
                    builder.append(ValueUtil.splitAlias(funcObj));
                } else {
                    builder.append(ValueUtil.splitNoAlias(funcObj));
                }
                if (i != funcObjs.length - 1) {
                    builder.append(separator);
                }
            }
        }

        // 函数尾
        if (EmptyValidate.isNotNull(func)) {
            builder.append(SqlConstant.RIGHT_PARENTHESES);
        }
        builder.append(SqlConstant.SPACE_STRING);

        // 函数别名
        if (EmptyValidate.isNotBlank(selectAlias)) {
            builder.append("AS `").append(selectAlias).append("` ");
        }
        return builder.toString();
    }
}
