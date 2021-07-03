package org.czh.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.czh.commons.enums.parent.IDictEnum;
import org.czh.commons.utils.convertor.ArrayConvertor;
import org.czh.commons.utils.convertor.CollectionConvertor;

/**
 * @author : czh
 * description :
 * date : 2021-05-02
 * email 916419307@qq.com
 */
@Getter
@AllArgsConstructor
public enum ConvertorTestDict implements IDictEnum<String, Class<?>> {

    ARRAY("array", ArrayConvertor.class),
    LIST("list", CollectionConvertor.class),
    SET("set", CollectionConvertor.class),

    // 预留扩展位
    ;

    public final String key;
    public final Class<?> value;
}