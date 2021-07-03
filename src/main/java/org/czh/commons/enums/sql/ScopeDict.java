package org.czh.commons.enums.sql;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.czh.commons.enums.parent.IKeyEnum;

/**
 * @author : czh
 * description :
 * date : 2021-06-25
 * email 916419307@qq.com
 */
@Getter
@AllArgsConstructor
public enum ScopeDict implements IKeyEnum<String> {

    GREATER_EQUAL(">="),
    GREATER(">"),
    EQUAL("="),
    UNEQUAL("!="),
    LESS("<"),
    LESS_EQUAL("<="),

    // 占位符
    ;

    public final String key;

}
