package org.czh.commons.enums.parent;

/**
 * @author : czh
 * description : 键值对 枚举 接口
 * date : 2021-04-28
 * email 916419307@qq.com
 */
public interface IDictEnum<K, V> extends IKeyEnum<K> {

    /**
     * 获取 枚举属性
     */
    V getValue();

}
