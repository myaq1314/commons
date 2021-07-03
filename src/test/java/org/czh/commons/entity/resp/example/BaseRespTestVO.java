package org.czh.commons.entity.resp.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.czh.commons.entity.resp.IBaseRespVO;

import java.math.BigDecimal;

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
public class BaseRespTestVO implements IBaseRespVO {

    private static final long serialVersionUID = 4679274593775701936L;

    private Long id;

    private String name;

    private Integer age;

    private BigDecimal score;

}
