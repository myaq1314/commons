package org.czh.commons.annotations.tag;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * @author : czh
 * description : 值大小限制 标签
 * date : 2021-07-03
 * email 916419307@qq.com
 */
@Documented
@Target({PARAMETER, FIELD})
@Retention(SOURCE)
public @interface ValueTag {

    int min() default Integer.MIN_VALUE;

    int max() default Integer.MAX_VALUE;

}