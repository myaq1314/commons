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
public class IBaseGroupTestEO {

    @Test
    public void test() {
        StudentTestEO studentTestEO = new StudentTestEO();
        studentTestEO.addGroup("grade")
                .addGroupSQL("month(a.`birthday`)");
        System.out.println(studentTestEO.getGroupSQLList()); // [ a.`grade` , month(a.`birthday`)]

        studentTestEO.resetGroup()
                .addGroup(StudentTestColumnEnum.GRADE)
                .addGroupFunction("left", StudentTestColumnEnum.STUDENT_NAME, 1)
                .addGroupFunction("year", StudentTestColumnEnum.BIRTHDAY);
        System.out.println(studentTestEO.getGroupSQLList()); // [ a.`grade` ,  left( a.`student_name` , 1 ) ,  year( a.`birthday` ) ]

        StringBuilder builder = new StringBuilder();
        builder.append(" select ");
        builder.append(" a.`grade` ");
        builder.append(" , left ( a.`student_name`, 1) as `student_name` ");
        builder.append(" , year ( a.`birthday`) as `birthday` ");
        builder.append(" , min(a.`score`) as `score` ");
        builder.append(" , avg(a.`score`) as `score` ");
        builder.append(" , sum(a.`score`) as `score` ");
        builder.append(" from student as a ");
        builder.append(" where 1 = 1 ");

        List<String> groupSQLList = studentTestEO.getGroupSQLList();
        if (EmptyValidate.isNotEmpty(groupSQLList)) {
            builder.append(" group by ");
            for (int i = 0; i < groupSQLList.size(); i++) {
                String groupSQL = groupSQLList.get(i);
                builder.append(groupSQL);
                if (i != groupSQLList.size() - 1) {
                    builder.append(" , ");
                }
            }
        }
        System.out.println(builder.toString());

        /*
            SELECT a.`grade`,
	            LEFT ( a.`student_name`, 1 ) AS `student_name`,
	            YEAR ( a.`birthday` ) AS `birthday`,
	            min( a.`score` ) AS `score`,
	            avg( a.`score` ) AS `score`,
	            sum( a.`score` ) AS `score`
            FROM student AS a
            WHERE 1 = 1
            GROUP BY a.`grade`, LEFT ( a.`student_name`, 1 ), YEAR (a.`birthday` )
         */
    }
}
