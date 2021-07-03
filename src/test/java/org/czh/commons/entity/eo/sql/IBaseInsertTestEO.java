package org.czh.commons.entity.eo.sql;

import org.czh.commons.entity.eo.example.StudentTestColumnEnum;
import org.czh.commons.entity.eo.example.StudentTestEO;
import org.czh.commons.utils.RandomUtil;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author : czh
 * description :
 * date : 2021-06-26
 * email 916419307@qq.com
 */
public class IBaseInsertTestEO {

    @Test
    public void test() {
        StudentTestEO studentTestEO = new StudentTestEO();
        studentTestEO.resetInsert()
                .addInsert(StudentTestColumnEnum.STUDENT_NAME, "宋晓佳")
                .addInsert(StudentTestColumnEnum.GRADE, 1)
                .addInsert(StudentTestColumnEnum.SCORE, new BigDecimal(RandomUtil.getRandom(60, 100)))
                .addInsertFunction(StudentTestColumnEnum.BIRTHDAY, "now")
                .addInsertEO(new InsertEO("id", "17"));

        StringBuilder builder = new StringBuilder();
        builder.append(" insert into student ");
        StringBuilder fieldBuilder = new StringBuilder();
        StringBuilder valueBuilder = new StringBuilder();
        for (int i = 0; i < studentTestEO.getInsertEOLList().size(); i++) {
            InsertEO insertEO = studentTestEO.getInsertEOLList().get(i);
            fieldBuilder.append(insertEO.getColumnName());
            valueBuilder.append(insertEO.getColumnValue());
            if (i != studentTestEO.getInsertEOLList().size() - 1) {
                fieldBuilder.append(" , ");
                valueBuilder.append(" , ");
            }
        }
        builder.append(" ( ").append(fieldBuilder.toString()).append(" ) ");
        builder.append(" values");
        builder.append(" ( ").append(valueBuilder.toString()).append(" ) ");

        System.out.println("delete from student where id = 17 ; ");
        System.out.println(builder.toString());

        /*
            DELETE
            FROM student
            WHERE id = 17;

            INSERT INTO student ( id, student_name, grade, score, birthday )
            VALUES (17, '宋晓佳', 1, 67, now() )

         */
    }
}
