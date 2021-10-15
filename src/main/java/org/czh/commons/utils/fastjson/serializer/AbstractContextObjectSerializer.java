package org.czh.commons.utils.fastjson.serializer;

import com.alibaba.fastjson.serializer.BeanContext;
import com.alibaba.fastjson.serializer.ContextObjectSerializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import org.czh.commons_core.copy.IObjectSerializer;
import org.czh.commons_core.validate.EmptyValidate;

import java.lang.reflect.Type;

/**
 * @author : czh
 * description : 格式化输出
 * date : 2021-07-01
 * email 916419307@qq.com
 */
public abstract class AbstractContextObjectSerializer<S, T>
        implements IObjectSerializer<S, T>, ContextObjectSerializer {

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
            target = serialize(source, null);
        } else {
            target = serialize(source, context.getFormat());
        }
        if (EmptyValidate.isNotNull(target)) {
            serializer.write(target);
        }
    }

}
