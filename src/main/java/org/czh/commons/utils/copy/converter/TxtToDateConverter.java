package org.czh.commons.utils.copy.converter;

import org.czh.commons.utils.DateUtil;
import org.czh.commons.utils.copy.FieldMapping;
import org.czh.commons.utils.copy.IFieldConverter;
import org.czh.commons.validate.EmptyAssert;

import java.util.Date;

/**
 * @author : czh
 * description :
 * date : 2021-06-29
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public final class TxtToDateConverter implements IFieldConverter<String, Date> {
    @Override
    public Date convert(String source, FieldMapping fieldMapping) {
        String expression = fieldMapping.getExpression();
        EmptyAssert.isNotBlank(expression, "[Assertion failed] - The text to date, expression must be filled");
        return DateUtil.parseToDate(source, expression);
    }
}