package org.czh.commons.entity.eo.sql;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.czh.commons.entity.eo.IBaseEO;

/**
 * @author : czh
 * description :
 * date : 2021-06-22
 * email 916419307@qq.com
 */
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class InsertEO implements IBaseEO {

    private static final long serialVersionUID = 6797807199159461176L;

    private String columnName;
    private String columnValue;

}
