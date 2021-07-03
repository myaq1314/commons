package org.czh.commons.entity.eo.sql;

import org.czh.commons.entity.eo.IBaseEO;
import org.czh.commons.enums.parent.IColumnEnum;
import org.czh.commons.utils.SqlJointUtil;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021-06-23
 * email 916419307@qq.com
 */
public interface IBaseGroupEO extends IBaseEO {

    List<String> getGroupSQLList();

    void setGroupSQLList(final List<String> groupSQLList);

    default IBaseGroupEO resetGroup() {
        setGroupSQLList(null);
        return this;
    }

    default IBaseGroupEO addGroup(final String... columnNames) {
        EmptyAssert.isNotEmpty(columnNames);

        for (String columnName : columnNames) {
            addGroupSQL(SqlJointUtil.columnNameConvertSql(columnName));
        }
        return this;
    }

    default IBaseGroupEO addGroup(final IColumnEnum... columnEnums) {
        EmptyAssert.isNotEmpty(columnEnums);

        for (IColumnEnum columnEnum : columnEnums) {
            addGroupSQL(SqlJointUtil.columnEnumConvertSql(columnEnum));
        }
        return this;
    }

    default IBaseGroupEO addGroupFunction(final String function, final Object... functionObjs) {
        return addGroupSQL(SqlJointUtil.convertFunctionSql(function, functionObjs));
    }

    default IBaseGroupEO addGroupSQL(final String groupSQL) {
        EmptyAssert.isNotBlank(groupSQL);

        List<String> groupSQLList = getGroupSQLList();
        if (EmptyValidate.isNull(groupSQLList)) {
            groupSQLList = new ArrayList<>();
            setGroupSQLList(groupSQLList);
        }
        if (!groupSQLList.contains(groupSQL)) {
            groupSQLList.add(groupSQL);
        }
        return this;
    }
}
