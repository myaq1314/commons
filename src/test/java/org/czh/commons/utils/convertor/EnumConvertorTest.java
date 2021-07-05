package org.czh.commons.utils.convertor;

import org.czh.commons.enums.ConvertorTestDict;
import org.czh.commons.validate.FlagAssert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author : czh
 * description :
 * date : 2021-04-29
 * email 916419307@qq.com
 */
public class EnumConvertorTest {

    @Test
    public void convertToNewArrayTest() {
        ConvertorTestDict[] compare1 = new ConvertorTestDict[]{ConvertorTestDict.ARRAY, ConvertorTestDict.LIST, ConvertorTestDict.SET};
        ConvertorTestDict[] target1 = EnumConvertor.convertToArray(ConvertorTestDict.class);
        FlagAssert.isTrue(Arrays.equals(target1, compare1));

        String[] compare2 = new String[]{"array", "list", "set"};
        String[] target2 = EnumConvertor.convertToArray(ConvertorTestDict.class, String.class, s -> s.key);
        FlagAssert.isTrue(Arrays.equals(target2, compare2));

        ConvertorTestDict[] compare3 = new ConvertorTestDict[]{ConvertorTestDict.LIST, ConvertorTestDict.SET};
        ConvertorTestDict[] target3 = EnumConvertor.convertToArray(
                ConvertorTestDict.class,
                ConvertorTestDict.class,
                s -> s,
                s -> CollectionConvertor.class.equals(s.value)
        );
        FlagAssert.isTrue(Arrays.equals(target3, compare3));
    }

    @Test
    public void convertToNewListTest() {
        List<ConvertorTestDict> compare1 = new ArrayList<>();
        Collections.addAll(compare1, ConvertorTestDict.ARRAY, ConvertorTestDict.LIST, ConvertorTestDict.SET);
        List<ConvertorTestDict> target1 = EnumConvertor.convertToList(ConvertorTestDict.class);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        List<String> compare2 = new ArrayList<>();
        Collections.addAll(compare2, "array", "list", "set");
        List<String> target2 = EnumConvertor.convertToList(ConvertorTestDict.class, s -> s.key);
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        List<ConvertorTestDict> compare3 = new ArrayList<>();
        Collections.addAll(compare3, ConvertorTestDict.LIST, ConvertorTestDict.SET);
        List<ConvertorTestDict> target3 = EnumConvertor.convertToList(
                ConvertorTestDict.class,
                s -> s,
                s -> CollectionConvertor.class.equals(s.value)
        );
        FlagAssert.isTrue(Objects.equals(target3, compare3));
    }

    @Test
    public void convertToOldListTest() {
        List<ConvertorTestDict> compare1 = new ArrayList<>();
        Collections.addAll(compare1, ConvertorTestDict.ARRAY, ConvertorTestDict.LIST, ConvertorTestDict.SET);
        List<ConvertorTestDict> target1 = new ArrayList<>();
        EnumConvertor.convertToCollection(ConvertorTestDict.class, target1);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        List<String> compare2 = new ArrayList<>();
        Collections.addAll(compare2, "array", "list", "set");
        List<String> target2 = new ArrayList<>();
        EnumConvertor.convertToCollection(ConvertorTestDict.class, target2, s -> s.key);
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        List<ConvertorTestDict> compare3 = new ArrayList<>();
        Collections.addAll(compare3, ConvertorTestDict.LIST, ConvertorTestDict.SET);
        List<ConvertorTestDict> target3 = new ArrayList<>();
        EnumConvertor.convertToCollection(ConvertorTestDict.class, target3,
                s -> s,
                s -> CollectionConvertor.class.equals(s.value)
        );
        FlagAssert.isTrue(Objects.equals(target3, compare3));
    }

    @Test
    public void convertToNewSetTest() {
        Set<ConvertorTestDict> compare1 = new HashSet<>();
        Collections.addAll(compare1, ConvertorTestDict.ARRAY, ConvertorTestDict.LIST, ConvertorTestDict.SET);
        Set<ConvertorTestDict> target1 = EnumConvertor.convertToSet(ConvertorTestDict.class);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        Set<String> compare2 = new HashSet<>();
        Collections.addAll(compare2, "array", "list", "set");
        Set<String> target2 = EnumConvertor.convertToSet(ConvertorTestDict.class, s -> s.key);
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        Set<ConvertorTestDict> compare3 = new HashSet<>();
        Collections.addAll(compare3, ConvertorTestDict.LIST, ConvertorTestDict.SET);
        Set<ConvertorTestDict> target3 = EnumConvertor.convertToSet(
                ConvertorTestDict.class,
                s -> s,
                s -> CollectionConvertor.class.equals(s.value)
        );
        FlagAssert.isTrue(Objects.equals(target3, compare3));
    }

    @Test
    public void convertToOldSetTest() {
        Set<ConvertorTestDict> compare1 = new HashSet<>();
        Collections.addAll(compare1, ConvertorTestDict.ARRAY, ConvertorTestDict.LIST, ConvertorTestDict.SET);
        Set<ConvertorTestDict> target1 = new HashSet<>();
        EnumConvertor.convertToCollection(ConvertorTestDict.class, target1);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        Set<String> compare2 = new HashSet<>();
        Collections.addAll(compare2, "array", "list", "set");
        Set<String> target2 = new HashSet<>();
        EnumConvertor.convertToCollection(ConvertorTestDict.class, target2, s -> s.key);
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        Set<ConvertorTestDict> compare3 = new HashSet<>();
        Collections.addAll(compare3, ConvertorTestDict.LIST, ConvertorTestDict.SET);
        Set<ConvertorTestDict> target3 = new HashSet<>();
        EnumConvertor.convertToCollection(ConvertorTestDict.class, target3,
                s -> s,
                s -> CollectionConvertor.class.equals(s.value)
        );
        FlagAssert.isTrue(Objects.equals(target3, compare3));
    }

    @Test
    public void convertToNewMapTest() {
        Map<ConvertorTestDict, ConvertorTestDict> compare1 = new HashMap<>();
        compare1.put(ConvertorTestDict.ARRAY, ConvertorTestDict.ARRAY);
        compare1.put(ConvertorTestDict.LIST, ConvertorTestDict.LIST);
        compare1.put(ConvertorTestDict.SET, ConvertorTestDict.SET);
        Map<ConvertorTestDict, ConvertorTestDict> target1 = EnumConvertor.convertToMap(ConvertorTestDict.class);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        Map<String, ConvertorTestDict> compare2 = new HashMap<>();
        compare2.put(ConvertorTestDict.ARRAY.key, ConvertorTestDict.ARRAY);
        compare2.put(ConvertorTestDict.LIST.key, ConvertorTestDict.LIST);
        compare2.put(ConvertorTestDict.SET.key, ConvertorTestDict.SET);
        Map<String, ConvertorTestDict> target2 = EnumConvertor.convertToMap(ConvertorTestDict.class, s -> s.key);
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        Map<String, Class<?>> compare3 = new HashMap<>();
        compare3.put(ConvertorTestDict.ARRAY.key, ConvertorTestDict.ARRAY.value);
        compare3.put(ConvertorTestDict.LIST.key, ConvertorTestDict.LIST.value);
        compare3.put(ConvertorTestDict.SET.key, ConvertorTestDict.SET.value);
        Map<String, Class<?>> target3 = EnumConvertor.convertToMap(
                ConvertorTestDict.class,
                s -> s.key,
                s -> s.value
        );
        FlagAssert.isTrue(Objects.equals(target3, compare3));

        Map<String, Class<?>> compare4 = new HashMap<>();
        compare4.put(ConvertorTestDict.LIST.key, ConvertorTestDict.LIST.value);
        compare4.put(ConvertorTestDict.SET.key, ConvertorTestDict.SET.value);
        Map<String, Class<?>> target4 = EnumConvertor.convertToMap(
                ConvertorTestDict.class,
                s -> s.key,
                s -> s.value,
                s -> CollectionConvertor.class.equals(s.value)
        );
        FlagAssert.isTrue(Objects.equals(target4, compare4));
    }

    @Test
    public void convertToOldMapTest() {
        Map<ConvertorTestDict, ConvertorTestDict> compare1 = new HashMap<>();
        compare1.put(ConvertorTestDict.ARRAY, ConvertorTestDict.ARRAY);
        compare1.put(ConvertorTestDict.LIST, ConvertorTestDict.LIST);
        compare1.put(ConvertorTestDict.SET, ConvertorTestDict.SET);
        Map<ConvertorTestDict, ConvertorTestDict> target1 = new HashMap<>();
        EnumConvertor.convertToMap(ConvertorTestDict.class, target1);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        Map<String, ConvertorTestDict> compare2 = new HashMap<>();
        compare2.put(ConvertorTestDict.ARRAY.key, ConvertorTestDict.ARRAY);
        compare2.put(ConvertorTestDict.LIST.key, ConvertorTestDict.LIST);
        compare2.put(ConvertorTestDict.SET.key, ConvertorTestDict.SET);
        Map<String, ConvertorTestDict> target2 = new HashMap<>();
        EnumConvertor.convertToMap(ConvertorTestDict.class, target2, s -> s.key);
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        Map<String, Class<?>> compare3 = new HashMap<>();
        compare3.put(ConvertorTestDict.ARRAY.key, ConvertorTestDict.ARRAY.value);
        compare3.put(ConvertorTestDict.LIST.key, ConvertorTestDict.LIST.value);
        compare3.put(ConvertorTestDict.SET.key, ConvertorTestDict.SET.value);
        Map<String, Class<?>> target3 = new HashMap<>();
        EnumConvertor.convertToMap(
                ConvertorTestDict.class,
                target3,
                s -> s.key,
                s -> s.value
        );
        FlagAssert.isTrue(Objects.equals(target3, compare3));

        Map<String, Class<?>> compare4 = new HashMap<>();
        compare4.put(ConvertorTestDict.LIST.key, ConvertorTestDict.LIST.value);
        compare4.put(ConvertorTestDict.SET.key, ConvertorTestDict.SET.value);
        Map<String, Class<?>> target4 = new HashMap<>();
        EnumConvertor.convertToMap(
                ConvertorTestDict.class,
                target4,
                s -> s.key,
                s -> s.value,
                s -> CollectionConvertor.class.equals(s.value)
        );
        FlagAssert.isTrue(Objects.equals(target4, compare4));
    }
}
