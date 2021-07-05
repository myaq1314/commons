package org.czh.commons.validate;

import org.czh.commons.enums.ConvertorTestDict;
import org.junit.Test;

/**
 * @author : czh
 * description :
 * date : 2021-04-29
 * email 916419307@qq.com
 */
public class EnumAssertTest {

    @Test
    public void test() {
        EnumAssert.isEnum(ConvertorTestDict.class);
        EnumAssert.isNotEnum(Object.class);
    }
}
