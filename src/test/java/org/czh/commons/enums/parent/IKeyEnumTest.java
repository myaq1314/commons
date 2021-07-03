package org.czh.commons.enums.parent;

import org.czh.commons.enums.example.ExampleKeyEnum;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author : czh
 * description :
 * date : 2021-04-28
 * email 916419307@qq.com
 */
public class IKeyEnumTest {

    @Test
    public void test() {
        System.out.println(ExampleKeyEnum.NONE.getKey());
        System.out.println(ExampleKeyEnum.NONE.key);

        Assert.assertEquals(
                "getKey() show equals key field",
                ExampleKeyEnum.NONE.getKey(),
                ExampleKeyEnum.NONE.key
        );
    }
}
