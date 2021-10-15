package org.czh.commons.enums.parent;

import org.czh.commons_core.parent.enums.IBaseEnum;

/**
 * @author : czh
 * description : 字段枚举 接口
 * date : 2021-04-28
 * email 916419307@qq.com
 */
public interface IColumnEnum extends IBaseEnum {

    /**
     * 获取 字段名称
     */
    String getColumn();

    /**
     * 获取 属性名称
     */
    String getField();

    /**
     * 获取 属性类型
     */
    Class<?> getType();

}
