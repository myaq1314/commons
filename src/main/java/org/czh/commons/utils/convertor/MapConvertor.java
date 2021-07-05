package org.czh.commons.utils.convertor;

import org.czh.commons.annotations.tag.NotEmptyTag;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author : czh
 * description : 键值对 转换器
 * date : 2021-04-28
 * email 916419307@qq.com
 */
public final class MapConvertor {
    
    /*
      -----------------------------map convert to first-------------------------------
     */

    /**
     * 键值对 转换为 第一个值
     *
     * @param source 源键值对
     * @param <K>    键值对 Key 类型
     * @param <V>    键值对 Value 类型
     * @return 第一个值
     */
    public static <K, V> Map.Entry<K, V> convertToFirst(@NotEmptyTag final Map<K, V> source) {
        return convertToFirst(source, null);
    }

    /**
     * 键值对 转换为 第一个值
     *
     * @param source 源键值对
     * @param filter 过滤器
     * @param <K>    键值对 Key 类型
     * @param <V>    键值对 Value 类型
     * @return 第一个值
     */
    public static <K, V> Map.Entry<K, V> convertToFirst(@NotEmptyTag final Map<K, V> source,
                                                        final Predicate<Map.Entry<K, V>> filter) {
        return convertToFirst(source, filter, entry -> entry);
    }

    /**
     * 键值对 转换为 第一个值
     *
     * @param source 源键值对
     * @param filter 过滤器
     * @param <K>    键值对 Key 类型
     * @param <V>    键值对 Value 类型
     * @return 第一个值
     */
    public static <K, V, T> T convertToFirst(@NotEmptyTag final Map<K, V> source,
                                             final Predicate<Map.Entry<K, V>> filter,
                                             @NotNullTag final Function<Map.Entry<K, V>, T> convertor) {
        EmptyAssert.isNotEmpty(source);
        EmptyAssert.isNotNull(convertor);

        Stream<Map.Entry<K, V>> stream = source.entrySet().stream();
        if (EmptyValidate.isNotNull(filter)) {
            stream = stream.filter(filter);
        }
        return stream.findFirst().map(convertor).orElse(null);
    }
    
    /*
      -----------------------------map convert to new array-------------------------------
     */

    /**
     * 键值对 转换为 数组
     *
     * @param source 源数组
     * @param <K>    键值对 Key 类型
     * @param <V>    键值对 Value 类型
     * @return 数组
     */
    @SuppressWarnings("unchecked")
    public static <K, V> Map.Entry<K, V>[] convertToArray(@NotEmptyTag final Map<K, V> source) {
        return convertToArray(source, Map.Entry.class, entry -> entry);
    }

    /**
     * 键值对 转换为 数组
     *
     * @param source      源数组
     * @param targetClazz 目标数组中元素类 类型
     * @param convertor   转换器
     * @param <K>         键值对 Key 类型
     * @param <V>         键值对 Value 类型
     * @param <T>         目标数组元素类型
     * @return 数组
     */
    public static <K, V, T> T[] convertToArray(@NotEmptyTag final Map<K, V> source,
                                               @NotNullTag final Class<T> targetClazz,
                                               @NotNullTag final Function<Map.Entry<K, V>, T> convertor) {
        return convertToArray(source, targetClazz, convertor, null);
    }

    /**
     * 键值对 转换为 数组
     *
     * @param source      源数组
     * @param targetClazz 目标数组中元素类 类型
     * @param convertor   转换器
     * @param filter      过滤器
     * @param <K>         键值对 Key 类型
     * @param <V>         键值对 Value 类型
     * @param <T>         目标数组元素类型
     * @return 数组
     */
    @SuppressWarnings("unchecked")
    public static <K, V, T> T[] convertToArray(@NotEmptyTag final Map<K, V> source,
                                               @NotNullTag final Class<T> targetClazz,
                                               @NotNullTag final Function<Map.Entry<K, V>, T> convertor,
                                               final Predicate<Map.Entry<K, V>> filter) {
        // 定长时，使用 数组 转 数组
        if (EmptyValidate.isNull(filter)) {
            EmptyAssert.isNotEmpty(source);
            EmptyAssert.allNotNull(targetClazz, convertor);

            T[] target = (T[]) Array.newInstance(targetClazz, source.size());
            Iterator<Map.Entry<K, V>> iterator = source.entrySet().iterator();
            IntStream.range(0, source.size()).forEach(i -> target[i] = convertor.apply(iterator.next()));
            return target;
        }

        // 不定长时
        // 先 数组 转 List 确定长度
        List<T> targetList = convertToList(source, convertor, filter);
        // 再 使用 List 转 数组
        return CollectionConvertor.convertToArray(targetList, targetClazz);
    }

    /*
      -----------------------------map convert to new list-------------------------------
     */

    /**
     * 键值对 转换为 List集合
     *
     * @param source 源键值对
     * @param <K>    键值对 Key 类型
     * @param <V>    键值对 Value 类型
     * @return List集合
     */
    public static <K, V> List<Map.Entry<K, V>> convertToList(@NotEmptyTag final Map<K, V> source) {
        return convertToList(source, entry -> entry);
    }

    /**
     * 键值对 转换为 List集合
     *
     * @param source    源键值对
     * @param convertor 转换器
     * @param <K>       键值对 Key 类型
     * @param <V>       键值对 Value 类型
     * @param <T>       目标List集合元素类型
     * @return List集合
     */
    public static <K, V, T> List<T> convertToList(@NotEmptyTag final Map<K, V> source,
                                                  @NotNullTag final Function<Map.Entry<K, V>, T> convertor) {
        return convertToList(source, convertor, null);
    }

    /**
     * 键值对 转换为 List集合
     *
     * @param source    源键值对
     * @param convertor 转换器
     * @param filter    过滤器
     * @param <K>       键值对 Key 类型
     * @param <V>       键值对 Value 类型
     * @param <T>       目标List集合元素类型
     * @return List集合
     */
    public static <K, V, T> List<T> convertToList(@NotEmptyTag final Map<K, V> source,
                                                  @NotNullTag final Function<Map.Entry<K, V>, T> convertor,
                                                  final Predicate<Map.Entry<K, V>> filter) {
        EmptyAssert.isNotEmpty(source);
        List<T> target = new ArrayList<>(source.size());
        convertToCollection(source, target, convertor, filter);
        return target;
    }

    /*
      -----------------------------map convert to new set-------------------------------
     */

    /**
     * 键值对 转换为 Set集合
     *
     * @param source 源键值对
     * @param <K>    键值对 Key 类型
     * @param <V>    键值对 Value 类型
     * @return Set集合
     */
    public static <K, V> Set<Map.Entry<K, V>> convertToSet(@NotEmptyTag final Map<K, V> source) {
        return convertToSet(source, entry -> entry);
    }

    /**
     * 键值对 转换为 Set集合
     *
     * @param source    源键值对
     * @param convertor 转换器
     * @param <K>       键值对 Key 类型
     * @param <V>       键值对 Value 类型
     * @param <T>       目标Set集合元素类型
     * @return Set集合
     */
    public static <K, V, T> Set<T> convertToSet(@NotEmptyTag final Map<K, V> source,
                                                @NotNullTag final Function<Map.Entry<K, V>, T> convertor) {
        return convertToSet(source, convertor, null);
    }

    /**
     * 键值对 转换为 Set集合
     *
     * @param source    源键值对
     * @param convertor 转换器
     * @param filter    过滤器
     * @param <K>       键值对 Key 类型
     * @param <V>       键值对 Value 类型
     * @param <T>       目标Set集合元素类型
     * @return Set集合
     */
    public static <K, V, T> Set<T> convertToSet(@NotEmptyTag final Map<K, V> source,
                                                @NotNullTag final Function<Map.Entry<K, V>, T> convertor,
                                                final Predicate<Map.Entry<K, V>> filter) {
        EmptyAssert.isNotEmpty(source);
        Set<T> target = new HashSet<>(source.size());
        convertToCollection(source, target, convertor, filter);
        return target;
    }

    /*
      -----------------------------map convert to collection-------------------------------
     */

    /**
     * 键值对 转换为 集合
     *
     * @param source 源键值对
     * @param target 目标集合
     * @param <K>    键值对 Key 类型
     * @param <V>    键值对 Value 类型
     */
    public static <K, V> void convertToCollection(@NotEmptyTag final Map<K, V> source,
                                                  @NotNullTag final Collection<Map.Entry<K, V>> target) {
        convertToCollection(source, target, entry -> entry);
    }

    /**
     * 键值对 转换为 集合
     *
     * @param source    源键值对
     * @param target    目标集合
     * @param convertor 转换器
     * @param <K>       键值对 Key 类型
     * @param <V>       键值对 Value 类型
     * @param <T>       目标集合元素类型
     */
    public static <K, V, T> void convertToCollection(@NotEmptyTag final Map<K, V> source,
                                                     @NotNullTag final Collection<T> target,
                                                     @NotNullTag final Function<Map.Entry<K, V>, T> convertor) {
        convertToCollection(source, target, convertor, null);
    }

    /**
     * 键值对 转换为 集合
     *
     * @param source    源键值对
     * @param target    目标集合
     * @param convertor 转换器
     * @param filter    过滤器
     * @param <K>       键值对 Key 类型
     * @param <V>       键值对 Value 类型
     * @param <T>       目标集合元素类型
     */
    public static <K, V, T> void convertToCollection(@NotEmptyTag final Map<K, V> source,
                                                     @NotNullTag final Collection<T> target,
                                                     @NotNullTag final Function<Map.Entry<K, V>, T> convertor,
                                                     final Predicate<Map.Entry<K, V>> filter) {
        EmptyAssert.isNotEmpty(source);
        EmptyAssert.allNotNull(target, convertor);

        Stream<Map.Entry<K, V>> stream = source.entrySet().stream();
        if (EmptyValidate.isNotNull(filter)) {
            stream = stream.filter(filter);
        }
        stream.map(convertor).forEach(target::add);
    }

    /*
      -----------------------------map convert to new map-------------------------------
     */

    /**
     * 键值对 转换为 键值对
     *
     * @param source 源键值对
     * @param <K>    键值对 Key 类型
     * @param <V>    键值对 Value 类型
     * @return 键值对
     */
    public static <K, V> Map<K, V> convertToMap(@NotEmptyTag final Map<K, V> source) {
        return convertToMap(source, Map.Entry::getKey, Map.Entry::getValue);
    }

    /**
     * 键值对 转换为 键值对
     *
     * @param source 源键值对
     * @param key    key转换器
     * @param value  value转换器
     * @param <SK>   键值对 Key 类型
     * @param <SV>   键值对 Value 类型
     * @param <TK>   目标键值对key元素类型
     * @param <TV>   目标键值对value元素类型
     * @return 键值对
     */
    public static <SK, SV, TK, TV> Map<TK, TV> convertToMap(@NotEmptyTag final Map<SK, SV> source,
                                                            @NotNullTag final Function<Map.Entry<SK, SV>, TK> key,
                                                            @NotNullTag final Function<Map.Entry<SK, SV>, TV> value) {
        return convertToMap(source, key, value, null);
    }

    /**
     * 键值对 转换为 键值对
     *
     * @param source 源键值对
     * @param key    key转换器
     * @param value  value转换器
     * @param filter 过滤器
     * @param <SK>   键值对 Key 类型
     * @param <SV>   键值对 Value 类型
     * @param <TK>   目标键值对key元素类型
     * @param <TV>   目标键值对value元素类型
     * @return 键值对
     */
    public static <SK, SV, TK, TV> Map<TK, TV> convertToMap(@NotEmptyTag final Map<SK, SV> source,
                                                            @NotNullTag final Function<Map.Entry<SK, SV>, TK> key,
                                                            @NotNullTag final Function<Map.Entry<SK, SV>, TV> value,
                                                            final Predicate<Map.Entry<SK, SV>> filter) {
        EmptyAssert.isNotEmpty(source);
        Map<TK, TV> target = new HashMap<>(source.size());
        convertToMap(source, target, key, value, filter);
        return target;
    }

    /*
      -----------------------------map convert to map-------------------------------
     */

    /**
     * 键值对 转换为 键值对
     *
     * @param source 源键值对
     * @param target 目标键值对
     * @param <K>    键值对 Key 类型
     * @param <V>    键值对 Value 类型
     */
    public static <K, V> void convertToMap(@NotEmptyTag final Map<K, V> source,
                                           @NotNullTag final Map<K, V> target) {
        convertToMap(source, target, Map.Entry::getKey, Map.Entry::getValue);
    }

    /**
     * 键值对 转换为 键值对
     *
     * @param source 源键值对
     * @param target 目标键值对
     * @param key    key转换器
     * @param value  value转换器
     * @param <SK>   键值对 Key 类型
     * @param <SV>   键值对 Value 类型
     * @param <TK>   目标键值对key元素类型
     * @param <TV>   目标键值对value元素类型
     */
    public static <SK, SV, TK, TV> void convertToMap(@NotEmptyTag final Map<SK, SV> source,
                                                     @NotNullTag final Map<TK, TV> target,
                                                     @NotNullTag final Function<Map.Entry<SK, SV>, TK> key,
                                                     @NotNullTag final Function<Map.Entry<SK, SV>, TV> value) {
        convertToMap(source, target, key, value, null);
    }

    /**
     * 键值对 转换为 键值对
     *
     * @param source 源键值对
     * @param target 目标键值对
     * @param key    key转换器
     * @param value  value转换器
     * @param filter 过滤器
     * @param <SK>   键值对 Key 类型
     * @param <SV>   键值对 Value 类型
     * @param <TK>   目标键值对key元素类型
     * @param <TV>   目标键值对value元素类型
     */
    public static <SK, SV, TK, TV> void convertToMap(@NotEmptyTag final Map<SK, SV> source,
                                                     @NotNullTag final Map<TK, TV> target,
                                                     @NotNullTag final Function<Map.Entry<SK, SV>, TK> key,
                                                     @NotNullTag final Function<Map.Entry<SK, SV>, TV> value,
                                                     final Predicate<Map.Entry<SK, SV>> filter) {
        EmptyAssert.isNotEmpty(source);
        EmptyAssert.allNotNull(target, key, value);

        Stream<Map.Entry<SK, SV>> stream = source.entrySet().stream();
        if (EmptyValidate.isNotNull(filter)) {
            stream = stream.filter(filter);
        }
        stream.forEach(entry -> target.put(key.apply(entry), value.apply(entry)));
    }
}
