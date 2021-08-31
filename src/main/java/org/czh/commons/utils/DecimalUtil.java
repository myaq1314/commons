package org.czh.commons.utils;

import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.annotations.tag.ValueTag;
import org.czh.commons.enums.parent.IBaseEnum;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;
import org.czh.commons.validate.NumAssert;
import org.czh.commons.validate.NumValidate;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author : czh
 * description :
 * date : 2021-07-01
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public final class DecimalUtil {

    public static BigDecimal parse(@NotNullTag final Double doubleNum) {
        EmptyAssert.isNotNull(doubleNum);
        return new BigDecimal(String.valueOf(doubleNum));
    }

    public static BigDecimal parse(@NotBlankTag String decimalText) {
        EmptyAssert.isNotBlank(decimalText);

        decimalText = decimalText.replaceAll(",", "");
        NumAssert.isNumber(decimalText);

        return new BigDecimal(decimalText);
    }

    public static BigDecimal parseDefault(String decimalText) {
        if (EmptyValidate.isBlank(decimalText)) {
            return BigDecimal.ZERO;
        }
        decimalText = decimalText.replaceAll(",", "");
        if (!NumValidate.isNumber(decimalText)) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(decimalText);
    }

    public static String trim(@NotNullTag BigDecimal decimal) {
        EmptyAssert.isNotNull(decimal);
        return decimal.stripTrailingZeros().toPlainString();
    }

    public static String trim(@NotBlankTag String decimalText) {
        EmptyAssert.isNotBlank(decimalText);
        return trim(parse(decimalText));
    }

    public static BigDecimal format(@NotNullTag BigDecimal decimal,
                                    @ValueTag(min = 0) int length,
                                    @NotNullTag RoundingMode roundingMode) {
        EmptyAssert.allNotNull(decimal, roundingMode);
        NumAssert.isMin(length, 0);
        return decimal.setScale(length, roundingMode);
    }

    public static BigDecimal calculate(@NotNullTag final BigDecimal one,
                                       @NotNullTag final TypeDict typeDict,
                                       @NotNullTag final BigDecimal two) {
        EmptyAssert.allNotNull(one, typeDict, two);
        switch (typeDict) {
            case ADD:
                return one.add(two);
            case SUBTRACT:
                return one.subtract(two);
            case MULTIPLY:
                return one.multiply(two);
            case DIVIDE:
                //noinspection BigDecimalMethodWithoutRoundingCalled
                return one.divide(two);
        }
        throw new RuntimeException("未知的计算类型");
    }

    public enum TypeDict implements IBaseEnum {
        ADD, // 加法
        SUBTRACT, // 减法
        MULTIPLY, // 乘法
        DIVIDE, // 除法

        // 占位符
        ;
    }

}
