package org.czh.commons.entity.eo.example;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.czh.commons.entity.eo.BaseQueryEO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : czh
 * description :
 * date : 2021-06-28
 * email 916419307@qq.com
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ExampleQueryEO extends BaseQueryEO {

    private static final long serialVersionUID = 7188796541231057703L;

    private String exampleName;
    private Integer type;
    private String label;
    private Date createTime;
    private BigDecimal score;
    private Double price;

}
