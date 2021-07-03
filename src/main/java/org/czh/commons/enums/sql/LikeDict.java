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
public enum LikeDict implements IKeyEnum<String> {

    START_LIKE("LIKE", "", "%"), // '${fieldName}%'
    NOT_START_LIKE("NOT LIKE", "", "%"), // '${fieldName}%'
    LIKE("LIKE", "%", "%"), // '%${fieldName}%'
    NOT_LIKE("NOT LIKE", "%", "%"), // '%${fieldName}%'
    END_LIKE("LIKE", "%", ""), // '%${fieldName}'
    NOT_END_LIKE("NOT LIKE", "%", ""), // '%${fieldName}'

    // 占位符
    ;

    public final String key;
    public final String start;
    public final String end;

}
