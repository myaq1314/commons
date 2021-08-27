package org.czh.commons.validate;

/**
 * @author : czh
 * description : 手机号码 断言
 * date : 2021-04-28
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public final class MobileAssert {

    /**
     * 验证字符串为手机号码
     *
     * @param mobile 手机号码字符串
     */
    public static void isMobile(final String mobile) {
        isMobile(mobile, "[Assertion failed] - this mobile argument must be a mobile ");
    }

    public static void isMobile(final String mobile, final String message) {
        if (!MobileValidate.isMobile(mobile)) {
            throw new IllegalStateException(message);
        }
    }
}
