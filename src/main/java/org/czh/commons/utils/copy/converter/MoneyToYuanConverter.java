package org.czh.commons.utils.copy.converter;

import org.czh.commons.utils.copy.FieldMapping;
import org.czh.commons.utils.copy.IFieldConverter;
import org.czh.commons.validate.EmptyValidate;
import org.czh.commons.validate.FlagAssert;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author : czh
 * description :
 * date : 2021-06-29
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public final class MoneyToYuanConverter implements IFieldConverter<Long, BigDecimal> {

    @Override
    public BigDecimal convert(Long source, FieldMapping fieldMapping) {
        String expression = fieldMapping.getExpression();
        if (EmptyValidate.isBlank(expression)) {
            return new BigDecimal(source).divide(BigDecimal.valueOf(100), 2, RoundingMode.DOWN);
        } else {
            int i = Integer.parseInt(expression.trim());
            FlagAssert.isTrue(i % 10 == 0);
            return new BigDecimal(source).divide(BigDecimal.valueOf(i), expression.trim().length() - 1, RoundingMode.DOWN);
        }
    }
}