package org.czh.commons.entity.eo.sql;

import org.czh.commons.entity.eo.example.StudentTestColumnEnum;
import org.czh.commons.entity.eo.example.StudentTestEO;
import org.czh.commons.enums.sql.CircleDict;
import org.czh.commons.enums.sql.LikeDict;
import org.czh.commons.enums.sql.NullDict;
import org.czh.commons.enums.sql.ScopeDict;
import org.czh.commons.validate.EmptyValidate;
import org.junit.Test;

import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021-06-26
 * email 916419307@qq.com
 */
public class IBaseHavingTestEO {

    @Test
    public void test() {
        StudentTestEO studentTestEO = new StudentTestEO();
        studentTestEO.resetHaving()
                .addHavingScope(ScopeDict.GREATER_EQUAL, 60, "AVG", StudentTestColumnEnum.SCORE)
                .addHavingLike(LikeDict.LIKE, "张", "MIN", StudentTestColumnEnum.STUDENT_NAME)
                .addHavingCircle(CircleDict.IN, new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, "MIN", StudentTestColumnEnum.ID)
                .addHavingNull(NullDict.NOT_NULL, "MAX", StudentTestColumnEnum.BIRTHDAY)
                .addHavingSQL("count(1) > 1");

        StringBuilder builder = new StringBuilder();
        builder.append(" select ");
        builder.append(" avg ( a.`score`) as `score` ");
        builder.append(" , min( a.`student_name` ) as `student_name` ");
        builder.append(" , min( a.`id` ) as `id`");
        builder.append(" , max( a.`birthday` ) as `birthday` ");
        builder.append(" from student as a ");
        builder.append(" where 1 = 1 ");
        builder.append(" group by a.`grade` ");

        List<String> havingSQLList = studentTestEO.getHavingSQLList();
        if (EmptyValidate.isNotEmpty(havingSQLList)) {
            builder.append(" having ");
            for (int i = 0; i < havingSQLList.size(); i++) {
                builder.append(havingSQLList.get(i));
                if (i != havingSQLList.size() - 1) {
                    builder.append(" AND ");
                }
            }
        }

        System.out.println(builder.toString());

        /*
            SELECT avg( a.`score` ) AS `score`,
	            min( a.`student_name` ) AS `student_name`,
	            min( a.`id` ) AS `id`,
	            max( a.`birthday` ) AS `birthday`
            FROM student AS a
            WHERE 1 = 1
            GROUP BY a.`grade`
            HAVING AVG( a.`score` ) >= 60
	            AND MIN( a.`student_name` ) LIKE '%张%'
	            AND MIN( a.`id` ) IN ( 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 )
	            AND MAX( a.`birthday` ) IS NOT NULL
         */
    }
}
