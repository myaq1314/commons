package org.czh.commons.entity.eo.sql;

import org.czh.commons.entity.eo.IBaseEO;
import org.czh.commons.validate.EmptyAssert;

/**
 * @author : czh
 * description :
 * date : 2021-06-23
 * email 916419307@qq.com
 */
public interface IBaseLimitEO extends IBaseEO {

    String getLimitSQL();

    void setLimitSQL(String limitSQL);

    default IBaseLimitEO resetLimit() {
        setLimitSQL(null);
        return this;
    }

    default IBaseLimitEO setLimit(final Integer currentPage, final Integer pageSize) {
        EmptyAssert.isNotNull(currentPage);
        EmptyAssert.isNotNull(pageSize);

        setLimitSQL(" " + (currentPage - 1) * pageSize + ", " + pageSize + " ");
        return this;
    }
}
