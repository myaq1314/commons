package org.czh.commons.utils.sql.base;

import org.czh.commons.utils.sql.eo.ColumnEO;
import org.czh.commons.validate.EqualsAssert;
import org.junit.Test;

/**
 * @author : czh
 * description :
 * date : 2021/10/8
 * email 916419307@qq.com
 */
public final class ColumnUtilTest {

    @Test
    public void splitColumnEnumTest() {
        String compareSQL = " `first_name` ";
        String compareDefaultAliasSQL = " a.`first_name` ";
        String compareAliasSQL = " abc.`first_name` ";

        String columnSQL = ColumnUtil.split(TbExampleEnum.FIRST_NAME);
        EqualsAssert.isEquals(compareSQL, columnSQL);

        columnSQL = ColumnUtil.splitDefaultAlias(TbExampleEnum.FIRST_NAME);
        EqualsAssert.isEquals(compareDefaultAliasSQL, columnSQL);

        columnSQL = ColumnUtil.splitAlias(TbExampleEnum.FIRST_NAME, "abc");
        EqualsAssert.isEquals(compareAliasSQL, columnSQL);
    }

    @Test
    public void splitColumnEOTest() {
        String compareSQL = " `first_name` ";
        String compareDefaultAliasSQL = " a.`first_name` ";
        String compareAliasSQL = " abc.`first_name` ";

        String columnSQL = ColumnUtil.split(ColumnEO.one("first_name"));
        EqualsAssert.isEquals(compareSQL, columnSQL);

        columnSQL = ColumnUtil.splitDefaultAlias(ColumnEO.one("first_name"));
        EqualsAssert.isEquals(compareDefaultAliasSQL, columnSQL);

        columnSQL = ColumnUtil.splitAlias(ColumnEO.one("first_name"), "abc");
        EqualsAssert.isEquals(compareAliasSQL, columnSQL);
    }

    @Test
    public void splitColumnNameTest() {
        String compareSQL = " `first_name` ";
        String compareDefaultAliasSQL = " a.`first_name` ";
        String compareAliasSQL = " abc.`first_name` ";

        String columnSQL = ColumnUtil.split("first_name");
        EqualsAssert.isEquals(compareSQL, columnSQL);

        columnSQL = ColumnUtil.splitDefaultAlias("first_name");
        EqualsAssert.isEquals(compareDefaultAliasSQL, columnSQL);

        columnSQL = ColumnUtil.splitAlias("first_name", "abc");
        EqualsAssert.isEquals(compareAliasSQL, columnSQL);
    }

    @Test
    public void appendColumnEnumTest() {
        String compareSQL = " `first_name` ";
        String compareDefaultAliasSQL = " a.`first_name` ";
        String compareAliasSQL = " abc.`first_name` ";

        StringBuilder builder = new StringBuilder();
        ColumnUtil.append(TbExampleEnum.FIRST_NAME, builder);
        EqualsAssert.isEquals(compareSQL, builder.toString());

        builder.delete(0, builder.length());
        ColumnUtil.appendDefaultAlias(TbExampleEnum.FIRST_NAME, builder);
        EqualsAssert.isEquals(compareDefaultAliasSQL, builder.toString());

        builder.delete(0, builder.length());
        ColumnUtil.appendAlias(TbExampleEnum.FIRST_NAME, "abc", builder);
        EqualsAssert.isEquals(compareAliasSQL, builder.toString());
    }

    @Test
    public void appendColumnEOTest() {
        String compareSQL = " `first_name` ";
        String compareDefaultAliasSQL = " a.`first_name` ";
        String compareAliasSQL = " abc.`first_name` ";

        StringBuilder builder = new StringBuilder();
        ColumnUtil.append(ColumnEO.one("first_name"), builder);
        EqualsAssert.isEquals(compareSQL, builder.toString());

        builder.delete(0, builder.length());
        ColumnUtil.appendDefaultAlias(ColumnEO.one("first_name"), builder);
        EqualsAssert.isEquals(compareDefaultAliasSQL, builder.toString());

        builder.delete(0, builder.length());
        ColumnUtil.appendAlias(ColumnEO.one("first_name"), "abc", builder);
        EqualsAssert.isEquals(compareAliasSQL, builder.toString());
    }

    @Test
    public void appendColumnNameTest() {
        String compareSQL = " `first_name` ";
        String compareDefaultAliasSQL = " a.`first_name` ";
        String compareAliasSQL = " abc.`first_name` ";

        StringBuilder builder = new StringBuilder();
        ColumnUtil.append("first_name", builder);
        EqualsAssert.isEquals(compareSQL, builder.toString());

        builder.delete(0, builder.length());
        ColumnUtil.appendDefaultAlias("first_name", builder);
        EqualsAssert.isEquals(compareDefaultAliasSQL, builder.toString());

        builder.delete(0, builder.length());
        ColumnUtil.appendAlias("first_name", "abc", builder);
        EqualsAssert.isEquals(compareAliasSQL, builder.toString());
    }
}
