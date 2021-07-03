package org.czh.commons.entity.eo.sql;

import org.czh.commons.entity.eo.IBaseEO;
import org.czh.commons.enums.sql.CircleDict;
import org.czh.commons.enums.sql.LikeDict;
import org.czh.commons.enums.sql.NullDict;
import org.czh.commons.enums.sql.ScopeDict;
import org.czh.commons.enums.parent.IColumnEnum;
import org.czh.commons.utils.SqlJointUtil;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : czh
 * description :
 * where age < 15
 * where name like 'Tom%'
 * where sex is null
 * where id in (1, 3, 5)
 * where min(id) > 3
 * date : 2021-06-24
 * email 916419307@qq.com
 */
public interface IBaseWhereEO extends IBaseEO {

    List<String> getWhereSQLList();

    void setWhereSQLList(final List<String> whereSQLList);

    default IBaseWhereEO resetWhere() {
        setWhereSQLList(null);
        return this;
    }

    default IBaseWhereEO addWhereScope(final IColumnEnum columnEnum,
                                       final ScopeDict scopeDict,
                                       final Object compareObj) {
        EmptyAssert.isNotNull(columnEnum);
        return addWhereScope(columnEnum.getColumn(), scopeDict, compareObj);
    }

    default IBaseWhereEO addWhereScope(final String columnName,
                                       final ScopeDict scopeDict,
                                       final Object compareObj) {
        EmptyAssert.isNotNull(scopeDict);
        return addWhereSQL(String.format(
                " %s %s %s ",
                SqlJointUtil.columnNameConvertSql(columnName),
                scopeDict.key,
                SqlJointUtil.objConvertSql(compareObj)
        ));
    }

    default IBaseWhereEO addWhereNull(final IColumnEnum columnEnum, final NullDict nullDict) {
        EmptyAssert.isNotNull(columnEnum);
        return addWhereNull(columnEnum.getColumn(), nullDict);
    }

    default IBaseWhereEO addWhereNull(final String columnName, final NullDict nullDict) {
        EmptyAssert.isNotNull(nullDict);
        return addWhereSQL(String.format(
                " %s %s ",
                SqlJointUtil.columnNameConvertSql(columnName),
                nullDict.key
        ));
    }

    default IBaseWhereEO addWhereLike(final IColumnEnum columnEnum,
                                      final LikeDict likeDict,
                                      final String compareObj) {
        EmptyAssert.isNotNull(columnEnum);
        return addWhereLike(columnEnum.getColumn(), likeDict, compareObj);
    }

    default IBaseWhereEO addWhereLike(final String columnName,
                                      final LikeDict likeDict,
                                      final String compareObj) {
        EmptyAssert.allNotNull(likeDict, compareObj);
        return addWhereSQL(String.format(
                " %s %s %s ",
                SqlJointUtil.columnNameConvertSql(columnName),
                likeDict.key,
                SqlJointUtil.objConvertSql(likeDict.start + compareObj + likeDict.end)
        ));
    }

    default IBaseWhereEO addWhereCircle(final IColumnEnum columnEnum,
                                        final CircleDict circleDict,
                                        final Object... circleObjs) {
        EmptyAssert.isNotNull(columnEnum);
        return addWhereCircle(columnEnum.getColumn(), circleDict, circleObjs);
    }

    default IBaseWhereEO addWhereCircle(final String columnName,
                                        final CircleDict circleDict,
                                        final Object... circleObjs) {
        EmptyAssert.isNotNull(circleDict);
        return addWhereSQL(String.format(
                " %s %s ",
                SqlJointUtil.columnNameConvertSql(columnName),
                SqlJointUtil.convertFunctionSql(circleDict.key, circleObjs)
        ));
    }

    default IBaseWhereEO addWhereSQL(final String whereSQL) {
        EmptyAssert.isNotBlank(whereSQL);

        List<String> whereSQLList = getWhereSQLList();
        if (EmptyValidate.isNull(whereSQLList)) {
            whereSQLList = new ArrayList<>();
            setWhereSQLList(whereSQLList);
        }
        if (!whereSQLList.contains(whereSQL)) {
            whereSQLList.add(whereSQL);
        }
        return this;
    }
}
