package org.czh.commons.utils.fastjson.serializer;

import com.alibaba.fastjson.serializer.BeanContext;
import com.alibaba.fastjson.serializer.ContextObjectSerializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import org.czh.commons.validate.EmptyValidate;

import java.lang.reflect.Type;

/**
 * @author : czh
 * description :
 * date : 2021-07-01
 * email 916419307@qq.com
 */
public abstract class AbstractContextObjectSerializer<S, T> implements ContextObjectSerializer {

    @Override
    public void write(JSONSerializer serializer,
                      Object object,
                      Object fieldName,
                      Type fieldType,
                      int features) {
        write(serializer, object, null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void write(JSONSerializer serializer, Object object, BeanContext context) {
        S source = (S) object;
        T target;
        if (EmptyValidate.isNull(context)) {
            target = write(source, null);
        } else {
            target = write(source, context.getFormat());
        }
        if (EmptyValidate.isNotNull(target)) {
            serializer.write(target);
        }
    }

    protected abstract T write(S source, String format);

}
