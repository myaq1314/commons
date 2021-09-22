package org.czh.commons.utils;

import org.czh.commons.annotations.tag.ChildValueTag;
import org.czh.commons.annotations.tag.IntervalTag;
import org.czh.commons.annotations.tag.NotEmptyTag;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.FlagAssert;
import org.czh.commons.validate.NumAssert;

import java.util.Random;

/**
 * @author : czh
 * description :
 * date : 2021-06-01
 * email 916419307@qq.com
 */
@SuppressWarnings({"unused"})
public final class RandomUtil {

    /**
     * 线程安全
     */
    private static final Random random = new Random();

    public static void main(String[] args) {
        int random = getRandom(1000, 300000, true);
        System.out.println(random); // 176277

        String hexRandom = getHexRandom(1000, 300000, true);
        System.out.println(hexRandom); // 17ef5

        String hexRandomByCircle = getHexRandom(32);
        System.out.println(hexRandomByCircle); // cb714f9-20071b9-3321301-6bb75d5-8cfb

        String hexRandomByArray = getHexRandomUUID("-", 7, 7, 7, 7, 4);
        System.out.println(hexRandomByArray); // cb40e30-56a7af1-34e9d4d-4ecbba4-3f67
    }

    /**
     * 自己指定随机数 每部分 长度
     *
     * @param separator 间隔符
     * @param lengths   每部分长度，子元素允许范围大于等于1
     * @return 随机数 十六进制
     */
    public static String getHexRandomUUID(String separator, @NotEmptyTag @ChildValueTag(min = 1) int... lengths) {
        EmptyAssert.isNotEmpty(lengths);

        separator = separator == null ? "" : separator;
        StringBuilder builder = new StringBuilder();
        for (int length : lengths) {
            builder.append(getHexRandom(length));
            builder.append(separator);
        }
        return builder.substring(0, builder.length() - separator.length());
    }

    /**
     * 单次 获取 十六进制 随机数
     * length 长度，默认为 1 到 7之间，多了，就超出了 十六进制 数字长度
     *
     * @param length 长度
     * @return 单次随机数
     */
    public static String getHexRandom(int length) {
        NumAssert.isPositiveInt(length);

        StringBuilder resultBuilder = new StringBuilder();
        while (length != 0) {
            StringBuilder minBuilder = new StringBuilder("1");
            StringBuilder maxBuilder = new StringBuilder("f");
            if (length >= 7) {
                minBuilder.append("000000");
                maxBuilder.append("ffffff");
                length -= 7;
            } else {
                for (int i = 1; i < length; i++) {
                    minBuilder.append("0");
                    maxBuilder.append("f");
                }
                length = 0;
            }
            int min = Integer.parseInt(minBuilder.toString(), 16);
            int max = Integer.parseInt(maxBuilder.toString(), 16);
            resultBuilder.append(getHexRandom(min, max, true));
        }
        return resultBuilder.toString();
    }

    /**
     * 获取 介于 start 到 end 之间的 随机数
     * 十六进制结果
     * 默认 随机数范围，包含 end
     * 默认 随机数范围，包含 start
     *
     * @param start 开始范围 默认包含
     * @param end   结束范围 默认包含
     * @return 十六进制随机数
     */
    public static String getHexRandom(@IntervalTag(match = "end", min = 1) int start, int end) {
        return getHexRandom(start, end, true);
    }

    /**
     * 获取 介于 start 到 end 之间的 随机数
     * 十六进制结果
     * 指定 随机数范围，是否，包含 end
     * 默认 随机数范围，包含 start
     *
     * @param start 开始范围 默认包含
     * @param end   结束范围 指定包含
     * @return 十六进制随机数
     */
    public static String getHexRandom(@IntervalTag(match = "end", min = 1) int start, int end, boolean containEnd) {
        return Integer.toHexString(getRandom(start, end, containEnd));
    }

    /**
     * 获取 介于 start 到 end 之间的 随机数
     * 十进制结果
     * 默认 随机数范围，包含 end
     * 默认 随机数范围，包含 start
     *
     * @param start 开始范围 默认包含
     * @param end   结束范围 默认包含
     * @return 整数
     */
    public static int getRandom(@IntervalTag(match = "end", min = 1) int start, int end) {
        return getRandom(start, end, true);
    }

    /**
     * 获取 介于 start 到 end 之间的 随机数
     * 十进制结果
     * 指定 随机数范围，是否，包含 end
     * 默认 随机数范围，包含 start
     *
     * @param start 开始范围 默认包含
     * @param end   结束范围 指定包含
     * @return 整数
     */
    public static int getRandom(@IntervalTag(match = "end", min = 1) int start, int end, boolean containEnd) {
        FlagAssert.isTrue(end - start >= 1, "间隔不能小于1");

        int endScope = containEnd ? 1 : 0;
        return random.nextInt(end - start + endScope) + start;
    }
}
