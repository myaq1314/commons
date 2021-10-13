package org.czh.commons.utils.sql.base;

import org.czh.commons.entity.eo.BaseViewEO;
import org.czh.commons.enums.sql.SpecialKey;
import org.czh.commons.utils.convertor.MapConvertor;
import org.czh.commons.utils.sql.constant.SqlConstant;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;

import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021/9/29
 * email 916419307@qq.com
 */
@SuppressWarnings({"unused", "DuplicatedCode"})
public final class DistinctUtil {

    /*
      -----------------------------拼装 去重 SQL-------------------------------
     */

    /**
     * 拼装 去重 SQL（数据来源实体）
     *
     * @param conditionEO 数据实体格式，
     *                    distinctSQL属性，true 拼装；false 不拼装
     * @return 完整SQL：true时： DISTINCT ；false时：空字符串
     * @see org.czh.commons.entity.eo.BaseViewEO.distinctSQL
     */
    public static <EO extends BaseViewEO> String split(final EO conditionEO) {
        EmptyAssert.isNotNull(conditionEO);
        return split(conditionEO.isDistinctSQL());
    }

    /**
     * 拼装 去重 SQL（数据来源Map）
     *
     * @param conditionMap Map格式，distinctSQL Key，true 拼装；false 不拼装
     * @return 完整SQL：true时： DISTINCT ；false时：空字符串
     * @see org.czh.commons.enums.sql.SpecialKey.distinctSQL 特殊Key值
     */
    public static String split(final Map<String, Object> conditionMap) {
        EmptyAssert.isNotNull(conditionMap);
        Boolean distinct = MapConvertor.convertToFirstValue(conditionMap,
                                                            SpecialKey.distinctSQL.name(),
                                                            obj -> (Boolean) obj);
        return split(distinct);
    }

    /**
     * 拼装 去重 SQL（数据来源布尔类型）
     *
     * @param distinct 是否拼装数据去重 状态位，true 拼装；false 不拼装
     * @return 完整SQL：true时： DISTINCT ；false时：空字符串
     */
    public static <EO extends BaseViewEO> String split(final Boolean distinct) {
        return EmptyValidate.isNotNull(distinct) && distinct ? SqlConstant.DISTINCT_SQL : SqlConstant.EMPTY_STRING;
    }

    /*
      -----------------------------追加 去重 SQL-------------------------------
     */

    /**
     * 拼装 去重 SQL（数据来源实体）
     *
     * @param conditionEO 数据实体格式，
     *                    distinctSQL属性，true 拼装；false 不拼装
     * @param builder     可变的字符序列，追加结果：完整SQL：true时： DISTINCT ；false时：空字符串
     * @see org.czh.commons.entity.eo.BaseViewEO.distinctSQL
     */
    public static <EO extends BaseViewEO> void append(final EO conditionEO, final StringBuilder builder) {
        EmptyAssert.isNotNull(conditionEO);
        append(conditionEO.isDistinctSQL(), builder);
    }

    /**
     * 拼装 去重 SQL（数据来源Map）
     *
     * @param conditionMap Map格式，distinctSQL Key，true 拼装；false 不拼装
     * @param builder      可变的字符序列，追加结果：完整SQL：true时： DISTINCT ；false时：空字符串
     * @see org.czh.commons.enums.sql.SpecialKey.distinctSQL 特殊Key值
     */
    public static void append(final Map<String, Object> conditionMap, final StringBuilder builder) {
        EmptyAssert.isNotNull(conditionMap);

        Boolean distinct = MapConvertor
                .convertToFirstValue(conditionMap, SpecialKey.distinctSQL.name(), obj -> (Boolean) obj);
        append(distinct, builder);
    }

    /**
     * 追加 去重 SQL（数据来源布尔类型）
     *
     * @param distinct 是否拼装数据去重 状态位，true 拼装；false 不拼装
     * @param builder  可变的字符序列，追加结果：完整SQL：true时： DISTINCT ；false时：空字符串
     */
    public static <EO extends BaseViewEO> void append(final Boolean distinct, final StringBuilder builder) {
        EmptyAssert.isNotNull(builder);
        builder.append(split(distinct));
    }
}
