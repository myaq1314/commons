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
public interface IBaseOrderEO extends IBaseEO {

    List<String> getOrderSQLList();

    void setOrderSQLList(final List<String> orderSQLList);

    default IBaseOrderEO resetOrder() {
        setOrderSQLList(null);
        return this;
    }

    default IBaseOrderEO addOrderASC(final String... columnNames) {
        EmptyAssert.isNotEmpty(columnNames);

        for (String columnName : columnNames) {
            addOrderSQL(String.format(" %s ASC ", SqlJointUtil.columnNameConvertSql(columnName)));
        }
        return this;
    }

    default IBaseOrderEO addOrderASC(final IColumnEnum... columnEnums) {
        EmptyAssert.isNotEmpty(columnEnums);

        for (IColumnEnum columnEnum : columnEnums) {
            addOrderSQL(String.format(" %s ASC ", SqlJointUtil.columnEnumConvertSql(columnEnum)));
        }
        return this;
    }

    default IBaseOrderEO addOrderDESC(final String... columnNames) {
        EmptyAssert.isNotEmpty(columnNames);

        for (String columnName : columnNames) {
            addOrderSQL(String.format(" %s DESC ", SqlJointUtil.columnNameConvertSql(columnName)));
        }
        return this;
    }

    default IBaseOrderEO addOrderDESC(final IColumnEnum... columnEnums) {
        EmptyAssert.isNotEmpty(columnEnums);

        for (IColumnEnum columnEnum : columnEnums) {
            addOrderSQL(String.format(" %s DESC ", SqlJointUtil.columnEnumConvertSql(columnEnum)));
        }
        return this;
    }

    default IBaseOrderEO addOrderASCFunction(final String function, final Object... functionObjs) {
        return addOrderSQL(String.format(" %s ASC ", SqlJointUtil.convertFunctionSql(function, functionObjs)));
    }

    default IBaseOrderEO addOrderDESCFunction(final String function, final Object... functionObjs) {
        return addOrderSQL(String.format("%s DESC ", SqlJointUtil.convertFunctionSql(function, functionObjs)));
    }

    default IBaseOrderEO addOrderSQL(final String orderSQL) {
        EmptyAssert.isNotBlank(orderSQL);

        List<String> orderSQLList = getOrderSQLList();
        if (EmptyValidate.isNull(orderSQLList)) {
            orderSQLList = new ArrayList<>();
            setOrderSQLList(orderSQLList);
        }
        if (!orderSQLList.contains(orderSQL)) {
            orderSQLList.add(orderSQL);
        }
        return this;
    }
}
