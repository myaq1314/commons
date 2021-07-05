package org.czh.commons.utils;

import java.util.UUID;

/**
 * @author : czh
 * description :
 * date : 2021-06-01
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public final class UUIDUtil {

    public static void main(String[] args) {
        String primevalUUID = getPrimevalUUID();
        System.out.println(primevalUUID);

        String trimUUID = getTrimUUID();
        System.out.println(trimUUID);

        String ownPrimevalUUID = getOwnPrimevalUUID();
        System.out.println(ownPrimevalUUID);

        String ownTrimUUID = getOwnTrimUUID();
        System.out.println(ownTrimUUID);
    }

    /**
     * 获取 原始 的 UUID
     * eg: 83225586-b596-4944-b86e-d04cfb2a3219
     */
    public static String getPrimevalUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取 去除 短横杠 的 UUID
     * eg: 23f69d123e384264bdcd375d54230adf
     */
    public static String getTrimUUID() {
        return getPrimevalUUID().replace("-", "");
    }

    /**
     * 获取 自定义 原始 的 UUID
     * eg: 83225586-b596-4944-b86e-d04cfb2a3219
     */
    public static String getOwnPrimevalUUID() {
        return RandomUtil.getHexRandomByArray("-", 8, 4, 4, 4, 12);
    }

    /**
     * 获取 自定义 去除 短横杠 的 UUID
     * eg: 23f69d123e384264bdcd375d54230adf
     */
    public static String getOwnTrimUUID() {
        return RandomUtil.getHexRandomByArray(null, 8, 4, 4, 4, 12);
    }
}
