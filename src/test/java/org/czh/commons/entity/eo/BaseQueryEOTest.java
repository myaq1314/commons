package org.czh.commons.entity.eo;

import org.czh.commons.entity.eo.example.BaseQueryTestEO;
import org.czh.commons.entity.eo.example.BaseQueryTestEnum;
import org.czh.commons.enums.LikeDict;
import org.czh.commons.enums.ScopeDict;
import org.czh.commons.validate.EmptyValidate;
import org.czh.commons.validate.FlagValidate;
import org.junit.Test;

import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021-06-26
 * email 916419307@qq.com
 */
public class BaseQueryEOTest {

    @Test
    public void test() {
        BaseQueryTestEO baseQueryTestEO = new BaseQueryTestEO();

        baseQueryTestEO.addSelect(BaseQueryTestEnum.GRADE);
        baseQueryTestEO.addSelectFunction("SUM", BaseQueryTestEnum.SCORE);
        baseQueryTestEO.addSelectFunction("MIN", BaseQueryTestEnum.BIRTHDAY);

        baseQueryTestEO.addWhereLike(BaseQueryTestEnum.STUDENT_NAME, LikeDict.START_LIKE, "张");

        baseQueryTestEO.addGroup(BaseQueryTestEnum.GRADE);

        baseQueryTestEO.addOrderASC(BaseQueryTestEnum.GRADE);

        baseQueryTestEO.addHavingScope(ScopeDict.GREATER_EQUAL, 60, "AVG", BaseQueryTestEnum.SCORE);

        baseQueryTestEO.setLimit(1, 1);


        StringBuilder builder = new StringBuilder();
        builder.append(" select ");

        if (FlagValidate.isTrue(baseQueryTestEO.getDistinctSQL())) {
            builder.append(" distinct ");
        }

        List<String> selectSQLList = baseQueryTestEO.getSelectSQLList();
        if (EmptyValidate.isNotEmpty(selectSQLList)) {
            for (int i = 0; i < selectSQLList.size(); i++) {
                String selectSQL = selectSQLList.get(i);
                builder.append(selectSQL);
                if (i != selectSQLList.size() - 1) {
                    builder.append(" , ");
                }
            }
        } else {
            builder.append(" * ");
        }

        builder.append(" from ");
        if (EmptyValidate.isNotBlank(baseQueryTestEO.getTableName())) {
            builder.append(baseQueryTestEO.getTableName());
        } else {
            builder.append(" student ");
        }
        builder.append(" as a ");

        builder.append(" where 1 = 1 ");
        List<String> whereSQLList = baseQueryTestEO.getWhereSQLList();
        if (EmptyValidate.isNotEmpty(whereSQLList)) {
            for (String whereSQL : whereSQLList) {
                builder.append(" AND ");
                builder.append(whereSQL);
            }
        }

        List<String> groupSQLList = baseQueryTestEO.getGroupSQLList();
        if (EmptyValidate.isNotEmpty(groupSQLList)) {
            builder.append(" GROUP BY ");
            for (int i = 0; i < groupSQLList.size(); i++) {
                String groupSQL = groupSQLList.get(i);
                builder.append(groupSQL);
                if (i != groupSQLList.size() - 1) {
                    builder.append(" , ");
                }
            }
        }

        List<String> havingSQLList = baseQueryTestEO.getHavingSQLList();
        if (EmptyValidate.isNotEmpty(groupSQLList) && EmptyValidate.isNotEmpty(havingSQLList)) {
            builder.append(" HAVING ");
            for (int i = 0; i < havingSQLList.size(); i++) {
                String havingSQL = havingSQLList.get(i);
                builder.append(havingSQL);
                if (i != havingSQLList.size() - 1) {
                    builder.append("AND");
                }
            }
        }

        List<String> orderSQLList = baseQueryTestEO.getOrderSQLList();
        if (EmptyValidate.isNotEmpty(orderSQLList)) {
            builder.append(" ORDER BY ");
            for (int i = 0; i < orderSQLList.size(); i++) {
                String orderSQL = orderSQLList.get(i);
                builder.append(orderSQL);
                if (i != orderSQLList.size() - 1) {
                    builder.append(" , ");
                }
            }
        }

        String limitSQL = baseQueryTestEO.getLimitSQL();
        if (EmptyValidate.isNotBlank(limitSQL)) {
            builder.append(" LIMIT ");
            builder.append(limitSQL);
        }

        System.out.println(builder.toString());

        /*
            SELECT a.`grade`,
	            SUM( a.`score` ) AS score,
                MIN( a.`birthday` ) AS birthday
            FROM student AS a
            WHERE 1 = 1
	            AND a.`student_name` LIKE '张%'
            GROUP BY a.`grade`
            HAVING AVG( a.`score` ) >= 60
            ORDER BY a.`grade` ASC
            LIMIT 0,1
         */
    }
}
