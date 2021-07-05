package org.czh.commons.utils.convertor;

import com.alibaba.fastjson.JSONArray;
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
public class CollectionConvertorTest {

    @Test
    public void convertToFirstFromListTest() {
        List<String> sourceList = new ArrayList<>();
        Collections.addAll(sourceList, "array", "list", "set");

        String compare1 = "array";
        String target1 = CollectionConvertor.convertToFirst(sourceList, "array"::equals);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        String compare2 = "array";
        String target2 = CollectionConvertor.convertToFirst(sourceList, null);
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        Integer compare3 = 5;
        Integer target3 = CollectionConvertor.convertToFirst(sourceList, "array"::equals, String::length);
        FlagAssert.isTrue(Objects.equals(target3, compare3));
    }

    @Test
    public void convertToFirstFromSetTest() {
        Set<String> sourceSet = new HashSet<>();
        Collections.addAll(sourceSet, "array", "list", "set");

        String compare1 = "array";
        String target1 = CollectionConvertor.convertToFirst(sourceSet, "array"::equals);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        String compare2 = "set";
        String target2 = CollectionConvertor.convertToFirst(sourceSet, null);
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        Integer compare3 = 5;
        Integer target3 = CollectionConvertor.convertToFirst(sourceSet, "array"::equals, String::length);
        FlagAssert.isTrue(Objects.equals(target3, compare3));
    }

    @Test
    public void convertToNewArrayFromListTest() {
        List<String> sourceList = new ArrayList<>();
        Collections.addAll(sourceList, "array", "list", "set");

        String[] compare1 = new String[]{"array", "list", "set"};
        String[] target1 = CollectionConvertor.convertToArray(sourceList, String.class);
        FlagAssert.isTrue(Arrays.equals(target1, compare1));

        Integer[] compare2 = new Integer[]{5, 4, 3};
        Integer[] target2 = CollectionConvertor.convertToArray(sourceList, Integer.class, String::length);
        FlagAssert.isTrue(Arrays.equals(target2, compare2));

        Integer[] compare3 = new Integer[]{5, 3};
        Integer[] target3 = CollectionConvertor.convertToArray(sourceList, Integer.class,
                String::length,
                s -> s.length() % 2 != 0
        );
        FlagAssert.isTrue(Arrays.equals(target3, compare3));
    }

    @Test
    public void convertToNewArrayFromSetTest() {
        Set<String> sourceSet = new HashSet<>();
        Collections.addAll(sourceSet, "array", "list", "set");

        String[] compare1 = new String[]{"set", "array", "list"};
        String[] target1 = CollectionConvertor.convertToArray(sourceSet, String.class);
        FlagAssert.isTrue(Arrays.equals(target1, compare1));

        Integer[] compare2 = new Integer[]{3, 5, 4};
        Integer[] target2 = CollectionConvertor.convertToArray(sourceSet, Integer.class, String::length);
        FlagAssert.isTrue(Arrays.equals(target2, compare2));

        Integer[] compare3 = new Integer[]{3, 5};
        Integer[] target3 = CollectionConvertor.convertToArray(sourceSet, Integer.class,
                String::length,
                s -> s.length() % 2 != 0
        );
        FlagAssert.isTrue(Arrays.equals(target3, compare3));
    }

    @Test
    public void convertToNewListFromListTest() {
        List<String> sourceList = new ArrayList<>();
        Collections.addAll(sourceList, "array", "list", "set");

        List<String> compare1 = new ArrayList<>();
        Collections.addAll(compare1, "array", "list", "set");
        List<String> target1 = CollectionConvertor.convertToList(sourceList);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        List<Integer> compare2 = new ArrayList<>();
        Collections.addAll(compare2, 5, 4, 3);
        List<Integer> target2 = CollectionConvertor.convertToList(sourceList, String::length);
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        List<Integer> compare3 = new ArrayList<>();
        Collections.addAll(compare3, 5, 3);
        List<Integer> target3 = CollectionConvertor.convertToList(sourceList, String::length, s -> s.length() % 2 != 0);
        FlagAssert.isTrue(Objects.equals(target3, compare3));
    }

    @Test
    public void convertToOldListFromListTest() {
        List<String> sourceList = new ArrayList<>();
        Collections.addAll(sourceList, "array", "list", "set");

        List<String> compare1 = new ArrayList<>();
        Collections.addAll(compare1, "array", "list", "set");
        List<String> target1 = new ArrayList<>();
        CollectionConvertor.convertToCollection(sourceList, target1);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        List<Integer> compare2 = new ArrayList<>();
        Collections.addAll(compare2, 5, 4, 3);
        List<Integer> target2 = new ArrayList<>();
        CollectionConvertor.convertToCollection(sourceList, target2, String::length);
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        List<Integer> compare3 = new ArrayList<>();
        Collections.addAll(compare3, 5, 3);
        List<Integer> target3 = new ArrayList<>();
        CollectionConvertor.convertToCollection(sourceList, target3, String::length, s -> s.length() % 2 != 0);
        FlagAssert.isTrue(Objects.equals(target3, compare3));
    }

    @Test
    public void convertToNewListFromSetTest() {
        Set<String> sourceSet = new HashSet<>();
        Collections.addAll(sourceSet, "array", "list", "set");

        List<String> compare1 = new ArrayList<>();
        Collections.addAll(compare1, "set", "array", "list");
        List<String> target1 = CollectionConvertor.convertToList(sourceSet);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        List<Integer> compare2 = new ArrayList<>();
        Collections.addAll(compare2, 3, 5, 4);
        List<Integer> target2 = CollectionConvertor.convertToList(sourceSet, String::length);
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        List<Integer> compare3 = new ArrayList<>();
        Collections.addAll(compare3, 3, 5);
        List<Integer> target3 = CollectionConvertor.convertToList(sourceSet, String::length, s -> s.length() % 2 != 0);
        FlagAssert.isTrue(Objects.equals(target3, compare3));
    }

    @Test
    public void convertToOldListFromSetTest() {
        Set<String> sourceSet = new HashSet<>();
        Collections.addAll(sourceSet, "array", "list", "set");

        List<String> compare1 = new ArrayList<>();
        Collections.addAll(compare1, "set", "array", "list");
        List<String> target1 = new ArrayList<>();
        CollectionConvertor.convertToCollection(sourceSet, target1);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        List<Integer> compare2 = new ArrayList<>();
        Collections.addAll(compare2, 3, 5, 4);
        List<Integer> target2 = new ArrayList<>();
        CollectionConvertor.convertToCollection(sourceSet, target2, String::length);
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        List<Integer> compare3 = new ArrayList<>();
        Collections.addAll(compare3, 3, 5);
        List<Integer> target3 = new ArrayList<>();
        CollectionConvertor.convertToCollection(sourceSet, target3, String::length, s -> s.length() % 2 != 0);
        FlagAssert.isTrue(Objects.equals(target3, compare3));
    }

    @Test
    public void convertToNewSetFromListTest() {
        List<String> sourceList = new ArrayList<>();
        Collections.addAll(sourceList, "array", "list", "set");

        Set<String> compare1 = new HashSet<>();
        Collections.addAll(compare1, "array", "list", "set");
        Set<String> target1 = CollectionConvertor.convertToSet(sourceList);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        Set<Integer> compare2 = new HashSet<>();
        Collections.addAll(compare2, 5, 4, 3);
        Set<Integer> target2 = CollectionConvertor.convertToSet(sourceList, String::length);
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        Set<Integer> compare3 = new HashSet<>();
        Collections.addAll(compare3, 5, 3);
        Set<Integer> target3 = CollectionConvertor.convertToSet(sourceList, String::length, s -> s.length() % 2 != 0);
        FlagAssert.isTrue(Objects.equals(target3, compare3));
    }

    @Test
    public void convertToOldSetFromListTest() {
        List<String> sourceList = new ArrayList<>();
        Collections.addAll(sourceList, "array", "list", "set");

        Set<String> compare1 = new HashSet<>();
        Collections.addAll(compare1, "array", "list", "set");
        Set<String> target1 = new HashSet<>();
        CollectionConvertor.convertToCollection(sourceList, target1);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        Set<Integer> compare2 = new HashSet<>();
        Collections.addAll(compare2, 5, 4, 3);
        Set<Integer> target2 = new HashSet<>();
        CollectionConvertor.convertToCollection(sourceList, target2, String::length);
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        Set<Integer> compare3 = new HashSet<>();
        Collections.addAll(compare3, 5, 3);
        Set<Integer> target3 = new HashSet<>();
        CollectionConvertor.convertToCollection(sourceList, target3, String::length, s -> s.length() % 2 != 0);
        FlagAssert.isTrue(Objects.equals(target3, compare3));
    }

    @Test
    public void convertToNewSetFromSetTest() {
        Set<String> sourceSet = new HashSet<>();
        Collections.addAll(sourceSet, "array", "list", "set");

        Set<String> compare1 = new HashSet<>();
        Collections.addAll(compare1, "array", "list", "set");
        Set<String> target1 = CollectionConvertor.convertToSet(sourceSet);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        Set<Integer> compare2 = new HashSet<>();
        Collections.addAll(compare2, 5, 4, 3);
        Set<Integer> target2 = CollectionConvertor.convertToSet(sourceSet, String::length);
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        Set<Integer> compare3 = new HashSet<>();
        Collections.addAll(compare3, 5, 3);
        Set<Integer> target3 = CollectionConvertor.convertToSet(sourceSet, String::length, s -> s.length() % 2 != 0);
        FlagAssert.isTrue(Objects.equals(target3, compare3));
    }

    @Test
    public void convertToOldSetFromSetTest() {
        Set<String> sourceSet = new HashSet<>();
        Collections.addAll(sourceSet, "array", "list", "set");

        Set<String> compare1 = new HashSet<>();
        Collections.addAll(compare1, "array", "list", "set");
        Set<String> target1 = new HashSet<>();
        CollectionConvertor.convertToCollection(sourceSet, target1);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        Set<Integer> compare2 = new HashSet<>();
        Collections.addAll(compare2, 5, 4, 3);
        Set<Integer> target2 = new HashSet<>();
        CollectionConvertor.convertToCollection(sourceSet, target2, String::length);
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        Set<Integer> compare3 = new HashSet<>();
        Collections.addAll(compare3, 5, 3);
        Set<Integer> target3 = new HashSet<>();
        CollectionConvertor.convertToCollection(sourceSet, target3, String::length, s -> s.length() % 2 != 0);
        FlagAssert.isTrue(Objects.equals(target3, compare3));
    }

    @Test
    public void convertToNewMapFromListTest() {
        List<String> sourceList = new ArrayList<>();
        Collections.addAll(sourceList, "array", "list", "set");

        Map<String, String> compare1 = new HashMap<>();
        compare1.put("array", "array");
        compare1.put("list", "list");
        compare1.put("set", "set");
        Map<String, String> target1 = CollectionConvertor.convertToMap(sourceList);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        Map<Integer, String> compare2 = new HashMap<>();
        compare2.put(5, "array");
        compare2.put(4, "list");
        compare2.put(3, "set");
        Map<Integer, String> target2 = CollectionConvertor.convertToMap(sourceList, String::length);
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        Map<String, Integer> compare3 = new HashMap<>();
        compare3.put("a", 5);
        compare3.put("l", 4);
        compare3.put("s", 3);
        Map<String, Integer> target3 = CollectionConvertor.convertToMap(
                sourceList,
                s -> s.substring(0, 1),
                String::length
        );
        FlagAssert.isTrue(Objects.equals(target3, compare3));

        Map<String, Integer> compare4 = new HashMap<>();
        compare4.put("a", 5);
        compare4.put("s", 3);
        Map<String, Integer> target4 = CollectionConvertor.convertToMap(
                sourceList,
                s -> s.substring(0, 1),
                String::length,
                s -> s.length() % 2 != 0
        );
        FlagAssert.isTrue(Objects.equals(target4, compare4));
    }

    @Test
    public void convertToOldMapFromListTest() {
        List<String> sourceList = new ArrayList<>();
        Collections.addAll(sourceList, "array", "list", "set");

        Map<String, String> compare1 = new HashMap<>();
        compare1.put("array", "array");
        compare1.put("list", "list");
        compare1.put("set", "set");
        Map<String, String> target1 = new HashMap<>();
        CollectionConvertor.convertToMap(sourceList, target1);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        Map<Integer, String> compare2 = new HashMap<>();
        compare2.put(5, "array");
        compare2.put(4, "list");
        compare2.put(3, "set");
        Map<Integer, String> target2 = new HashMap<>();
        CollectionConvertor.convertToMap(sourceList, target2, String::length);
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        Map<String, Integer> compare3 = new HashMap<>();
        compare3.put("a", 5);
        compare3.put("l", 4);
        compare3.put("s", 3);
        Map<String, Integer> target3 = new HashMap<>();
        CollectionConvertor.convertToMap(
                sourceList,
                target3,
                s -> s.substring(0, 1),
                String::length
        );
        FlagAssert.isTrue(Objects.equals(target3, compare3));

        Map<String, Integer> compare4 = new HashMap<>();
        compare4.put("a", 5);
        compare4.put("s", 3);
        Map<String, Integer> target4 = new HashMap<>();
        CollectionConvertor.convertToMap(
                sourceList,
                target4,
                s -> s.substring(0, 1),
                String::length,
                s -> s.length() % 2 != 0
        );
        FlagAssert.isTrue(Objects.equals(target4, compare4));
    }

    @Test
    public void convertToNewMapFromSetTest() {
        Set<String> sourceSet = new HashSet<>();
        Collections.addAll(sourceSet, "array", "list", "set");

        Map<String, String> compare1 = new HashMap<>();
        compare1.put("array", "array");
        compare1.put("list", "list");
        compare1.put("set", "set");
        Map<String, String> target1 = CollectionConvertor.convertToMap(sourceSet);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        Map<Integer, String> compare2 = new HashMap<>();
        compare2.put(5, "array");
        compare2.put(4, "list");
        compare2.put(3, "set");
        Map<Integer, String> target2 = CollectionConvertor.convertToMap(sourceSet, String::length);
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        Map<String, Integer> compare3 = new HashMap<>();
        compare3.put("a", 5);
        compare3.put("l", 4);
        compare3.put("s", 3);
        Map<String, Integer> target3 = CollectionConvertor.convertToMap(
                sourceSet,
                s -> s.substring(0, 1),
                String::length
        );
        FlagAssert.isTrue(Objects.equals(target3, compare3));

        Map<String, Integer> compare4 = new HashMap<>();
        compare4.put("a", 5);
        compare4.put("s", 3);
        Map<String, Integer> target4 = CollectionConvertor.convertToMap(
                sourceSet,
                s -> s.substring(0, 1),
                String::length,
                s -> s.length() % 2 != 0
        );
        FlagAssert.isTrue(Objects.equals(target4, compare4));
    }

    @Test
    public void convertToOldMapFromSetTest() {
        Set<String> sourceSet = new HashSet<>();
        Collections.addAll(sourceSet, "array", "list", "set");

        Map<String, String> compare1 = new HashMap<>();
        compare1.put("array", "array");
        compare1.put("list", "list");
        compare1.put("set", "set");
        Map<String, String> target1 = new HashMap<>();
        CollectionConvertor.convertToMap(sourceSet, target1);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        Map<Integer, String> compare2 = new HashMap<>();
        compare2.put(5, "array");
        compare2.put(4, "list");
        compare2.put(3, "set");
        Map<Integer, String> target2 = new HashMap<>();
        CollectionConvertor.convertToMap(sourceSet, target2, String::length);
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        Map<String, Integer> compare3 = new HashMap<>();
        compare3.put("a", 5);
        compare3.put("l", 4);
        compare3.put("s", 3);
        Map<String, Integer> target3 = new HashMap<>();
        CollectionConvertor.convertToMap(
                sourceSet,
                target3,
                s -> s.substring(0, 1),
                String::length
        );
        FlagAssert.isTrue(Objects.equals(target3, compare3));

        Map<String, Integer> compare4 = new HashMap<>();
        compare4.put("a", 5);
        compare4.put("s", 3);
        Map<String, Integer> target4 = new HashMap<>();
        CollectionConvertor.convertToMap(
                sourceSet,
                target4,
                s -> s.substring(0, 1),
                String::length,
                s -> s.length() % 2 != 0
        );
        FlagAssert.isTrue(Objects.equals(target4, compare4));
    }

    @Test
    public void convertToNewJsonFromListTest() {
        List<String> sourceList = new ArrayList<>();
        Collections.addAll(sourceList, "array", "list", "set");

        JSONArray compare1 = new JSONArray();
        Collections.addAll(compare1, "array", "list", "set");
        JSONArray target1 = JSONConvertor.convertToJsonObject(sourceList);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        String compare2 = "[\"array\",\"list\",\"set\"]";
        String target2 = JSONConvertor.convertToJsonString(sourceList);
        FlagAssert.isTrue(Objects.equals(target2, compare2));
    }

    @Test
    public void convertToNewJsonFromSetTest() {
        Set<String> sourceSet = new HashSet<>();
        Collections.addAll(sourceSet, "array", "list", "set");

        JSONArray compare1 = new JSONArray();
        Collections.addAll(compare1, "set", "array", "list");
        JSONArray target1 = JSONConvertor.convertToJsonObject(sourceSet);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        String compare2 = "[\"set\",\"array\",\"list\"]";
        String target2 = JSONConvertor.convertToJsonString(sourceSet);
        FlagAssert.isTrue(Objects.equals(target2, compare2));
    }

}
