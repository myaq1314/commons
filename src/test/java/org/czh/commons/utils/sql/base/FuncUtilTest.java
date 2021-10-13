package org.czh.commons.utils.sql.base;

import org.czh.commons.utils.sql.constant.SqlConstant;
import org.czh.commons.validate.EqualsAssert;
import org.junit.Test;

/**
 * @author : czh
 * description :
 * date : 2021/10/8
 * email 916419307@qq.com
 */
public final class FuncUtilTest {

    @Test
    public void splitTest() {
        String text = FuncUtil.split("now");
        EqualsAssert.isEquals(" now() ", text);

        text = FuncUtil.split("pi");
        EqualsAssert.isEquals(" pi() ", text);

        text = FuncUtil.splitOne("min", SqlConstant.HAS_TABLE_ALIAS_FLAG, TbExampleEnum.AGE);
        EqualsAssert.isEquals(" min( `a`.`age` ) ", text);

        text = FuncUtil.splitOne("count", true, 1);
        EqualsAssert.isEquals(" count( 1 ) ", text);

        text = FuncUtil.splitMore("group_concat", ",", SqlConstant.HAS_TABLE_ALIAS_FLAG, TbExampleEnum.FIRST_NAME, "-");
        EqualsAssert.isEquals(" concat( `a`.`first_name` , '-') ", text);

        text = FuncUtil.splitMore("substr", ",", SqlConstant.HAS_TABLE_ALIAS_FLAG, TbExampleEnum.FIRST_NAME, 0, 1);
        EqualsAssert.isEquals(" substr( `a`.`first_name` , 0 , 1 ) ", text);
    }

    @Test
    public void splitSelectTest() {
        String text = FuncUtil.splitSelectAlias("now", "date");
        EqualsAssert.isEquals(" now() AS `date` ", text);

        text = FuncUtil.splitSelectAlias("pi", "pi");
        EqualsAssert.isEquals(" pi() AS `pi` ", text);

        text = FuncUtil.splitSelectAliasOne("min", "age", SqlConstant.HAS_TABLE_ALIAS_FLAG, TbExampleEnum.AGE);
        EqualsAssert.isEquals(" min( `a`.`age` AS `age` ) ", text);

        text = FuncUtil.splitSelectAliasOne("count", "count", true, 1);
        EqualsAssert.isEquals(" count( 1 ) AS `count` ", text);

        text = FuncUtil.splitSelectAliasMore("group_concat",
                                             "first_name",
                                             ",",
                                             SqlConstant.HAS_TABLE_ALIAS_FLAG,
                                             TbExampleEnum.FIRST_NAME,
                                             "-"
        );
        EqualsAssert.isEquals(" concat( `a`.`first_name` , '-') AS `first_name` ", text);

        text = FuncUtil.splitSelectAliasMore("substr",
                                             "first_name",
                                             ",",
                                             SqlConstant.HAS_TABLE_ALIAS_FLAG,
                                             TbExampleEnum.FIRST_NAME,
                                             0,
                                             1
        );
        EqualsAssert.isEquals(" substr( `a`.`first_name` , 0 , 1 ) AS `first_name` ", text);
    }

}
