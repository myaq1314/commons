package org.czh.commons.entity.eo.example;

import lombok.AllArgsConstructor;
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
 * date : 2021-06-25
 * email 916419307@qq.com
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class StudentTestEO extends BasePrimaryEO {

    private static final long serialVersionUID = 5517784455884306257L;

    private String studentName;

    private Integer grade;

    private BigDecimal score;

    private Date birthday;

}
