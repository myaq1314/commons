package org.czh.commons.utils.copy.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.czh.commons.entity.IBaseEntity;
import org.czh.commons.utils.copy.CopyFromField;
import org.czh.commons.utils.copy.CopyToField;
import org.czh.commons.utils.fastjson.deserializer.DateToTextDeserializer;
import org.czh.commons.utils.fastjson.deserializer.DecimalToLongDeserializer;
import org.czh.commons.utils.fastjson.deserializer.ObjectToTextDeserializer;
import org.czh.commons.utils.fastjson.deserializer.TextToIntegerDeserializer;
import org.czh.commons.utils.fastjson.serializer.LongToDecimalSerializer;
import org.czh.commons.utils.fastjson.serializer.ObjectToTextSerializer;
import org.czh.commons.utils.fastjson.serializer.TextToDateSerializer;
import org.czh.commons.utils.fastjson.serializer.TextToShortSerializer;

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

    @CopyFromField(match = "exampleTargetEntityName")
    @CopyToField(match = "exampleTargetEntityName")
    private String exampleSourceEntityName;

    @CopyFromField(match = "createDateText", using = TextToDateSerializer.class, format = "yyyy-MM-dd HH:mm:ss")
    @CopyToField(match = "createDateText", using = DateToTextDeserializer.class, format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @CopyFromField(exclude = true)
    @CopyToField(exclude = true)
    private Boolean hidden;

    @CopyFromField(using = ObjectToTextSerializer.class)
    @CopyToField(using = TextToIntegerDeserializer.class)
    private String exampleNum;

    @CopyFromField(match = "fen", using = LongToDecimalSerializer.class, format = "100")
    @CopyToField(match = "fen", using = DecimalToLongDeserializer.class, format = "100")
    private BigDecimal yuan;

    private Character character;

    private Long id;

    @CopyFromField(using = TextToShortSerializer.class)
    @CopyToField(using = ObjectToTextDeserializer.class)
    private Short age;

}
