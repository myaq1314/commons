package org.czh.commons.utils;

import org.czh.commons.utils.convertor.CollectionConvertor;
import org.czh.commons.entity.example.SonTest;
import org.czh.commons.validate.EmptyValidate;
import org.czh.commons.validate.FlagAssert;
import org.junit.Test;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author : czh
 * description :
 * date : 2021-04-30
 * email 916419307@qq.com
 */
public class FieldUtilTest {

    @Test
    public void sonWriteFieldTest() {
        SonTest son = new SonTest();

        List<String> fieldNameList = new ArrayList<>();
        Collections.addAll(fieldNameList,
                "gPri", "gPro", "gDef", "gPub",
                "gPriSta", "gProSta", "gDefSta", "gPubSta",
                "sPri", "sPro", "sDef", "sPub",
                "sPriSta", "sProSta", "sDefSta", "sPubSta",
                "pPri", "pPro", "pDef", "pPub",
                "pPriSta", "pProSta", "pDefSta", "pPubSta"
        );

        List<String> targetAllFieldValueList = new ArrayList<>();
        for (String fieldName : fieldNameList) {
            try {
                FieldUtil.writeFromAll(son, fieldName, fieldName + "VaAll");
                String fieldValue = FieldUtil.readFromAll(son, fieldName);
                targetAllFieldValueList.add(fieldValue);
            } catch (RuntimeException ignored) {
            }
        }
        List<String> compareAllFieldValueList = new ArrayList<>();
        Collections.addAll(compareAllFieldValueList,
                "sPriVaAll", "sProVaAll", "sDefVaAll", "sPubVaAll",
                "sPriStaVaAll", "sProStaVaAll", "sDefStaVaAll", "sPubStaVaAll",
                "pPriVaAll", "pProVaAll", "pDefVaAll", "pPubVaAll",
                "pPriStaVaAll", "pProStaVaAll", "pDefStaVaAll", "pPubStaVaAll"
        );
        FlagAssert.isTrue(Objects.equals(targetAllFieldValueList, compareAllFieldValueList));

        List<String> targetPubFieldValueList = new ArrayList<>();
        for (String fieldName : fieldNameList) {
            try {
                FieldUtil.writeFromPub(son, fieldName, fieldName + "VaPub");
                String fieldValue = FieldUtil.readFromPub(son, fieldName);
                targetPubFieldValueList.add(fieldValue);
            } catch (RuntimeException ignored) {
            }
        }
        List<String> comparePubFieldValueList = new ArrayList<>();
        Collections.addAll(comparePubFieldValueList, "sPubVaPub", "sPubStaVaPub", "pPubVaPub", "pPubStaVaPub");
        FlagAssert.isTrue(Objects.equals(targetPubFieldValueList, comparePubFieldValueList));

        List<String> targetOwnFieldValueList = new ArrayList<>();
        for (String fieldName : fieldNameList) {
            try {
                FieldUtil.writeFromOwn(son, fieldName, fieldName + "VaOwn");
                String fieldValue = FieldUtil.readFromOwn(son, fieldName);
                targetOwnFieldValueList.add(fieldValue);
            } catch (RuntimeException ignored) {
            }
        }
        List<String> compareOwnFieldValueList = new ArrayList<>();
        Collections.addAll(compareOwnFieldValueList,
                "sPriVaOwn", "sProVaOwn", "sDefVaOwn", "sPubVaOwn",
                "sPriStaVaOwn", "sProStaVaOwn", "sDefStaVaOwn", "sPubStaVaOwn"
        );
        FlagAssert.isTrue(Objects.equals(targetOwnFieldValueList, compareOwnFieldValueList));
    }

    @Test
    public void sonWriteStaticFieldTest() {
        Class<?> sonClazz = SonTest.class;

        List<String> fieldNameList = new ArrayList<>();
        Collections.addAll(fieldNameList,
                "gPri", "gPro", "gDef", "gPub",
                "gPriSta", "gProSta", "gDefSta", "gPubSta",
                "sPri", "sPro", "sDef", "sPub",
                "sPriSta", "sProSta", "sDefSta", "sPubSta",
                "pPri", "pPro", "pDef", "pPub",
                "pPriSta", "pProSta", "pDefSta", "pPubSta"
        );

        List<String> targetAllStaticFieldValueList = new ArrayList<>();
        for (String fieldName : fieldNameList) {
            try {
                FieldUtil.writeStaticField(sonClazz, fieldName, fieldName + "StaVaAll");
                String fieldValue = FieldUtil.readStaticField(sonClazz, fieldName);
                targetAllStaticFieldValueList.add(fieldValue);
            } catch (RuntimeException ignored) {

            }
        }
        List<String> compareAllStaticFieldValueList = new ArrayList<>();
        Collections.addAll(compareAllStaticFieldValueList,
                "sPriStaStaVaAll", "sProStaStaVaAll", "sDefStaStaVaAll", "sPubStaStaVaAll",
                "pPriStaStaVaAll", "pProStaStaVaAll", "pDefStaStaVaAll", "pPubStaStaVaAll"
        );
        FlagAssert.isTrue(Objects.equals(targetAllStaticFieldValueList, compareAllStaticFieldValueList));

        List<String> targetPubStaticFieldValueList = new ArrayList<>();
        for (String fieldName : fieldNameList) {
            try {
                FieldUtil.writeStaticFromPub(sonClazz, fieldName, fieldName + "StaVaPub");
                String fieldValue = FieldUtil.readStaticFromPub(sonClazz, fieldName);
                targetPubStaticFieldValueList.add(fieldValue);
            } catch (RuntimeException ignored) {
            }
        }
        List<String> comparePubStaticFieldValueList = new ArrayList<>();
        Collections.addAll(comparePubStaticFieldValueList, "sPubStaStaVaPub", "pPubStaStaVaPub");
        FlagAssert.isTrue(Objects.equals(targetPubStaticFieldValueList, comparePubStaticFieldValueList));

        List<String> targetOwnStaticFieldValueList = new ArrayList<>();
        for (String fieldName : fieldNameList) {
            try {
                FieldUtil.writeStaticFromOwn(sonClazz, fieldName, fieldName + "StaVaOwn");
                String fieldValue = FieldUtil.readStaticFromOwn(sonClazz, fieldName);
                targetOwnStaticFieldValueList.add(fieldValue);
            } catch (RuntimeException ignored) {
            }
        }
        List<String> compareOwnStaticFieldValueList = new ArrayList<>();
        Collections.addAll(compareOwnStaticFieldValueList,
                "sPriStaStaVaOwn", "sProStaStaVaOwn", "sDefStaStaVaOwn", "sPubStaStaVaOwn"
        );
        FlagAssert.isTrue(Objects.equals(targetOwnStaticFieldValueList, compareOwnStaticFieldValueList));
    }

    @Test
    public void sonReadFieldTest() {
        SonTest son = new SonTest();

        List<String> fieldNameList = new ArrayList<>();
        Collections.addAll(fieldNameList,
                "gPri", "gPro", "gDef", "gPub",
                "gPriSta", "gProSta", "gDefSta", "gPubSta",
                "sPri", "sPro", "sDef", "sPub",
                "sPriSta", "sProSta", "sDefSta", "sPubSta",
                "pPri", "pPro", "pDef", "pPub",
                "pPriSta", "pProSta", "pDefSta", "pPubSta"
        );

        List<String> targetAllFieldValueList = new ArrayList<>();
        for (String fieldName : fieldNameList) {
            try {
                String fieldValue = FieldUtil.readFromAll(son, fieldName);
                targetAllFieldValueList.add(fieldValue);
            } catch (RuntimeException ignored) {
            }
        }
        List<String> compareAllFieldValueList = new ArrayList<>();
        Collections.addAll(compareAllFieldValueList,
                "sPriVa", "sProVa", "sDefVa", "sPubVa",
                "sPriStaVa", "sProStaVa", "sDefStaVa", "sPubStaVa",
                "pPriVa", "pProVa", "pDefVa", "pPubVa",
                "pPriStaVa", "pProStaVa", "pDefStaVa", "pPubStaVa"
        );
        FlagAssert.isTrue(Objects.equals(targetAllFieldValueList, compareAllFieldValueList));

        List<String> targetPubFieldValueList = new ArrayList<>();
        for (String fieldName : fieldNameList) {
            try {
                String fieldValue = FieldUtil.readFromPub(son, fieldName);
                targetPubFieldValueList.add(fieldValue);
            } catch (RuntimeException ignored) {
            }
        }
        List<String> comparePubFieldValueList = new ArrayList<>();
        Collections.addAll(comparePubFieldValueList, "sPubVa", "sPubStaVa", "pPubVa", "pPubStaVa");
        FlagAssert.isTrue(Objects.equals(targetPubFieldValueList, comparePubFieldValueList));

        List<String> targetOwnFieldValueList = new ArrayList<>();
        for (String fieldName : fieldNameList) {
            try {
                String fieldValue = FieldUtil.readFromOwn(son, fieldName);
                targetOwnFieldValueList.add(fieldValue);
            } catch (RuntimeException ignored) {
            }
        }
        List<String> compareOwnFieldValueList = new ArrayList<>();
        Collections.addAll(compareOwnFieldValueList,
                "sPriVa", "sProVa", "sDefVa", "sPubVa",
                "sPriStaVa", "sProStaVa", "sDefStaVa", "sPubStaVa"
        );
        FlagAssert.isTrue(Objects.equals(targetOwnFieldValueList, compareOwnFieldValueList));
    }

    @Test
    public void sonReadStaticFieldTest() {
        Class<?> sonClazz = SonTest.class;

        List<String> fieldNameList = new ArrayList<>();
        Collections.addAll(fieldNameList,
                "gPri", "gPro", "gDef", "gPub",
                "gPriSta", "gProSta", "gDefSta", "gPubSta",
                "sPri", "sPro", "sDef", "sPub",
                "sPriSta", "sProSta", "sDefSta", "sPubSta",
                "pPri", "pPro", "pDef", "pPub",
                "pPriSta", "pProSta", "pDefSta", "pPubSta"
        );

        List<String> targetAllStaticFieldValueList = new ArrayList<>();
        for (String fieldName : fieldNameList) {
            try {
                String fieldValue = FieldUtil.readStaticField(sonClazz, fieldName);
                targetAllStaticFieldValueList.add(fieldValue);
            } catch (RuntimeException ignored) {
            }
        }
        List<String> compareAllStaticFieldValueList = new ArrayList<>();
        Collections.addAll(compareAllStaticFieldValueList,
                "sPriStaVa", "sProStaVa", "sDefStaVa", "sPubStaVa",
                "pPriStaVa", "pProStaVa", "pDefStaVa", "pPubStaVa"
        );
        FlagAssert.isTrue(Objects.equals(targetAllStaticFieldValueList, compareAllStaticFieldValueList));

        List<String> targetPubStaticFieldValueList = new ArrayList<>();
        for (String fieldName : fieldNameList) {
            try {
                String fieldValue = FieldUtil.readStaticFromPub(sonClazz, fieldName);
                targetPubStaticFieldValueList.add(fieldValue);
            } catch (RuntimeException ignored) {
            }
        }
        List<String> comparePubStaticFieldValueList = new ArrayList<>();
        Collections.addAll(comparePubStaticFieldValueList, "sPubStaVa", "pPubStaVa");
        FlagAssert.isTrue(Objects.equals(targetPubStaticFieldValueList, comparePubStaticFieldValueList));

        List<String> targetOwnStaticFieldValueList = new ArrayList<>();
        for (String fieldName : fieldNameList) {
            try {
                String fieldValue = FieldUtil.readStaticFromOwn(sonClazz, fieldName);
                targetOwnStaticFieldValueList.add(fieldValue);
            } catch (RuntimeException ignored) {
            }
        }
        List<String> compareOwnStaticFieldValueList = new ArrayList<>();
        Collections.addAll(compareOwnStaticFieldValueList,
                "sPriStaVa", "sProStaVa", "sDefStaVa", "sPubStaVa"
        );
        FlagAssert.isTrue(Objects.equals(targetOwnStaticFieldValueList, compareOwnStaticFieldValueList));
    }

    @Test
    public void sonGetFieldTest() {
        SonTest son = new SonTest();
        Class<?> sonClazz = son.getClass();

        List<String> fieldNameList = new ArrayList<>();
        Collections.addAll(fieldNameList,
                "gPri", "gPro", "gDef", "gPub",
                "gPriSta", "gProSta", "gDefSta", "gPubSta",
                "sPri", "sPro", "sDef", "sPub",
                "sPriSta", "sProSta", "sDefSta", "sPubSta",
                "pPri", "pPro", "pDef", "pPub",
                "pPriSta", "pProSta", "pDefSta", "pPubSta"
        );

        List<String> targetAllFieldNameList = new ArrayList<>();
        for (String fieldName : fieldNameList) {
            try {
                Field field = FieldUtil.getFromAll(sonClazz, fieldName);
                targetAllFieldNameList.add(field.getName());
            } catch (RuntimeException ignored) {
            }
        }
        List<String> compareAllFieldNameList = new ArrayList<>();
        Collections.addAll(compareAllFieldNameList,
                "sPri", "sPro", "sDef", "sPub",
                "sPriSta", "sProSta", "sDefSta", "sPubSta",
                "pPri", "pPro", "pDef", "pPub",
                "pPriSta", "pProSta", "pDefSta", "pPubSta"
        );
        FlagAssert.isTrue(Objects.equals(targetAllFieldNameList, compareAllFieldNameList));

        List<String> targetPubFieldNameList = new ArrayList<>();
        for (String fieldName : fieldNameList) {
            try {
                Field field = FieldUtil.getFromPub(sonClazz, fieldName);
                targetPubFieldNameList.add(field.getName());
            } catch (RuntimeException ignored) {
            }
        }
        List<String> comparePubFieldNameList = new ArrayList<>();
        Collections.addAll(comparePubFieldNameList, "sPub", "sPubSta", "pPub", "pPubSta");
        FlagAssert.isTrue(Objects.equals(targetPubFieldNameList, comparePubFieldNameList));

        List<String> targetOwnFieldNameList = new ArrayList<>();
        for (String fieldName : fieldNameList) {
            try {
                Field field = FieldUtil.getFromOwn(sonClazz, fieldName);
                targetOwnFieldNameList.add(field.getName());
            } catch (RuntimeException ignored) {
            }
        }
        List<String> compareOwnFieldNameList = new ArrayList<>();
        Collections.addAll(compareOwnFieldNameList,
                "sPri", "sPro", "sDef", "sPub",
                "sPriSta", "sProSta", "sDefSta", "sPubSta"
        );
        FlagAssert.isTrue(Objects.equals(targetOwnFieldNameList, compareOwnFieldNameList));
    }

    @Test
    public void sonQueryFieldTest() {
        SonTest son = new SonTest();
        Class<?> sonClazz = son.getClass();

        List<Field> targetAllFieldList = FieldUtil.queryFromAll(sonClazz);
        List<String> targetAllFieldNameList = CollectionConvertor.convertToList(targetAllFieldList, Field::getName);
        List<String> compareAllFieldNameList = new ArrayList<>();
        Collections.addAll(compareAllFieldNameList,
                "sPubSta", "sProSta", "sDefSta", "sPriSta",
                "sPub", "sPro", "sDef", "sPri",
                "pPubSta", "pProSta", "pDefSta", "pPriSta",
                "pPub", "pPro", "pDef", "pPri"
        );
        FlagAssert.isTrue(Objects.equals(targetAllFieldNameList, compareAllFieldNameList));

        List<Field> targetPubFieldList = FieldUtil.queryFromPub(sonClazz);
        List<String> targetPubFieldNameList = CollectionConvertor.convertToList(targetPubFieldList, Field::getName);
        List<String> comparePubFieldNameList = new ArrayList<>();
        Collections.addAll(comparePubFieldNameList, "sPubSta", "sPub", "pPubSta", "pPub");
        FlagAssert.isTrue(Objects.equals(targetPubFieldNameList, comparePubFieldNameList));

        List<Field> targetOwnFieldList = FieldUtil.queryFromOwn(sonClazz);
        List<String> targetOwnFieldNameList = CollectionConvertor.convertToList(targetOwnFieldList, Field::getName);
        List<String> compareOwnFieldNameList = new ArrayList<>();
        Collections.addAll(compareOwnFieldNameList,
                "sPubSta", "sProSta", "sDefSta", "sPriSta",
                "sPub", "sPro", "sDef", "sPri"
        );
        FlagAssert.isTrue(Objects.equals(targetOwnFieldNameList, compareOwnFieldNameList));
    }

    @Test
    public void sonQueryFieldWithAnnoTest() {
        SonTest son = new SonTest();
        Class<?> sonClazz = son.getClass();

        List<Field> targetAllFieldList = FieldUtil.queryFromAll(sonClazz, field -> EmptyValidate.isNotNull(field.getAnnotation(NotNull.class)));
        List<String> targetAllFieldNameList = CollectionConvertor.convertToList(targetAllFieldList, Field::getName);
        List<String> compareAllFieldNameList = new ArrayList<>();
        Collections.addAll(compareAllFieldNameList, "sDefSta", "sDef", "pDefSta", "pDef");
        FlagAssert.isTrue(Objects.equals(targetAllFieldNameList, compareAllFieldNameList));

        List<Field> targetPubFieldList = FieldUtil.queryFromPub(sonClazz, field -> EmptyValidate.isNotNull(field.getAnnotation(Pattern.class)));
        List<String> targetPubFieldNameList = CollectionConvertor.convertToList(targetPubFieldList, Field::getName);
        List<String> comparePubFieldNameList = new ArrayList<>();
        Collections.addAll(comparePubFieldNameList, "sPubSta", "sPub", "pPubSta", "pPub");
        FlagAssert.isTrue(Objects.equals(targetPubFieldNameList, comparePubFieldNameList));

        List<Field> targetOwnFieldList = FieldUtil.queryFromOwn(sonClazz, field -> EmptyValidate.isNotNull(field.getAnnotation(NotBlank.class)));
        List<String> targetOwnFieldNameList = CollectionConvertor.convertToList(targetOwnFieldList, Field::getName);
        List<String> compareOwnFieldNameList = new ArrayList<>();
        Collections.addAll(compareOwnFieldNameList, "sPriSta", "sPri");
        FlagAssert.isTrue(Objects.equals(targetOwnFieldNameList, compareOwnFieldNameList));
    }
}


