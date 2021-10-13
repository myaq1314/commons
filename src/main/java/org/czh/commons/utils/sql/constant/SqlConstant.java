package org.czh.commons.utils.sql.constant;

import org.apache.commons.lang3.StringUtils;

/**
 * @author : czh
 * description :
 * date : 2021/10/13
 * email : 916419307@qq.com
 */
@SuppressWarnings("unused")
public final class SqlConstant {

    public static final String EMPTY_STRING = StringUtils.EMPTY;

    /**
     * 空字符串
     */
    public static final String SPACE_STRING = StringUtils.SPACE;
    /**
     * 没有表别名
     */
    public static final String NO_TABLE_ALIAS = StringUtils.EMPTY;
    /**
     * 默认表别名
     */
    public static final String DEFAULT_TABLE_ALIAS = "a";

    /**
     * 没有查询结果别名
     */
    public static final String NO_SELECT_ALIAS = StringUtils.EMPTY;

    /**
     * 不使用表别名调用列
     */
    public static final boolean NO_TABLE_ALIAS_FLAG = false;

    /**
     * 使用表别名调用列
     */
    public static final boolean HAS_TABLE_ALIAS_FLAG = true;

    /**
     * 没有分隔符
     */
    public static final String NO_SEPARATOR = StringUtils.EMPTY;

    /**
     * 默认分隔符
     */
    public static final String DEFAULT_SEPARATOR = ",";

    /**
     * 左括号
     */
    public static final String LEFT_PARENTHESES = "(";

    /**
     * 右括号
     */
    public static final String RIGHT_PARENTHESES = ")";

}
