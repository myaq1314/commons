package org.czh.commons.utils.convertor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.czh.commons.validate.EmptyAssert;

import java.util.Collection;
import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021-06-17
 * email 916419307@qq.com
 */
public final class JSONConvertor {
    
    /*
      -----------------------------array convert to new json-------------------------------
     */

    /**
     * 数组 转换为 JSONArray
     *
     * @param source 源数组
     * @param <S>    源数组元素类型
     * @return JSONArray
     */
    public static <S> JSONArray convertToJsonObject(final S[] source) {
        EmptyAssert.isNotEmpty(source);
        return (JSONArray) JSONArray.toJSON(source);
    }

    /**
     * 数组 转换为 JSONString
     *
     * @param source 源数组
     * @param <S>    源数组元素类型
     * @return JSONString
     */
    public static <S> String convertToJsonString(final S[] source) {
        EmptyAssert.isNotEmpty(source);
        return JSONArray.toJSONString(source);
    }
    
    /*
      -----------------------------collection convert to new json-------------------------------
     */

    /**
     * 集合 转换为 JSONArray
     *
     * @param source 源集合
     * @param <S>    源集合元素类型
     * @return JSONArray
     */
    public static <S> JSONArray convertToJsonObject(final Collection<S> source) {
        EmptyAssert.isNotEmpty(source);
        return (JSONArray) JSONArray.toJSON(source);
    }

    /**
     * 集合 转换为 JSONString
     *
     * @param source 源集合
     * @param <S>    源集合元素类型
     * @return JSONString
     */
    public static <S> String convertToJsonString(final Collection<S> source) {
        EmptyAssert.isNotEmpty(source);
        return JSONArray.toJSONString(source);
    }

    /*
      -----------------------------bean convert to new json-------------------------------
     */
    public static <S> JSONObject convertToJsonObject(final S source) {
        EmptyAssert.isNotNull(source);

        return (JSONObject) JSONObject.toJSON(source);
    }

    public static <S> String convertToJsonString(final S source) {
        EmptyAssert.isNotNull(source);

        return JSONObject.toJSONString(source, SerializerFeature.DisableCircularReferenceDetect);
    }
    
    /*
      -----------------------------map convert to new json-------------------------------
     */

    /**
     * 键值对 转换为 JSONArray
     *
     * @param source 源键值对
     * @param <K>    键值对 Key 类型
     * @param <V>    键值对 Value 类型
     * @return JSONArray
     */
    public static <K, V> JSONObject convertToJsonObject(final Map<K, V> source) {
        EmptyAssert.isNotEmpty(source);
        return (JSONObject) JSONArray.toJSON(source);
    }

    /**
     * 键值对 转换为 JSONString
     *
     * @param source 源键值对
     * @param <K>    键值对 Key 类型
     * @param <V>    键值对 Value 类型
     * @return JSONString
     */
    public static <K, V> String convertToJsonString(final Map<K, V> source) {
        EmptyAssert.isNotEmpty(source);
        return JSONArray.toJSONString(source);
    }
}
