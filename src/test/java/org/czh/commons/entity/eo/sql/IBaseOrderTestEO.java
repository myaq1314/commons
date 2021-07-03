package org.czh.commons.entity.eo.sql;

import org.czh.commons.entity.eo.example.StudentTestColumnEnum;
import org.czh.commons.entity.eo.example.StudentTestEO;
import org.czh.commons.validate.EmptyValidate;
import org.junit.Test;

import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021-06-26
 * email 916419307@qq.com
 */
public class IBaseOrderTestEO {

    @Test
    public void test() {
        StudentTestEO studentTestEO = new StudentTestEO();
        studentTestEO.resetOrder()
                .addOrderASC(StudentTestColumnEnum.GRADE)
                .addOrderDESC(StudentTestColumnEnum.SCORE)
                .addOrderASC("name")
                .addOrderDESC("score")
                .addOrderASCFunction("year", StudentTestColumnEnum.BIRTHDAY)
                .addOrderDESCFunction("field", StudentTestColumnEnum.ID, 1, 3, 5, 7)
                .addOrderSQL("month(a.`birthday`) asc");

        StringBuilder builder = new StringBuilder();
        builder.append(" select * ");
        builder.append(" from student as a ");
        builder.append(" where 1 = 1 ");

        List<String> orderSQLList = studentTestEO.getOrderSQLList();
        if (EmptyValidate.isNotEmpty(orderSQLList)) {
            builder.append(" order by ");
            for (int i = 0; i < orderSQLList.size(); i++) {
                String orderSQL = orderSQLList.get(i);
                builder.append(orderSQL);
                if (i != orderSQLList.size() - 1) {
                    builder.append(" , ");
                }
            }
        }
        System.out.println(builder.toString());

        /*
            SELECT *
            FROM student AS a
            WHERE 1 = 1
            ORDER BY a.`grade` ASC,
	            a.`score` DESC,
	            field ( a.`id`, 1, 3, 5, 7 ) DESC;
         */
    }
}
