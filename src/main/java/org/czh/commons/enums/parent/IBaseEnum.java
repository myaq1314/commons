package org.czh.commons.enums.parent;

/**
 * @author : czh
 * description : 所有枚举 接口
 * date : 2021-04-28
 * email 916419307@qq.com
 */
public interface IBaseEnum {

    /**
     * 枚举声明
     */
    String name();

    /**
     * 获取枚举声明
     */
    default String getName() {
        return name();
    }

}
