package org.czh.commons.entity.req.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.czh.commons.entity.req.BasePageReqVO;

/**
 * @author : czh
 * description :
 * date : 2021-07-03
 * email 916419307@qq.com
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class BasePageReqTestVO extends BasePageReqVO {

    private static final long serialVersionUID = 2101289642353233639L;

    private String searchName;

}
