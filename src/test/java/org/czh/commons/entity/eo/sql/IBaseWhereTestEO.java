package org.czh.commons.entity.eo.sql;

import org.czh.commons.entity.eo.example.StudentTestColumnEnum;
import org.czh.commons.entity.eo.example.StudentTestEO;
import org.czh.commons.enums.sql.CircleDict;
import org.czh.commons.enums.sql.LikeDict;
import org.czh.commons.enums.sql.NullDict;
import org.czh.commons.enums.sql.ScopeDict;
import org.czh.commons.utils.SqlJointUtil;
import org.czh.commons.validate.EmptyValidate;
import org.junit.Test;

import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021-06-25
 * email 916419307@qq.com
 */
public class IBaseWhereTestEO {

    @Test
    public void test() {
        StudentTestEO studentTestEO = new StudentTestEO();
        studentTestEO.resetWhere()
                .addWhereScope(StudentTestColumnEnum.ID, ScopeDict.GREATER_EQUAL, 1)
                .addWhereScope(StudentTestColumnEnum.GRADE, ScopeDict.UNEQUAL, StudentTestColumnEnum.ID)
                .addWhereScope(StudentTestColumnEnum.SCORE, ScopeDict.LESS, 100)

                .addWhereNull(StudentTestColumnEnum.SCORE, NullDict.NOT_NULL)
                .addWhereNull(StudentTestColumnEnum.BIRTHDAY, NullDict.NOT_NULL)

                .addWhereCircle(StudentTestColumnEnum.ID, CircleDict.IN, 2, 4, 6, 8, 10, 12, 14, 16)
                .addWhereCircle(StudentTestColumnEnum.GRADE, CircleDict.NOT_IN, 5, 6, 7, 8)

                .addWhereLike(StudentTestColumnEnum.STUDENT_NAME, LikeDict.LIKE, "张")
                .addWhereLike(StudentTestColumnEnum.STUDENT_NAME, LikeDict.START_LIKE, "张")
                .addWhereLike(StudentTestColumnEnum.STUDENT_NAME, LikeDict.NOT_LIKE, "赵日天")
                .addWhereLike(StudentTestColumnEnum.STUDENT_NAME, LikeDict.NOT_END_LIKE, "小花");

        String month = SqlJointUtil.convertFunctionSql("MONTH", StudentTestColumnEnum.BIRTHDAY);
        String day = SqlJointUtil.convertFunctionSql("DAYOFMONTH", StudentTestColumnEnum.BIRTHDAY);
        studentTestEO.addWhereSQL(String.format(" %s %s %s ", month, ScopeDict.LESS_EQUAL.key, day));

        StringBuilder builder = new StringBuilder();
        builder.append("select * from student as a ");
        List<String> whereSQLList = studentTestEO.getWhereSQLList();
        if (EmptyValidate.isNotEmpty(whereSQLList)) {
            builder.append(" where ");
            for (int i = 0; i < whereSQLList.size(); i++) {
                builder.append(whereSQLList.get(i));
                if (i != whereSQLList.size() - 1) {
                    builder.append(" and ");
                }
            }
        } else {
            builder.append(" where 1 = 1");
        }
        System.out.println(builder.toString());

        /*
            SELECT *
            FROM student AS a
            WHERE a.`id` >= 1
                AND a.`grade` != a.`id`
                AND a.`score` < 100
                AND a.`score` IS NOT NULL
                AND a.`birthday` IS NOT NULL
	            AND a.`id` IN (2, 4, 6, 8, 10, 12, 14, 16)
                AND a.`grade` NOT IN ( 5, 6, 7, 8 )
                AND a.`student_name` LIKE '%张%'
                AND a.`student_name` LIKE '张%'
                AND a.`student_name` NOT LIKE '%赵日天%'
                AND a.`student_name` NOT LIKE '%小花'
                AND MONTH (a.`birthday`) <= DAYOFMONTH (a.`birthday`)
         */
    }
}
