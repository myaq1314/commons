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
 * date : 2021-06-25
 * email 916419307@qq.com
 */
public interface IInsertEO extends IBaseEO {

    List<InsertEO> getInsertSQLEOLList();

    void setInsertSQLEOLList(List<InsertEO> insertSQLEOLList);

    default IInsertEO resetInsert() {
        setInsertSQLEOLList(null);
        return this;
    }

    default IInsertEO addInsert(final IColumnEnum columnEnum, final Object columnValue) {
        EmptyAssert.isNotNull(columnEnum);
        return addInsert(columnEnum.getColumn(), columnValue);
    }

    default IInsertEO addInsert(final String columnName, final Object columnValue) {
        EmptyAssert.allNotBlank(columnName);
        EmptyAssert.isNotNull(columnValue);
        return addInsertEO(new InsertEO(columnName, SqlJointUtil.objConvertSql(columnValue)));
    }

    default IInsertEO addInsertFunction(final IColumnEnum columnEnum,
                                        final String function,
                                        final Object... functionObjs) {
        EmptyAssert.isNotNull(columnEnum);
        return addInsertFunction(columnEnum.getColumn(), function, functionObjs);
    }

    default IInsertEO addInsertFunction(final String columnName,
                                        final String function,
                                        final Object... functionObjs) {
        EmptyAssert.isNotBlank(columnName);
        return addInsertEO(new InsertEO(columnName, SqlJointUtil.convertFunctionSql(function, functionObjs)));
    }

    default IInsertEO addInsertEO(final InsertEO insertEO) {
        EmptyAssert.isNotNull(insertEO);

        List<InsertEO> insertSQLEOLList = getInsertSQLEOLList();
        if (EmptyValidate.isNull(insertSQLEOLList)) {
            insertSQLEOLList = new ArrayList<>();
            setInsertSQLEOLList(insertSQLEOLList);
        }
        if (!insertSQLEOLList.contains(insertEO)) {
            insertSQLEOLList.add(insertEO);
        }
        return this;
    }
}
