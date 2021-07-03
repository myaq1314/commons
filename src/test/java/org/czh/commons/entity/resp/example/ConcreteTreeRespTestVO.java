package org.czh.commons.entity.resp.example;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.czh.commons.entity.resp.ConcreteTreeRespVO;

import java.math.BigDecimal;

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
public class ConcreteTreeRespTestVO extends ConcreteTreeRespVO<ConcreteTreeRespTestVO> {

    private static final long serialVersionUID = 152586813114325142L;

    private Long id;

    private Long parentId;

    private String name;

    private Integer age;

    private BigDecimal score;

}
