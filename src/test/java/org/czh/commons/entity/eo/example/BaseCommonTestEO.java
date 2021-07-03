package org.czh.commons.entity.eo.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.czh.commons.entity.eo.BaseCommonEO;

import java.math.BigDecimal;
import java.util.Date;

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
public class BaseCommonTestEO extends BaseCommonEO {

    private static final long serialVersionUID = 5921866216421083858L;

    private Long id;

    private String studentName;

    private Integer grade;

    private BigDecimal score;

    private Date birthday;

}