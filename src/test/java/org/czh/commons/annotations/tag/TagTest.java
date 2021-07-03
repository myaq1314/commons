package org.czh.commons.annotations.tag;

import org.czh.commons.annotations.tag.example.TagTestEO;
import org.czh.commons.validate.FlagAssert;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @author : czh
 * description :
 * date : 2021-07-03
 * email 916419307@qq.com
 */
public class TagTest {

    /**
     * 标识注解，作用范围在源码，所以代码中找不到作用在属性上的标识注解
     */
    @Test
    public void test() {
        Class<TagTestEO> clazz = TagTestEO.class;
        Field[] ownFields = clazz.getDeclaredFields();

        boolean flag = false;
        for (Field ownField : ownFields) {
            //noinspection ReflectionForUnavailableAnnotation
            ChildLengthTag childLengthTag = ownField.getAnnotation(ChildLengthTag.class);
            if (childLengthTag != null) {
                flag = true;
                break;
            }
        }
        FlagAssert.isFalse(flag);
    }
}
