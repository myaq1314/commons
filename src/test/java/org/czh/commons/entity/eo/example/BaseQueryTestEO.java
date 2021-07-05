package org.czh.commons.entity.eo.example;

import lombok.AllArgsConstructor;
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
 * date : 2021-07-03
 * email 916419307@qq.com
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class BaseQueryTestEO extends BaseQueryEO {

    private static final long serialVersionUID = 781235697309328917L;

    private Long id;

    private String studentName;

    private Integer grade;

    private BigDecimal score;

    private Date birthday;

}
