package org.czh.commons.utils.fastjson.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ContextObjectDeserializer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021-07-01
 * email 916419307@qq.com
 */
public abstract class AbstractContextObjectDeserializer<S, T> extends ContextObjectDeserializer {

    protected static Map<Class<?>, Class<?>> sourceClazzMap = new HashMap<>();

    @SuppressWarnings("unchecked")
    @Override
    public T deserialze(DefaultJSONParser parser,
                        Type type,
                        Object fieldName,
                        String format,
                        int features) {
        Class<?> currentClazz = this.getClass();
        Class<?> sourceClazz = sourceClazzMap.get(currentClazz);
        if (sourceClazz == null) {
            Type genericSuperclass = this.getClass().getGenericSuperclass();
            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
            try {
                sourceClazz = Class.forName(actualTypeArguments[0].getTypeName());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("找不到要转换的属性类型");
            }
            sourceClazzMap.put(currentClazz, sourceClazz);
        }
        S source = parser.parseObject((Type) sourceClazz);
        return deserialize(source, format);
    }

    protected abstract T deserialize(S source, String format);

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
