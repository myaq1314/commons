package org.czh.commons.entity.eo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.czh.commons.entity.eo.sql.IDistinctEO;
import org.czh.commons.entity.eo.sql.IGroupEO;
import org.czh.commons.entity.eo.sql.IHavingEO;
import org.czh.commons.entity.eo.sql.ILimitEO;
import org.czh.commons.entity.eo.sql.IOrderEO;
import org.czh.commons.entity.eo.sql.ISelectEO;
import org.czh.commons.entity.eo.sql.IWhereEO;

import java.util.List;

/**
 * @author : czh
 * description : sql实体，主要用于更简便操作sql
 * date : 2021-06-21
 * email 916419307@qq.com
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public abstract class BaseViewEO
        implements IDistinctEO, ISelectEO, IWhereEO, IGroupEO, IHavingEO, IOrderEO, ILimitEO, IBaseEO {

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
