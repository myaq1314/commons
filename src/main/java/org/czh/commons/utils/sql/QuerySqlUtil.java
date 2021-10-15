package org.czh.commons.utils.sql;

import org.czh.commons.entity.eo.BaseViewEO;
import org.czh.commons.utils.sql.annotations.Column;
import org.czh.commons_core.asserts.EmptyAssert;
import org.czh.commons_core.utils.FieldUtil;
import org.czh.commons_core.validate.EmptyValidate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021/9/28
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public final class QuerySqlUtil {

    public static <EO extends BaseViewEO> String selectEOSql(final EO conditionEO) {
        StringBuilder builder = new StringBuilder();
        selectEOSql(conditionEO, builder);
        return builder.toString();
    }

    public static <EO extends BaseViewEO> void selectEOSql(final EO conditionEO,
                                                           final StringBuilder builder) {
        EmptyAssert.allNotNull(conditionEO, builder);

        // 拼装 自定义 查询字段 SQL
        List<String> selectSQLList;
        if (EmptyValidate.isNotEmpty(selectSQLList = conditionEO.getSelectSQLList())) {
            for (int i = 0; i < selectSQLList.size(); i++) {
                builder.append(" ").append(selectSQLList.get(i)).append(" ");
                if (i != selectSQLList.size() - 1) {
                    builder.append(",");
                }
            }
            return;
        }

        // 拼装 所有字段 SQL
        // 查找所有标记 Column 注解 的属性
        List<Field> fieldList = FieldUtil.queryFieldList(conditionEO.getClass(),
                                                         field -> field.getAnnotation(Column.class) != null
        );
        for (int i = 0; i < fieldList.size(); i++) {
            builder.append(" a.`").append(fieldList.get(i).getAnnotation(Column.class).name()).append("` ");
            if (i != fieldList.size() - 1) {
                builder.append(",");
            }
        }
    }

    public static String selectMapSql(final Map<String, Object> conditionMap) {
        StringBuilder builder = new StringBuilder();
        selectMapSql(conditionMap, builder);
        return builder.toString();
    }

    public static void selectMapSql(final Map<String, Object> conditionMap,
                                    final StringBuilder builder) {
        EmptyAssert.allNotNull(conditionMap, builder);

        // 拼装 自定义 查询字段 SQL
        Object selectSQLListTmp;
        if (EmptyValidate.isNotNull(selectSQLListTmp = conditionMap.get("selectSQLList"))) {
            @SuppressWarnings("unchecked")
            List<String> selectSQLList = (ArrayList<String>) selectSQLListTmp;
            if (EmptyValidate.isNotEmpty(selectSQLList)) {
                for (int i = 0; i < selectSQLList.size(); i++) {
                    builder.append(" ").append(selectSQLList.get(i));
                    if (i != selectSQLList.size() - 1) {
                        builder.append(",");
                    }
                }
                return;
            }
        }

        // 拼装 所有字段 SQL
        builder.append(" * ");
    }
}
