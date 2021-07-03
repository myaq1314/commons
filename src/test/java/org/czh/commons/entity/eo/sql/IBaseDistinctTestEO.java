package org.czh.commons.entity.eo.sql;

import org.czh.commons.entity.eo.example.StudentTestEO;
import org.junit.Test;

/**
 * @author : czh
 * description :
 * date : 2021-06-25
 * email 916419307@qq.com
 */
public class IBaseDistinctTestEO {

    @Test
    public void test() {
        StudentTestEO studentTestEO = new StudentTestEO();
        studentTestEO.setDistinctSQL(true);

        StringBuilder builder = new StringBuilder();
        builder.append("select ");
        if (studentTestEO.getDistinctSQL() != null && studentTestEO.getDistinctSQL()) {
            builder.append(" distinct ");
        }
        builder.append(" * ");
        builder.append(" from student as a ;");
        System.out.println(builder.toString());
        /*
            SELECT DISTINCT *
            FROM
	        student AS a;
         */
    }
}
