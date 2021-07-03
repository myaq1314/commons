package org.czh.commons.entity.eo.sql;

import org.czh.commons.entity.eo.example.StudentTestColumnEnum;
import org.czh.commons.entity.eo.example.StudentTestEO;
import org.czh.commons.utils.RandomUtil;
import org.junit.Test;

import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021-06-26
 * email 916419307@qq.com
 */
public class IBaseUpdateTestEO {

    @Test
    public void test() {
        StudentTestEO studentTestEO = new StudentTestEO();
        studentTestEO.resetUpdate()
                .addUpdate(StudentTestColumnEnum.STUDENT_NAME, "JAK" + RandomUtil.getRandom(1, 10))
                .addUpdateFunction(StudentTestColumnEnum.BIRTHDAY, "now")
                .addUpdateSQL("a.`grade` = " + RandomUtil.getRandom(1, 4));

        StringBuilder builder = new StringBuilder();
        builder.append(" update student as a set ");
        List<String> updateSQLList = studentTestEO.getUpdateSQLList();
        for (int i = 0; i < updateSQLList.size(); i++) {
            builder.append(updateSQLList.get(i));
            if (i != updateSQLList.size() - 1) {
                builder.append(" , ");
            }
        }
        builder.append(" where a.id = 1 ");
        System.out.println(builder.toString());

        /*
            UPDATE student AS a
            SET a.`grade` = 4,
                a.`student_name` = 'JAK2',
                a.`birthday` = now()
            WHERE a.id = 1
         */
    }
}
