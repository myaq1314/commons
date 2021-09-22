package org.czh.commons.service;

import org.czh.commons.entity.eo.BaseCommonEO;

import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021-06-21
 * email 916419307@qq.com
 */
public interface IBaseCommonService<Entity extends BaseCommonEO> extends IBaseViewService<Entity> {

    int insert(final Entity entity);

    int insertExistUpdate(final Entity entity);

    int batchInsert(final Entity condition, final List<Entity> entityList);

    int batchInsertExistUpdate(final Entity condition, final List<Entity> entityList);

    int delete(final Entity condition);

    int update(final Entity condition, final Entity entity);

}
