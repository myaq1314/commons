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
        ExampleSourceEntity exampleSourceEntity = new ExampleSourceEntity(
                "exampleSourceEntityName",
                DateUtil.getNowDate(),
                true,
                "12345",
                new BigDecimal("3.14"),
                'A',
                100L,
                (short) 23
        );
        ExampleTargetEntity exampleTargetEntity = CopyUtil.copy(exampleSourceEntity, ExampleTargetEntity.class);
        // ExampleSourceEntity(exampleSourceEntityName=exampleSourceEntityName, createDate=Tue Jun 29 00:13:42 CST 2021, hidden=true, exampleNum=12345, yuan=3.14, character=A, id=100, age=23)
        System.out.println(exampleSourceEntity);
        // ExampleTargetEntity(exampleTargetEntityName=exampleSourceEntityName, createDateTxt=2021-06-29 00:13:42, hidden=null, exampleNum=12345, fen=314, character=A, id=null, age=23)
        System.out.println(exampleTargetEntity);
    }

}
