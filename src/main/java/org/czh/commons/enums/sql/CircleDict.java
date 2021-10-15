package org.czh.commons.enums.sql;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.czh.commons_core.parent.enums.IKeyEnum;

/**
 * @author : czh
 * description :
 * date : 2021-06-25
 * email 916419307@qq.com
 */
@Getter
@AllArgsConstructor
public enum CircleDict implements IKeyEnum<String> {

    IN(" IN "),
    NOT_IN(" NOT IN "),

    // 占位符
    ;

    public final String key;

}
