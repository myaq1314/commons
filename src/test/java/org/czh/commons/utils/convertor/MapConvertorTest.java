package org.czh.commons.utils.convertor;

import com.alibaba.fastjson.JSONObject;
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
 * date : 2021-05-02
 * email 916419307@qq.com
 */
public class MapConvertorTest {

    @Test
    public void convertToFirstTest() {
        Map<String, Class<?>> sourceMap = new HashMap<>();
        sourceMap.put("array", ArrayConvertor.class);
        sourceMap.put("list", CollectionConvertor.class);
        sourceMap.put("set", CollectionConvertor.class);

        String compareKey1 = "array";
        Class<?> compareValue1 = ArrayConvertor.class;
        Map.Entry<String, Class<?>> targetEntry1 = MapConvertor
                .convertToFirst(sourceMap, entry -> "array".equals(entry.getKey()));
        FlagAssert.isTrue(Objects.equals(targetEntry1.getKey(), compareKey1));
        FlagAssert.isTrue(Objects.equals(targetEntry1.getValue(), compareValue1));

        String compareKey2 = "set";
        Class<?> compareValue2 = CollectionConvertor.class;
        Map.Entry<String, Class<?>> targetEntry2 = MapConvertor
                .convertToFirst(sourceMap, entry -> CollectionConvertor.class.equals(entry.getValue()));
        FlagAssert.isTrue(Objects.equals(targetEntry2.getKey(), compareKey2));
        FlagAssert.isTrue(Objects.equals(targetEntry2.getValue(), compareValue2));

        Integer compare3 = 3;
        Integer target3 = MapConvertor.convertToFirst(sourceMap,
                entry -> CollectionConvertor.class.equals(entry.getValue()), entry -> entry.getKey().length());
        FlagAssert.isTrue(Objects.equals(target3, compare3));
    }

    @Test
    public void convertToNewArrayTest() {
        Map<String, Class<?>> sourceMap = new HashMap<>();
        sourceMap.put("array", ArrayConvertor.class);
        sourceMap.put("list", CollectionConvertor.class);
        sourceMap.put("set", CollectionConvertor.class);

        @SuppressWarnings("unchecked")
        Map.Entry<String, Class<?>>[] compareEntry1 = new Map.Entry[sourceMap.size()];
        int index = 0;
        for (Map.Entry<String, Class<?>> entry : sourceMap.entrySet()) {
            compareEntry1[index++] = entry;
        }
        Map.Entry<String, Class<?>>[] targetEntry1 = MapConvertor.convertToArray(sourceMap);
        FlagAssert.isTrue(Arrays.equals(targetEntry1, compareEntry1));

        Integer[] compare2 = new Integer[]{3, 5, 4};
        Integer[] target2 = MapConvertor.convertToArray(sourceMap, Integer.class, entry -> entry.getKey().length());
        FlagAssert.isTrue(Arrays.equals(target2, compare2));

        String[] compare3 = new String[]{"ArrayConvertor"};
        String[] target3 = MapConvertor.convertToArray(sourceMap, String.class, entry -> entry.getValue().getSimpleName(),
                entry -> !entry.getValue().equals(CollectionConvertor.class)
        );
        FlagAssert.isTrue(Arrays.equals(target3, compare3));
    }

    @Test
    public void convertToNewListTest() {
        Map<String, Class<?>> sourceMap = new HashMap<>();
        sourceMap.put("array", ArrayConvertor.class);
        sourceMap.put("list", CollectionConvertor.class);
        sourceMap.put("set", CollectionConvertor.class);

        List<Map.Entry<String, Class<?>>> compareEntry1 = new ArrayList<>(sourceMap.entrySet());
        List<Map.Entry<String, Class<?>>> targetEntry1 = MapConvertor.convertToList(sourceMap);
        FlagAssert.isTrue(Objects.equals(compareEntry1, targetEntry1));

        List<Integer> compare2 = new ArrayList<>();
        Collections.addAll(compare2, 3, 5, 4);
        List<Integer> target2 = MapConvertor.convertToList(sourceMap, entry -> entry.getKey().length());
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        List<String> compare3 = new ArrayList<>();
        Collections.addAll(compare3, "ArrayConvertor");
        List<String> target3 = MapConvertor.convertToList(sourceMap,
                entry -> entry.getValue().getSimpleName(),
                entry -> !entry.getValue().equals(CollectionConvertor.class)
        );
        FlagAssert.isTrue(Objects.equals(target3, compare3));
    }

    @Test
    public void convertToOldListTest() {
        Map<String, Class<?>> sourceMap = new HashMap<>();
        sourceMap.put("array", ArrayConvertor.class);
        sourceMap.put("list", CollectionConvertor.class);
        sourceMap.put("set", CollectionConvertor.class);

        List<Map.Entry<String, Class<?>>> compareEntry1 = new ArrayList<>(sourceMap.entrySet());
        List<Map.Entry<String, Class<?>>> targetEntry1 = new ArrayList<>();
        MapConvertor.convertToCollection(sourceMap, targetEntry1);
        FlagAssert.isTrue(Objects.equals(compareEntry1, targetEntry1));

        List<Integer> compare2 = new ArrayList<>();
        Collections.addAll(compare2, 3, 5, 4);
        List<Integer> target2 = new ArrayList<>();
        MapConvertor.convertToCollection(sourceMap, target2, entry -> entry.getKey().length());
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        List<String> compare3 = new ArrayList<>();
        Collections.addAll(compare3, "ArrayConvertor");
        List<String> target3 = new ArrayList<>();
        MapConvertor.convertToCollection(sourceMap, target3,
                entry -> entry.getValue().getSimpleName(),
                entry -> !entry.getValue().equals(CollectionConvertor.class)
        );
        FlagAssert.isTrue(Objects.equals(target3, compare3));
    }

    @Test
    public void convertToNewSetTest() {
        Map<String, Class<?>> sourceMap = new HashMap<>();
        sourceMap.put("array", ArrayConvertor.class);
        sourceMap.put("list", CollectionConvertor.class);
        sourceMap.put("set", CollectionConvertor.class);

        Set<Map.Entry<String, Class<?>>> compareEntry1 = sourceMap.entrySet();
        Set<Map.Entry<String, Class<?>>> targetEntry1 = MapConvertor.convertToSet(sourceMap);
        FlagAssert.isTrue(Objects.equals(compareEntry1, targetEntry1));

        Set<Integer> compare2 = new HashSet<>();
        Collections.addAll(compare2, 5, 4, 3);
        Set<Integer> target2 = MapConvertor.convertToSet(sourceMap, entry -> entry.getKey().length());
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        Set<String> compare3 = new HashSet<>();
        Collections.addAll(compare3, "ArrayConvertor");
        Set<String> target3 = MapConvertor.convertToSet(sourceMap,
                entry -> entry.getValue().getSimpleName(),
                entry -> !entry.getValue().equals(CollectionConvertor.class)
        );
        FlagAssert.isTrue(Objects.equals(target3, compare3));
    }

    @Test
    public void convertToOldSetTest() {
        Map<String, Class<?>> sourceMap = new HashMap<>();
        sourceMap.put("array", ArrayConvertor.class);
        sourceMap.put("list", CollectionConvertor.class);
        sourceMap.put("set", CollectionConvertor.class);

        Set<Map.Entry<String, Class<?>>> compareEntry1 = sourceMap.entrySet();
        Set<Map.Entry<String, Class<?>>> targetEntry1 = new HashSet<>();
        MapConvertor.convertToCollection(sourceMap, targetEntry1);
        FlagAssert.isTrue(Objects.equals(compareEntry1, targetEntry1));

        Set<Integer> compare2 = new HashSet<>();
        Collections.addAll(compare2, 5, 4, 3);
        Set<Integer> target2 = new HashSet<>();
        MapConvertor.convertToCollection(sourceMap, target2, entry -> entry.getKey().length());
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        Set<String> compare3 = new HashSet<>();
        Collections.addAll(compare3, "ArrayConvertor");
        Set<String> target3 = new HashSet<>();
        MapConvertor.convertToCollection(sourceMap, target3,
                entry -> entry.getValue().getSimpleName(),
                entry -> !entry.getValue().equals(CollectionConvertor.class)
        );
        FlagAssert.isTrue(Objects.equals(target3, compare3));
    }

    @Test
    public void convertToNewMapTest() {
        Map<String, Class<?>> sourceMap = new HashMap<>();
        sourceMap.put("array", ArrayConvertor.class);
        sourceMap.put("list", CollectionConvertor.class);
        sourceMap.put("set", CollectionConvertor.class);

        Map<String, Class<?>> compare1 = new HashMap<>();
        compare1.put("array", ArrayConvertor.class);
        compare1.put("list", CollectionConvertor.class);
        compare1.put("set", CollectionConvertor.class);
        Map<String, Class<?>> target1 = MapConvertor.convertToMap(sourceMap);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        Map<String, Integer> compare2 = new HashMap<>();
        compare2.put("a", 5);
        compare2.put("l", 4);
        compare2.put("s", 3);
        Map<String, Integer> target2 = MapConvertor.convertToMap(sourceMap,
                entry -> entry.getKey().substring(0, 1), entry -> entry.getKey().length());
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        Map<String, Integer> compare3 = new HashMap<>();
        compare3.put("l", 4);
        Map<String, Integer> target3 = MapConvertor.convertToMap(sourceMap,
                entry -> entry.getKey().substring(0, 1), entry -> entry.getKey().length(),
                entry -> entry.getKey().length() % 2 == 0
        );
        FlagAssert.isTrue(Objects.equals(target3, compare3));
    }

    @Test
    public void convertToOldMapTest() {
        Map<String, Class<?>> sourceMap = new HashMap<>();
        sourceMap.put("array", ArrayConvertor.class);
        sourceMap.put("list", CollectionConvertor.class);
        sourceMap.put("set", CollectionConvertor.class);

        Map<String, Class<?>> compare1 = new HashMap<>();
        compare1.put("array", ArrayConvertor.class);
        compare1.put("list", CollectionConvertor.class);
        compare1.put("set", CollectionConvertor.class);
        Map<String, Class<?>> target1 = new HashMap<>();
        MapConvertor.convertToMap(sourceMap, target1);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        Map<String, Integer> compare2 = new HashMap<>();
        compare2.put("a", 5);
        compare2.put("l", 4);
        compare2.put("s", 3);
        Map<String, Integer> target2 = new HashMap<>();
        MapConvertor.convertToMap(sourceMap, target2,
                entry -> entry.getKey().substring(0, 1), entry -> entry.getKey().length());
        FlagAssert.isTrue(Objects.equals(target2, compare2));

        Map<String, Integer> compare3 = new HashMap<>();
        compare3.put("l", 4);
        Map<String, Integer> target3 = new HashMap<>();
        MapConvertor.convertToMap(sourceMap, target3,
                entry -> entry.getKey().substring(0, 1), entry -> entry.getKey().length(),
                entry -> entry.getKey().length() % 2 == 0
        );
        FlagAssert.isTrue(Objects.equals(target3, compare3));
    }

    @Test
    public void convertToNewJsonTest() {
        Map<String, Class<?>> sourceMap = new HashMap<>();
        sourceMap.put("array", ArrayConvertor.class);
        sourceMap.put("list", CollectionConvertor.class);
        sourceMap.put("set", CollectionConvertor.class);

        JSONObject compare1 = new JSONObject(new HashMap<>());
        compare1.put("array", ArrayConvertor.class.getName());
        compare1.put("list", CollectionConvertor.class.getName());
        compare1.put("set", CollectionConvertor.class.getName());
        JSONObject target1 = JSONConvertor.convertToJsonObject(sourceMap);
        FlagAssert.isTrue(Objects.equals(target1, compare1));

        String compare2 = "{\"set\":\"" + CollectionConvertor.class.getName()
                + "\",\"array\":\"" + ArrayConvertor.class.getName()
                + "\",\"list\":\"" + CollectionConvertor.class.getName()
                + "\"}";
        String target2 = JSONConvertor.convertToJsonString(sourceMap);
        FlagAssert.isTrue(Objects.equals(target2, compare2));
    }
}
