package org.czh.commons.entity.header.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.czh.commons.entity.header.IBaseHeaderVO;

/**
 * @author : czh
 * description :
 * date : 2021-07-03
 * email 916419307@qq.com
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BaseHeaderTestVO implements IBaseHeaderVO {

    private static final long serialVersionUID = 3240822579156997579L;

    protected String token;

    protected String sign;

}
