package org.czh.commons.enums.parent;

import org.czh.commons.enums.example.ExampleBaseEnum;
import org.czh.commons.validate.EqualsAssert;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author : czh
 * description :
 * date : 2021-04-28
 * email 916419307@qq.com
 */
public class IBaseEnumTest {

    @Test
    public void test() {
        Assert.assertEquals(
                "getName() show equals name()",
                ExampleBaseEnum.NONE.getName(),
                ExampleBaseEnum.NONE.name()
        );
        EqualsAssert.isEquals(ExampleBaseEnum.NONE.getName(), "NONE");
    }
}
