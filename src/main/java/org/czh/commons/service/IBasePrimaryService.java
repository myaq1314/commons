package org.czh.commons.service;

import org.czh.commons.entity.eo.BasePrimaryEO;

import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021-06-28
 * email 916419307@qq.com
 */
public interface IBasePrimaryService<Entity extends BasePrimaryEO> extends IBaseCommonService<Entity> {

    Entity getById(final Object id);

    Entity getById(final String tableName, final Object id);

    int updateById(final Entity entity);

    int batchUpdateById(final List<Entity> entityList);

    int batchUpdateById(final String tableName, final List<Entity> entityList);

    int deleteById(final Object id);

    int deleteById(final String tableName, final Object id);

    List<Entity> queryListByIdList(final List<Object> idList);

    List<Entity> queryListByIdList(final String tableName, final List<Object> idList);

}