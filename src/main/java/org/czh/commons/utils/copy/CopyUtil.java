package org.czh.commons.utils.copy;

import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.utils.convertor.CollectionConvertor;
import org.czh.commons.utils.ConstructorUtil;
import org.czh.commons.utils.FieldUtil;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;
import org.czh.commons.validate.EqualsAssert;
import org.czh.commons.validate.EqualsValidate;
import org.czh.commons.validate.FlagAssert;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021-06-28
 * email 916419307@qq.com
 */
public final class CopyUtil {

    private static final Map<String, List<FieldMapping>> fieldMappingListMap = new HashMap<>();

    public static <S, T> T copy(@NotNullTag final S source, @NotNullTag final Class<T> targetClazz) {
        EmptyAssert.isNotNull(targetClazz);

        T target = ConstructorUtil.newInstance(targetClazz);
        copy(source, target);
        return target;
    }

    public static <S, T> void copy(@NotNullTag final S source, @NotNullTag final T target) {
        EmptyAssert.allNotNull(source, target);

        Class<?> sourceClazz = source.getClass();
        Class<?> targetClazz = target.getClass();
        List<FieldMapping> fieldMappingList = getFieldMappingList(sourceClazz, targetClazz);
        EmptyAssert.isNotNull(fieldMappingList);
        // 目标找不到任何可以匹配的属性
        if (EmptyValidate.isEmpty(fieldMappingList)) {
            return;
        }

        for (FieldMapping fieldMapping : fieldMappingList) {
            Object resultFieldValue = FieldUtil.readField(source, fieldMapping.getSourceField());
            if (EmptyValidate.isNull(resultFieldValue)) {
                continue;
            }

            //noinspection rawtypes
            Class<? extends IFieldConverter> fieldConverterClazz = fieldMapping.getFieldConverterClazz();
            if (EmptyValidate.isNotNull(fieldConverterClazz)) {
                //noinspection rawtypes
                IFieldConverter fieldConverter = ConstructorUtil.newInstance(fieldConverterClazz);
                //noinspection unchecked
                resultFieldValue = fieldConverter.convert(resultFieldValue, fieldMapping);
            }
            FieldUtil.writeField(target, fieldMapping.getTargetField(), resultFieldValue);
        }
    }

    private static List<FieldMapping> getFieldMappingList(@NotNullTag final Class<?> sourceClazz,
                                                          @NotNullTag final Class<?> targetClazz) {
        EmptyAssert.allNotNull(sourceClazz, targetClazz);

        String fieldMappingListMapKey = createFieldMappingListMapKey(sourceClazz, targetClazz);
        List<FieldMapping> fieldMappingList = fieldMappingListMap.get(fieldMappingListMapKey);
        if (EmptyValidate.isNull(fieldMappingList)) {
            synchronized (CopyUtil.class) {
                fieldMappingList = fieldMappingListMap.get(fieldMappingListMapKey);
                if (EmptyValidate.isNull(fieldMappingList)) {
                    createFieldMapping(sourceClazz, targetClazz);
                    fieldMappingList = fieldMappingListMap.get(fieldMappingListMapKey);
                    EmptyAssert.isNotNull(fieldMappingList);
                }
            }
        }
        return fieldMappingList;
    }

    private static void createFieldMapping(@NotNullTag final Class<?> sourceClazz,
                                           @NotNullTag final Class<?> targetClazz) {
        EmptyAssert.allNotNull(sourceClazz, targetClazz);

        List<Field> targetFieldList = FieldUtil.queryFieldList(targetClazz);
        if (EmptyValidate.isEmpty(targetFieldList)) {
            fieldMappingListMap.put(createFieldMappingListMapKey(sourceClazz, targetClazz), new ArrayList<>(0));
            return;
        }
        Map<String, Field> targetFieldMap = CollectionConvertor.convertToMap(targetFieldList, Field::getName);

        List<Field> sourceFieldList = FieldUtil.queryFieldList(sourceClazz);
        EmptyAssert.isNotEmpty(sourceFieldList);

        List<FieldMapping> fieldMappingList = new ArrayList<>(sourceFieldList.size());
        for (Field sourceField : sourceFieldList) {
            if ("serialVersionUID".equals(sourceField.getName())) {
                continue;
            }

            CopyConverter copyConverter = sourceField.getAnnotation(CopyConverter.class);
            if (EmptyValidate.isNotNull(copyConverter) && copyConverter.exclude()) {
                continue;
            }

            if (EmptyValidate.isNull(copyConverter)) {
                Field targetField = targetFieldMap.get(sourceField.getName());
                if (EmptyValidate.isNull(targetField)
                        || EqualsValidate.notEquals(sourceField.getType(), targetField.getType())) {
                    continue;
                }
                fieldMappingList.add(new FieldMapping(sourceField, targetField, null, ""));
            } else {
                String sourceFieldMappingName = copyConverter.name();
                if (EmptyValidate.isBlank(sourceFieldMappingName)) {
                    sourceFieldMappingName = sourceField.getName();
                }
                Field targetField = targetFieldMap.get(sourceFieldMappingName);
                EmptyAssert.isNotNull(targetField, "[Assertion failed] - The target field could not be found");

                Class<? extends IFieldConverter<?, ?>> fieldConverterClazz = copyConverter.using();
                if (EqualsValidate.equals(fieldConverterClazz, IFieldConverter.None.class)) {
                    fieldConverterClazz = null;
                    EqualsAssert.isEquals(sourceField.getType(), targetField.getType());
                } else {
                    Type[] genericInterfaces = fieldConverterClazz.getGenericInterfaces();
                    Type[] actualTypeArguments = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments();
                    try {
                        Class<?> sourceClassMapping = Class.forName(actualTypeArguments[0].getTypeName());
                        FlagAssert.isTrue(sourceClassMapping.isAssignableFrom(sourceField.getType()));

                        Class<?> targetClassMapping = Class.forName(actualTypeArguments[1].getTypeName());
                        FlagAssert.isTrue(targetField.getType().isAssignableFrom(targetClassMapping));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                fieldMappingList.add(new FieldMapping(sourceField, targetField, fieldConverterClazz, copyConverter.expression()));
            }
        }
        fieldMappingListMap.put(createFieldMappingListMapKey(sourceClazz, targetClazz), fieldMappingList);
    }

    private static String createFieldMappingListMapKey(@NotNullTag final Class<?> sourceClazz,
                                                       @NotNullTag final Class<?> targetClazz) {
        EmptyAssert.allNotNull(sourceClazz, targetClazz);
        return String.format("%s:%s", sourceClazz.getName(), targetClazz.getName());
    }
}
