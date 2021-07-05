package org.czh.commons.utils.copy.converter;

import org.czh.commons.utils.copy.FieldMapping;
import org.czh.commons.utils.copy.IFieldConverter;
import org.czh.commons.validate.EmptyValidate;
import org.czh.commons.validate.FlagAssert;

import java.math.BigDecimal;

/**
 * @author : czh
 * description :
 * date : 2021-06-29
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public final class MoneyYuanToConverter implements IFieldConverter<BigDecimal, Long> {

    @Override
    public Long convert(BigDecimal source, FieldMapping fieldMapping) {
        String expression = fieldMapping.getExpression();
        if (EmptyValidate.isBlank(expression)) {
            return source.multiply(new BigDecimal(100)).longValue();
        } else {
            int i = Integer.parseInt(expression.trim());
            FlagAssert.isTrue(i % 10 == 0);
            return source.multiply(new BigDecimal(i)).longValue();
        }
    }
}