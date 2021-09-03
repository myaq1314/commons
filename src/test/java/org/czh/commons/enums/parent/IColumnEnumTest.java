package org.czh.commons.enums.parent;

import org.czh.commons.enums.example.ExampleColumnEnum;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author : czh
 * description :
 * date : 2021-04-28
 * email 916419307@qq.com
 */
public class IColumnEnumTest {

    @Test
    public void test() {
        Assert.assertEquals(
                "getColumn() show equals column field",
                ExampleColumnEnum.ID.getColumn(),
                ExampleColumnEnum.ID.column
        );

        Assert.assertEquals(
                "getField() show equals field field",
                ExampleColumnEnum.ID.getField(),
                ExampleColumnEnum.ID.field
        );

        Assert.assertEquals(
                "getType() show equals type field",
                ExampleColumnEnum.ID.getType(),
                ExampleColumnEnum.ID.type
        );
    }
}
