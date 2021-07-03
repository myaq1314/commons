package org.czh.commons.entity.eo.sql;

import org.czh.commons.entity.eo.example.StudentTestColumnEnum;
import org.czh.commons.entity.eo.example.StudentTestEO;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;
import org.junit.Test;

import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021-06-25
 * email 916419307@qq.com
 */
public class IBaseSelectTestEO {

    @Test
    public void test() {
        StudentTestEO studentTestEO = new StudentTestEO();
        studentTestEO
                .resetSelect()
                .addSelect("id")
                .addSelect(StudentTestColumnEnum.ID)
                .addSelect(StudentTestColumnEnum.STUDENT_NAME, StudentTestColumnEnum.GRADE)
                .addSelectFunction("reverse", StudentTestColumnEnum.STUDENT_NAME)
                .addSelectFunction("date_format", StudentTestColumnEnum.BIRTHDAY, StudentTestColumnEnum.BIRTHDAY, "%Y%m%d")
                .addSelectFunction("concat", StudentTestColumnEnum.STUDENT_NAME, StudentTestColumnEnum.STUDENT_NAME, "--", StudentTestColumnEnum.BIRTHDAY)
                .addSelectFunction("", StudentTestColumnEnum.GRADE, "+", StudentTestColumnEnum.GRADE, StudentTestColumnEnum.GRADE, 1)
                .addSelectSQL("concat(a.`student_name`, '--', date_format(a.`birthday`, '%Y%m%d')) AS student_name");

        StringBuilder builder = new StringBuilder("select ");
        List<String> selectSQLList = studentTestEO.getSelectSQLList();
        if (EmptyValidate.isNotEmpty(selectSQLList)) {
            builder.append(" ");
            for (int i = 0; i < selectSQLList.size(); i++) {
                String selectSQL = selectSQLList.get(i);
                EmptyAssert.isNotBlank(selectSQL);
                builder.append(selectSQL);
                if (i != selectSQLList.size() - 1) {
                    builder.append(" , ");
                }
            }
        } else {
            builder.append(" * ");
        }
        builder.append(" from student as a ; ");
        System.out.println(builder.toString());
        /*
            SELECT
	            a.`id`,
	            a.`student_name`,
	            a.`grade`,
	            reverse ( a.`student_name` ) AS student_name,
	            date_format ( a.`birthday`, '%Y%m%d' ) AS birthday,
	            concat ( a.`student_name`, '--', a.`birthday` ) AS student_name,
	            concat( a.`student_name`, '--', date_format( a.`birthday`, '%Y%m%d' )) AS student_name,
	            ( a.`grade` + a.`grade` + 1 ) AS grade
            FROM student AS a
         */
    }
}
