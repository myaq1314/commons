package org.czh.commons.utils.convertor;

import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.annotations.tag.NotEmptyTag;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.entity.IBaseEntity;
import org.czh.commons.utils.FieldUtil;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
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
 * description : 对象转换器
 * date : 2021-04-28
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public final class EntityConvertor {

    /*
      -----------------------------bean convert to url-------------------------------
     */
    public static <S> String convertToUrl(@NotNullTag final S source, @NotBlankTag final String connector) {
        return convertToUrl(source, connector, null);
    }

    public static <S> String convertToUrl(@NotNullTag final S source,
                                          @NotBlankTag final String connector,
                                          final Predicate<Field> filter) {
        EmptyAssert.allNotNull(source, connector);
        List<Field> fieldList = FieldUtil.queryFieldList(source.getClass());
        EmptyAssert.isNotEmpty(fieldList);

        StringBuilder builder = new StringBuilder();
        for (Field field : fieldList) {
            if (EmptyValidate.isNull(filter) || filter.test(field)) {
                Object fieldValue = FieldUtil.readField(source, field);
                if (EmptyValidate.isNotNull(fieldValue)) {
                    builder.append(field.getName()).append("=").append(fieldValue);
                }
            }
            builder.append(connector);
        }
        return builder.substring(0, builder.length() - connector.length());
    }

    /*
      -----------------------------bean convert to other bean-------------------------------
     */
    public static <S, T> T convertToBean(@NotNullTag final S source,
                                         @NotNullTag final Class<T> targetClazz) {
        EmptyAssert.isNotNull(targetClazz);

        T target = BeanUtils.instantiateClass(targetClazz);
        convertToBean(source, target);
        return target;
    }

    public static <S, T> T convertToBean(@NotNullTag final S source,
                                         @NotNullTag final Function<S, T> convertor) {
        EmptyAssert.allNotNull(source, convertor);

        T target = convertor.apply(source);
        convertToBean(source, target);
        return target;
    }

    public static <S, T> void convertToBean(@NotNullTag final S source,
                                            @NotNullTag final T target) {
        EmptyAssert.allNotNull(source, target);
        BeanUtils.copyProperties(source, target);
    }

    /*
      -----------------------------bean collection convert to other bean list-------------------------------
     */
    public static <S, T> List<T> convertToBeanList(@NotEmptyTag final Collection<S> sourceCollection,
                                                   @NotNullTag final Class<T> targetClazz) {
        EmptyAssert.isNotEmpty(sourceCollection);

        List<T> targetList = new ArrayList<>(sourceCollection.size());
        for (S source : sourceCollection) {
            targetList.add(convertToBean(source, targetClazz));
        }
        return targetList;
    }

    public static <S, T> List<T> convertToBeanList(@NotNullTag final Collection<S> sourceCollection,
                                                   @NotNullTag final Function<S, T> convertor) {
        EmptyAssert.isNotEmpty(sourceCollection);

        List<T> targetList = new ArrayList<>(sourceCollection.size());
        for (S source : sourceCollection) {
            targetList.add(convertToBean(source, convertor));
        }
        return targetList;
    }

    public static <S, T> Set<T> convertToBeanSet(@NotEmptyTag final Collection<S> sourceCollection,
                                                 @NotNullTag final Class<T> targetClazz) {
        EmptyAssert.isNotEmpty(sourceCollection);

        Set<T> targetSet = new HashSet<>(sourceCollection.size());
        for (S source : sourceCollection) {
            targetSet.add(convertToBean(source, targetClazz));
        }
        return targetSet;
    }

    public static <S, T> Set<T> convertToBeanSet(@NotNullTag final Collection<S> sourceCollection,
                                                 @NotNullTag final Function<S, T> convertor) {
        EmptyAssert.isNotEmpty(sourceCollection);

        Set<T> targetSet = new HashSet<>(sourceCollection.size());
        for (S source : sourceCollection) {
            targetSet.add(convertToBean(source, convertor));
        }
        return targetSet;
    }

    /*
      -----------------------------bean convert to new map-------------------------------
     */

    public static <S extends IBaseEntity> Map<String, Object> convertToMap(@NotNullTag final S source) {
        Map<String, Object> target = new HashMap<>();
        convertToMap(source, target);
        return target;
    }

    /*
      -----------------------------bean convert to map-------------------------------
     */
    public static <S extends IBaseEntity> void convertToMap(@NotNullTag final S source,
                                                            @NotNullTag final Map<String, Object> target) {
        EmptyAssert.allNotNull(source, target);

        Class<?> clazz = source.getClass();
        List<Field> fieldList = FieldUtil.queryFieldList(clazz);
        if (EmptyValidate.isEmpty(fieldList)) {
            return;
        }

        fieldList.forEach(field -> target.put(field.getName(), FieldUtil.readField(source, field)));
    }
}
