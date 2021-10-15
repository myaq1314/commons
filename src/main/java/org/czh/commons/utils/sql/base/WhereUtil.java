package org.czh.commons.utils.sql.base;

import org.czh.commons.entity.eo.BaseViewEO;
import org.czh.commons.enums.parent.IColumnEnum;
import org.czh.commons.enums.sql.CircleDict;
import org.czh.commons.enums.sql.LikeDict;
import org.czh.commons.enums.sql.NullDict;
import org.czh.commons.enums.sql.ScopeDict;
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
public final class WhereUtil {

    /*
      -----------------------------拼装 范围条件 SQL（装载到实体）-------------------------------
     */

    /**
     * SQL示例：a.`age` > 13
     */
    public static <EO extends BaseViewEO> void addScope(final EO conditionEO,
                                                        final IColumnEnum columnEnum,
                                                        final ScopeDict scopeDict,
                                                        final Object compareObj) {
        addSQL(conditionEO, splitScope(columnEnum, scopeDict, compareObj));
    }

    /**
     * SQL示例：a.`age` > 13
     */
    public static <EO extends BaseViewEO> void addScope(final EO conditionEO,
                                                        final ColumnEO columnEO,
                                                        final ScopeDict scopeDict,
                                                        final Object compareObj) {
        addSQL(conditionEO, splitScope(columnEO, scopeDict, compareObj));
    }

    /**
     * SQL示例：a.`age` > 13
     */
    public static <EO extends BaseViewEO> void addScope(final EO conditionEO,
                                                        final String columnName,
                                                        final ScopeDict scopeDict,
                                                        final Object compareObj) {
        addSQL(conditionEO, splitScope(columnName, scopeDict, compareObj));
    }

    /**
     * SQL示例：now() > '2021-10-08 11:03:54'
     */
    public static <EO extends BaseViewEO> void addScopeFunc(final EO conditionEO,
                                                            final String func,
                                                            final ScopeDict scopeDict,
                                                            final Object compareObj) {
        addSQL(conditionEO, splitScopeFunc(func, scopeDict, compareObj));
    }

    /**
     * SQL示例：year(a.`birthday`) > 2018
     */
    public static <EO extends BaseViewEO> void addScopeFunc(final EO conditionEO,
                                                            final String func,
                                                            final Object funcObj,
                                                            final ScopeDict scopeDict,
                                                            final Object compareObj) {
        addSQL(conditionEO, splitScopeFunc(func, funcObj, scopeDict, compareObj));
    }

    /**
     * SQL示例：ifnull(a.`age`, a.`fatherAge`) > 1
     */
    public static <EO extends BaseViewEO> void addScopeFunc(final EO conditionEO,
                                                            final String func,
                                                            final String separator,
                                                            final Object[] funcObjs,
                                                            final ScopeDict scopeDict,
                                                            final Object compareObj) {
        addSQL(conditionEO, splitScopeFunc(func, separator, funcObjs, scopeDict, compareObj));
    }

    /*
      -----------------------------拼装 范围条件 SQL（装载到键值对）-------------------------------
     */

    /**
     * SQL示例：a.`age` > 13
     */
    public static void addScope(final Map<String, Object> conditionMap,
                                final IColumnEnum columnEnum,
                                final ScopeDict scopeDict,
                                final Object compareObj) {
        addSQL(conditionMap, splitScope(columnEnum, scopeDict, compareObj));
    }

    /**
     * SQL示例：a.`age` > 13
     */
    public static void addScope(final Map<String, Object> conditionMap,
                                final ColumnEO columnEO,
                                final ScopeDict scopeDict,
                                final Object compareObj) {
        addSQL(conditionMap, splitScope(columnEO, scopeDict, compareObj));
    }

    /**
     * SQL示例：a.`age` > 13
     */
    public static void addScope(final Map<String, Object> conditionMap,
                                final String columnName,
                                final ScopeDict scopeDict,
                                final Object compareObj) {
        addSQL(conditionMap, splitScope(columnName, scopeDict, compareObj));
    }

    /**
     * SQL示例：now() > '2021-10-08 11:03:54'
     */
    public static void addScopeFunc(final Map<String, Object> conditionMap,
                                    final String func,
                                    final ScopeDict scopeDict,
                                    final Object compareObj) {
        addSQL(conditionMap, splitScopeFunc(func, scopeDict, compareObj));
    }

    /**
     * SQL示例：year(a.`birthday`) > 2018
     */
    public static void addScopeFunc(final Map<String, Object> conditionMap,
                                    final String func,
                                    final Object funcObj,
                                    final ScopeDict scopeDict,
                                    final Object compareObj) {
        addSQL(conditionMap, splitScopeFunc(func, funcObj, scopeDict, compareObj));
    }

    /**
     * SQL示例：ifnull(a.`age`, a.`fatherAge`) > 1
     */
    public static void addScopeFunc(final Map<String, Object> conditionMap,
                                    final String func,
                                    final String separator,
                                    final Object[] funcObjs,
                                    final ScopeDict scopeDict,
                                    final Object compareObj) {
        addSQL(conditionMap, splitScopeFunc(func, separator, funcObjs, scopeDict, compareObj));
    }

    /*
      -----------------------------拼装 范围条件 SQL（装载到可变的字符序列）-------------------------------
     */

    /**
     * SQL示例：a.`age` > 13
     */
    public static void appendScope(final StringBuilder builder,
                                   final boolean isTop,
                                   final IColumnEnum columnEnum,
                                   final ScopeDict scopeDict,
                                   final Object compareObj) {
        EmptyAssert.isNotNull(builder);
        if (!isTop) {
            builder.append(" AND ");
        }
        builder.append(splitScope(columnEnum, scopeDict, compareObj));
    }

    /**
     * SQL示例：a.`age` > 13
     */
    public static void appendScope(final StringBuilder builder,
                                   final boolean isTop,
                                   final ColumnEO columnEO,
                                   final ScopeDict scopeDict,
                                   final Object compareObj) {
        EmptyAssert.isNotNull(builder);
        if (!isTop) {
            builder.append(" AND ");
        }
        builder.append(splitScope(columnEO, scopeDict, compareObj));
    }

    /**
     * SQL示例：a.`age` > 13
     */
    public static void appendScope(final StringBuilder builder,
                                   final boolean isTop,
                                   final String columnName,
                                   final ScopeDict scopeDict,
                                   final Object compareObj) {
        EmptyAssert.isNotNull(builder);
        if (!isTop) {
            builder.append(" AND ");
        }
        builder.append(splitScope(columnName, scopeDict, compareObj));
    }

    /**
     * SQL示例：now() > '2021-10-08 11:03:54'
     */
    public static void appendScopeFunc(final StringBuilder builder,
                                       final boolean isTop,
                                       final String func,
                                       final ScopeDict scopeDict,
                                       final Object compareObj) {
        EmptyAssert.isNotNull(builder);
        if (!isTop) {
            builder.append(" AND ");
        }
        builder.append(splitScopeFunc(func, scopeDict, compareObj));
    }

    /**
     * SQL示例：year(a.`birthday`) > 2018
     */
    public static void appendScopeFunc(final StringBuilder builder,
                                       final boolean isTop,
                                       final String func,
                                       final Object funcObj,
                                       final ScopeDict scopeDict,
                                       final Object compareObj) {
        EmptyAssert.isNotNull(builder);
        if (!isTop) {
            builder.append(" AND ");
        }
        builder.append(splitScopeFunc(func, funcObj, scopeDict, compareObj));
    }

    /**
     * SQL示例：ifnull(a.`age`, a.`fatherAge`) > 1
     */
    public static void appendScopeFunc(final StringBuilder builder,
                                       final boolean isTop,
                                       final String func,
                                       final String separator,
                                       final Object[] funcObjs,
                                       final ScopeDict scopeDict,
                                       final Object compareObj) {
        EmptyAssert.isNotNull(builder);
        if (!isTop) {
            builder.append(" AND ");
        }
        builder.append(splitScopeFunc(func, separator, funcObjs, scopeDict, compareObj));
    }

    /*
      -----------------------------拼装 范围条件 SQL（不进行装载，直接返回）-------------------------------
     */

    /**
     * SQL示例：a.`age` > 13
     */
    public static String splitScope(final IColumnEnum columnEnum, final ScopeDict scopeDict, final Object obj) {
        EmptyAssert.isNotNull(columnEnum);
        return splitScope(columnEnum.getColumn(), scopeDict, obj);
    }

    /**
     * SQL示例：a.`age` > 13
     */
    public static String splitScope(final ColumnEO columnEO, final ScopeDict scopeDict, final Object obj) {
        EmptyAssert.isNotNull(columnEO);
        return splitScope(columnEO.getColumn(), scopeDict, obj);
    }

    /**
     * SQL示例：a.`age` > 13
     */
    public static String splitScope(final String columnName, final ScopeDict scopeDict, final Object obj) {
        EmptyAssert.isNotNull(scopeDict);
        return String.format(
                " %s %s %s ",
                ColumnUtil.splitDefaultAlias(columnName),
                scopeDict.key,
                ValueUtil.splitAlias(obj)
        );
    }

    /**
     * SQL示例：now() > '2021-10-08 11:03:54'
     */
    public static String splitScopeFunc(final String func,
                                        final ScopeDict scopeDict,
                                        final Object compareObj) {
        return splitScopeFunc(func, null, scopeDict, compareObj);
    }

    /**
     * SQL示例：year(a.`birthday`) > 2018
     */
    public static String splitScopeFunc(final String func,
                                        final Object funcObj,
                                        final ScopeDict scopeDict,
                                        final Object compareObj) {
        return splitScopeFunc(func, null, new Object[]{funcObj}, scopeDict, compareObj);
    }

    /**
     * SQL示例：ifnull(a.`age`, a.`fatherAge`) > 1
     */
    public static String splitScopeFunc(final String func,
                                        final String separator,
                                        final Object[] funcObjs,
                                        final ScopeDict scopeDict,
                                        final Object compareObj) {
        EmptyAssert.isNotNull(scopeDict);
        return String.format(
                " %s %s %s ",
                FuncUtil.splitMore(func, separator, true, funcObjs),
                scopeDict.key,
                ValueUtil.splitAlias(compareObj)
        );
    }

    /*
      -----------------------------拼装 判空条件 SQL（装载到实体）-------------------------------
     */

    /**
     * SQL示例：a.`age` is not null
     */
    public static <EO extends BaseViewEO> void addNull(final EO conditionEO,
                                                       final IColumnEnum columnEnum,
                                                       final NullDict nullDict) {
        addSQL(conditionEO, splitNull(columnEnum, nullDict));
    }

    /**
     * SQL示例：a.`age` is not null
     */
    public static <EO extends BaseViewEO> void addNull(final EO conditionEO,
                                                       final ColumnEO columnEO,
                                                       final NullDict nullDict) {
        addSQL(conditionEO, splitNull(columnEO, nullDict));
    }

    /**
     * SQL示例：a.`age` is not null
     */
    public static <EO extends BaseViewEO> void addNull(final EO conditionEO,
                                                       final String columnName,
                                                       final NullDict nullDict) {
        addSQL(conditionEO, splitNull(columnName, nullDict));
    }

    /**
     * SQL示例：now() is not null
     */
    public static <EO extends BaseViewEO> void addNullFunc(final EO conditionEO,
                                                           final String func,
                                                           final NullDict nullDict) {
        addSQL(conditionEO, splitNullFunc(func, nullDict));
    }

    /**
     * SQL示例：min(a.`age`) is not null
     */
    public static <EO extends BaseViewEO> void addNullFunc(final EO conditionEO,
                                                           final String func,
                                                           final Object funcObj,
                                                           final NullDict nullDict) {
        addSQL(conditionEO, splitNullFunc(func, funcObj, nullDict));
    }

    /**
     * SQL示例：ifnull(a.`age`, a.`fatherAge`) is not null
     */
    public static <EO extends BaseViewEO> void addNullFunc(final EO conditionEO,
                                                           final String func,
                                                           final String separator,
                                                           final Object[] funcObjs,
                                                           final NullDict nullDict) {
        addSQL(conditionEO, splitNullFunc(func, separator, funcObjs, nullDict));
    }

    /*
      -----------------------------拼装 判空条件 SQL（装载到键值对）-------------------------------
     */

    /**
     * SQL示例：a.`age` is not null
     */
    public static void addNull(final Map<String, Object> conditionMap,
                               final IColumnEnum columnEnum,
                               final NullDict nullDict) {
        addSQL(conditionMap, splitNull(columnEnum, nullDict));
    }

    /**
     * SQL示例：a.`age` is not null
     */
    public static void addNull(final Map<String, Object> conditionMap,
                               final ColumnEO columnEO,
                               final NullDict nullDict) {
        addSQL(conditionMap, splitNull(columnEO, nullDict));
    }

    /**
     * SQL示例：a.`age` is not null
     */
    public static void addNull(final Map<String, Object> conditionMap,
                               final String columnName,
                               final NullDict nullDict) {
        addSQL(conditionMap, splitNull(columnName, nullDict));
    }

    /**
     * SQL示例：now() is not null
     */
    public static void addNullFunc(final Map<String, Object> conditionMap,
                                   final String func,
                                   final NullDict nullDict) {
        addSQL(conditionMap, splitNullFunc(func, nullDict));
    }

    /**
     * SQL示例：min(a.`age`) is not null
     */
    public static void addNullFunc(final Map<String, Object> conditionMap,
                                   final String func,
                                   final Object funcObj,
                                   final NullDict nullDict) {
        addSQL(conditionMap, splitNullFunc(func, funcObj, nullDict));
    }

    /**
     * SQL示例：ifnull(a.`age`, a.`fatherAge`) is not null
     */
    public static void addNullFunc(final Map<String, Object> conditionMap,
                                   final String func,
                                   final String separator,
                                   final Object[] funcObjs,
                                   final NullDict nullDict) {
        addSQL(conditionMap, splitNullFunc(func, separator, funcObjs, nullDict));
    }

    /*
      -----------------------------拼装 判空条件 SQL（装载到可变的字符序列）-------------------------------
     */

    /**
     * SQL示例：a.`age` is not null
     */
    public static void appendNull(final StringBuilder builder,
                                  final boolean isTop,
                                  final IColumnEnum columnEnum,
                                  final NullDict nullDict) {
        EmptyAssert.isNotNull(builder);
        if (!isTop) {
            builder.append(" AND ");
        }
        builder.append(splitNull(columnEnum, nullDict));
    }

    /**
     * SQL示例：a.`age` is not null
     */
    public static void appendNull(final StringBuilder builder,
                                  final boolean isTop,
                                  final ColumnEO columnEO,
                                  final NullDict nullDict) {
        EmptyAssert.isNotNull(builder);
        if (!isTop) {
            builder.append(" AND ");
        }
        builder.append(splitNull(columnEO, nullDict));
    }

    /**
     * SQL示例：a.`age` is not null
     */
    public static void appendNull(final StringBuilder builder,
                                  final boolean isTop,
                                  final String columnName,
                                  final NullDict nullDict) {
        EmptyAssert.isNotNull(builder);
        if (!isTop) {
            builder.append(" AND ");
        }
        builder.append(splitNull(columnName, nullDict));
    }

    /**
     * SQL示例：now() is not null
     */
    public static void appendNullFunc(final StringBuilder builder,
                                      final boolean isTop,
                                      final String func,
                                      final NullDict nullDict) {
        EmptyAssert.isNotNull(builder);
        if (!isTop) {
            builder.append(" AND ");
        }
        builder.append(splitNullFunc(func, nullDict));
    }

    /**
     * SQL示例：min(a.`age`) is not null
     */
    public static void appendNullFunc(final StringBuilder builder,
                                      final boolean isTop,
                                      final String func,
                                      final Object funcObj,
                                      final NullDict nullDict) {
        EmptyAssert.isNotNull(builder);
        if (!isTop) {
            builder.append(" AND ");
        }
        builder.append(splitNullFunc(func, funcObj, nullDict));
    }

    /**
     * SQL示例：ifnull(a.`age`, a.`fatherAge`) is not null
     */
    public static void appendNullFunc(final StringBuilder builder,
                                      final boolean isTop,
                                      final String func,
                                      final String separator,
                                      final Object[] funcObjs,
                                      final NullDict nullDict) {
        EmptyAssert.isNotNull(builder);
        if (!isTop) {
            builder.append(" AND ");
        }
        builder.append(splitNullFunc(func, separator, funcObjs, nullDict));
    }

    /*
      -----------------------------拼装 判空条件 SQL（不进行装载，直接返回）-------------------------------
     */

    /**
     * SQL示例：a.`age` is not null
     */
    public static String splitNull(final IColumnEnum columnEnum, final NullDict nullDict) {
        EmptyAssert.isNotNull(columnEnum);
        return splitNull(columnEnum.getColumn(), nullDict);
    }

    /**
     * SQL示例：a.`age` is not null
     */
    public static String splitNull(final ColumnEO columnEO, final NullDict nullDict) {
        EmptyAssert.isNotNull(columnEO);
        return splitNull(columnEO.getColumn(), nullDict);
    }

    /**
     * SQL示例：a.`age` is not null
     */
    public static String splitNull(final String columnName, final NullDict nullDict) {
        EmptyAssert.isNotNull(nullDict);
        return String.format(
                " %s %s ",
                ColumnUtil.splitDefaultAlias(columnName),
                nullDict.key
        );
    }

    /**
     * SQL示例：now() is not null
     */
    public static String splitNullFunc(final String func, final NullDict nullDict) {
        return splitNullFunc(func, null, nullDict);
    }

    /**
     * SQL示例：min(a.`age`) is not null
     */
    public static String splitNullFunc(final String func, final Object funcObj, final NullDict nullDict) {
        return splitNullFunc(func, null, new Object[]{funcObj}, nullDict);
    }

    /**
     * SQL示例：ifnull(a.`age`, a.`fatherAge`) is not null
     */
    public static String splitNullFunc(final String func,
                                       final String separator,
                                       final Object[] funcObjs,
                                       final NullDict nullDict) {
        EmptyAssert.isNotNull(nullDict);
        return String.format(
                " %s %s ",
                FuncUtil.splitMore(func, separator, true, funcObjs),
                nullDict.key
        );
    }

    /*
      -----------------------------拼装 模糊查询条件 SQL（装载到实体）-------------------------------
     */

    /**
     * SQL示例：a.`name` like '%张三%'
     */
    public static <EO extends BaseViewEO> void addLike(final EO conditionEO,
                                                       final IColumnEnum columnEnum,
                                                       final LikeDict likeDict,
                                                       final String compareObj) {
        addSQL(conditionEO, splitLike(columnEnum, likeDict, compareObj));
    }

    /**
     * SQL示例：a.`name` like '%张三%'
     */
    public static <EO extends BaseViewEO> void addLike(final EO conditionEO,
                                                       final ColumnEO columnEO,
                                                       final LikeDict likeDict,
                                                       final String compareObj) {
        addSQL(conditionEO, splitLike(columnEO, likeDict, compareObj));
    }

    /**
     * SQL示例：a.`name` like '%张三%'
     */
    public static <EO extends BaseViewEO> void addLike(final EO conditionEO,
                                                       final String columnName,
                                                       final LikeDict likeDict,
                                                       final String compareObj) {
        addSQL(conditionEO, splitLike(columnName, likeDict, compareObj));
    }

    /**
     * SQL示例：now() like '%2021%'
     */
    public static <EO extends BaseViewEO> void addLikeFunc(final EO conditionEO,
                                                           final String func,
                                                           final LikeDict likeDict,
                                                           final String compareObj) {
        addSQL(conditionEO, splitLikeFunc(func, likeDict, compareObj));
    }

    /**
     * SQL示例：min(a.`name`) like '%张三%'
     */
    public static <EO extends BaseViewEO> void addLikeFunc(final EO conditionEO,
                                                           final String func,
                                                           final Object funcObj,
                                                           final LikeDict likeDict,
                                                           final String compareObj) {
        addSQL(conditionEO, splitLikeFunc(func, funcObj, likeDict, compareObj));
    }

    /**
     * SQL示例：ifnull(a.`first_name`, a.`last_name`) like '%张三%'
     */
    public static <EO extends BaseViewEO> void addLikeFunc(final EO conditionEO,
                                                           final String func,
                                                           final String separator,
                                                           final Object[] funcObjs,
                                                           final LikeDict likeDict,
                                                           final String compareObj) {
        addSQL(conditionEO, splitLikeFunc(func, separator, funcObjs, likeDict, compareObj));
    }

    /*
      -----------------------------拼装 模糊查询条件 SQL（装载到键值对）-------------------------------
     */

    /**
     * SQL示例：a.`name` like '%张三%'
     */
    public static void addLike(final Map<String, Object> conditionMap,
                               final IColumnEnum columnEnum,
                               final LikeDict likeDict,
                               final String compareObj) {
        addSQL(conditionMap, splitLike(columnEnum, likeDict, compareObj));
    }

    /**
     * SQL示例：a.`name` like '%张三%'
     */
    public static void addLike(final Map<String, Object> conditionMap,
                               final ColumnEO columnEO,
                               final LikeDict likeDict,
                               final String compareObj) {
        addSQL(conditionMap, splitLike(columnEO, likeDict, compareObj));
    }

    /**
     * SQL示例：a.`name` like '%张三%'
     */
    public static void addLike(final Map<String, Object> conditionMap,
                               final String columnName,
                               final LikeDict likeDict,
                               final String compareObj) {
        addSQL(conditionMap, splitLike(columnName, likeDict, compareObj));
    }

    /**
     * SQL示例：now() like '%2021%'
     */
    public static void addLikeFunc(final Map<String, Object> conditionMap,
                                   final String func,
                                   final LikeDict likeDict,
                                   final String compareObj) {
        addSQL(conditionMap, splitLikeFunc(func, likeDict, compareObj));
    }

    /**
     * SQL示例：min(a.`name`) like '%张三%'
     */
    public static void addLikeFunc(final Map<String, Object> conditionMap,
                                   final String func,
                                   final Object funcObj,
                                   final LikeDict likeDict,
                                   final String compareObj) {
        addSQL(conditionMap, splitLikeFunc(func, funcObj, likeDict, compareObj));
    }

    /**
     * SQL示例：ifnull(a.`first_name`, a.`last_name`) like '%张三%'
     */
    public static void addLikeFunc(final Map<String, Object> conditionMap,
                                   final String func,
                                   final String separator,
                                   final Object[] funcObjs,
                                   final LikeDict likeDict,
                                   final String compareObj) {
        addSQL(conditionMap, splitLikeFunc(func, separator, funcObjs, likeDict, compareObj));
    }

    /*
      -----------------------------拼装 模糊查询条件 SQL（装载到可变的字符序列）-------------------------------
     */

    /**
     * SQL示例：a.`name` like '%张三%'
     */
    public static void appendLike(final StringBuilder builder,
                                  final boolean isTop,
                                  final IColumnEnum columnEnum,
                                  final LikeDict likeDict,
                                  final String compareObj) {
        EmptyAssert.isNotNull(builder);
        if (!isTop) {
            builder.append(" AND ");
        }
        builder.append(splitLike(columnEnum, likeDict, compareObj));
    }

    /**
     * SQL示例：a.`name` like '%张三%'
     */
    public static void appendLike(final StringBuilder builder,
                                  final boolean isTop,
                                  final ColumnEO columnEO,
                                  final LikeDict likeDict,
                                  final String compareObj) {
        EmptyAssert.isNotNull(builder);
        if (!isTop) {
            builder.append(" AND ");
        }
        builder.append(splitLike(columnEO, likeDict, compareObj));
    }

    /**
     * SQL示例：a.`name` like '%张三%'
     */
    public static void appendLike(final StringBuilder builder,
                                  final boolean isTop,
                                  final String columnName,
                                  final LikeDict likeDict,
                                  final String compareObj) {
        EmptyAssert.isNotNull(builder);
        if (!isTop) {
            builder.append(" AND ");
        }
        builder.append(splitLike(columnName, likeDict, compareObj));
    }

    /**
     * SQL示例：now() like '%2021%'
     */
    public static void appendLikeFunc(final StringBuilder builder,
                                      final boolean isTop,
                                      final String func,
                                      final LikeDict likeDict,
                                      final String compareObj) {
        EmptyAssert.isNotNull(builder);
        if (!isTop) {
            builder.append(" AND ");
        }
        builder.append(splitLikeFunc(func, likeDict, compareObj));
    }

    /**
     * SQL示例：min(a.`name`) like '%张三%'
     */
    public static void appendLikeFunc(final StringBuilder builder,
                                      final boolean isTop,
                                      final String func,
                                      final Object funcObj,
                                      final LikeDict likeDict,
                                      final String compareObj) {
        EmptyAssert.isNotNull(builder);
        if (!isTop) {
            builder.append(" AND ");
        }
        builder.append(splitLikeFunc(func, funcObj, likeDict, compareObj));
    }

    /**
     * SQL示例：ifnull(a.`first_name`, a.`last_name`) like '%张三%'
     */
    public static void appendLikeFunc(final StringBuilder builder,
                                      final boolean isTop,
                                      final String func,
                                      final String separator,
                                      final Object[] funcObjs,
                                      final LikeDict likeDict,
                                      final String compareObj) {
        EmptyAssert.isNotNull(builder);
        if (!isTop) {
            builder.append(" AND ");
        }
        builder.append(splitLikeFunc(func, separator, funcObjs, likeDict, compareObj));
    }

    /*
      -----------------------------拼装 模糊查询条件 SQL（不进行装载，直接返回）-------------------------------
     */

    /**
     * SQL示例：a.`name` like '%张三%'
     */
    public static String splitLike(final IColumnEnum columnEnum, final LikeDict likeDict, final String compareObj) {
        EmptyAssert.isNotNull(columnEnum);
        return splitLike(columnEnum.getColumn(), likeDict, compareObj);
    }

    /**
     * SQL示例：a.`name` like '%张三%'
     */
    public static String splitLike(final ColumnEO columnEO, final LikeDict likeDict, final String compareObj) {
        EmptyAssert.isNotNull(columnEO);
        return splitLike(columnEO.getColumn(), likeDict, compareObj);
    }

    /**
     * SQL示例：a.`name` like '%张三%'
     */
    public static String splitLike(final String columnName, final LikeDict likeDict, final String compareObj) {
        EmptyAssert.allNotNull(likeDict, compareObj);
        return String.format(
                " %s %s %s ",
                ColumnUtil.splitDefaultAlias(columnName),
                likeDict.key,
                ValueUtil.splitAlias(likeDict.start + compareObj + likeDict.end)
        );
    }

    /**
     * SQL示例：now() like '%2021%'
     */
    public static String splitLikeFunc(final String func,
                                       final LikeDict likeDict,
                                       final String compareObj) {
        return splitLikeFunc(func, null, likeDict, compareObj);
    }

    /**
     * SQL示例：min(a.`name`) like '%张三%'
     */
    public static String splitLikeFunc(final String func,
                                       final Object funcObj,
                                       final LikeDict likeDict,
                                       final String compareObj) {
        return splitLikeFunc(func, null, new Object[]{funcObj}, likeDict, compareObj);
    }

    /**
     * SQL示例：ifnull(a.`first_name`, a.`last_name`) like '%张三%'
     */
    public static String splitLikeFunc(final String func,
                                       final String separator,
                                       final Object[] funcObjs,
                                       final LikeDict likeDict,
                                       final String compareObj) {
        EmptyAssert.allNotNull(likeDict, compareObj);
        return String.format(
                " %s %s %s ",
                FuncUtil.splitMore(func, separator, true, funcObjs),
                likeDict.key,
                ValueUtil.splitAlias(likeDict.start + compareObj + likeDict.end)
        );
    }

    /*
      -----------------------------拼装 循环条件 SQL（装载到实体）-------------------------------
     */

    /**
     * SQL示例：a.`age` in (1, 2, 3)
     */
    public static <EO extends BaseViewEO> void addCircle(final EO conditionEO,
                                                         final IColumnEnum columnEnum,
                                                         final CircleDict circleDict,
                                                         final Object... compareObjs) {
        addSQL(conditionEO, splitCircle(columnEnum, circleDict, compareObjs));
    }

    /**
     * SQL示例：a.`age` in (1, 2, 3)
     */
    public static <EO extends BaseViewEO> void addCircle(final EO conditionEO,
                                                         final ColumnEO columnEO,
                                                         final CircleDict circleDict,
                                                         final Object... compareObjs) {
        addSQL(conditionEO, splitCircle(columnEO, circleDict, compareObjs));
    }

    /**
     * SQL示例：a.`age` in (1, 2, 3)
     */
    public static <EO extends BaseViewEO> void addCircle(final EO conditionEO,
                                                         final String columnName,
                                                         final CircleDict circleDict,
                                                         final Object... compareObjs) {
        addSQL(conditionEO, splitCircle(columnName, circleDict, compareObjs));
    }

    /**
     * SQL示例：now() in ('2021-10-08 12:33:32', '2021-10-08 12:33:36')
     */
    public static <EO extends BaseViewEO> void addCircleFunc(final EO conditionEO,
                                                             final String func,
                                                             final CircleDict circleDict,
                                                             final Object... compareObjs) {
        addSQL(conditionEO, splitCircleFunc(func, circleDict, compareObjs));
    }

    /**
     * SQL示例：min(a.`age`) in (1, 2, 3)
     */
    public static <EO extends BaseViewEO> void addCircleFunc(final EO conditionEO,
                                                             final String func,
                                                             final Object funcObj,
                                                             final CircleDict circleDict,
                                                             final Object... compareObjs) {
        addSQL(conditionEO, splitCircleFunc(func, funcObj, circleDict, compareObjs));
    }

    /**
     * SQL示例：ifnull(a.`first_name`, a.`last_name`) in ('TOM', "SALE")
     */
    public static <EO extends BaseViewEO> void addCircleFunc(final EO conditionEO,
                                                             final String func,
                                                             final String separator,
                                                             final Object[] funcObjs,
                                                             final CircleDict circleDict,
                                                             final Object... compareObjs) {
        addSQL(conditionEO, splitCircleFunc(func, separator, funcObjs, circleDict, compareObjs));
    }

    /*
      -----------------------------拼装 循环条件 SQL（装载到键值对）-------------------------------
     */

    /**
     * SQL示例：a.`age` in (1, 2, 3)
     */
    public static void addCircle(final Map<String, Object> conditionMap,
                                 final IColumnEnum columnEnum,
                                 final CircleDict circleDict,
                                 final Object... compareObjs) {
        addSQL(conditionMap, splitCircle(columnEnum, circleDict, compareObjs));
    }

    /**
     * SQL示例：a.`age` in (1, 2, 3)
     */
    public static void addCircle(final Map<String, Object> conditionMap,
                                 final ColumnEO columnEO,
                                 final CircleDict circleDict,
                                 final Object... compareObjs) {
        addSQL(conditionMap, splitCircle(columnEO, circleDict, compareObjs));
    }

    /**
     * SQL示例：a.`age` in (1, 2, 3)
     */
    public static void addCircle(final Map<String, Object> conditionMap,
                                 final String columnName,
                                 final CircleDict circleDict,
                                 final Object... compareObjs) {
        addSQL(conditionMap, splitCircle(columnName, circleDict, compareObjs));
    }

    /**
     * SQL示例：now() in ('2021-10-08 12:33:32', '2021-10-08 12:33:36')
     */
    public static void addCircleFunc(final Map<String, Object> conditionMap,
                                     final String func,
                                     final CircleDict circleDict,
                                     final Object... compareObjs) {
        addSQL(conditionMap, splitCircleFunc(func, circleDict, compareObjs));
    }

    /**
     * SQL示例：min(a.`age`) in (1, 2, 3)
     */
    public static void addCircleFunc(final Map<String, Object> conditionMap,
                                     final String func,
                                     final Object funcObj,
                                     final CircleDict circleDict,
                                     final Object... compareObjs) {
        addSQL(conditionMap, splitCircleFunc(func, funcObj, circleDict, compareObjs));
    }

    /**
     * SQL示例：ifnull(a.`first_name`, a.`last_name`) in ('TOM', "SALE")
     */
    public static void addCircleFunc(final Map<String, Object> conditionMap,
                                     final String func,
                                     final String separator,
                                     final Object[] funcObjs,
                                     final CircleDict circleDict,
                                     final Object... compareObjs) {
        addSQL(conditionMap, splitCircleFunc(func, separator, funcObjs, circleDict, compareObjs));
    }

    /*
      -----------------------------拼装 循环条件 SQL（装载到可变的字符序列）-------------------------------
     */

    /**
     * SQL示例：a.`age` in (1, 2, 3)
     */
    public static void appendCircle(final StringBuilder builder,
                                    final boolean isTop,
                                    final IColumnEnum columnEnum,
                                    final CircleDict circleDict,
                                    final Object... compareObjs) {
        EmptyAssert.isNotNull(builder);
        if (!isTop) {
            builder.append(" AND ");
        }
        builder.append(splitCircle(columnEnum, circleDict, compareObjs));
    }

    /**
     * SQL示例：a.`age` in (1, 2, 3)
     */
    public static void appendCircle(final StringBuilder builder,
                                    final boolean isTop,
                                    final ColumnEO columnEO,
                                    final CircleDict circleDict,
                                    final Object... compareObjs) {
        EmptyAssert.isNotNull(builder);
        if (!isTop) {
            builder.append(" AND ");
        }
        builder.append(splitCircle(columnEO, circleDict, compareObjs));
    }

    /**
     * SQL示例：a.`age` in (1, 2, 3)
     */
    public static void appendCircle(final StringBuilder builder,
                                    final boolean isTop,
                                    final String columnName,
                                    final CircleDict circleDict,
                                    final Object... compareObjs) {
        EmptyAssert.isNotNull(builder);
        if (!isTop) {
            builder.append(" AND ");
        }
        builder.append(splitCircle(columnName, circleDict, compareObjs));
    }

    /**
     * SQL示例：now() in ('2021-10-08 12:33:32', '2021-10-08 12:33:36')
     */
    public static void appendCircleFunc(final StringBuilder builder,
                                        final boolean isTop,
                                        final String func,
                                        final CircleDict circleDict,
                                        final Object... compareObjs) {
        EmptyAssert.isNotNull(builder);
        if (!isTop) {
            builder.append(" AND ");
        }
        builder.append(splitCircleFunc(func, circleDict, compareObjs));
    }

    /**
     * SQL示例：min(a.`age`) in (1, 2, 3)
     */
    public static void appendCircleFunc(final StringBuilder builder,
                                        final boolean isTop,
                                        final String func,
                                        final Object funcObj,
                                        final CircleDict circleDict,
                                        final Object... compareObjs) {
        EmptyAssert.isNotNull(builder);
        if (!isTop) {
            builder.append(" AND ");
        }
        builder.append(splitCircleFunc(func, funcObj, circleDict, compareObjs));
    }

    /**
     * SQL示例：ifnull(a.`first_name`, a.`last_name`) in ('TOM', "SALE")
     */
    public static void appendCircleFunc(final StringBuilder builder,
                                        final boolean isTop,
                                        final String func,
                                        final String separator,
                                        final Object[] funcObjs,
                                        final CircleDict circleDict,
                                        final Object... compareObjs) {
        EmptyAssert.isNotNull(builder);
        if (!isTop) {
            builder.append(" AND ");
        }
        builder.append(splitCircleFunc(func, separator, funcObjs, circleDict, compareObjs));
    }
    
    /*
      -----------------------------拼装 循环条件 SQL（不进行装载，直接返回）-------------------------------
     */

    /**
     * SQL示例：a.`age` in (1, 2, 3)
     */
    public static String splitCircle(final IColumnEnum columnEnum,
                                     final CircleDict circleDict,
                                     final Object... compareObjs) {
        EmptyAssert.isNotNull(columnEnum);
        return splitCircle(columnEnum.getColumn(), circleDict, compareObjs);
    }

    /**
     * SQL示例：a.`age` in (1, 2, 3)
     */
    public static String splitCircle(final ColumnEO columnEO,
                                     final CircleDict circleDict,
                                     final Object... compareObjs) {
        EmptyAssert.isNotNull(columnEO);
        return splitCircle(columnEO.getColumn(), circleDict, compareObjs);
    }

    /**
     * SQL示例：a.`age` in (1, 2, 3)
     */
    public static String splitCircle(final String columnName,
                                     final CircleDict circleDict,
                                     final Object... compareObjs) {
        EmptyAssert.isNotNull(circleDict);
        return String.format(
                " %s %s ",
                ColumnUtil.splitDefaultAlias(columnName),
                FuncUtil.splitMore(circleDict.key, ",", true, compareObjs)

        );
    }

    /**
     * SQL示例：now() in ('2021-10-08 12:33:32', '2021-10-08 12:33:36')
     */
    public static String splitCircleFunc(final String func,
                                         final CircleDict circleDict,
                                         final Object... compareObjs) {
        return splitCircleFunc(func, null, circleDict, compareObjs);
    }

    /**
     * SQL示例：min(a.`age`) in (1, 2, 3)
     */
    public static String splitCircleFunc(final String func,
                                         final Object funcObj,
                                         final CircleDict circleDict,
                                         final Object... compareObjs) {
        return splitCircleFunc(func, null, new Object[]{funcObj}, circleDict, compareObjs);
    }

    /**
     * SQL示例：ifnull(a.`first_name`, a.`last_name`) in ('TOM', "SALE")
     */
    public static String splitCircleFunc(final String func,
                                         final String separator,
                                         final Object[] funcObjs,
                                         final CircleDict circleDict,
                                         final Object... compareObjs) {
        EmptyAssert.isNotNull(circleDict);
        return String.format(
                " %s %s ",
                FuncUtil.splitMore(func, separator, true, funcObjs),
                FuncUtil.splitMore(circleDict.key, ",", true, compareObjs)
        );
    }

    /*
      -----------------------------装载 查询条件 SQL-------------------------------
     */

    public static <EO extends BaseViewEO> void addSQL(final EO conditionEO, final String whereSQL) {
        EmptyAssert.isNotNull(conditionEO);
        EmptyAssert.isNotBlank(whereSQL);

        List<String> whereSQLList = conditionEO.getWhereSQLList();
        if (EmptyValidate.isNull(whereSQLList)) {
            whereSQLList = new ArrayList<>();
            conditionEO.setWhereSQLList(whereSQLList);
        }
        if (!whereSQLList.contains(whereSQL)) {
            whereSQLList.add(whereSQL);
        }
    }

    public static <EO extends BaseViewEO> void addSQLList(final EO conditionEO, final List<String> whereSQLS) {
        EmptyAssert.isNotNull(conditionEO);
        EmptyAssert.isNotEmpty(whereSQLS);

        List<String> whereSQLList = conditionEO.getWhereSQLList();
        if (EmptyValidate.isNull(whereSQLList)) {
            conditionEO.setWhereSQLList(whereSQLS);
            return;
        }

        for (String whereSQL : whereSQLS) {
            if (!whereSQLList.contains(whereSQL)) {
                whereSQLList.add(whereSQL);
            }
        }
    }

    public static void addSQL(final Map<String, Object> conditionMap, final String whereSQL) {
        EmptyAssert.isNotNull(conditionMap);
        EmptyAssert.isNotBlank(whereSQL);

        @SuppressWarnings("unchecked")
        List<String> whereSQLList = MapConvertor.convertToFirstValue(
                conditionMap,
                SpecialKey.whereSQLList.name(),
                obj -> (List<String>) obj
        );
        if (EmptyValidate.isNull(whereSQLList)) {
            whereSQLList = new ArrayList<>();
            conditionMap.put(SpecialKey.whereSQLList.name(), whereSQLList);
        }
        if (!whereSQLList.contains(whereSQL)) {
            whereSQLList.add(whereSQL);
        }
    }

    public static void addSQLList(final Map<String, Object> conditionMap, final List<String> whereSQLS) {
        EmptyAssert.isNotNull(conditionMap);
        EmptyAssert.isNotEmpty(whereSQLS);

        @SuppressWarnings("unchecked")
        List<String> whereSQLList = MapConvertor.convertToFirstValue(
                conditionMap,
                SpecialKey.whereSQLList.name(),
                obj -> (List<String>) obj
        );
        if (EmptyValidate.isNull(whereSQLList)) {
            conditionMap.put(SpecialKey.whereSQLList.name(), whereSQLS);
            return;
        }

        for (String whereSQL : whereSQLS) {
            if (!whereSQLList.contains(whereSQL)) {
                whereSQLList.add(whereSQL);
            }
        }
    }
}
