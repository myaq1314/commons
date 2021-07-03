package org.czh.commons.enums.parent;

import org.czh.commons.enums.example.ExampleBaseEnum;
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
        System.out.println(ExampleBaseEnum.NONE);
        System.out.println(ExampleBaseEnum.NONE.getName());
        System.out.println(ExampleBaseEnum.NONE.name());

        Assert.assertEquals(
                "getName() show equals name()",
                ExampleBaseEnum.NONE.getName(),
                ExampleBaseEnum.NONE.name()
        );
    }
}
