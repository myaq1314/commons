package org.czh.commons.validate;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author : czh
 * description :
 * date : 2021-04-28
 * email 916419307@qq.com
 */
public class NumValidateTest {

    @Test
    public void test() {
        Assert.assertTrue(NumValidate.isNumber("1"));
        Assert.assertTrue(NumValidate.isNumber("1.1"));

        Assert.assertTrue(NumValidate.isInt("1"));
        Assert.assertTrue(NumValidate.isInt("2"));

        Assert.assertTrue(NumValidate.isDouble("1.1"));
        Assert.assertTrue(NumValidate.isDouble("2.2"));

        // 自然数 0 - 无穷大
        Assert.assertTrue(NumValidate.isNaturalInt(0));
        Assert.assertTrue(NumValidate.isNaturalInt(1));

        // 负整数 -1 - 无穷小
        Assert.assertTrue(NumValidate.isNegativeInt(-1));
        Assert.assertTrue(NumValidate.isNegativeInt(-2));

        // 正整数 1 - 无穷大
        Assert.assertTrue(NumValidate.isPositiveInt(1));
        Assert.assertTrue(NumValidate.isPositiveInt(2));

        // 自然数 0 - 无穷大
        Assert.assertTrue(NumValidate.isNaturalLong(0L));
        Assert.assertTrue(NumValidate.isNaturalLong(1L));

        // 负整数 -1 - 无穷小
        Assert.assertTrue(NumValidate.isNegativeLong(-1L));
        Assert.assertTrue(NumValidate.isNegativeLong(-2L));

        // 正整数 1 - 无穷大
        Assert.assertTrue(NumValidate.isPositiveLong(1L));
        Assert.assertTrue(NumValidate.isPositiveLong(2L));
    }
}
