package org.czh.commons.validate;

/**
 * @author : czh
 * description : 手机号码 判断
 * date : 2021-04-28
 * email 916419307@qq.com
 */
public final class MobileValidate {

    /**
     * 验证字符串是否为手机号码
     *
     * @param mobile 手机号码字符串
     * @return true 为手机号码，false 不为手机号码
     */
    public static boolean isMobile(final String mobile) {
        return EmptyValidate.isNotBlank(mobile) && mobile.matches("^(1[3-9])\\d{9}$");
    }
}
