package org.czh.commons.validate;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * @author : czh
 * description : 空 判断
 * date : 2021-04-28
 * email 916419307@qq.com
 */
public final class EmptyValidate {

    /*
      -----------------------------null validate-------------------------------
     */

    /**
     * 校验 对象 为空
     * 属于 null
     *
     * @param t   对象
     * @param <T> 对象类型
     * @return true 对象为空
     */
    public static <T> boolean isNull(final T t) {
        return t == null;
    }

    /**
     * 校验 对象 不为空
     * 不属于 null
     *
     * @param t   对象
     * @param <T> 对象类型
     * @return true 对象不为空
     */
    public static <T> boolean isNotNull(final T t) {
        return t != null;
    }

    /*
      -----------------------------text validate-------------------------------
     */

    /**
     * 判断 文本 是否为空、或为空字符串
     * 属于 null || ""
     *
     * @param text 文本
     * @return true 文本为空、或为空字符串
     */
    public static boolean isEmpty(final String text) {
        return text == null || text.length() == 0;
    }

    /**
     * 判断 文本 是否不为空、不为空字符串
     * 不属于 null && ""
     *
     * @param text 文本
     * @return true 文本不为空、不为空字符串
     */
    public static boolean isNotEmpty(final String text) {
        return !isEmpty(text);
    }

    /**
     * 判断 文本 是否为空、或为空字符串、或为空格字符串
     * null、空字符串、空格、退格\b、换页\f、换行\n、回车\r、制表\t
     *
     * @param text 文本
     * @return true 文本为空、或为空字符串、或为空格字符串
     */
    public static boolean isBlank(final String text) {
        return text == null || text.trim().length() == 0;
    }

    /**
     * 判断 文本 是否不为空、不为空字符串、不为空格字符串
     * 不属于 null && "" && " " && "\n" && "\t" && "\r" && " \n\t\r"
     *
     * @param text 文本
     * @return true 文本不为空、不为空字符串、不为空格字符串
     */
    public static boolean isNotBlank(final String text) {
        return !isBlank(text);
    }

    /*
      -----------------------------collection validate-------------------------------
     */

    /**
     * 判断 集合 为空、或为空集
     * 属于 null || new List<>() 空集 || new Set<>() 空集
     *
     * @param collection 集合
     * @return true 集合为空、或为空集
     */
    public static boolean isEmpty(final Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断 集合 不为空、不为空集
     * 不属于 null && new List<>() 空集 && new Set<>() 空集
     *
     * @param collection 集合
     * @return true 集合 不为空、不为空集
     */
    public static boolean isNotEmpty(final Collection<?> collection) {
        return !isEmpty(collection);
    }

    /*
      -----------------------------map validate-------------------------------
     */

    /**
     * 校验 键值对 为空、或为空集
     * 属于 null || new HashMap<>() 空集
     *
     * @param map 键值对
     * @return true 键值对 为空、或为空集
     */
    public static boolean isEmpty(final Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 校验 键值对 不为空、不为空集
     * 不属于 null && new HashMap<>() 空集
     *
     * @param map 键值对
     * @return true 键值对 不为空、不为空集
     */
    public static boolean isNotEmpty(final Map<?, ?> map) {
        return !isEmpty(map);
    }

    /*
      -----------------------------array validate-------------------------------
     */

    /**
     * 校验 数组 为空、或为空数组
     * 属于 null || new byte[0]
     * new byte[1] 不为空，有默认1个元素 0
     *
     * @param array 数组
     * @return true 数组 为空、或为空数组
     */
    public static boolean isEmpty(final byte[] array) {
        return array == null || Array.getLength(array) == 0;
    }

    /**
     * 校验 数组 不为空、不为空数组
     * 不属于 null && new byte[0]
     * new byte[1] 不为空，有默认1个元素 0
     *
     * @param array 数组
     * @return true 数组 不为空、不为空数组
     */
    public static boolean isNotEmpty(final byte[] array) {
        return !isEmpty(array);
    }

    /**
     * 校验 数组 为空、或为空数组
     * 属于 null || new short[0]
     * new short[1] 不为空，有默认1个元素 0
     *
     * @param array 数组
     * @return true 数组 为空、或为空数组
     */
    public static boolean isEmpty(final short[] array) {
        return array == null || Array.getLength(array) == 0;
    }

    /**
     * 校验 数组 不为空、不为空数组
     * 不属于 null && new short[0]
     * new short[1] 不为空，有默认1个元素 0
     *
     * @param array 数组
     * @return true 数组 不为空、不为空数组
     */
    public static boolean isNotEmpty(final short[] array) {
        return !isEmpty(array);
    }

    /**
     * 校验 数组 为空、或为空数组
     * 属于 null || new int[0]
     * new int[1] 不为空，有默认1个元素 0
     *
     * @param array 数组
     * @return true 数组 为空、或为空数组
     */
    public static boolean isEmpty(final int[] array) {
        return array == null || Array.getLength(array) == 0;
    }

    /**
     * 校验 数组 不为空、不为空数组
     * 不属于 null && new int[0]
     * new int[1] 不为空，有默认1个元素 0
     *
     * @param array 数组
     * @return true 数组 不为空、不为空数组
     */
    public static boolean isNotEmpty(final int[] array) {
        return !isEmpty(array);
    }

    /**
     * 校验 数组 为空、或为空数组
     * 属于 null || new long[0]
     * new long[1] 不为空，有默认1个元素 0
     *
     * @param array 数组
     * @return true 数组 为空、或为空数组
     */
    public static boolean isEmpty(final long[] array) {
        return array == null || Array.getLength(array) == 0;
    }

    /**
     * 校验 数组 不为空、不为空数组
     * 不属于 null && new long[0]
     * new long[1] 不为空，有默认1个元素 0
     *
     * @param array 数组
     * @return true 数组 不为空、不为空数组
     */
    public static boolean isNotEmpty(final long[] array) {
        return !isEmpty(array);
    }

    /**
     * 校验 数组 为空、或为空数组
     * 属于 null || new float[0]
     * new float[1] 不为空，有默认1个元素 0.0
     *
     * @param array 数组
     * @return true 数组 为空、或为空数组
     */
    public static boolean isEmpty(final float[] array) {
        return array == null || Array.getLength(array) == 0;
    }

    /**
     * 校验 数组 不为空、不为空数组
     * 不属于 null && new float[0]
     * new float[1] 不为空，有默认1个元素 0.0
     *
     * @param array 数组
     * @return true 数组 不为空、不为空数组
     */
    public static boolean isNotEmpty(final float[] array) {
        return !isEmpty(array);
    }

    /**
     * 校验 数组 为空、或为空数组
     * 属于 null || new double[0]
     * new double[1] 不为空，有默认1个元素 0.0
     *
     * @param array 数组
     * @return true 数组 为空、或为空数组
     */
    public static boolean isEmpty(final double[] array) {
        return array == null || Array.getLength(array) == 0;
    }

    /**
     * 校验 数组 不为空、不为空数组
     * 不属于 null && new double[0]
     * new double[1] 不为空，有默认1个元素 0.0
     *
     * @param array 数组
     * @return true 数组 不为空、不为空数组
     */
    public static boolean isNotEmpty(final double[] array) {
        return !isEmpty(array);
    }

    /**
     * 校验 数组 为空、或为空数组
     * 属于 null || new char[0]
     * new char[1] 不为空，有默认1个元素 '\u0000'
     *
     * @param array 数组
     * @return true 数组 为空、或为空数组
     */
    public static boolean isEmpty(final char[] array) {
        return array == null || Array.getLength(array) == 0;
    }

    /**
     * 校验 数组 不为空、不为空数组
     * 不属于 null && new char[0]
     * new char[1] 不为空，有默认1个元素 '\u0000'
     *
     * @param array 数组
     * @return true 数组 不为空、不为空数组
     */
    public static boolean isNotEmpty(final char[] array) {
        return !isEmpty(array);
    }

    /**
     * 校验 数组 为空、或为空数组
     * 属于 null || new boolean[0]
     * new boolean[1] 不为空，有默认1个元素 false
     *
     * @param array 数组
     * @return true 数组 为空、或为空数组
     */
    public static boolean isEmpty(final boolean[] array) {
        return array == null || Array.getLength(array) == 0;
    }

    /**
     * 校验 数组 不为空、不为空数组
     * 不属于 null && new boolean[0]
     * new boolean[1] 不为空，有默认1个元素 false
     *
     * @param array 数组
     * @return true 数组 不为空、不为空数组
     */
    public static boolean isNotEmpty(final boolean[] array) {
        return !isEmpty(array);
    }

    /**
     * 校验 数组 为空、或为空数组
     * 属于 null || new T[0]
     * new T[1] 不为空，有默认1个元素 null
     *
     * @param array 数组
     * @param <T>   数组类型
     * @return true 数组 为空、或为空数组
     */
    public static <T> boolean isEmpty(final T[] array) {
        return array == null || Array.getLength(array) == 0;
    }

    /**
     * 校验 数组 不为空、不为空数组
     * 不属于 null && new T[0]
     * new T[1] 不为空，有默认1个元素 null
     *
     * @param array 数组
     * @param <T>   数组类型
     * @return true 数组 不为空、不为空数组
     */
    public static <T> boolean isNotEmpty(final T[] array) {
        return !isEmpty(array);
    }
}
