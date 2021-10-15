package org.czh.commons.utils.sql.base;

import org.czh.commons.entity.eo.BaseViewEO;
import org.czh.commons.enums.sql.SpecialKey;
import org.czh.commons_core.asserts.EmptyAssert;

import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021/10/8
 * email 916419307@qq.com
 */
public final class LimitUtil {

    /*
      -----------------------------拼装 分页 SQL（装载到实体）-------------------------------
     */

    public static <EO extends BaseViewEO> void set(final EO conditionEO, final Integer pageSize) {
        setSQL(conditionEO, split(pageSize));
    }

    public static <EO extends BaseViewEO> void set(final EO conditionEO,
                                                   final Integer currentPage,
                                                   final Integer pageSize) {
        setSQL(conditionEO, split(currentPage, pageSize));
    }

    /*
      -----------------------------拼装 分页 SQL（装载到键值对）-------------------------------
     */

    public static void set(final Map<String, Object> conditionMap, final Integer pageSize) {
        setSQL(conditionMap, split(pageSize));
    }

    public static void set(final Map<String, Object> conditionMap,
                           final Integer currentPage,
                           final Integer pageSize) {
        setSQL(conditionMap, split(currentPage, pageSize));
    }

    /*
      -----------------------------拼装 分页 SQL（装载到可变的字符序列）-------------------------------
     */

    public static void append(final StringBuilder builder, final Integer pageSize) {
        append(builder, 1, pageSize);
    }

    public static void append(final StringBuilder builder,
                              final Integer currentPage,
                              final Integer pageSize) {
        EmptyAssert.isNotNull(builder);
        builder.append(split(currentPage, pageSize));
    }

    /*
      -----------------------------拼装 分页 SQL（不进行装载，直接返回）-------------------------------
     */

    public static String split(final Integer pageSize) {
        return split(1, pageSize);
    }

    public static String split(final Integer currentPage, final Integer pageSize) {
        EmptyAssert.isNotNull(currentPage);
        EmptyAssert.isNotNull(pageSize);
        return " " + (currentPage - 1) * pageSize + ", " + pageSize + " ";
    }

    /*
      -----------------------------装载 分页 SQL-------------------------------
     */

    public static <EO extends BaseViewEO> void setSQL(final EO conditionEO, final String limitSQL) {
        EmptyAssert.isNotNull(conditionEO);
        EmptyAssert.isNotBlank(limitSQL);
        conditionEO.setLimitSQL(limitSQL);
    }

    public static void setSQL(final Map<String, Object> conditionMap, final String limitSQL) {
        EmptyAssert.isNotNull(conditionMap);
        EmptyAssert.isNotBlank(limitSQL);
        conditionMap.put(SpecialKey.limitSQL.name(), limitSQL);
    }
}
