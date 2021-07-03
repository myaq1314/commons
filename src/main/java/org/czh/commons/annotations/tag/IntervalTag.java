package org.czh.commons.annotations.tag;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * @author : czh
 * description : 间隙标签
 * date : 2021-06-01
 * email 916419307@qq.com
 */
@Documented
@Target({PARAMETER, FIELD})
@Retention(SOURCE)
public @interface IntervalTag {

    String match();

    int min() default 0;

    int max() default Integer.MAX_VALUE;

}
