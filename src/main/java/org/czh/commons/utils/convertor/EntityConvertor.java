package org.czh.commons.utils.convertor;

import org.czh.commons.entity.IBaseEntity;
import org.czh.commons.utils.FieldUtil;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public static <S> String convertToUrl(final S source, final String connector) {
        return convertToUrl(source, connector, null);
    }

    public static <S> String convertToUrl(final S source,
                                          final String connector,
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
      -----------------------------bean convert to new map-------------------------------
     */

    public static <S extends IBaseEntity> Map<String, Object> convertToMap(final S source) {
        return convertToMap(source, Field::getName);
    }

    public static <S extends IBaseEntity, Key> Map<Key, Object> convertToMap(final S source,
                                                                             final Function<Field, Key> keyFunction) {
        return convertToMap(source, keyFunction, field -> FieldUtil.readField(source, field));
    }

    public static <S extends IBaseEntity, Key, Value> Map<Key, Value> convertToMap(final S source,
                                                                                   final Function<Field, Key> keyFunction,
                                                                                   final Function<Field, Value> valueFunction) {
        return convertToMap(source, keyFunction, valueFunction, null);
    }

    public static <S extends IBaseEntity, Key, Value> Map<Key, Value> convertToMap(final S source,
                                                                                   final Function<Field, Key> keyFunction,
                                                                                   final Function<Field, Value> valueFunction,
                                                                                   final Predicate<Field> filter) {
        Map<Key, Value> target = new HashMap<>();
        convertToMap(source, target, keyFunction, valueFunction, filter);
        return target;
    }

    /*
      -----------------------------bean convert to map-------------------------------
     */
    public static <S extends IBaseEntity> void convertToMap(final S source,
                                                            final Map<String, Object> target) {
        convertToMap(source, target, Field::getName);
    }

    public static <S extends IBaseEntity, Key> void convertToMap(final S source,
                                                                 final Map<Key, Object> target,
                                                                 final Function<Field, Key> keyFunction) {
        convertToMap(source, target, keyFunction, field -> FieldUtil.readField(source, field));
    }

    public static <S extends IBaseEntity, Key, Value> void convertToMap(final S source,
                                                                        final Map<Key, Value> target,
                                                                        final Function<Field, Key> keyFunction,
                                                                        final Function<Field, Value> valueFunction) {
        convertToMap(source, target, keyFunction, valueFunction, null);
    }

    public static <S extends IBaseEntity, Key, Value> void convertToMap(final S source,
                                                                        final Map<Key, Value> target,
                                                                        final Function<Field, Key> keyFunction,
                                                                        final Function<Field, Value> valueFunction,
                                                                        final Predicate<Field> filter) {
        EmptyAssert.allNotNull(source, target, keyFunction, valueFunction);

        List<Field> fieldList = FieldUtil.queryFieldList(source.getClass(), field -> !field.getName().equals("serialVersionUID"));
        if (EmptyValidate.isEmpty(fieldList)) {
            return;
        }
        for (Field field : fieldList) {
            if (EmptyValidate.isNull(filter) || filter.test(field)) {
                target.put(keyFunction.apply(field), valueFunction.apply(field));
            }
        }
    }
}
