package org.czh.commons.validate;

import org.czh.commons.enums.ConvertorTestDict;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author : czh
 * description :
 * date : 2021-04-29
 * email 916419307@qq.com
 */
public class EnumValidateTest {

    @Test
    public void test() {
        Assert.assertTrue(EnumValidate.isEnum(ConvertorTestDict.class));
        Assert.assertFalse(EnumValidate.isNotEnum(ConvertorTestDict.class));
    }
}
