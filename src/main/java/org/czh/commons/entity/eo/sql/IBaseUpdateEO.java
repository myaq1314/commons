package org.czh.commons.entity.eo.sql;

import org.czh.commons.entity.eo.IBaseEO;
import org.czh.commons.enums.parent.IColumnEnum;
import org.czh.commons.utils.SqlJointUtil;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;
import org.czh.commons.validate.FlagAssert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021-06-25
 * email 916419307@qq.com
 */
public interface IBaseUpdateEO extends IBaseEO {

    List<String> getUpdateSQLList();

    void setUpdateSQLList(final List<String> updateSQLList);

    default IBaseUpdateEO resetUpdate() {
        setUpdateSQLList(null);
        return this;
    }

    default IBaseUpdateEO addUpdate(final IColumnEnum columnEnum, final Object columnValue) {
        EmptyAssert.allNotNull(columnEnum, columnValue);
        FlagAssert.isTrue(columnEnum.getType().equals(columnValue.getClass()));
        return addUpdate(columnEnum.getColumn(), columnValue);
    }

    default IBaseUpdateEO addUpdate(final String columnName, final Object columnValue) {
        return addUpdateSQL(String.format(
                " %s = %s",
                SqlJointUtil.columnNameConvertSql(columnName),
                SqlJointUtil.objConvertSql(columnValue)
        ));
    }

    default IBaseUpdateEO addUpdateFunction(final IColumnEnum columnEnum,
                                            final String function,
                                            final Object... functionObjs) {
        EmptyAssert.isNotNull(columnEnum);
        return addUpdateFunction(columnEnum.getColumn(), function, functionObjs);
    }

    default IBaseUpdateEO addUpdateFunction(final String columnName,
                                            final String function,
                                            final Object... functionObjs) {
        return addUpdateSQL(String.format(
                " %s = %s ",
                SqlJointUtil.columnNameConvertSql(columnName),
                SqlJointUtil.convertFunctionSql(function, functionObjs)
        ));
    }

    default IBaseUpdateEO addUpdateSQL(final String updateSQL) {
        EmptyAssert.isNotBlank(updateSQL);

        List<String> updateSQLList = getUpdateSQLList();
        if (EmptyValidate.isNull(updateSQLList)) {
            updateSQLList = new ArrayList<>();
            setUpdateSQLList(updateSQLList);
        }
        if (!updateSQLList.contains(updateSQL)) {
            updateSQLList.add(updateSQL);
        }
        return this;
    }
}
