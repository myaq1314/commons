package org.czh.commons.enums.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.czh.commons.enums.parent.IColumnEnum;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : czh
 * description :
 * date : 2021-07-05
 * email 916419307@qq.com
 */
@Getter
@AllArgsConstructor
public enum ExampleCommonEnum implements IColumnEnum {

    EXAMPLE_NAME("example_name", "exampleName", String.class),
    TYPE("type", "type", Integer.class),
    LABEL("label", "label", String.class),
    CREATE_TIME("create_time", "createTime", Date.class),
    SCORE("score", "score", BigDecimal.class),
    PRICE("price", "price", Double.class),

    // 占位符
    ;

    public final String column;
    public final String field;
    public final Class<?> type;
}
