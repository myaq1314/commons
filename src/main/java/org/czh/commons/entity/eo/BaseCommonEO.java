package org.czh.commons.entity.eo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.czh.commons.entity.eo.sql.IBaseInsertEO;
import org.czh.commons.entity.eo.sql.IBaseUpdateEO;
import org.czh.commons.entity.eo.sql.InsertEO;

import java.util.List;

/**
 * @author : czh
 * description : 通用数据库实体，主要集成一些数据库表公用字段，类似创建时间、修改时间
 * date : 2021-06-21
 * email 916419307@qq.com
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public abstract class BaseCommonEO extends BaseQueryEO
        implements IBaseInsertEO, IBaseUpdateEO, IBaseEO {

    private static final long serialVersionUID = -2976191070162397245L;

    protected List<InsertEO> insertEOLList;
    protected List<String> updateSQLList;

}
