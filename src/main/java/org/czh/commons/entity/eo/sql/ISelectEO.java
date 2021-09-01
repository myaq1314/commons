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
public interface ISelectEO extends IBaseEO {

    List<String> getSelectSQLList();

    void setSelectSQLList(final List<String> selectSQLList);

    default ISelectEO resetSelect() {
        setSelectSQLList(null);
        return this;
    }

    default ISelectEO addSelect(final String... columnNames) {
        EmptyAssert.isNotEmpty(columnNames);

        for (String columnName : columnNames) {
            addSelectSQL(SqlJointUtil.columnNameConvertSql(columnName));
        }
        return this;
    }

    default ISelectEO addSelect(final IColumnEnum... columnEnums) {
        EmptyAssert.isNotEmpty(columnEnums);

        for (IColumnEnum columnEnum : columnEnums) {
            addSelectSQL(SqlJointUtil.columnEnumConvertSql(columnEnum));
        }
        return this;
    }

    default ISelectEO addSelectFunction(final String function, final IColumnEnum aliasColumn) {
        return addSelectFunction(function, aliasColumn, aliasColumn);
    }

    default ISelectEO addSelectFunction(final String function,
                                        final IColumnEnum aliasColumn,
                                        final Object... functionObjs) {
        EmptyAssert.isNotNull(aliasColumn);
        return addSelectFunction(function, aliasColumn.getColumn(), functionObjs);
    }

    default ISelectEO addSelectFunction(final String function,
                                        final String alias,
                                        final Object... functionObjs) {
        return addSelectSQL(SqlJointUtil.convertSelectFunctionSql(function, alias, functionObjs));
    }

    default ISelectEO addSelectFunction(final String function,
                                        final IColumnEnum aliasColumn,
                                        final String separator,
                                        final Object... functionObjs) {
        EmptyAssert.isNotNull(aliasColumn);
        return addSelectFunction(function, aliasColumn.getColumn(), separator, functionObjs);
    }

    default ISelectEO addSelectFunction(final String function,
                                        final String alias,
                                        final String separator,
                                        final Object... functionObjs) {
        return addSelectSQL(SqlJointUtil.convertSelectFunctionSql(function, alias, separator, functionObjs));
    }

    default ISelectEO addSelectSQL(final String selectSQL) {
        EmptyAssert.isNotBlank(selectSQL);

        List<String> selectSQLList = getSelectSQLList();
        if (EmptyValidate.isNull(selectSQLList)) {
            selectSQLList = new ArrayList<>();
            setSelectSQLList(selectSQLList);
        }
        if (!selectSQLList.contains(selectSQL)) {
            selectSQLList.add(selectSQL);
        }
        return this;
    }
}
