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
public class ArrayConvertorTest {

    @Test
    public void convertToFirstTest() {
        String[] sourceArray = new String[]{"array", "list", "set"};

        String compare1 = "array";
        String target1 = ArrayConvertor.convertToFirst(sourceArray, "array"::equals);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        String compare2 = "array";
        String target2 = ArrayConvertor.convertToFirst(sourceArray, null);
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        Integer compare3 = 5;
        Integer target3 = ArrayConvertor.convertToFirst(sourceArray, "array"::equals, String::length);
        FlagAssert.isTrue(Objects.equals(target3, compare3));
    }

    @Test
    public void convertToNewArrayTest() {
        String[] sourceArray = new String[]{"array", "list", "set"};

        String[] compare1 = new String[]{"array", "list", "set"};
        String[] target1 = ArrayConvertor.convertToArray(sourceArray, String.class);
        FlagAssert.isTrue(Arrays.equals(target1, compare1));

        Integer[] compare2 = new Integer[]{5, 4, 3};
        Integer[] target2 = ArrayConvertor.convertToArray(sourceArray, Integer.class, String::length);
        FlagAssert.isTrue(Arrays.equals(target2, compare2));

        Integer[] compare3 = new Integer[]{5, 3};
        Integer[] target3 = ArrayConvertor.convertToArray(sourceArray, Integer.class,
                String::length,
                s -> s.length() % 2 != 0
        );
        FlagAssert.isTrue(Arrays.equals(target3, compare3));
    }

    @Test
    public void convertToNewListTest() {
        String[] sourceArray = new String[]{"array", "list", "set"};

        List<String> compare1 = new ArrayList<>();
        Collections.addAll(compare1, "array", "list", "set");
        List<String> target1 = ArrayConvertor.convertToList(sourceArray);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        List<Integer> compare2 = new ArrayList<>();
        Collections.addAll(compare2, 5, 4, 3);
        List<Integer> target2 = ArrayConvertor.convertToList(sourceArray, String::length);
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        List<Integer> compare3 = new ArrayList<>();
        Collections.addAll(compare3, 5, 3);
        List<Integer> target3 = ArrayConvertor.convertToList(sourceArray, String::length, s -> s.length() % 2 != 0);
        FlagAssert.isTrue(Objects.equals(target3, compare3));
    }

    @Test
    public void convertToOldListTest() {
        String[] sourceArray = new String[]{"array", "list", "set"};

        List<String> compare1 = new ArrayList<>();
        Collections.addAll(compare1, "array", "list", "set");
        List<String> target1 = new ArrayList<>();
        ArrayConvertor.convertToCollection(sourceArray, target1);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        List<Integer> compare2 = new ArrayList<>();
        Collections.addAll(compare2, 5, 4, 3);
        List<Integer> target2 = new ArrayList<>();
        ArrayConvertor.convertToCollection(sourceArray, target2, String::length);
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        List<Integer> compare3 = new ArrayList<>();
        Collections.addAll(compare3, 5, 3);
        List<Integer> target3 = new ArrayList<>();
        ArrayConvertor.convertToCollection(sourceArray, target3, String::length, s -> s.length() % 2 != 0);
        FlagAssert.isTrue(Objects.equals(target3, compare3));
    }

    @Test
    public void convertToNewSetTest() {
        String[] sourceArray = new String[]{"array", "list", "set"};

        Set<String> compare1 = new HashSet<>();
        Collections.addAll(compare1, "array", "list", "set");
        Set<String> target1 = ArrayConvertor.convertToSet(sourceArray);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        Set<Integer> compare2 = new HashSet<>();
        Collections.addAll(compare2, 5, 4, 3);
        Set<Integer> target2 = ArrayConvertor.convertToSet(sourceArray, String::length);
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        Set<Integer> compare3 = new HashSet<>();
        Collections.addAll(compare3, 5, 3);
        Set<Integer> target3 = ArrayConvertor.convertToSet(sourceArray, String::length, s -> s.length() % 2 != 0);
        FlagAssert.isTrue(Objects.equals(target3, compare3));
    }

    @Test
    public void convertToOldSetTest() {
        String[] sourceArray = new String[]{"array", "list", "set"};

        Set<String> compare1 = new HashSet<>();
        Collections.addAll(compare1, "array", "list", "set");
        Set<String> target1 = new HashSet<>();
        ArrayConvertor.convertToCollection(sourceArray, target1);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        Set<Integer> compare2 = new HashSet<>();
        Collections.addAll(compare2, 5, 4, 3);
        Set<Integer> target2 = new HashSet<>();
        ArrayConvertor.convertToCollection(sourceArray, target2, String::length);
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        Set<Integer> compare3 = new HashSet<>();
        Collections.addAll(compare3, 5, 3);
        Set<Integer> target3 = new HashSet<>();
        ArrayConvertor.convertToCollection(sourceArray, target3, String::length, s -> s.length() % 2 != 0);
        FlagAssert.isTrue(Objects.equals(target3, compare3));
    }

    @Test
    public void convertToNewMapTest() {
        String[] sourceArray = new String[]{"array", "list", "set"};

        Map<String, String> compare1 = new HashMap<>();
        compare1.put("array", "array");
        compare1.put("list", "list");
        compare1.put("set", "set");
        Map<String, String> target1 = ArrayConvertor.convertToMap(sourceArray);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        Map<Integer, String> compare2 = new HashMap<>();
        compare2.put(5, "array");
        compare2.put(4, "list");
        compare2.put(3, "set");
        Map<Integer, String> target2 = ArrayConvertor.convertToMap(sourceArray, String::length);
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        Map<String, Integer> compare3 = new HashMap<>();
        compare3.put("a", 5);
        compare3.put("l", 4);
        compare3.put("s", 3);
        Map<String, Integer> target3 = ArrayConvertor.convertToMap(
                sourceArray,
                s -> s.substring(0, 1),
                String::length
        );
        FlagAssert.isTrue(Objects.equals(target3, compare3));

        Map<String, Integer> compare4 = new HashMap<>();
        compare4.put("a", 5);
        compare4.put("s", 3);
        Map<String, Integer> target4 = ArrayConvertor.convertToMap(
                sourceArray,
                s -> s.substring(0, 1),
                String::length,
                s -> s.length() % 2 != 0
        );
        FlagAssert.isTrue(Objects.equals(target4, compare4));
    }

    @Test
    public void convertToOldMapTest() {
        String[] sourceArray = new String[]{"array", "list", "set"};

        Map<String, String> compare1 = new HashMap<>();
        compare1.put("array", "array");
        compare1.put("list", "list");
        compare1.put("set", "set");
        Map<String, String> target1 = new HashMap<>();
        ArrayConvertor.convertToMap(sourceArray, target1);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        Map<Integer, String> compare2 = new HashMap<>();
        compare2.put(5, "array");
        compare2.put(4, "list");
        compare2.put(3, "set");
        Map<Integer, String> target2 = new HashMap<>();
        ArrayConvertor.convertToMap(sourceArray, target2, String::length);
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        Map<String, Integer> compare3 = new HashMap<>();
        compare3.put("a", 5);
        compare3.put("l", 4);
        compare3.put("s", 3);
        Map<String, Integer> target3 = new HashMap<>();
        ArrayConvertor.convertToMap(
                sourceArray,
                target3,
                s -> s.substring(0, 1),
                String::length
        );
        FlagAssert.isTrue(Objects.equals(target3, compare3));

        Map<String, Integer> compare4 = new HashMap<>();
        compare4.put("a", 5);
        compare4.put("s", 3);
        Map<String, Integer> target4 = new HashMap<>();
        ArrayConvertor.convertToMap(
                sourceArray,
                target4,
                s -> s.substring(0, 1),
                String::length,
                s -> s.length() % 2 != 0
        );
        FlagAssert.isTrue(Objects.equals(target4, compare4));
    }

    @Test
    public void convertToNewJsonTest() {
        String[] sourceArray = new String[]{"array", "list", "set"};

        JSONArray compare1 = new JSONArray();
        Collections.addAll(compare1, "array", "list", "set");
        JSONArray target1 = JSONConvertor.convertToJsonObject(sourceArray);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        String compare2 = "[\"array\",\"list\",\"set\"]";
        String target2 = JSONConvertor.convertToJsonString(sourceArray);
        FlagAssert.isTrue(Objects.equals(target2, compare2));
    }
}
