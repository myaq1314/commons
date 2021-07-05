package org.czh.commons.utils.tsdb.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.czh.commons.enums.parent.IKeyEnum;

/**
 * @author : czh
 * description :
 * date : 2021-07-01
 * email 916419307@qq.com
 */
@Getter
@AllArgsConstructor
public enum FilterTypeDict implements IKeyEnum<String> {

    LITERAL_OR("literal_or"), // tagv的过滤规则: 精确匹配多项迭代值，多项迭代值以'|'分隔，大小写敏感
    ILITERAL_OR("iliteral_or"), // tagv的过滤规则: 精确匹配多项迭代值，多项迭代值以'|'分隔，忽略大小写
    NOT_LITERAL_OR("not_literal_or"), // tagv的过滤规则: 通配符取非匹配，大小写敏感
    NOT_ILITERAL_OR("not_iliteral_or"), // tagv的过滤规则: 通配符取非匹配，忽略大小写
    WILDCARD("wildcard"), // tagv的过滤规则: 通配符匹配，大小写敏感
    IWILDCARD("iwildcard"), // tagv的过滤规则: 通配符匹配，忽略大小写
    REGEXP("regexp"), // tagv的过滤规则: 正则表达式匹配
    NOT_KEY("not_key"), // 跳过拥有这个tagv的数据

    // 占位符
    ;

    public final String key;
}
