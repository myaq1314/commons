package org.czh.commons.utils.copy.example;

import org.czh.commons.utils.DateUtil;
import org.czh.commons.utils.copy.CopyUtil;

import java.math.BigDecimal;

/**
 * @author : czh
 * description :
 * date : 2021-06-28
 * email 916419307@qq.com
 */
public class ExampleCopyTest {

    public static void main(String[] args) {
        ExampleSourceEntity exampleSourceEntity1 = new ExampleSourceEntity(
                "exampleSourceEntityName",
                DateUtil.parseToDate("2021-07-21 15:17:35"),
                true,
                "12345",
                new BigDecimal("3.14"),
                'A',
                100L,
                (short) 23
        );
        ExampleTargetEntity exampleTargetEntity1 = CopyUtil.copyTo(exampleSourceEntity1, ExampleTargetEntity.class);
        ExampleSourceEntity exampleSourceEntity2 = CopyUtil.copyFrom(exampleTargetEntity1, ExampleSourceEntity.class);

        System.out.println(exampleSourceEntity1);
        System.out.println(exampleSourceEntity2);
        System.out.println(exampleTargetEntity1);


        ExampleTargetEntity exampleTargetEntity10 = new ExampleTargetEntity(
                "exampleTargetEntityName",
                "2021-07-21 15:17:46",
                true,
                12345,
                314L,
                'A',
                100,
                "23"
        );
        ExampleSourceEntity exampleSourceEntity10 = CopyUtil.copyFrom(exampleTargetEntity10, ExampleSourceEntity.class);
        ExampleTargetEntity exampleTargetEntity11 = CopyUtil.copyTo(exampleSourceEntity10, ExampleTargetEntity.class);
        System.out.println(exampleTargetEntity10);
        System.out.println(exampleTargetEntity11);
        System.out.println(exampleSourceEntity10);
    }

}
