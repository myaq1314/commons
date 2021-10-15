package org.czh.commons.entity.eo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.czh.commons_core.parent.entity.eo.IBaseEO;

/**
 * @author : czh
 * description : 有自增主键数据库表实体
 * date : 2021-06-21
 * email 916419307@qq.com
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public abstract class BasePrimaryEO extends BaseCommonEO implements IBaseEO {

    private static final long serialVersionUID = -8527963434331940231L;

}
