package org.czh.commons.entity.eo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.czh.commons_core.asserts.EmptyAssert;
import org.czh.commons_core.parent.entity.eo.IBaseEO;

/**
 * @author : czh
 * description :
 * date : 2021-06-22
 * email 916419307@qq.com
 */
@Getter
@ToString
@EqualsAndHashCode
public class InsertEO implements IBaseEO {

    private static final long serialVersionUID = -5834544604142832045L;

    private final String columnName;
    private final String columnValue;

    public InsertEO(String columnName, String columnValue) {
        EmptyAssert.allNotBlank(columnName, columnValue);
        this.columnName = columnName;
        this.columnValue = columnValue;
    }
}
