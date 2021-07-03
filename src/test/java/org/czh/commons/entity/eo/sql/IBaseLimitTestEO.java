package org.czh.commons.entity.eo.sql;

import org.czh.commons.entity.eo.example.StudentTestEO;
import org.czh.commons.validate.EmptyValidate;
import org.junit.Test;

/**
 * @author : czh
 * description :
 * date : 2021-06-26
 * email 916419307@qq.com
 */
public class IBaseLimitTestEO {

    @Test
    public void test() {
        StudentTestEO studentTestEO = new StudentTestEO();
        studentTestEO.resetLimit()
                .setLimit(2, 5);
        System.out.println(studentTestEO.getLimitSQL()); // 5, 5

        studentTestEO.setLimit(3, 5)
                .setLimit(1, 5);
        System.out.println(studentTestEO.getLimitSQL()); // 0, 5

        StringBuilder builder = new StringBuilder();
        builder.append(" select * ");
        builder.append(" from student as a ");
        builder.append(" where 1 = 1 ");

        if (EmptyValidate.isNotBlank(studentTestEO.getLimitSQL())) {
            builder.append(" limit ");
            builder.append(studentTestEO.getLimitSQL());
        }

        System.out.println(builder.toString());

        /*
            select *
            from student as a
            where 1 = 1
            limit  0, 5
         */
    }
}
