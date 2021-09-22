package org.czh.commons.utils.copy;

import org.czh.commons.utils.ConstructorUtil;
import org.czh.commons.utils.FieldUtil;
import org.czh.commons.utils.convertor.CollectionConvertor;
import org.czh.commons.utils.fastjson.deserializer.IObjectDeserializer;
import org.czh.commons.utils.fastjson.deserializer.NoneDeserializer;
import org.czh.commons.utils.fastjson.serializer.IObjectSerializer;
import org.czh.commons.utils.fastjson.serializer.NoneSerializer;
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

    public static <Source, Target> Target copyFrom(final Source source,
                                                   final Class<Target> targetClazz) {
        EmptyAssert.isNotNull(targetClazz);

        Target target = ConstructorUtil.newInstance(targetClazz);
        copyFrom(source, target);
        return target;
    }

    public static <Source, Target> void copyFrom(final Source source, final Target target) {
        copy(source, target, getFromType());
    }

    public static <Source, Target> Target copyTo(final Source source,
                                                 final Class<Target> targetClazz) {
        EmptyAssert.isNotNull(targetClazz);

        Target target = ConstructorUtil.newInstance(targetClazz);
        copyTo(source, target);
        return target;
    }

    public static <Source, Target> void copyTo(final Source source, final Target target) {
        copy(source, target, getToType());
    }

    // type:    TO/FROM
    // TO:      CopyUtil.getToType()
    // FROM:    CopyUtil.getFromType()
    private static <Source, Target> void copy(final Source source,
                                              final Target target,
                                              String type) {
        EmptyAssert.allNotNull(source, target);
        EmptyAssert.isNotBlank(type);

        Class<?> sourceClazz = source.getClass();
        Class<?> targetClazz = target.getClass();
        List<FieldMapping> fieldMappingList = getFieldMappingList(sourceClazz, targetClazz, type);
        EmptyAssert.isNotNull(fieldMappingList);
        if (EmptyValidate.isEmpty(fieldMappingList)) { // 目标找不到任何可以匹配的属性
            return;
        }

        for (FieldMapping fieldMapping : fieldMappingList) {
            Object resultFieldValue = FieldUtil.readField(source, fieldMapping.getSourceField());
            if (EmptyValidate.isNull(resultFieldValue)) { // null值结果不处理
                continue;
            }

            if (getToType().equals(type)) {
                //noinspection rawtypes
                Class<? extends IObjectDeserializer> objectDeserializerClazz = fieldMapping.getObjectDeserializerClazz();
                if (EmptyValidate.isNotNull(objectDeserializerClazz)) {
                    //noinspection rawtypes
                    IObjectDeserializer objectDeserializer = ConstructorUtil.newInstance(objectDeserializerClazz);
                    //noinspection unchecked
                    resultFieldValue = objectDeserializer.deserialize(resultFieldValue, fieldMapping.getFormat());
                }
            } else {
                //noinspection rawtypes
                Class<? extends IObjectSerializer> objectSerializerClazz = fieldMapping.getObjectSerializerClazz();
                if (EmptyValidate.isNotNull(objectSerializerClazz)) {
                    //noinspection rawtypes
                    IObjectSerializer objectSerializer = ConstructorUtil.newInstance(objectSerializerClazz);
                    //noinspection unchecked
                    resultFieldValue = objectSerializer.serialize(resultFieldValue, fieldMapping.getFormat());
                }
            }
            FieldUtil.writeField(target, fieldMapping.getTargetField(), resultFieldValue);
        }
    }

    // type:    TO/FROM
    // TO:      CopyUtil.getToType()
    // FROM:    CopyUtil.getFromType()
    private static List<FieldMapping> getFieldMappingList(final Class<?> sourceClazz,
                                                          final Class<?> targetClazz,
                                                          final String type) {
        EmptyAssert.allNotNull(sourceClazz, targetClazz);
        EmptyAssert.isNotBlank(type);

        String fieldMappingListMapKey = createFieldMappingListMapKey(sourceClazz, targetClazz, type);
        List<FieldMapping> fieldMappingList = fieldMappingListMap.get(fieldMappingListMapKey);
        if (EmptyValidate.isNull(fieldMappingList)) {
            synchronized (CopyUtil.class) {
                fieldMappingList = fieldMappingListMap.get(fieldMappingListMapKey);
                if (EmptyValidate.isNull(fieldMappingList)) {
                    if (getToType().equals(type)) {
                        createToFieldMapping(sourceClazz, targetClazz);
                    } else {
                        createFromFieldMapping(sourceClazz, targetClazz);
                    }
                    fieldMappingList = fieldMappingListMap.get(fieldMappingListMapKey);
                    EmptyAssert.isNotNull(fieldMappingList);
                }
            }
        }
        return fieldMappingList;
    }

    private static void createFromFieldMapping(final Class<?> sourceClazz,
                                               final Class<?> targetClazz) {
        EmptyAssert.allNotNull(sourceClazz, targetClazz);

        List<Field> sourceFieldList = FieldUtil.queryFieldList(sourceClazz);
        List<Field> targetFieldList = FieldUtil.queryFieldList(targetClazz);
        if (EmptyValidate.isEmpty(sourceFieldList) || EmptyValidate.isEmpty(targetFieldList)) {
            fieldMappingListMap.put(createFieldMappingListMapKey(sourceClazz, targetClazz, getFromType()), new ArrayList<>(0));
            return;
        }
        Map<String, Field> sourceFieldMap = CollectionConvertor.convertToMap(sourceFieldList, Field::getName);

        List<FieldMapping> fieldMappingList = new ArrayList<>(targetFieldList.size());
        for (Field targetField : targetFieldList) {
            if ("serialVersionUID".equals(targetField.getName())) {
                continue;
            }

            CopyFromField copyFromField = targetField.getAnnotation(CopyFromField.class);
            if (EmptyValidate.isNotNull(copyFromField) && copyFromField.exclude()) {
                continue;
            }

            if (EmptyValidate.isNull(copyFromField)) {
                Field sourceField = sourceFieldMap.get(targetField.getName());
                if (EmptyValidate.isNull(sourceField)
                        || EqualsValidate.notEquals(sourceField.getType(), targetField.getType())) {
                    continue;
                }
                fieldMappingList.add(new FieldMapping(sourceField, targetField, null, null, ""));
            } else {
                String sourceFieldName = copyFromField.match();
                if (EmptyValidate.isBlank(sourceFieldName)) {
                    sourceFieldName = targetField.getName();
                }
                Field sourceField = sourceFieldMap.get(sourceFieldName);
                EmptyAssert.isNotNull(sourceField, "[Assertion failed] - The source field could not be found");

                Class<? extends IObjectSerializer<?, ?>> objectSerializerClazz = copyFromField.using();
                if (EqualsValidate.equals(objectSerializerClazz, NoneSerializer.class)) {
                    objectSerializerClazz = null;
                    EqualsAssert.isEquals(sourceField.getType(), targetField.getType());
                } else {
                    Type genericSuperclass = objectSerializerClazz.getGenericSuperclass();
                    Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
                    try {
                        Class<?> sourceClassMapping = Class.forName(actualTypeArguments[0].getTypeName());
                        FlagAssert.isTrue(sourceClassMapping.isAssignableFrom(sourceField.getType()));

                        Class<?> targetClassMapping = Class.forName(actualTypeArguments[1].getTypeName());
                        FlagAssert.isTrue(targetField.getType().isAssignableFrom(targetClassMapping));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                fieldMappingList.add(new FieldMapping(sourceField, targetField, null, objectSerializerClazz, copyFromField.format()));
            }
        }
        fieldMappingListMap.put(createFieldMappingListMapKey(sourceClazz, targetClazz, getFromType()), fieldMappingList);

    }

    private static void createToFieldMapping(final Class<?> sourceClazz,
                                             final Class<?> targetClazz) {
        EmptyAssert.allNotNull(sourceClazz, targetClazz);

        List<Field> sourceFieldList = FieldUtil.queryFieldList(sourceClazz);
        List<Field> targetFieldList = FieldUtil.queryFieldList(targetClazz);
        if (EmptyValidate.isEmpty(sourceFieldList) || EmptyValidate.isEmpty(targetFieldList)) {
            fieldMappingListMap.put(createFieldMappingListMapKey(sourceClazz, targetClazz, getToType()), new ArrayList<>(0));
            return;
        }
        Map<String, Field> targetFieldMap = CollectionConvertor.convertToMap(targetFieldList, Field::getName);

        List<FieldMapping> fieldMappingList = new ArrayList<>(sourceFieldList.size());
        for (Field sourceField : sourceFieldList) {
            if ("serialVersionUID".equals(sourceField.getName())) {
                continue;
            }

            CopyToField copyToField = sourceField.getAnnotation(CopyToField.class);
            if (EmptyValidate.isNotNull(copyToField) && copyToField.exclude()) {
                continue;
            }

            if (EmptyValidate.isNull(copyToField)) {
                Field targetField = targetFieldMap.get(sourceField.getName());
                if (EmptyValidate.isNull(targetField)
                        || EqualsValidate.notEquals(sourceField.getType(), targetField.getType())) {
                    continue;
                }
                fieldMappingList.add(new FieldMapping(sourceField, targetField, null, null, ""));
            } else {
                String targetFieldName = copyToField.match();
                if (EmptyValidate.isBlank(targetFieldName)) {
                    targetFieldName = sourceField.getName();
                }
                Field targetField = targetFieldMap.get(targetFieldName);
                EmptyAssert.isNotNull(targetField, "[Assertion failed] - The target field could not be found");

                Class<? extends IObjectDeserializer<?, ?>> objectDeserializerClazz = copyToField.using();
                if (EqualsValidate.equals(objectDeserializerClazz, NoneDeserializer.class)) {
                    objectDeserializerClazz = null;
                    EqualsAssert.isEquals(sourceField.getType(), targetField.getType());
                } else {
                    Type genericSuperclass = objectDeserializerClazz.getGenericSuperclass();
                    Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
                    try {
                        Class<?> sourceClassMapping = Class.forName(actualTypeArguments[0].getTypeName());
                        FlagAssert.isTrue(sourceClassMapping.isAssignableFrom(sourceField.getType()));

                        Class<?> targetClassMapping = Class.forName(actualTypeArguments[1].getTypeName());
                        FlagAssert.isTrue(targetField.getType().isAssignableFrom(targetClassMapping));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                fieldMappingList.add(new FieldMapping(sourceField, targetField, objectDeserializerClazz, null, copyToField.format()));
            }
        }
        fieldMappingListMap.put(createFieldMappingListMapKey(sourceClazz, targetClazz, getToType()), fieldMappingList);
    }

    private static String getToType() {
        return "TO";
    }

    private static String getFromType() {
        return "FROM";
    }

    private static String createFieldMappingListMapKey(final Class<?> sourceClazz,
                                                       final Class<?> targetClazz,
                                                       final String type) {
        EmptyAssert.allNotNull(sourceClazz, targetClazz);
        EmptyAssert.isNotBlank(type);
        return String.format("%s:%s:%s", sourceClazz.getName(), type, targetClazz.getName());
    }
}
