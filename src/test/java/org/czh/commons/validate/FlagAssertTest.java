package org.czh.commons.validate;

import org.junit.Test;

/**
 * @author : czh
 * description :
 * date : 2021-04-28
 * email 916419307@qq.com
 */
@SuppressWarnings("all")
public class FlagAssertTest {

    @Test
    public void test() {
        FlagAssert.isTrue(1 == 1);
        FlagAssert.isFalse(1 == 2);
        FlagAssert.isTrue(Boolean.TRUE);
        FlagAssert.isFalse(Boolean.FALSE);
    }
}
