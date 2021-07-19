package org.czh.commons.utils.fastjson.deserializer;

/**
 * @author : czh
 * description :
 * date : 2021-07-19
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public class NoneDeserializer implements IObjectDeserializer<Object, Object> {

    @Override
    public Object deserialize(Object source, String format) {
        return source;
    }
}