package org.czh.commons.utils.copy;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : czh
 * description :
 * date : 2021-06-28
 * email 916419307@qq.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
@Documented
public @interface CopyConverter {

    String name() default "";

    Class<? extends IFieldConverter<?, ?>> using() default IFieldConverter.None.class;

    boolean exclude() default false;

    String expression() default "";

}
