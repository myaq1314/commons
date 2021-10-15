package org.czh.commons.service.impl;

import org.czh.commons.service.IBaseEOService;
import org.czh.commons_core.parent.dao.IBaseDao;
import org.czh.commons_core.parent.entity.eo.IBaseEO;
import org.czh.commons_core.validate.EmptyValidate;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author : czh
 * description :
 * date : 2021-06-21
 * email 916419307@qq.com
 */
public abstract class BaseEOServiceImpl<Dao extends IBaseDao<Entity>, Entity extends IBaseEO>
        implements IBaseEOService<Entity> {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    protected Dao baseDao;

    protected Class<Entity> clazz;

    Entity newInstance() {
        Entity entity;
        try {
            entity = getEntityClass().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("instantiation class :" + clazz + " fail");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("illegal access class :" + clazz);
        }
        return entity;
    }

    Class<Entity> getEntityClass() {
        if (EmptyValidate.isNull(this.clazz)) {
            // 获取泛型
            Type superclass = this.getClass().getGenericSuperclass();
            // 获取泛型内类型
            Type[] actualTypeArguments = ((ParameterizedType) superclass).getActualTypeArguments();
            try {
                // 第二个是实体泛型，通过名称，获取实体类对象
                //noinspection unchecked
                this.clazz = (Class<Entity>) Class.forName(actualTypeArguments[1].getTypeName());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(actualTypeArguments[1].getTypeName() + " class not found");
            }
        }
        return this.clazz;
    }
}
