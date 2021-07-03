package org.czh.commons.entity.eo.sql;

import org.czh.commons.entity.eo.IBaseEO;
import org.czh.commons.enums.CircleDict;
import org.czh.commons.enums.LikeDict;
import org.czh.commons.enums.NullDict;
import org.czh.commons.enums.ScopeDict;
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
public interface IBaseHavingEO extends IBaseEO {

    List<String> getHavingSQLList();

    void setHavingSQLList(final List<String> havingSQLList);

    default IBaseHavingEO resetHaving() {
        setHavingSQLList(null);
        return this;
    }

    default IBaseHavingEO addHavingScope(final ScopeDict scopeDict,
                                         final Object compare,
                                         final String function,
                                         final Object... functionObjs) {
        EmptyAssert.isNotNull(scopeDict);
        return addHavingSQL(String.format(
                " %s %s %s ",
                SqlJointUtil.convertFunctionSql(function, functionObjs),
                scopeDict.key,
                SqlJointUtil.objConvertSql(compare)
        ));
    }

    default IBaseHavingEO addHavingNull(final NullDict nullDict,
                                        final String function,
                                        final Object... functionObjs) {
        EmptyAssert.isNotNull(nullDict);
        return addHavingSQL(String.format(
                " %s %s ",
                SqlJointUtil.convertFunctionSql(function, functionObjs),
                nullDict.key
        ));
    }

    default IBaseHavingEO addHavingLike(final LikeDict likeDict,
                                        final String compare,
                                        final String function,
                                        final Object... functionObjs) {
        EmptyAssert.allNotNull(likeDict, compare);
        return addHavingSQL(String.format(
                " %s %s %s ",
                SqlJointUtil.convertFunctionSql(function, functionObjs),
                likeDict.key,
                SqlJointUtil.objConvertSql(likeDict.start + compare + likeDict.end)
        ));
    }

    default IBaseHavingEO addHavingCircle(final CircleDict circleDict,
                                          final Object[] compareObjs,
                                          final String function,
                                          final Object... functionObjs) {
        return addHavingSQL(String.format(
                " %s %s ",
                SqlJointUtil.convertFunctionSql(function, functionObjs),
                SqlJointUtil.convertFunctionSql(circleDict.key, compareObjs)
        ));
    }

    default IBaseHavingEO addHavingSQL(final String havingSQL) {
        EmptyAssert.isNotBlank(havingSQL);

        List<String> havingSQLList = getHavingSQLList();
        if (EmptyValidate.isNull(havingSQLList)) {
            havingSQLList = new ArrayList<>();
            setHavingSQLList(havingSQLList);
        }
        if (!havingSQLList.contains(havingSQL)) {
            havingSQLList.add(havingSQL);
        }
        return this;
    }
}
