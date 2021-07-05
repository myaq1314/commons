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

    public enum TypeDict implements IBaseEnum {
        ADD, // 加法
        SUBTRACT, // 减法
        MULTIPLY, // 乘法
        DIVIDE, // 除法

        // 占位符
        ;
    }

    public static BigDecimal parse(@NotNullTag final Double doubleNum) {
        EmptyAssert.isNotNull(doubleNum);
        return new BigDecimal(String.valueOf(doubleNum));
    }

    public static BigDecimal parse(@NotBlankTag String decimalTxt) {
        EmptyAssert.isNotBlank(decimalTxt);

        decimalTxt = decimalTxt.replaceAll(",", "");
        NumAssert.isNumber(decimalTxt);

        return new BigDecimal(decimalTxt);
    }

    public static BigDecimal parseDefault(String decimalTxt) {
        if (EmptyValidate.isBlank(decimalTxt)) {
            return BigDecimal.ZERO;
        }
        decimalTxt = decimalTxt.replaceAll(",", "");
        if (!NumValidate.isNumber(decimalTxt)) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(decimalTxt);
    }

    public static String trim(@NotNullTag BigDecimal decimal) {
        EmptyAssert.isNotNull(decimal);
        return decimal.stripTrailingZeros().toPlainString();
    }

    public static String trim(@NotBlankTag String decimalTxt) {
        EmptyAssert.isNotBlank(decimalTxt);
        return trim(parse(decimalTxt));
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


}
