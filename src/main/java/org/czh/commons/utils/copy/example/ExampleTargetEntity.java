package org.czh.commons.utils.copy.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.czh.commons.entity.IBaseEntity;
import org.czh.commons.utils.copy.CopyFromField;
import org.czh.commons.utils.copy.CopyToField;
import org.czh.commons.utils.fastjson.deserializer.LongToDecimalDeserializer;
import org.czh.commons.utils.fastjson.deserializer.ObjectToTextDeserializer;
import org.czh.commons.utils.fastjson.deserializer.TextToDateDeserializer;
import org.czh.commons.utils.fastjson.deserializer.TextToShortDeserializer;
import org.czh.commons.utils.fastjson.serializer.DateToTextSerializer;
import org.czh.commons.utils.fastjson.serializer.DecimalToLongSerializer;
import org.czh.commons.utils.fastjson.serializer.ObjectToTextSerializer;
import org.czh.commons.utils.fastjson.serializer.TextToIntegerSerializer;

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
public class ExampleTargetEntity implements IBaseEntity {

    private static final long serialVersionUID = -8454155070429728151L;

    @CopyFromField(match = "exampleSourceEntityName")
    @CopyToField(match = "exampleSourceEntityName")
    private String exampleTargetEntityName;

    @CopyFromField(match = "createDate", using = DateToTextSerializer.class, format = "yyyy-MM-dd HH:mm:ss")
    @CopyToField(match = "createDate", using = TextToDateDeserializer.class, format = "yyyy-MM-dd HH:mm:ss")
    private String createDateText;

    @CopyFromField(exclude = true)
    @CopyToField(exclude = true)
    private Boolean hidden;

    @CopyFromField(using = TextToIntegerSerializer.class)
    @CopyToField(using = ObjectToTextDeserializer.class)
    private Integer exampleNum;

    @CopyFromField(match = "yuan", using = DecimalToLongSerializer.class, format = "100")
    @CopyToField(match = "yuan", using = LongToDecimalDeserializer.class, format = "100")
    private Long fen;

    private Character character;

    private Integer id;

    @CopyFromField(using = ObjectToTextSerializer.class)
    @CopyToField(using = TextToShortDeserializer.class)
    private String age;

}
