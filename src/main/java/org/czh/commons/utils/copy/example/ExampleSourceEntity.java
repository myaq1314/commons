package org.czh.commons.utils.copy.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.czh.commons.utils.copy.CopyConverter;
import org.czh.commons.utils.copy.converter.DateToTxtConverter;
import org.czh.commons.utils.copy.converter.MoneyYuanToConverter;
import org.czh.commons.utils.copy.converter.ObjectToTxtConverter;
import org.czh.commons.utils.copy.converter.TxtToIntegerConverter;
import org.czh.commons.entity.IBaseEntity;

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
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ExampleSourceEntity implements IBaseEntity {

    private static final long serialVersionUID = -7099503280479595580L;

    @CopyConverter(name = "exampleTargetEntityName")
    private String exampleSourceEntityName;

    @CopyConverter(name = "createDateTxt", using = DateToTxtConverter.class, expression = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @CopyConverter(exclude = true)
    private Boolean hidden;

    @CopyConverter(using = TxtToIntegerConverter.class)
    private String exampleNum;

    @CopyConverter(name = "fen", using = MoneyYuanToConverter.class, expression = "100")
    private BigDecimal yuan;

    private Character character;

    private Long id;

    @CopyConverter(using = ObjectToTxtConverter.class)
    private Short age;

}
