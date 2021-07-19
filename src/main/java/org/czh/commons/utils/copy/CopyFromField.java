package org.czh.commons.utils.copy;

import org.czh.commons.utils.fastjson.serializer.IObjectSerializer;
import org.czh.commons.utils.fastjson.serializer.NoneSerializer;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : czh
 * description :
 * date : 2021-07-21
 * email 916419307@qq.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
@Documented
public @interface CopyFromField {

    // 匹配
    String match() default "";

    // 格式化输入
    Class<? extends IObjectSerializer<?, ?>> using() default NoneSerializer.class;

    // 排除
    boolean exclude() default false;

    // 格式化
    String format() default "";

}