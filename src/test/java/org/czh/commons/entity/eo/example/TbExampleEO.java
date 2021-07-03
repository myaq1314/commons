package org.czh.commons.entity.eo.example;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.czh.commons.entity.eo.BasePrimaryEO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : czh
 * description :
 * date : 2021-06-28
 * email 916419307@qq.com
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class TbExampleEO extends BasePrimaryEO {

    private static final long serialVersionUID = 7188796541231057703L;

    private String exampleName;
    private Integer type;
    private String label;
    private Date createTime;
    private BigDecimal score;
    private Double price;

}
