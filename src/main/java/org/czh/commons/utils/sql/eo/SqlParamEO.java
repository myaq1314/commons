package org.czh.commons.utils.sql.eo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.czh.commons_core.parent.entity.eo.IBaseEO;

import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021/9/27
 * email 916419307@qq.com
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SqlParamEO implements IBaseEO {

    private static final long serialVersionUID = 4618317583779189397L;

    private String sql;
    private List<Object> params;

}