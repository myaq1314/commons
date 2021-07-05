package org.czh.commons.utils.copy.converter;

import org.czh.commons.utils.copy.FieldMapping;
import org.czh.commons.utils.copy.IFieldConverter;

/**
 * @author : czh
 * description :
 * date : 2021-06-29
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public final class ObjectToTxtConverter implements IFieldConverter<Object, String> {

    @Override
    public String convert(Object source, FieldMapping fieldMapping) {
        return source.toString();
    }
}