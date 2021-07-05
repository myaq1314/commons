package org.czh.commons.utils.copy;

/**
 * @author : czh
 * description :
 * date : 2021-06-28
 * email 916419307@qq.com
 */
public interface IFieldConverter<S, T> {

    T convert(S source, FieldMapping fieldMapping);

    abstract class None implements IFieldConverter<Object, Object> {

    }
}
