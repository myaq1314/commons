package org.czh.commons.service.impl;

import org.czh.commons.annotations.tag.NotEmptyTag;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.dao.IBaseCommonDao;
import org.czh.commons.entity.eo.BaseCommonEO;
import org.czh.commons.service.IBaseCommonService;
import org.czh.commons.validate.EmptyAssert;

import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021-06-28
 * email 916419307@qq.com
 */
public abstract class BaseCommonServiceImpl<Dao extends IBaseCommonDao<Entity>, Entity extends BaseCommonEO>
        extends BaseViewServiceImpl<Dao, Entity>
        implements IBaseCommonService<Entity> {

    @Override
    public int insert(@NotNullTag final Entity entity) {
        EmptyAssert.isNotNull(entity);
        return this.baseDao.insert(entity);
    }

    @Override
    public int insertExistUpdate(@NotNullTag final Entity entity) {
        EmptyAssert.isNotNull(entity);
        return this.baseDao.insertExistUpdate(entity);
    }

    @Override
    public int batchInsert(@NotNullTag final Entity condition,
                           @NotEmptyTag final List<Entity> entityList) {
        EmptyAssert.isNotNull(condition);
        EmptyAssert.isNotEmpty(entityList);
        return this.baseDao.batchInsert(condition, entityList);
    }

    @Override
    public int batchInsertExistUpdate(@NotNullTag final Entity condition,
                                      @NotEmptyTag final List<Entity> entityList) {
        EmptyAssert.isNotNull(condition);
        EmptyAssert.isNotEmpty(entityList);
        return this.baseDao.batchInsertExistUpdate(condition, entityList);
    }

    @Override
    public int delete(@NotNullTag final Entity condition) {
        EmptyAssert.isNotNull(condition);
        return this.baseDao.delete(condition);
    }

    @Override
    public int update(@NotNullTag final Entity condition,
                      @NotNullTag final Entity entity) {
        EmptyAssert.allNotNull(condition, entity);
        return this.baseDao.update(condition, entity);
    }
}
