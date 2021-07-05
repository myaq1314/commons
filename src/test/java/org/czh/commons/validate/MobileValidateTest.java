package org.czh.commons.validate;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author : czh
 * description :
 * date : 2021-04-28
 * email 916419307@qq.com
 */
public class MobileValidateTest {

    @Test
    public void test() {
        Assert.assertTrue(MobileValidate.isMobile("18354171596"));
        Assert.assertFalse(MobileValidate.isMobile("1835417159"));
    }
}
