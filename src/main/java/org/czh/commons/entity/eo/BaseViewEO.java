package org.czh.commons.entity.eo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.czh.commons_core.parent.entity.eo.IBaseEO;

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
public abstract class BaseViewEO implements IBaseEO {

    private static final long serialVersionUID = -4930499248691058610L;

    protected String tableNameSQL;

    protected boolean distinctSQL;

    protected List<String> selectSQLList;

    protected List<String> whereSQLList;

    protected List<String> groupSQLList;

    protected List<String> havingSQLList;

    protected List<String> orderSQLList;

    protected String limitSQL;

}
