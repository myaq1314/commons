package org.czh.commons.utils;

import org.czh.commons.annotations.tag.ChildValueTag;
import org.czh.commons.annotations.tag.IntervalTag;
import org.czh.commons.annotations.tag.NotEmptyTag;
import org.czh.commons.annotations.tag.ValueTag;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.FlagAssert;

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

        String hexRandomByCircle = getHexRandomByCircle("-", 32);
        System.out.println(hexRandomByCircle); // cb714f9-20071b9-3321301-6bb75d5-8cfb

        String hexRandomByArray = getHexRandomByArray("-", 7, 7, 7, 7, 4);
        System.out.println(hexRandomByArray); // cb40e30-56a7af1-34e9d4d-4ecbba4-3f67
    }

    /**
     * 自己指定随机数 每部分 长度
     *
     * @param separator 间隔符
     * @param lengths   每部分长度，子元素允许范围大于等于1
     * @return 随机数 十六进制
     */
    public static String getHexRandomByArray(String separator,
                                             @NotEmptyTag @ChildValueTag(min = 1) int... lengths) {
        EmptyAssert.isNotEmpty(lengths);

        separator = separator == null ? "" : separator;
        StringBuilder builder = new StringBuilder();
        for (int length : lengths) {
            builder.append(getHexRandomByCircle(null, length));
            builder.append(separator);
        }
        return builder.substring(0, builder.length() - separator.length());
    }

    /**
     * 生成 指定 长度 的随机数
     *
     * @param separator 间隔符
     * @param length    总长度（不包含间隔符）允许范围大于等于1
     * @return 指定长度的随机数
     */
    public static String getHexRandomByCircle(String separator,
                                              @ValueTag(min = 1) int length) {
        FlagAssert.isTrue(length >= 1);

        int temp = length;
        separator = separator == null ? "" : separator;
        StringBuilder builder = new StringBuilder();
        while (temp > 0) {
            if (temp <= 7) {
                builder.append(getOnceHexRandom(temp));
                break;
            }
            builder.append(getOnceHexRandom(7));
            builder.append(separator);
            temp -= 7;
        }
        return builder.toString();
    }

    /**
     * 单次 获取 十六进制 随机数
     * length 长度，默认为 1 到 7之间，多了，就超出了 十六进制 数字长度
     *
     * @param length 单次长度，允许范围 1 - 7
     * @return 单次随机数
     */
    private static String getOnceHexRandom(@ValueTag(min = 1, max = 7) int length) {
        FlagAssert.isTrue(length >= 1 && length <= 7);

        int min = 0;
        int max;
        StringBuilder builder = new StringBuilder("1");
        for (int i = 0; i < length; i++) {
            builder.append("0");
            if (i == length - 2) {
                min = Integer.parseInt(builder.toString(), 16);
            }
        }
        max = Integer.parseInt(builder.toString(), 16);
        return getHexRandom(min, max);
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
