package org.czh.commons.enums.sql;

import org.czh.commons.enums.parent.IBaseEnum;
import org.czh.commons.utils.convertor.EnumConvertor;
import org.czh.commons.validate.EmptyValidate;

import java.util.Set;

/**
 * @author : czh
 * description :
 * date : 2021/9/28
 * email 916419307@qq.com
 */
public enum SpecialKey implements IBaseEnum {

    serialVersionUID,
    insertSQLEOLList,
    updateSQLList,
    tableNameSql,
    distinctSQL,
    selectSQLList,
    whereSQLList,
    groupSQLList,
    havingSQLList,
    orderSQLList,
    limitSQL,

    // 占位符
    ;

    private static Set<String> specialKeySet;

    public static Set<String> getSpecialKeySet() {
        if (EmptyValidate.isNull(specialKeySet)) {
            specialKeySet = EnumConvertor.convertToSet(SpecialKey.class, IBaseEnum::getName);
        }
        return specialKeySet;
    }
}
