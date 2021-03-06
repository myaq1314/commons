package org.czh.commons.utils;

import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;
import org.czh.commons.validate.FlagAssert;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author : czh
 * description :
 * date : 2021-06-18
 * email 916419307@qq.com
 */
public final class FieldUtil {

    /*
        设置 属性 值
     */
    public static void writeStaticField(@NotNullTag Class<?> clazz,
                                        @NotBlankTag String fieldName,
                                        Object fieldValue) {
        writeStaticFromAll(clazz, fieldName, fieldValue);
    }

    public static void writeField(@NotNullTag Object sourceObj, @NotBlankTag String fieldName, Object fieldValue) {
        writeFromAll(sourceObj, fieldName, fieldValue);
    }

    public static void writeStaticFromAll(@NotNullTag Class<?> clazz,
                                          @NotBlankTag String fieldName,
                                          Object fieldValue) {
        writeField(null, getFromAll(clazz, fieldName), fieldValue);
    }

    public static void writeFromAll(@NotNullTag Object sourceObj, @NotBlankTag String fieldName, Object fieldValue) {
        EmptyAssert.isNotNull(sourceObj);
        writeField(sourceObj, getFromAll(sourceObj.getClass(), fieldName), fieldValue);
    }

    public static void writeStaticFromPub(@NotNullTag Class<?> clazz,
                                          @NotBlankTag String fieldName,
                                          Object fieldValue) {
        writeField(null, getFromPub(clazz, fieldName), fieldValue);
    }

    public static void writeFromPub(@NotNullTag Object sourceObj, @NotBlankTag String fieldName, Object fieldValue) {
        EmptyAssert.isNotNull(sourceObj);
        writeField(sourceObj, getFromPub(sourceObj.getClass(), fieldName), fieldValue);
    }

    public static void writeStaticFromOwn(@NotNullTag Class<?> clazz,
                                          @NotBlankTag String fieldName,
                                          Object fieldValue) {
        writeField(null, getFromOwn(clazz, fieldName), fieldValue);
    }

    public static void writeFromOwn(@NotNullTag Object sourceObj, @NotBlankTag String fieldName, Object fieldValue) {
        EmptyAssert.isNotNull(sourceObj);
        writeField(sourceObj, getFromOwn(sourceObj.getClass(), fieldName), fieldValue);
    }

    public static void writeField(Object sourceObj, @NotNullTag Field field, Object fieldValue) {
        EmptyAssert.isNotNull(field);

        try {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            FlagAssert.isTrue(EmptyValidate.isNotNull(sourceObj) || Modifier.isStatic(field.getModifiers()));
            field.set(sourceObj, EmptyValidate.isNull(fieldValue) ? new Object[]{null} : fieldValue);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unauthorized access");
        }
    }

    /*
        读取 属性 值
     */
    public static <T> T readStaticField(@NotNullTag Class<?> clazz, @NotBlankTag String fieldName) {
        return readStaticFromAll(clazz, fieldName);
    }

    public static <T> T readField(@NotNullTag Object sourceObj, @NotBlankTag String fieldName) {
        return readFromAll(sourceObj, fieldName);
    }

    public static <T> T readStaticFromAll(@NotNullTag Class<?> clazz, @NotBlankTag String fieldName) {
        return readField(null, getFromAll(clazz, fieldName));
    }

    public static <T> T readFromAll(@NotNullTag Object sourceObj, @NotBlankTag String fieldName) {
        EmptyAssert.isNotNull(sourceObj);
        return readField(sourceObj, getFromAll(sourceObj.getClass(), fieldName));
    }

    public static <T> T readStaticFromPub(@NotNullTag Class<?> clazz, @NotBlankTag String fieldName) {
        return readField(null, getFromPub(clazz, fieldName));
    }

    public static <T> T readFromPub(@NotNullTag Object sourceObj, @NotBlankTag String fieldName) {
        EmptyAssert.isNotNull(sourceObj);
        return readField(sourceObj, getFromPub(sourceObj.getClass(), fieldName));
    }

    public static <T> T readStaticFromOwn(@NotNullTag Class<?> clazz, @NotBlankTag String fieldName) {
        return readField(null, getFromOwn(clazz, fieldName));
    }

    public static <T> T readFromOwn(@NotNullTag Object sourceObj, @NotBlankTag String fieldName) {
        EmptyAssert.isNotNull(sourceObj);
        return readField(sourceObj, getFromOwn(sourceObj.getClass(), fieldName));
    }

    @SuppressWarnings("unchecked")
    public static <T> T readField(Object sourceObj, @NotNullTag Field field) {
        EmptyAssert.isNotNull(field);

        try {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            FlagAssert.isTrue(EmptyValidate.isNotNull(sourceObj) || Modifier.isStatic(field.getModifiers()));
            Object fieldObj = field.get(sourceObj);
            return EmptyValidate.isNull(fieldObj) ? null : (T) fieldObj;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unauthorized access");
        }
    }

    /*
        根据 属性名称，查找 指定属性
     */

    public static Field getField(@NotNullTag Class<?> clazz, @NotBlankTag String fieldName) {
        return getFromAll(clazz, fieldName);
    }

    public static Field getFromAll(@NotNullTag Class<?> clazz, @NotBlankTag String fieldName) {
        EmptyAssert.isNotNull(clazz);
        EmptyAssert.isNotBlank(fieldName);

        for (Class<?> eachClazz = clazz; eachClazz != null; eachClazz = eachClazz.getSuperclass()) {
            try {
                return eachClazz.getDeclaredField(fieldName);
            } catch (final NoSuchFieldException ignore) {
            }
        }
        throw new RuntimeException("The field could not be found by the fieldName");
    }

    public static Field getFromPub(@NotNullTag Class<?> clazz, @NotBlankTag String fieldName) {
        EmptyAssert.isNotNull(clazz);
        EmptyAssert.isNotBlank(fieldName);

        try {
            return clazz.getField(fieldName);
        } catch (final NoSuchFieldException e) {
            throw new RuntimeException("The field could not be found by the fieldName");
        }
    }

    public static Field getFromOwn(@NotNullTag Class<?> clazz, @NotBlankTag String fieldName) {
        EmptyAssert.isNotNull(clazz);
        EmptyAssert.isNotBlank(fieldName);

        try {
            return clazz.getDeclaredField(fieldName);
        } catch (final NoSuchFieldException e) {
            throw new RuntimeException("The field could not be found by the fieldName");
        }
    }

    /*
        查找 所有 属性
     */
    public static List<Field> queryFieldList(@NotNullTag Class<?> clazz) {
        return queryFieldList(clazz, null);
    }

    public static List<Field> queryFieldList(@NotNullTag final Class<?> clazz, final Predicate<Field> filter) {
        return queryFromAll(clazz, filter);
    }

    public static List<Field> queryFromAll(@NotNullTag final Class<?> clazz) {
        return queryFromAll(clazz, null);
    }

    public static List<Field> queryFromAll(@NotNullTag final Class<?> clazz,
                                           final Predicate<Field> filter) {
        EmptyAssert.allNotNull(clazz);

        final List<Field> fieldList = new ArrayList<>();
        for (Class<?> eachClazz = clazz; eachClazz != null; eachClazz = eachClazz.getSuperclass()) {
            Field[] fields = eachClazz.getDeclaredFields();
            if (EmptyValidate.isEmpty(fields)) {
                continue;
            }
            Arrays.stream(fields)
                    .filter(field -> EmptyValidate.isNull(filter) || filter.test(field))
                    .forEach(fieldList::add);
        }
        return fieldList;
    }

    public static List<Field> queryFromPub(@NotNullTag final Class<?> clazz) {
        return queryFromPub(clazz, null);
    }

    public static List<Field> queryFromPub(@NotNullTag final Class<?> clazz,
                                           final Predicate<Field> filter) {
        EmptyAssert.allNotNull(clazz);

        Field[] fields = clazz.getFields();
        if (EmptyValidate.isEmpty(fields)) {
            return new ArrayList<>(0);
        }
        return Arrays.stream(fields)
                .filter(field -> EmptyValidate.isNull(filter) || filter.test(field))
                .collect(Collectors.toCollection(() -> new ArrayList<>(fields.length)));
    }

    public static List<Field> queryFromOwn(@NotNullTag final Class<?> clazz) {
        return queryFromOwn(clazz, null);
    }

    public static List<Field> queryFromOwn(@NotNullTag final Class<?> clazz,
                                           final Predicate<Field> filter) {
        EmptyAssert.isNotNull(clazz);

        Field[] fields = clazz.getDeclaredFields();
        if (EmptyValidate.isEmpty(fields)) {
            return new ArrayList<>(0);
        }
        return Arrays.stream(fields)
                .filter(field -> EmptyValidate.isNull(filter) || filter.test(field))
                .collect(Collectors.toCollection(() -> new ArrayList<>(fields.length)));
    }
}
