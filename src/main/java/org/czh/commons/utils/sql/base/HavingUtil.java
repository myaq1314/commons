package org.czh.commons.utils.sql.base;

import org.czh.commons.entity.eo.BaseViewEO;
import org.czh.commons.enums.sql.CircleDict;
import org.czh.commons.enums.sql.LikeDict;
import org.czh.commons.enums.sql.NullDict;
import org.czh.commons.enums.sql.ScopeDict;
import org.czh.commons.enums.sql.SpecialKey;
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
public final class HavingUtil {

    /*
      -----------------------------拼装 分组筛选 范围条件 SQL（装载到实体）-------------------------------
     */

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
      -----------------------------拼装 分组筛选 范围条件 SQL（装载到键值对）-------------------------------
     */

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
      -----------------------------拼装 分组筛选 范围条件 SQL（装载到可变的字符序列）-------------------------------
     */

    /**
     * SQL示例：now() > '2021-10-08 11:03:54'
     */
    public static void appendScopeFunc(final StringBuilder builder,
                                       final String func,
                                       final ScopeDict scopeDict,
                                       final Object compareObj) {
        appendScopeFunc(builder, false, func, scopeDict, compareObj);
    }

    /**
     * SQL示例：year(a.`birthday`) > 2018
     */
    public static void appendScopeFunc(final StringBuilder builder,
                                       final String func,
                                       final Object funcObj,
                                       final ScopeDict scopeDict,
                                       final Object compareObj) {
        appendScopeFunc(builder, false, func, funcObj, scopeDict, compareObj);
    }

    /**
     * SQL示例：ifnull(a.`age`, a.`fatherAge`) > 1
     */
    public static void appendScopeFunc(final StringBuilder builder,
                                       final String func,
                                       final String separator,
                                       final Object[] funcObjs,
                                       final ScopeDict scopeDict,
                                       final Object compareObj) {
        appendScopeFunc(builder, false, func, separator, funcObjs, scopeDict, compareObj);
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
      -----------------------------拼装 分组筛选 范围条件 SQL（不进行装载，直接返回）-------------------------------
     */

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
                FuncUtil.splitMore(func, separator, false, funcObjs),
                scopeDict.key,
                ValueUtil.splitAlias(compareObj)
        );
    }

    /*
      -----------------------------拼装 分组筛选 判空条件 SQL（装载到实体）-------------------------------
     */

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
      -----------------------------拼装 分组筛选 判空条件 SQL（装载到键值对）-------------------------------
     */

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
      -----------------------------拼装 分组筛选 判空条件 SQL（装载到可变的字符序列）-------------------------------
     */

    /**
     * SQL示例：now() is not null
     */
    public static void appendNullFunc(final StringBuilder builder,
                                      final String func,
                                      final NullDict nullDict) {
        appendNullFunc(builder, false, func, nullDict);
    }

    /**
     * SQL示例：min(a.`age`) is not null
     */
    public static void appendNullFunc(final StringBuilder builder,
                                      final String func,
                                      final Object funcObj,
                                      final NullDict nullDict) {
        appendNullFunc(builder, false, func, funcObj, nullDict);
    }

    /**
     * SQL示例：ifnull(a.`age`, a.`fatherAge`) is not null
     */
    public static void appendNullFunc(final StringBuilder builder,
                                      final String func,
                                      final String separator,
                                      final Object[] funcObjs,
                                      final NullDict nullDict) {
        appendNullFunc(builder, false, func, separator, funcObjs, nullDict);
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
      -----------------------------拼装 分组筛选 判空条件 SQL（不进行装载，直接返回）-------------------------------
     */

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
                FuncUtil.splitMore(func, separator, false, funcObjs),
                nullDict.key
        );
    }

    /*
      -----------------------------拼装 分组筛选 模糊查询条件 SQL（装载到实体）-------------------------------
     */

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
      -----------------------------拼装 分组筛选 模糊查询条件 SQL（装载到键值对）-------------------------------
     */

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
      -----------------------------拼装 分组筛选 模糊查询条件 SQL（装载到可变的字符序列）-------------------------------
     */

    /**
     * SQL示例：now() like '%2021%'
     */
    public static void appendLikeFunc(final StringBuilder builder,
                                      final String func,
                                      final LikeDict likeDict,
                                      final String compareObj) {
        appendLikeFunc(builder, false, func, likeDict, compareObj);
    }

    /**
     * SQL示例：min(a.`name`) like '%张三%'
     */
    public static void appendLikeFunc(final StringBuilder builder,
                                      final String func,
                                      final Object funcObj,
                                      final LikeDict likeDict,
                                      final String compareObj) {
        appendLikeFunc(builder, false, func, funcObj, likeDict, compareObj);
    }

    /**
     * SQL示例：ifnull(a.`first_name`, a.`last_name`) like '%张三%'
     */
    public static void appendLikeFunc(final StringBuilder builder,
                                      final String func,
                                      final String separator,
                                      final Object[] funcObjs,
                                      final LikeDict likeDict,
                                      final String compareObj) {
        appendLikeFunc(builder, false, func, separator, funcObjs, likeDict, compareObj);
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
      -----------------------------拼装 分组筛选 模糊查询条件 SQL（不进行装载，直接返回）-------------------------------
     */

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
                FuncUtil.splitMore(func, separator, false, funcObjs),
                likeDict.key,
                ValueUtil.splitAlias(likeDict.start + compareObj + likeDict.end)
        );
    }

    /*
      -----------------------------拼装 分组筛选 循环条件 SQL（装载到实体）-------------------------------
     */

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
      -----------------------------拼装 分组筛选 循环条件 SQL（装载到键值对）-------------------------------
     */

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
      -----------------------------拼装 分组筛选 循环条件 SQL（装载到可变的字符序列）-------------------------------
     */

    /**
     * SQL示例：now() in ('2021-10-08 12:33:32', '2021-10-08 12:33:36')
     */
    public static void appendCircleFunc(final StringBuilder builder,
                                        final String func,
                                        final CircleDict circleDict,
                                        final Object... compareObjs) {
        appendCircleFunc(builder, false, func, circleDict, compareObjs);
    }

    /**
     * SQL示例：min(a.`age`) in (1, 2, 3)
     */
    public static void appendCircleFunc(final StringBuilder builder,
                                        final String func,
                                        final Object funcObj,
                                        final CircleDict circleDict,
                                        final Object... compareObjs) {
        appendCircleFunc(builder, false, func, funcObj, circleDict, compareObjs);
    }

    /**
     * SQL示例：ifnull(a.`first_name`, a.`last_name`) in ('TOM', "SALE")
     */
    public static void appendCircleFunc(final StringBuilder builder,
                                        final String func,
                                        final String separator,
                                        final Object[] funcObjs,
                                        final CircleDict circleDict,
                                        final Object... compareObjs) {
        appendCircleFunc(builder, false, func, separator, funcObjs, circleDict, compareObjs);
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
      -----------------------------拼装 分组筛选 循环条件 SQL（不进行装载，直接返回）-------------------------------
     */

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
                FuncUtil.splitMore(func, separator, false, funcObjs),
                FuncUtil.splitMore(circleDict.key, ",", false, compareObjs)
        );
    }

    /*
      -----------------------------装载 分组筛选 查询条件 SQL-------------------------------
     */

    public static <EO extends BaseViewEO> void addSQL(final EO conditionEO, final String havingSQL) {
        EmptyAssert.isNotNull(conditionEO);
        EmptyAssert.isNotBlank(havingSQL);

        List<String> havingSQLList = conditionEO.getHavingSQLList();
        if (EmptyValidate.isNull(havingSQLList)) {
            havingSQLList = new ArrayList<>();
            conditionEO.setHavingSQLList(havingSQLList);
        }
        if (!havingSQLList.contains(havingSQL)) {
            havingSQLList.add(havingSQL);
        }
    }

    public static <EO extends BaseViewEO> void addSQLList(final EO conditionEO, final List<String> havingSQLS) {
        EmptyAssert.isNotNull(conditionEO);
        EmptyAssert.isNotEmpty(havingSQLS);

        List<String> havingSQLList = conditionEO.getHavingSQLList();
        if (EmptyValidate.isNull(havingSQLList)) {
            conditionEO.setHavingSQLList(havingSQLS);
            return;
        }

        for (String havingSQL : havingSQLS) {
            if (!havingSQLList.contains(havingSQL)) {
                havingSQLList.add(havingSQL);
            }
        }
    }

    public static void addSQL(final Map<String, Object> conditionMap, final String havingSQL) {
        EmptyAssert.isNotNull(conditionMap);
        EmptyAssert.isNotBlank(havingSQL);

        @SuppressWarnings("unchecked")
        List<String> havingSQLList = MapConvertor.convertToFirstValue(
                conditionMap,
                SpecialKey.havingSQLList.name(),
                obj -> (List<String>) obj
        );
        if (EmptyValidate.isNull(havingSQLList)) {
            havingSQLList = new ArrayList<>();
            conditionMap.put(SpecialKey.havingSQLList.name(), havingSQLList);
        }
        if (!havingSQLList.contains(havingSQL)) {
            havingSQLList.add(havingSQL);
        }
    }

    public static void addSQLList(final Map<String, Object> conditionMap, final List<String> havingSQLS) {
        EmptyAssert.isNotNull(conditionMap);
        EmptyAssert.isNotEmpty(havingSQLS);

        @SuppressWarnings("unchecked")
        List<String> havingSQLList = MapConvertor.convertToFirstValue(
                conditionMap,
                SpecialKey.havingSQLList.name(),
                obj -> (List<String>) obj
        );
        if (EmptyValidate.isNull(havingSQLList)) {
            conditionMap.put(SpecialKey.havingSQLList.name(), havingSQLS);
            return;
        }

        for (String havingSQL : havingSQLS) {
            if (!havingSQLList.contains(havingSQL)) {
                havingSQLList.add(havingSQL);
            }
        }
    }
}
