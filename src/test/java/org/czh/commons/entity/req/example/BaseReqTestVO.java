package org.czh.commons.entity.req.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.czh.commons.entity.req.IBaseReqVO;

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
public class BaseReqTestVO implements IBaseReqVO {

    private static final long serialVersionUID = 4883651650707110295L;

    private String searchName;

}
