package org.czh.commons.utils.sql.base;

import org.czh.commons.constant.DateConstant;
import org.czh.commons.utils.DateUtil;
import org.czh.commons.utils.sql.eo.ColumnEO;
import org.czh.commons.validate.EqualsAssert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author : czh
 * description :
 * date : 2021/10/13
 * email : 916419307@qq.com
 */
public final class ValueUtilTest {

    @Test
    public void splitObjTest() {
        String text = ValueUtil.splitAlias(TbExampleEnum.FIRST_NAME);
        EqualsAssert.isEquals(" a.`first_name` ", text);

        ColumnEO columnEO = ColumnEO.one("first_name");
        text = ValueUtil.splitAlias(columnEO);
        EqualsAssert.isEquals(" a.`first_name` ", text);

        text = ValueUtil.splitNoAlias(TbExampleEnum.FIRST_NAME);
        EqualsAssert.isEquals(" `first_name` ", text);

        text = ValueUtil.splitNoAlias(columnEO);
        EqualsAssert.isEquals(" `first_name` ", text);

        text = ValueUtil.splitAlias(1);
        EqualsAssert.isEquals(" 1 ", text);

        //noinspection UnnecessaryBoxing
        text = ValueUtil.splitAlias(Integer.valueOf(1));
        EqualsAssert.isEquals(" 1 ", text);

        text = ValueUtil.splitAlias(1L);
        EqualsAssert.isEquals(" 1 ", text);

        //noinspection UnnecessaryBoxing
        text = ValueUtil.splitAlias(Long.valueOf(1L));
        EqualsAssert.isEquals(" 1 ", text);

        text = ValueUtil.splitAlias((short) 1);
        EqualsAssert.isEquals(" 1 ", text);

        //noinspection UnnecessaryBoxing
        text = ValueUtil.splitAlias(Short.valueOf((short) 1));
        EqualsAssert.isEquals(" 1 ", text);

        text = ValueUtil.splitAlias((byte) 1);
        EqualsAssert.isEquals(" 1 ", text);

        //noinspection UnnecessaryBoxing
        text = ValueUtil.splitAlias(Byte.valueOf((byte) 1));
        EqualsAssert.isEquals(" 1 ", text);

        text = ValueUtil.splitAlias(1d);
        EqualsAssert.isEquals(" 1.0 ", text);

        text = ValueUtil.splitAlias(1.0d);
        EqualsAssert.isEquals(" 1.0 ", text);

        text = ValueUtil.splitAlias(1F);
        EqualsAssert.isEquals(" 1.0 ", text);

        text = ValueUtil.splitAlias(1.0F);
        EqualsAssert.isEquals(" 1.0 ", text);

        text = ValueUtil.splitAlias(new BigDecimal(1));
        EqualsAssert.isEquals(" 1 ", text);

        text = ValueUtil.splitAlias(new BigDecimal("1.0"));
        EqualsAssert.isEquals(" 1.0 ", text);

        text = ValueUtil.splitAlias(true);
        EqualsAssert.isEquals(" 1 ", text);

        text = ValueUtil.splitAlias(false);
        EqualsAssert.isEquals(" 0 ", text);

        text = ValueUtil.splitAlias("123");
        EqualsAssert.isEquals(" '123' ", text);

        StringBuilder builder = new StringBuilder();
        builder.append(" 123 ");
        text = ValueUtil.splitAlias(builder);
        EqualsAssert.isEquals(" ' 123 ' ", text);

        StringBuffer buffer = new StringBuffer();
        buffer.append(" 123 ");
        text = ValueUtil.splitAlias(buffer);
        EqualsAssert.isEquals(" ' 123 ' ", text);

        text = ValueUtil.splitAlias('c');
        EqualsAssert.isEquals(" 'c' ", text);

        //noinspection UnnecessaryBoxing
        text = ValueUtil.splitAlias(Character.valueOf('c'));
        EqualsAssert.isEquals(" 'c' ", text);

        String dateText = "2021-10-08 18:41:45";
        text = ValueUtil.splitAlias(DateUtil.parseToDate(dateText, DateConstant.DATETIME_STANDARD()));
        EqualsAssert.isEquals(" '" + dateText + "' ", text);

        dateText = "2021-10-08";
        text = ValueUtil.splitAlias(DateUtil.parseToLDate(dateText, DateConstant.DATE_STANDARD()));
        EqualsAssert.isEquals(" '" + dateText + "' ", text);

        dateText = "2021-10-08 18:41:45";
        text = ValueUtil.splitAlias(DateUtil.parseToLDTime(dateText, DateConstant.DATETIME_STANDARD()));
        EqualsAssert.isEquals(" '" + dateText + "' ", text);

        dateText = "18:41:45";
        text = ValueUtil.splitAlias(DateUtil.parseToLTime(dateText, DateConstant.TIME_STANDARD()));
        EqualsAssert.isEquals(" '" + dateText + "' ", text);
    }
}
