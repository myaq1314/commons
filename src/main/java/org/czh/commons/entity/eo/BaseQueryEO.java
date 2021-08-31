package org.czh.commons.entity.eo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.czh.commons.entity.eo.sql.IBaseGroupEO;
import org.czh.commons.entity.eo.sql.IBaseHavingEO;
import org.czh.commons.entity.eo.sql.IBaseLimitEO;
import org.czh.commons.entity.eo.sql.IBaseOrderEO;
import org.czh.commons.entity.eo.sql.IBaseSelectEO;
import org.czh.commons.entity.eo.sql.IBaseWhereEO;

import java.util.List;

/**
 * @author : czh
 * description : sql实体，主要用于更简便操作sql
 * date : 2021-06-21
 * email 916419307@qq.com
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public abstract class BaseQueryEO
        implements IBaseSelectEO,
        IBaseWhereEO, IBaseGroupEO, IBaseHavingEO, IBaseOrderEO, IBaseLimitEO, IBaseEO {

    private static final long serialVersionUID = -2418646692491059691L;

    protected String tableName;

    protected boolean distinctSQL;

    protected List<String> selectSQLList;

    protected List<String> whereSQLList;

    protected List<String> groupSQLList;

    protected List<String> orderSQLList;

    protected List<String> havingSQLList;

    protected String limitSQL;

}
