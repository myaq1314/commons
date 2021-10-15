package org.czh.commons.entity.eo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.czh.commons_core.parent.entity.eo.IBaseEO;

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
public abstract class BaseCommonEO extends BaseViewEO implements IBaseEO {

    private static final long serialVersionUID = 6634357730206375008L;

    protected List<InsertEO> insertSQLEOLList;
    protected List<String> updateSQLList;

}
