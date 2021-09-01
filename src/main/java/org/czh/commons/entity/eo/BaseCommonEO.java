package org.czh.commons.entity.eo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.czh.commons.entity.eo.sql.IInsertEO;
import org.czh.commons.entity.eo.sql.IUpdateEO;
import org.czh.commons.entity.eo.sql.InsertEO;

import java.util.List;

/**
 * @author : czh
 * description : 通用数据库实体，主要集成一些数据库表公用字段，类似创建时间、修改时间
 * date : 2021-06-21
 * email 916419307@qq.com
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public abstract class BaseCommonEO extends BaseViewEO
        implements IInsertEO, IUpdateEO, IBaseEO {

    private static final long serialVersionUID = -2976191070162397245L;

    protected List<InsertEO> insertEOLList;
    protected List<String> updateSQLList;

}
