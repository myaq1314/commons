package org.czh.commons.validate;

/**
 * @author : czh
 * description :
 * date : 2021-06-26
 * email 916419307@qq.com
 */
public final class FlagValidate {

    public static boolean isTrue(final Boolean flag) {
        return EmptyValidate.isNotNull(flag) && flag;
    }

    public static boolean isFalse(final Boolean flag) {
        return !isTrue(flag);
    }
}
