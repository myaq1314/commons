package org.czh.commons.enums.parent;

/**
 * @author : czh
 * description : 只有一个属性的枚举 接口
 * date : 2021-04-28
 * email 916419307@qq.com
 */
public interface IKeyEnum<K> extends IBaseEnum {

    /**
     * 获取 枚举编码
     */
    K getKey();

}
