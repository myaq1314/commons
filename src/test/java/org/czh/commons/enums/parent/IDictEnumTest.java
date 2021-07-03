package org.czh.commons.enums.parent;

import org.czh.commons.enums.example.ExampleDictEnum;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author : czh
 * description :
 * date : 2021-04-28
 * email 916419307@qq.com
 */
public class IDictEnumTest {

    @Test
    public void test() {
        System.out.println(ExampleDictEnum.NAME.getValue());
        System.out.println(ExampleDictEnum.NAME.value);

        Assert.assertEquals(
                "getValue() show equals value field",
                ExampleDictEnum.NAME.getValue(),
                ExampleDictEnum.NAME.value
        );
    }
}
