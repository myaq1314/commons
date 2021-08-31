package org.czh.commons.entity.eo;

import org.czh.commons.entity.eo.example.BaseCommonTestEO;
import org.czh.commons.entity.eo.example.BaseCommonTestEnum;
import org.czh.commons.entity.eo.sql.InsertEO;
import org.czh.commons.enums.sql.LikeDict;
import org.czh.commons.utils.RandomUtil;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author : czh
 * description :
 * date : 2021-07-03
 * email 916419307@qq.com
 */
public class BaseCommonEOTest {

    @Test
    public void test() {
        BaseCommonEO conditionEO = new BaseCommonTestEO();
        conditionEO.addWhereLike(BaseCommonTestEnum.STUDENT_NAME, LikeDict.LIKE, "宋晓佳");
        StringBuilder deleteBuilder = new StringBuilder(" delete from student as a where 1 = 1 ");
        for (String whereSQL : conditionEO.getWhereSQLList()) {
            deleteBuilder.append(" and ")
                    .append(whereSQL);
        }
        deleteBuilder.append(" ; ");
        // delete from student as a where 1 = 1  and   a.`student_name`  LIKE  '%宋晓佳%'
        System.out.println(deleteBuilder.toString());

        BaseCommonTestEO entity = new BaseCommonTestEO();
        entity.addInsert(BaseCommonTestEnum.STUDENT_NAME, "宋晓佳")
                .addInsert(BaseCommonTestEnum.GRADE, 1)
                .addInsert(BaseCommonTestEnum.SCORE, new BigDecimal(RandomUtil.getRandom(60, 100)))
                .addInsertFunction(BaseCommonTestEnum.BIRTHDAY, "now");

        StringBuilder builder = new StringBuilder();
        builder.append(" insert into student ");
        StringBuilder fieldBuilder = new StringBuilder();
        StringBuilder valueBuilder = new StringBuilder();
        for (int i = 0; i < entity.getInsertEOLList().size(); i++) {
            InsertEO insertEO = entity.getInsertEOLList().get(i);
            fieldBuilder.append(insertEO.getColumnName());
            valueBuilder.append(insertEO.getColumnValue());
            if (i != entity.getInsertEOLList().size() - 1) {
                fieldBuilder.append(" , ");
                valueBuilder.append(" , ");
            }
        }
        builder.append(" ( ").append(fieldBuilder.toString()).append(" ) ");
        builder.append(" values");
        builder.append(" ( ").append(valueBuilder.toString()).append(" ) ;");

        System.out.println(builder.toString());
    }
}
