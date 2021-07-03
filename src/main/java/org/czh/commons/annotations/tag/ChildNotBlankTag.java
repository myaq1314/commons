package org.czh.commons.annotations.tag;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * @author : czh
 * description : 子元素 不为空白字符串限制 标签
 * 包含 @ChildNotNullTag 限制
 * @see org.czh.commons.annotations.tag.ChildNotNullTag
 * date : 2021-04-30
 * email 916419307@qq.com
 */
@Documented
@Target({PARAMETER, FIELD})
@Retention(SOURCE)
public @interface ChildNotBlankTag {
}
