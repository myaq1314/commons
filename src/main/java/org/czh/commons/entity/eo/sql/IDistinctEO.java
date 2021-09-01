package org.czh.commons.entity.eo.sql;

import org.czh.commons.entity.eo.IBaseEO;

/**
 * @author : czh
 * description :
 * date : 2021/9/1
 * email 916419307@qq.com
 */
public interface IDistinctEO extends IBaseEO {

    boolean isDistinctSQL();

    void setDistinctSQL(boolean distinctSQL);

    default IDistinctEO resetDistinct() {
        setDistinctSQL(false);
        return this;
    }
}
