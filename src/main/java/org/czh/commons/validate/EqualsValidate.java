package org.czh.commons.validate;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author : czh
 * description :
 * date : 2021-05-02
 * email 916419307@qq.com
 */
public final class EqualsValidate {

    public static <T> boolean notEquals(final T target, final T compare) {
        return !equals(target, compare);
    }

    public static <T> boolean equals(final T target, final T compare) {
        return Objects.equals(target, compare);
    }

    public static <T> boolean notEquals(final T[] target, final T[] compare) {
        return !equals(target, compare);
    }

    public static <T> boolean equals(final T[] target, final T[] compare) {
        return Arrays.equals(target, compare);
    }

    public static boolean notEquals(final byte[] target, final byte[] compare) {
        return !equals(target, compare);
    }

    public static boolean equals(final byte[] target, final byte[] compare) {
        return Arrays.equals(target, compare);
    }

    public static boolean notEquals(final short[] target, final short[] compare) {
        return !equals(target, compare);
    }

    public static boolean equals(final short[] target, final short[] compare) {
        return Arrays.equals(target, compare);
    }

    public static boolean notEquals(final int[] target, final int[] compare) {
        return !equals(target, compare);
    }

    public static boolean equals(final int[] target, final int[] compare) {
        return Arrays.equals(target, compare);
    }

    public static boolean notEquals(final long[] target, final long[] compare) {
        return !equals(target, compare);
    }

    public static boolean equals(final long[] target, final long[] compare) {
        return Arrays.equals(target, compare);
    }

    public static boolean notEquals(final float[] target, final float[] compare) {
        return !equals(target, compare);
    }

    public static boolean equals(final float[] target, final float[] compare) {
        return Arrays.equals(target, compare);
    }

    public static boolean notEquals(final double[] target, final double[] compare) {
        return !equals(target, compare);
    }

    public static boolean equals(final double[] target, final double[] compare) {
        return Arrays.equals(target, compare);
    }

    public static boolean notEquals(final char[] target, final char[] compare) {
        return !equals(target, compare);
    }

    public static boolean equals(final char[] target, final char[] compare) {
        return Arrays.equals(target, compare);
    }

    public static boolean notEquals(final boolean[] target, final boolean[] compare) {
        return !equals(target, compare);
    }

    public static boolean equals(final boolean[] target, final boolean[] compare) {
        return Arrays.equals(target, compare);
    }
}
