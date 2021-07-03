package org.czh.commons.enums.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.czh.commons.enums.parent.IKeyEnum;

/**
 * @author : czh
 * description :
 * date : 2021-06-27
 * email 916419307@qq.com
 */
@Getter
@AllArgsConstructor
public enum ExampleKeyEnum implements IKeyEnum<String> {

    NONE("NONE"),

    // 占位符
    ;

    public final String key;

}
