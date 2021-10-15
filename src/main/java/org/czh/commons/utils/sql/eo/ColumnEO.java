package org.czh.commons.utils.sql.eo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.czh.commons_core.asserts.EmptyAssert;
import org.czh.commons_core.parent.entity.eo.IBaseEO;

/**
 * @author : czh
 * description :
 * date : 2021/9/29
 * email 916419307@qq.com
 */
@Getter
@ToString
@EqualsAndHashCode
public class ColumnEO implements IBaseEO {

    private static final long serialVersionUID = -3024742276165335306L;

    private final String column;

    private ColumnEO(String column) {
        this.column = column;
    }

    public static ColumnEO one(String column) {
        return new ColumnEO(column);
    }

    public static ColumnEO[] array(String... columns) {
        EmptyAssert.isNotEmpty(columns);

        ColumnEO[] array = new ColumnEO[columns.length];
        for (int i = 0; i < columns.length; i++) {
            array[i] = new ColumnEO(columns[i]);
        }
        return array;
    }
}
