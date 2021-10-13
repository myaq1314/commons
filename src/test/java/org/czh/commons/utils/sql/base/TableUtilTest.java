package org.czh.commons.utils.sql.base;

import org.czh.commons.enums.sql.SpecialKey;
import org.czh.commons.validate.EqualsAssert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021/10/11
 * email : 916419307@qq.com
 */
public final class TableUtilTest {

    @Test
    public void splitEOTest() {
        String compareSQL = " `tb_example` ";
        String compareDefaultAliasSQL = " `tb_example` AS a ";
        String compareAliasSQL = " `tb_example` AS abc ";

        String compare2021SQL = " `tb_example_2021` ";
        String compareDefaultAlias2021SQL = " `tb_example_2021` AS a ";
        String compareAlias2021SQL = " `tb_example_2021` AS abc ";

        TbExampleEO conditionEO = new TbExampleEO();
        String tableNameSQL = TableUtil.split(conditionEO);
        EqualsAssert.isEquals(compareSQL, tableNameSQL);

        tableNameSQL = TableUtil.splitDefaultAlias(conditionEO);
        EqualsAssert.isEquals(compareDefaultAliasSQL, tableNameSQL);

        tableNameSQL = TableUtil.splitAlias(conditionEO, "abc");
        EqualsAssert.isEquals(compareAliasSQL, tableNameSQL);

        conditionEO.setTableNameSQL("tb_example_2021");
        tableNameSQL = TableUtil.split(conditionEO);
        EqualsAssert.isEquals(compare2021SQL, tableNameSQL);

        tableNameSQL = TableUtil.splitDefaultAlias(conditionEO);
        EqualsAssert.isEquals(compareDefaultAlias2021SQL, tableNameSQL);

        tableNameSQL = TableUtil.splitAlias(conditionEO, "abc");
        EqualsAssert.isEquals(compareAlias2021SQL, tableNameSQL);
    }

    @Test
    public void appendEOTest() {
        String compareSQL = " `tb_example` ";
        String compareDefaultAliasSQL = " `tb_example` AS a ";
        String compareAliasSQL = " `tb_example` AS abc ";

        String compare2021SQL = " `tb_example_2021` ";
        String compareDefaultAlias2021SQL = " `tb_example_2021` AS a ";
        String compareAlias2021SQL = " `tb_example_2021` AS abc ";

        StringBuilder builder = new StringBuilder();
        TbExampleEO conditionEO = new TbExampleEO();
        TableUtil.append(conditionEO, builder);
        EqualsAssert.isEquals(compareSQL, builder.toString());

        builder.delete(0, builder.length());
        TableUtil.appendDefaultAlias(conditionEO, builder);
        EqualsAssert.isEquals(compareDefaultAliasSQL, builder.toString());

        builder.delete(0, builder.length());
        TableUtil.appendAlias(conditionEO, "abc", builder);
        EqualsAssert.isEquals(compareAliasSQL, builder.toString());

        builder.delete(0, builder.length());
        conditionEO.setTableNameSQL("tb_example_2021");
        TableUtil.append(conditionEO, builder);
        EqualsAssert.isEquals(compare2021SQL, builder.toString());

        builder.delete(0, builder.length());
        TableUtil.appendDefaultAlias(conditionEO, builder);
        EqualsAssert.isEquals(compareDefaultAlias2021SQL, builder.toString());

        builder.delete(0, builder.length());
        TableUtil.appendAlias(conditionEO, "abc", builder);
        EqualsAssert.isEquals(compareAlias2021SQL, builder.toString());
    }

    @Test
    public void splitMapTest() {
        String compareSQL = " `tb_example` ";
        String compareDefaultAliasSQL = " `tb_example` AS a ";
        String compareAliasSQL = " `tb_example` AS abc ";

        Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put(SpecialKey.tableNameSQL.name(), "tb_example");
        String tableNameSQL = TableUtil.split(conditionMap);
        EqualsAssert.isEquals(compareSQL, tableNameSQL);

        tableNameSQL = TableUtil.splitDefaultAlias(conditionMap);
        EqualsAssert.isEquals(compareDefaultAliasSQL, tableNameSQL);

        tableNameSQL = TableUtil.splitAlias(conditionMap, "abc");
        EqualsAssert.isEquals(compareAliasSQL, tableNameSQL);
    }

    @Test
    public void appendMapTest() {
        String compareSQL = " `tb_example` ";
        String compareDefaultAliasSQL = " `tb_example` AS a ";
        String compareAliasSQL = " `tb_example` AS abc ";

        StringBuilder builder = new StringBuilder();
        Map<String, Object> conditionMap = new HashMap<>();
        TableUtil.append(conditionMap, builder);
        EqualsAssert.isEquals(compareSQL, builder.toString());

        builder.delete(0, builder.length());
        TableUtil.appendDefaultAlias(conditionMap, builder);
        EqualsAssert.isEquals(compareDefaultAliasSQL, builder.toString());

        builder.delete(0, builder.length());
        TableUtil.appendAlias(conditionMap, "abc", builder);
        EqualsAssert.isEquals(compareAliasSQL, builder.toString());
    }

    @Test
    public void splitTableNameTest() {
        String compareSQL = " `tb_example` ";
        String compareDefaultAliasSQL = " `tb_example` AS a ";
        String compareAliasSQL = " `tb_example` AS abc ";

        String tableName = "tb_example";
        String tableNameSQL = TableUtil.split(tableName);
        EqualsAssert.isEquals(compareSQL, tableNameSQL);

        tableNameSQL = TableUtil.splitDefaultAlias(tableName);
        EqualsAssert.isEquals(compareDefaultAliasSQL, tableNameSQL);

        tableNameSQL = TableUtil.splitAlias(tableName, "abc");
        EqualsAssert.isEquals(compareAliasSQL, tableNameSQL);
    }

    @Test
    public void appendTableNameTest() {
        String compareSQL = " `tb_example` ";
        String compareDefaultAliasSQL = " `tb_example` AS a ";
        String compareAliasSQL = " `tb_example` AS abc ";

        StringBuilder builder = new StringBuilder();
        String tableName = "tb_example";
        TableUtil.append(tableName, builder);
        EqualsAssert.isEquals(compareSQL, builder.toString());

        builder.delete(0, builder.length());
        TableUtil.appendDefaultAlias(tableName, builder);
        EqualsAssert.isEquals(compareDefaultAliasSQL, builder.toString());

        builder.delete(0, builder.length());
        TableUtil.appendAlias(tableName, "abc", builder);
        EqualsAssert.isEquals(compareAliasSQL, builder.toString());
    }
}
