package org.czh.commons.utils;

import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.FlagAssert;

/**
 * @author : czh
 * description :
 * date : 2021-07-01
 * email 916419307@qq.com
 */
public final class AreaCodeUtil {

    public static String getProvinceCode(String areaCode) {
        EmptyAssert.isNotBlank(areaCode, "区域编码不能空");
        FlagAssert.isTrue(areaCode.length() == 6, "区域编码格式错误");

        if (areaCode.endsWith("0000")) {
            return areaCode;
        }
        return areaCode.substring(0, 2) + "0000";
    }

    public static String getCityCode(String areaCode) {
        EmptyAssert.isNotBlank(areaCode, "区域编码不能空");
        FlagAssert.isTrue(areaCode.length() == 6, "区域编码格式错误");
        FlagAssert.isTrue(areaCode.endsWith("0000"), "当前区域编码为省编码，无法获取城市编码");

        if (areaCode.endsWith("00")) {
            return areaCode;
        }
        return areaCode.substring(0, 4) + "00";
    }
}
