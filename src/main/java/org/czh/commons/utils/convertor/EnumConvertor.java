package org.czh.commons.utils.convertor;

import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;
import org.czh.commons.validate.EnumAssert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author : czh
 * description : 枚举转换器
 * date : 2021-04-28
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public final class EnumConvertor {

    /*
      -----------------------------enum convert to first-------------------------------
     */

    /**
     * 枚举 取 第一个值
     *
     * @param sourceClazz 枚举类 类型
     * @param <S>         枚举类型
     * @return 第一个值
     */
    public static <S> S convertToFirst(@NotNullTag final Class<S> sourceClazz) {
        return convertToFirst(sourceClazz, null);
    }

    /**
     * 枚举 取 第一个值
     *
     * @param sourceClazz 枚举类 类型
     * @param filter      过滤器
     * @param <S>         枚举类型
     * @return 第一个值
     */
    public static <S> S convertToFirst(@NotNullTag final Class<S> sourceClazz,
                                       final Predicate<S> filter) {
        return convertToFirst(sourceClazz, filter, s -> s);
    }

    /**
     * 枚举 取 第一个值
     *
     * @param sourceClazz 枚举类 类型
     * @param filter      过滤器
     * @param convertor   转换器
     * @param <S>         枚举类型
     * @param <T>         目标元素类型
     * @return 第一个值
     */
    public static <S, T> T convertToFirst(@NotNullTag final Class<S> sourceClazz,
                                          final Predicate<S> filter,
                                          @NotNullTag final Function<S, T> convertor) {
        EnumAssert.isEnum(sourceClazz);
        EmptyAssert.isNotNull(convertor);

        return ArrayConvertor.convertToFirst(sourceClazz.getEnumConstants(), filter, convertor);
    }

    /*
      -----------------------------enum convert to new array-------------------------------
     */

    /**
     * 枚举 转换为 数组
     *
     * @param sourceClazz 枚举类 类型
     * @param <S>         枚举类型
     * @return 数组
     */
    public static <S> S[] convertToArray(@NotNullTag final Class<S> sourceClazz) {
        return convertToArray(sourceClazz, sourceClazz, s -> s);
    }

    /**
     * 枚举 转换为 数组
     *
     * @param sourceClazz 枚举类 类型
     * @param targetClazz 目标数组中元素类 类型
     * @param convertor   转换器
     * @param <S>         枚举类型
     * @param <T>         目标数组元素类型
     * @return 数组
     */
    public static <S, T> T[] convertToArray(@NotNullTag final Class<S> sourceClazz,
                                            @NotNullTag final Class<T> targetClazz,
                                            @NotNullTag final Function<S, T> convertor) {
        return convertToArray(sourceClazz, targetClazz, convertor, null);
    }

    /**
     * 枚举 转换为 数组
     *
     * @param sourceClazz 枚举类 类型
     * @param targetClazz 目标数组中元素类 类型
     * @param convertor   转换器
     * @param filter      过滤器
     * @param <S>         枚举类型
     * @param <T>         目标数组元素类型
     * @return 数组
     */
    public static <S, T> T[] convertToArray(@NotNullTag final Class<S> sourceClazz,
                                            @NotNullTag final Class<T> targetClazz,
                                            @NotNullTag final Function<S, T> convertor,
                                            final Predicate<S> filter) {
        // 定长时，使用 数组 转 数组
        if (EmptyValidate.isNull(filter)) {
            EnumAssert.isEnum(sourceClazz);
            S[] sourceFieldArray = sourceClazz.getEnumConstants();
            return ArrayConvertor.convertToArray(sourceFieldArray, targetClazz, convertor);
        }
        // 不定长时，先使用 数组 转 List集合 确定最终长度
        List<T> targetList = convertToList(sourceClazz, convertor, filter);
        // 再使用 集合 转 数组
        return CollectionConvertor.convertToArray(targetList, targetClazz);
    }

    /*
      -----------------------------enum convert to new list-------------------------------
     */

    /**
     * 枚举 转换为 List集合
     *
     * @param sourceClazz 枚举类 类型
     * @param <S>         枚举类型
     * @return List集合
     */
    public static <S> List<S> convertToList(@NotNullTag final Class<S> sourceClazz) {
        return convertToList(sourceClazz, s -> s);
    }

    /**
     * 枚举 转换为 List集合
     *
     * @param sourceClazz 枚举类 类型
     * @param convertor   转换器
     * @param <S>         枚举类型
     * @param <T>         目标List集合元素类型
     * @return List集合
     */
    public static <S, T> List<T> convertToList(@NotNullTag final Class<S> sourceClazz,
                                               @NotNullTag final Function<S, T> convertor) {
        return convertToList(sourceClazz, convertor, null);
    }

    /**
     * 枚举 转换为 List集合
     *
     * @param sourceClazz 枚举类 类型
     * @param convertor   转换器
     * @param filter      过滤器
     * @param <S>         枚举类型
     * @param <T>         目标List集合元素类型
     * @return List集合
     */
    public static <S, T> List<T> convertToList(@NotNullTag final Class<S> sourceClazz,
                                               @NotNullTag final Function<S, T> convertor,
                                               final Predicate<S> filter) {
        List<T> target = new ArrayList<>();
        convertToCollection(sourceClazz, target, convertor, filter);
        return target;
    }

    /*
      -----------------------------enum convert to new set-------------------------------
     */

    /**
     * 枚举 转换为 Set集合
     *
     * @param sourceClazz 枚举类 类型
     * @param <S>         枚举类型
     * @return Set集合
     */
    public static <S> Set<S> convertToSet(@NotNullTag final Class<S> sourceClazz) {
        return convertToSet(sourceClazz, s -> s);
    }

    /**
     * 枚举 转换为 Set集合
     *
     * @param sourceClazz 枚举类 类型
     * @param convertor   转换器
     * @param <S>         枚举类型
     * @param <T>         目标Set集合元素类型
     * @return Set集合
     */
    public static <S, T> Set<T> convertToSet(@NotNullTag final Class<S> sourceClazz,
                                             @NotNullTag final Function<S, T> convertor) {
        return convertToSet(sourceClazz, convertor, null);
    }

    /**
     * 枚举 转换为 Set集合
     *
     * @param sourceClazz 枚举类 类型
     * @param convertor   转换器
     * @param filter      过滤器
     * @param <S>         枚举类型
     * @param <T>         目标Set集合元素类型
     * @return Set集合
     */
    public static <S, T> Set<T> convertToSet(@NotNullTag final Class<S> sourceClazz,
                                             @NotNullTag final Function<S, T> convertor,
                                             final Predicate<S> filter) {
        Set<T> target = new HashSet<>();
        convertToCollection(sourceClazz, target, convertor, filter);
        return target;
    }

    /*
      -----------------------------enum convert to collection-------------------------------
     */

    /**
     * 枚举 转换为 集合
     *
     * @param sourceClazz 枚举类 类型
     * @param target      目标集合
     * @param <S>         枚举类型
     */
    public static <S> void convertToCollection(@NotNullTag final Class<S> sourceClazz,
                                               @NotNullTag final Collection<S> target) {
        convertToCollection(sourceClazz, target, s -> s);
    }

    /**
     * 枚举 转换为 集合
     *
     * @param sourceClazz 枚举类 类型
     * @param target      目标集合
     * @param convertor   转换器
     * @param <S>         枚举类型
     * @param <T>         目标集合元素类型
     */
    public static <S, T> void convertToCollection(@NotNullTag final Class<S> sourceClazz,
                                                  @NotNullTag final Collection<T> target,
                                                  @NotNullTag final Function<S, T> convertor) {
        convertToCollection(sourceClazz, target, convertor, null);
    }

    /**
     * 枚举 转换为 集合
     *
     * @param sourceClazz 枚举类 类型
     * @param target      目标集合
     * @param convertor   转换器
     * @param filter      过滤器
     * @param <S>         枚举类型
     * @param <T>         目标集合元素类型
     */
    public static <S, T> void convertToCollection(@NotNullTag final Class<S> sourceClazz,
                                                  @NotNullTag final Collection<T> target,
                                                  @NotNullTag final Function<S, T> convertor,
                                                  final Predicate<S> filter) {
        EnumAssert.isEnum(sourceClazz);
        EmptyAssert.allNotNull(target, convertor);

        S[] sourceFieldArray = sourceClazz.getEnumConstants();
        ArrayConvertor.convertToCollection(sourceFieldArray, target, convertor, filter);
    }

    /*
      -----------------------------enum convert to new map-------------------------------
     */

    /**
     * 枚举 转换为 键值对
     *
     * @param sourceClazz 枚举类 类型
     * @param <S>         枚举类型
     * @return 键值对
     */
    public static <S> Map<S, S> convertToMap(@NotNullTag final Class<S> sourceClazz) {
        return convertToMap(sourceClazz, s -> s);
    }

    /**
     * 枚举 转换为 键值对
     *
     * @param sourceClazz 枚举类 类型
     * @param key         key转换器
     * @param <S>         枚举类型
     * @param <K>         键值对key元素类型
     * @return 键值对
     */
    public static <S, K> Map<K, S> convertToMap(@NotNullTag final Class<S> sourceClazz,
                                                @NotNullTag final Function<S, K> key) {
        return convertToMap(sourceClazz, key, s -> s);
    }

    /**
     * 枚举 转换为 键值对
     *
     * @param sourceClazz 枚举类 类型
     * @param key         key转换器
     * @param value       value转换器
     * @param <S>         枚举类型
     * @param <K>         键值对key元素类型
     * @param <V>         键值对value元素类型
     * @return 键值对
     */
    public static <S, K, V> Map<K, V> convertToMap(@NotNullTag final Class<S> sourceClazz,
                                                   @NotNullTag final Function<S, K> key,
                                                   @NotNullTag final Function<S, V> value) {
        return convertToMap(sourceClazz, key, value, null);
    }

    /**
     * 枚举 转换为 键值对
     *
     * @param sourceClazz 枚举类 类型
     * @param key         key转换器
     * @param value       value转换器
     * @param filter      过滤器
     * @param <S>         枚举类型
     * @param <K>         键值对key元素类型
     * @param <V>         键值对value元素类型
     * @return 键值对
     */
    public static <S, K, V> Map<K, V> convertToMap(@NotNullTag final Class<S> sourceClazz,
                                                   @NotNullTag final Function<S, K> key,
                                                   @NotNullTag final Function<S, V> value,
                                                   final Predicate<S> filter) {
        Map<K, V> target = new HashMap<>();
        convertToMap(sourceClazz, target, key, value, filter);
        return target;
    }

    /*
      -----------------------------enum convert to map-------------------------------
     */

    /**
     * 枚举 转换为 键值对
     *
     * @param sourceClazz 枚举类 类型
     * @param target      目标键值对
     * @param <S>         枚举类型
     */
    public static <S> void convertToMap(@NotNullTag final Class<S> sourceClazz,
                                        @NotNullTag final Map<S, S> target) {
        convertToMap(sourceClazz, target, s -> s);
    }

    /**
     * 枚举 转换为 键值对
     *
     * @param sourceClazz 枚举类 类型
     * @param target      目标键值对
     * @param key         key转换器
     * @param <S>         枚举类型
     * @param <K>         键值对key元素类型
     */
    public static <S, K> void convertToMap(@NotNullTag final Class<S> sourceClazz,
                                           @NotNullTag final Map<K, S> target,
                                           @NotNullTag final Function<S, K> key) {
        convertToMap(sourceClazz, target, key, s -> s);
    }

    /**
     * 枚举 转换为 键值对
     *
     * @param sourceClazz 枚举类 类型
     * @param target      目标键值对
     * @param key         key转换器
     * @param value       value转换器
     * @param <S>         枚举类型
     * @param <K>         键值对key元素类型
     * @param <V>         键值对value元素类型
     */
    public static <S, K, V> void convertToMap(@NotNullTag final Class<S> sourceClazz,
                                              @NotNullTag final Map<K, V> target,
                                              @NotNullTag final Function<S, K> key,
                                              @NotNullTag final Function<S, V> value) {
        convertToMap(sourceClazz, target, key, value, null);
    }

    /**
     * 枚举 转换为 键值对
     *
     * @param sourceClazz 枚举类 类型
     * @param target      目标键值对
     * @param key         key转换器
     * @param value       value转换器
     * @param filter      过滤器
     * @param <S>         枚举类型
     * @param <K>         键值对key元素类型
     * @param <V>         键值对value元素类型
     */
    public static <S, K, V> void convertToMap(@NotNullTag final Class<S> sourceClazz,
                                              @NotNullTag final Map<K, V> target,
                                              @NotNullTag final Function<S, K> key,
                                              @NotNullTag final Function<S, V> value,
                                              final Predicate<S> filter) {
        EnumAssert.isEnum(sourceClazz);
        EmptyAssert.allNotNull(target, key, value);

        S[] sourceFieldArray = sourceClazz.getEnumConstants();
        ArrayConvertor.convertToMap(sourceFieldArray, target, key, value, filter);
    }
}
