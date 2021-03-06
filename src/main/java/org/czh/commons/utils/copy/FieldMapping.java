package org.czh.commons.utils.copy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.czh.commons.entity.IBaseEntity;

import java.lang.reflect.Field;

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
public class FieldMapping implements IBaseEntity {

    private static final long serialVersionUID = -3384540625711971082L;

    private Field sourceField;
    private Field targetField;
    @SuppressWarnings("rawtypes")
    private Class<? extends IFieldConverter> fieldConverterClazz;
    private String expression;

}
