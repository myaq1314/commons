package org.czh.commons.validate;

import org.junit.Test;

/**
 * @author : czh
 * description :
 * date : 2021-04-28
 * email 916419307@qq.com
 */
public class NumAssertTest {

    @Test
    public void test() {
        NumAssert.isNumber("1");
        NumAssert.isNumber("1.1");

        NumAssert.isInt("1");
        NumAssert.isInt("2");

        NumAssert.isDouble("1.1");
        NumAssert.isDouble("2.2");

        // 自然数 0 - 无穷大
        NumAssert.isNaturalInt(0);
        NumAssert.isNaturalInt(1);

        // 负整数 -1 - 无穷小
        NumAssert.isNegativeInt(-1);
        NumAssert.isNegativeInt(-2);

        // 正整数 1 - 无穷大
        NumAssert.isPositiveInt(1);
        NumAssert.isPositiveInt(2);

        // 自然数 0 - 无穷大
        NumAssert.isNaturalLong(0L);
        NumAssert.isNaturalLong(1L);

        // 负整数 -1 - 无穷小
        NumAssert.isNegativeLong(-1L);
        NumAssert.isNegativeLong(-2L);

        // 正整数 1 - 无穷大
        NumAssert.isPositiveLong(1L);
        NumAssert.isPositiveLong(2L);
    }

}
