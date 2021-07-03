package org.czh.commons.entity.domain.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.czh.commons.entity.domain.IBaseDO;
import org.czh.commons.validate.EmptyValidate;

import java.math.BigDecimal;

/**
 * @author : czh
 * description :
 * date : 2021-07-03
 * email 916419307@qq.com
 */
@ToString
@EqualsAndHashCode
public class BaseDOTest implements IBaseDO {

    private static final long serialVersionUID = 844036830229959347L;

    private final BigDecimal yuan;
    @Getter
    private Long fen;

    public BaseDOTest(BigDecimal yuan) {
        this.yuan = yuan;
        if (EmptyValidate.isNotNull(this.yuan)) {
            this.fen = yuan.multiply(new BigDecimal(100)).longValue();
        }
    }

    public static void main(String[] args) {
        // BaseDOTest(yuan=2.3, fen=230)
        System.out.println(new BaseDOTest(new BigDecimal("2.3")));
    }
}
