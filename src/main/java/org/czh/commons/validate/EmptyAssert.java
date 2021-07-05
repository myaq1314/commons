package org.czh.commons.validate;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * @author : czh
 * description : 空 断言
 * date : 2021-04-28
 * email 916419307@qq.com
 */
public final class EmptyAssert {

    /*
      -----------------------------null assert-------------------------------
     */

    /**
     * 校验 对象 为空
     *
     * @param t   对象
     * @param <T> 对象类型
     */
    public static <T> void isNull(final T t) {
        isNull(t, "[Assertion failed] - this t argument must be null");
    }

    public static <T> void isNull(final T t, final String message) {
        if (EmptyValidate.isNotNull(t)) {
            throw new RuntimeException(message);
        }
    }

    @SafeVarargs
    public static <T> void allNull(final T... ts) {
        Arrays.stream(ts).forEach(EmptyAssert::isNull);
    }

    /**
     * 校验 对象 不为空
     *
     * @param t   对象
     * @param <T> 对象类型
     */
    public static <T> void isNotNull(final T t) {
        isNotNull(t, "[Assertion failed] - this t argument must not be null");
    }

    public static <T> void isNotNull(final T t, final String message) {
        if (EmptyValidate.isNull(t)) {
            throw new RuntimeException(message);
        }
    }

    @SafeVarargs
    public static <T> void allNotNull(final T... ts) {
        Arrays.stream(ts).forEach(EmptyAssert::isNotNull);
    }


    /*
     * -----------------------------text assert-------------------------------
     */

    /**
     * 判断 文本 是否为空、或为空字符串
     *
     * @param text 文本
     */
    public static void isEmpty(final String text) {
        isEmpty(text, "[Assertion failed] - this text argument must be empty");
    }

    public static void isEmpty(final String text, final String message) {
        if (EmptyValidate.isNotEmpty(text)) {
            throw new RuntimeException(message);
        }
    }

    public static void allEmpty(final String... texts) {
        Arrays.stream(texts).forEach(EmptyAssert::isEmpty);
    }

    /**
     * 判断 文本 是否不为空、不为空字符串
     *
     * @param text 文本
     */
    public static void isNotEmpty(final String text) {
        isNotEmpty(text, "[Assertion failed] - this text argument must not be empty");
    }

    public static void isNotEmpty(final String text, final String message) {
        if (EmptyValidate.isEmpty(text)) {
            throw new RuntimeException(message);
        }
    }

    public static void allNotEmpty(final String... texts) {
        Arrays.stream(texts).forEach(EmptyAssert::isNotEmpty);
    }

    /**
     * 判断 文本 是否为空、或为空字符串、或为空格字符串
     *
     * @param text 文本
     */
    public static void isBlank(final String text) {
        isBlank(text, "[Assertion failed] - this text argument must be blank");
    }

    public static void isBlank(final String text, final String message) {
        if (EmptyValidate.isNotBlank(text)) {
            throw new RuntimeException(message);
        }
    }

    public static void allBlank(final String... texts) {
        Arrays.stream(texts).forEach(EmptyAssert::isBlank);
    }

    /**
     * 判断 文本 是否不为空、不为空字符串、不为空格字符串
     *
     * @param text 文本
     */
    public static void isNotBlank(final String text) {
        isNotBlank(text, "[Assertion failed] - this text argument must not be blank");
    }

    public static void isNotBlank(final String text, final String message) {
        if (EmptyValidate.isBlank(text)) {
            throw new RuntimeException(message);
        }
    }

    public static void allNotBlank(final String... texts) {
        Arrays.stream(texts).forEach(EmptyAssert::isNotBlank);
    }

    /*
     * -----------------------------collection assert-------------------------------
     */

    /**
     * 判断 集合 为空、或为空集
     *
     * @param collection 集合
     */
    public static void isEmpty(final Collection<?> collection) {
        isEmpty(collection, "[Assertion failed] - this collection argument must be empty");
    }

    public static void isEmpty(final Collection<?> collection, final String message) {
        if (EmptyValidate.isNotEmpty(collection)) {
            throw new RuntimeException(message);
        }
    }

    public static void allEmpty(final Collection<?>... collections) {
        Arrays.stream(collections).forEach(EmptyAssert::isEmpty);
    }

    /**
     * 判断 集合 不为空、不为空集
     *
     * @param collection 集合
     */
    public static void isNotEmpty(final Collection<?> collection) {
        isNotEmpty(collection, "[Assertion failed] - this collection argument must not be empty");
    }

    public static void isNotEmpty(final Collection<?> collection, final String message) {
        if (EmptyValidate.isEmpty(collection)) {
            throw new RuntimeException(message);
        }
    }

    public static void allNotEmpty(final Collection<?>... collections) {
        Arrays.stream(collections).forEach(EmptyAssert::isNotEmpty);
    }

    /*
      -----------------------------map assert-------------------------------
     */

    /**
     * 校验 键值对 为空、或为空集
     *
     * @param map 键值对
     */
    public static void isEmpty(final Map<?, ?> map) {
        isEmpty(map, "[Assertion failed] - this map argument must be empty");
    }

    public static void isEmpty(final Map<?, ?> map, final String message) {
        if (EmptyValidate.isNotEmpty(map)) {
            throw new RuntimeException(message);
        }
    }

    public static void allEmpty(final Map<?, ?>... maps) {
        Arrays.stream(maps).forEach(EmptyAssert::isEmpty);
    }

    /**
     * 校验 键值对 不为空、不为空集
     *
     * @param map 键值对
     */
    public static void isNotEmpty(final Map<?, ?> map) {
        isNotEmpty(map, "[Assertion failed] - this map argument must not be empty");
    }

    public static void isNotEmpty(final Map<?, ?> map, final String message) {
        if (EmptyValidate.isEmpty(map)) {
            throw new RuntimeException(message);
        }
    }

    public static void allNotEmpty(final Map<?, ?>... maps) {
        Arrays.stream(maps).forEach(EmptyAssert::isNotEmpty);
    }

    /*
      -----------------------------array assert-------------------------------
     */

    /**
     * 校验 数组 为空、或为空数组
     *
     * @param bs 数组
     */
    public static void isEmpty(final byte[] bs) {
        isEmpty(bs, "[Assertion failed] - this bs array argument must be empty");
    }

    public static void isEmpty(final byte[] bs, final String message) {
        if (EmptyValidate.isNotEmpty(bs)) {
            throw new RuntimeException(message);
        }
    }

    public static void allEmpty(final byte[]... bss) {
        Arrays.stream(bss).forEach(EmptyAssert::isEmpty);
    }

    /**
     * 校验 数组 不为空、不为空数组
     *
     * @param bs 数组
     */
    public static void isNotEmpty(final byte[] bs) {
        isNotEmpty(bs, "[Assertion failed] - this bs array argument must not be empty");
    }

    public static void isNotEmpty(final byte[] bs, final String message) {
        if (EmptyValidate.isEmpty(bs)) {
            throw new RuntimeException(message);
        }
    }

    public static void allNotEmpty(final byte[]... bss) {
        Arrays.stream(bss).forEach(EmptyAssert::isNotEmpty);
    }

    /**
     * 校验 数组 为空、或为空数组
     *
     * @param ss 数组
     */
    public static void isEmpty(final short[] ss) {
        isEmpty(ss, "[Assertion failed] - this ss array argument must be empty");
    }

    public static void isEmpty(final short[] ss, final String message) {
        if (EmptyValidate.isNotEmpty(ss)) {
            throw new RuntimeException(message);
        }
    }

    public static void allEmpty(final short[]... sss) {
        Arrays.stream(sss).forEach(EmptyAssert::isEmpty);
    }

    /**
     * 校验 数组 不为空、不为空数组
     *
     * @param ss 数组
     */
    public static void isNotEmpty(final short[] ss) {
        isNotEmpty(ss, "[Assertion failed] - this ss array argument must not be empty");
    }

    public static void isNotEmpty(final short[] ss, final String message) {
        if (EmptyValidate.isEmpty(ss)) {
            throw new RuntimeException(message);
        }
    }

    public static void allNotEmpty(final short[]... sss) {
        Arrays.stream(sss).forEach(EmptyAssert::isNotEmpty);
    }

    /**
     * 校验 数组 为空、或为空数组
     *
     * @param is 数组
     */
    public static void isEmpty(final int[] is) {
        isEmpty(is, "[Assertion failed] - this is array argument must be empty");
    }

    public static void isEmpty(final int[] is, final String message) {
        if (EmptyValidate.isNotEmpty(is)) {
            throw new RuntimeException(message);
        }
    }

    public static void allEmpty(final int[]... iss) {
        Arrays.stream(iss).forEach(EmptyAssert::isEmpty);
    }

    /**
     * 校验 数组 不为空、不为空数组
     *
     * @param is 数组
     */
    public static void isNotEmpty(final int[] is) {
        isNotEmpty(is, "[Assertion failed] - this is array argument must not be empty");
    }

    public static void isNotEmpty(final int[] is, final String message) {
        if (EmptyValidate.isEmpty(is)) {
            throw new RuntimeException(message);
        }
    }

    public static void allNotEmpty(final int[]... iss) {
        Arrays.stream(iss).forEach(EmptyAssert::isNotEmpty);
    }

    /**
     * 校验 数组 为空、或为空数组
     *
     * @param ls 数组
     */
    public static void isEmpty(final long[] ls) {
        isEmpty(ls, "[Assertion failed] - this ls array argument must be empty");
    }

    public static void isEmpty(final long[] ls, final String message) {
        if (EmptyValidate.isNotEmpty(ls)) {
            throw new RuntimeException(message);
        }
    }

    public static void allEmpty(final long[]... lss) {
        Arrays.stream(lss).forEach(EmptyAssert::isEmpty);
    }

    /**
     * 校验 数组 不为空、不为空数组
     *
     * @param ls 数组
     */
    public static void isNotEmpty(final long[] ls) {
        isNotEmpty(ls, "[Assertion failed] - this ls array argument must not be empty");
    }

    public static void isNotEmpty(final long[] ls, final String message) {
        if (EmptyValidate.isEmpty(ls)) {
            throw new RuntimeException(message);
        }
    }

    public static void allNotEmpty(final long[]... lss) {
        Arrays.stream(lss).forEach(EmptyAssert::isNotEmpty);
    }

    /**
     * 校验 数组 为空、或为空数组
     *
     * @param fs 数组
     */
    public static void isEmpty(final float[] fs) {
        isEmpty(fs, "[Assertion failed] - this fs array argument must be empty");
    }

    public static void isEmpty(final float[] fs, final String message) {
        if (EmptyValidate.isNotEmpty(fs)) {
            throw new RuntimeException(message);
        }
    }

    public static void allEmpty(final float[]... fss) {
        Arrays.stream(fss).forEach(EmptyAssert::isEmpty);
    }

    /**
     * 校验 数组 不为空、不为空数组
     *
     * @param fs 数组
     */
    public static void isNotEmpty(final float[] fs) {
        isNotEmpty(fs, "[Assertion failed] - this fs array argument must not be empty");
    }

    public static void isNotEmpty(final float[] fs, final String message) {
        if (EmptyValidate.isEmpty(fs)) {
            throw new RuntimeException(message);
        }
    }

    public static void allNotEmpty(final float[]... fss) {
        Arrays.stream(fss).forEach(EmptyAssert::isNotEmpty);
    }

    /**
     * 校验 数组 为空、或为空数组
     *
     * @param ds 数组
     */
    public static void isEmpty(final double[] ds) {
        isEmpty(ds, "[Assertion failed] - this ds array argument must be empty");
    }

    public static void isEmpty(final double[] ds, final String message) {
        if (EmptyValidate.isNotEmpty(ds)) {
            throw new RuntimeException(message);
        }
    }

    public static void allEmpty(final double[]... dss) {
        Arrays.stream(dss).forEach(EmptyAssert::isEmpty);
    }

    /**
     * 校验 数组 不为空、不为空数组
     *
     * @param ds 数组
     */
    public static void isNotEmpty(final double[] ds) {
        isNotEmpty(ds, "[Assertion failed] - this ds array argument must not be empty");
    }

    public static void isNotEmpty(final double[] ds, final String message) {
        if (EmptyValidate.isEmpty(ds)) {
            throw new RuntimeException(message);
        }
    }

    public static void allNotEmpty(final double[]... dss) {
        Arrays.stream(dss).forEach(EmptyAssert::isNotEmpty);
    }

    /**
     * 校验 数组 为空、或为空数组
     *
     * @param cs 数组
     */
    public static void isEmpty(final char[] cs) {
        isEmpty(cs, "[Assertion failed] - this cs array argument must be empty");
    }

    public static void isEmpty(final char[] cs, final String message) {
        if (EmptyValidate.isNotEmpty(cs)) {
            throw new RuntimeException(message);
        }
    }

    public static void allEmpty(final char[]... css) {
        Arrays.stream(css).forEach(EmptyAssert::isEmpty);
    }

    /**
     * 校验 数组 不为空、不为空数组
     *
     * @param cs 数组
     */
    public static void isNotEmpty(final char[] cs) {
        isNotEmpty(cs, "[Assertion failed] - this cs array argument must not be empty");
    }

    public static void isNotEmpty(final char[] cs, final String message) {
        if (EmptyValidate.isEmpty(cs)) {
            throw new RuntimeException(message);
        }
    }

    public static void allNotEmpty(final char[]... css) {
        Arrays.stream(css).forEach(EmptyAssert::isNotEmpty);
    }

    /**
     * 校验 数组 为空、或为空数组
     *
     * @param bs 数组
     */
    public static void isEmpty(final boolean[] bs) {
        isEmpty(bs, "[Assertion failed] - this bs array argument must be empty");
    }

    public static void isEmpty(final boolean[] bs, final String message) {
        if (EmptyValidate.isNotEmpty(bs)) {
            throw new RuntimeException(message);
        }
    }

    public static void allEmpty(final boolean[]... bss) {
        Arrays.stream(bss).forEach(EmptyAssert::isEmpty);
    }

    /**
     * 校验 数组 不为空、不为空数组
     *
     * @param bs 数组
     */
    public static void isNotEmpty(final boolean[] bs) {
        isNotEmpty(bs, "[Assertion failed] - this bs array argument must not be empty");
    }

    public static void isNotEmpty(final boolean[] bs, final String message) {
        if (EmptyValidate.isEmpty(bs)) {
            throw new RuntimeException(message);
        }
    }

    public static void allNotEmpty(final boolean[]... bss) {
        Arrays.stream(bss).forEach(EmptyAssert::isNotEmpty);
    }

    /**
     * 校验 数组 为空、或为空数组
     *
     * @param ts  数组
     * @param <T> 数组类型
     */
    public static <T> void isEmpty(final T[] ts) {
        isEmpty(ts, "[Assertion failed] - this ts array argument must be empty");
    }

    public static <T> void isEmpty(final T[] ts, final String message) {
        if (EmptyValidate.isNotEmpty(ts)) {
            throw new RuntimeException(message);
        }
    }

    @SafeVarargs
    public static <T> void allEmpty(final T[]... tss) {
        Arrays.stream(tss).forEach(EmptyAssert::isEmpty);
    }

    /**
     * 校验 数组 不为空、不为空数组
     *
     * @param ts  数组
     * @param <T> 数组类型
     */
    public static <T> void isNotEmpty(final T[] ts) {
        isNotEmpty(ts, "[Assertion failed] - this ts array argument must not be empty");
    }

    public static <T> void isNotEmpty(final T[] ts, final String message) {
        if (EmptyValidate.isEmpty(ts)) {
            throw new RuntimeException(message);
        }
    }

    @SafeVarargs
    public static <T> void allNotEmpty(final T[]... tss) {
        Arrays.stream(tss).forEach(EmptyAssert::isNotEmpty);
    }
}
