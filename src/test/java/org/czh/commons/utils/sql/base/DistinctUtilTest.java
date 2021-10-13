package org.czh.commons.utils.sql.base;

import org.czh.commons.enums.sql.SpecialKey;
import org.czh.commons.utils.sql.constant.SqlConstant;
import org.czh.commons.validate.EqualsAssert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021/10/8
 * email 916419307@qq.com
 */
public final class DistinctUtilTest {

    @Test
    public void splitTest() {
        TbExampleEO conditionEO = new TbExampleEO();
        String distinctSQL = DistinctUtil.split(conditionEO);
        EqualsAssert.isEquals(SqlConstant.EMPTY_STRING, distinctSQL);

        Map<String, Object> conditionMap = new HashMap<>();
        distinctSQL = DistinctUtil.split(conditionMap);
        EqualsAssert.isEquals(SqlConstant.EMPTY_STRING, distinctSQL);

        distinctSQL = DistinctUtil.split((Boolean) null);
        EqualsAssert.isEquals(SqlConstant.EMPTY_STRING, distinctSQL);

        distinctSQL = DistinctUtil.split(Boolean.FALSE);
        EqualsAssert.isEquals(SqlConstant.EMPTY_STRING, distinctSQL);

        conditionEO.setDistinctSQL(true);
        distinctSQL = DistinctUtil.split(conditionEO);
        EqualsAssert.isEquals(SqlConstant.DISTINCT_SQL, distinctSQL);

        conditionMap.put(SpecialKey.distinctSQL.name(), true);
        distinctSQL = DistinctUtil.split(conditionMap);
        EqualsAssert.isEquals(SqlConstant.DISTINCT_SQL, distinctSQL);

        distinctSQL = DistinctUtil.split(Boolean.TRUE);
        EqualsAssert.isEquals(SqlConstant.DISTINCT_SQL, distinctSQL);
    }

    @Test
    public void appendTest() {
        StringBuilder builder = new StringBuilder();

        TbExampleEO conditionEO = new TbExampleEO();
        DistinctUtil.append(conditionEO, builder);
        EqualsAssert.isEquals(SqlConstant.EMPTY_STRING, builder.toString());

        builder.delete(0, builder.length());
        Map<String, Object> conditionMap = new HashMap<>();
        DistinctUtil.append(conditionMap, builder);
        EqualsAssert.isEquals(SqlConstant.EMPTY_STRING, builder.toString());

        builder.delete(0, builder.length());
        DistinctUtil.append((Boolean) null, builder);
        EqualsAssert.isEquals(SqlConstant.EMPTY_STRING, builder.toString());

        builder.delete(0, builder.length());
        DistinctUtil.append(Boolean.FALSE, builder);
        EqualsAssert.isEquals(SqlConstant.EMPTY_STRING, builder.toString());

        builder.delete(0, builder.length());
        conditionEO.setDistinctSQL(true);
        DistinctUtil.append(conditionEO, builder);
        EqualsAssert.isEquals(SqlConstant.DISTINCT_SQL, builder.toString());

        builder.delete(0, builder.length());
        conditionMap.put(SpecialKey.distinctSQL.name(), true);
        DistinctUtil.append(conditionMap, builder);
        EqualsAssert.isEquals(SqlConstant.DISTINCT_SQL, builder.toString());

        builder.delete(0, builder.length());
        DistinctUtil.append(Boolean.TRUE, builder);
        EqualsAssert.isEquals(SqlConstant.DISTINCT_SQL, builder.toString());
    }
}
