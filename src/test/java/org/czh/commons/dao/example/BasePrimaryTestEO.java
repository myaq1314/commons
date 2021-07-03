package org.czh.commons.dao.example;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.czh.commons.entity.eo.BasePrimaryEO;

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
public class BasePrimaryTestEO extends BasePrimaryEO {

    private static final long serialVersionUID = 5899355882436641497L;

    private String name;

    private int age;

    private BigDecimal score;

}
