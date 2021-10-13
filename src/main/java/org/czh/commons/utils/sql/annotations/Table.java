package org.czh.commons.utils.sql.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author : czh
 * description :
 * date : 2021/9/27
 * email 916419307@qq.com
 */
@Documented
@Target({TYPE})
@Retention(RUNTIME)
@SuppressWarnings("unused")
public @interface Table {

    String name();

}
