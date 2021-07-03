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
public interface IBaseInsertEO extends IBaseEO {

    List<InsertEO> getInsertEOLList();

    void setInsertEOLList(final List<InsertEO> insertEOLList);

    default IBaseInsertEO resetInsert() {
        setInsertEOLList(null);
        return this;
    }

    default IBaseInsertEO addInsert(final IColumnEnum columnEnum, final Object columnValue) {
        EmptyAssert.isNotNull(columnEnum);
        return addInsert(columnEnum.getColumn(), columnValue);
    }

    default IBaseInsertEO addInsert(final String columnName, final Object columnValue) {
        EmptyAssert.allNotBlank(columnName);
        EmptyAssert.isNotNull(columnValue);
        return addInsertEO(new InsertEO(columnName, SqlJointUtil.objConvertSql(columnValue)));
    }

    default IBaseInsertEO addInsertFunction(final IColumnEnum columnEnum,
                                            final String function,
                                            final Object... functionObjs) {
        EmptyAssert.isNotNull(columnEnum);
        return addInsertFunction(columnEnum.getColumn(), function, functionObjs);
    }

    default IBaseInsertEO addInsertFunction(final String columnName,
                                            final String function,
                                            final Object... functionObjs) {
        EmptyAssert.isNotBlank(columnName);
        return addInsertEO(new InsertEO(columnName, SqlJointUtil.convertFunctionSql(function, functionObjs)));
    }

    default IBaseInsertEO addInsertEO(final InsertEO insertEO) {
        EmptyAssert.isNotNull(insertEO);

        List<InsertEO> insertEOLList = getInsertEOLList();
        if (EmptyValidate.isNull(insertEOLList)) {
            insertEOLList = new ArrayList<>();
            setInsertEOLList(insertEOLList);
        }
        if (!insertEOLList.contains(insertEO)) {
            insertEOLList.add(insertEO);
        }
        return this;
    }
}
