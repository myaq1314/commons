package org.czh.commons.service.impl;

import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.dao.IBaseViewDao;
import org.czh.commons.entity.Page;
import org.czh.commons.entity.PageHelper;
import org.czh.commons.entity.eo.BaseViewEO;
import org.czh.commons.enums.parent.IColumnEnum;
import org.czh.commons.exceptions.CommonException;
import org.czh.commons.service.IBaseViewService;
import org.czh.commons.utils.FieldUtil;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;

import java.util.List;
import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021-06-21
 * email 916419307@qq.com
 */
public abstract class BaseViewServiceImpl<Dao extends IBaseViewDao<Entity>, Entity extends BaseViewEO>
        extends BaseEOServiceImpl<Dao, Entity>
        implements IBaseViewService<Entity> {

    private Entity createEntity(@NotNullTag final IColumnEnum columnEnum, @NotNullTag final Object columnValue) {
        EmptyAssert.allNotNull(columnEnum, columnValue);

        Entity entity = this.newInstance();
        FieldUtil.writeField(entity, columnEnum.getField(), columnValue);
        return entity;
    }

    @Override
    public Entity getOneEntity(@NotNullTag IColumnEnum columnEnum, @NotNullTag Object columnValue) {
        return getOneEntity(this.createEntity(columnEnum, columnValue));
    }

    @Override
    public Entity getOneEntity(@NotNullTag final Entity condition) {
        EmptyAssert.isNotNull(condition);

        condition.setLimit(1, 1);
        List<Entity> entityList = this.queryEntityList(condition);
        if (EmptyValidate.isEmpty(entityList)) {
            return null;
        }
        return entityList.get(0);
    }

    @Override
    public Map<String, Object> getOneMap(@NotNullTag IColumnEnum columnEnum, @NotNullTag Object columnValue) {
        return getOneMap(this.createEntity(columnEnum, columnValue));
    }

    @Override
    public Map<String, Object> getOneMap(@NotNullTag final Entity condition) {
        EmptyAssert.isNotNull(condition);

        condition.setLimit(1, 1);
        List<Map<String, Object>> entityList = this.queryMapList(condition);
        if (EmptyValidate.isEmpty(entityList)) {
            return null;
        }
        return entityList.get(0);
    }

    @Override
    public Entity getOnlyEntity(@NotNullTag IColumnEnum columnEnum, @NotNullTag Object columnValue) {
        return getOnlyEntity(this.createEntity(columnEnum, columnValue));
    }

    @Override
    public Entity getOnlyEntity(@NotNullTag final Entity condition) {
        EmptyAssert.isNotNull(condition);

        condition.setLimit(1, 2);
        List<Entity> entityList = this.queryEntityList(condition);
        if (EmptyValidate.isEmpty(entityList)) {
            return null;
        } else if (entityList.size() != 1) {
            throw new CommonException("The getOnlyEntity method gets multiple pieces of data");
        }
        return entityList.get(0);
    }

    @Override
    public Map<String, Object> getOnlyMap(@NotNullTag IColumnEnum columnEnum, @NotNullTag Object columnValue) {
        return getOnlyMap(this.createEntity(columnEnum, columnValue));
    }

    @Override
    public Map<String, Object> getOnlyMap(@NotNullTag final Entity condition) {
        EmptyAssert.isNotNull(condition);

        condition.setLimit(1, 2);
        List<Map<String, Object>> entityList = this.queryMapList(condition);
        if (EmptyValidate.isEmpty(entityList)) {
            return null;
        } else if (entityList.size() != 1) {
            throw new CommonException("The getOnlyMap method gets multiple pieces of data");
        }
        return entityList.get(0);
    }

    @Override
    public Page<Entity> queryEntityPage(@NotNullTag final Entity condition,
                                        @NotNullTag final Integer currentPage,
                                        @NotNullTag final Integer pageSize) {
        EmptyAssert.isNotNull(condition);

        PageHelper.enablePage(currentPage, pageSize);
        return PageHelper.setAndReturn(this.queryEntityList(condition));
    }

    @Override
    public Page<Map<String, Object>> queryMapPage(@NotNullTag final Entity condition,
                                                  @NotNullTag final Integer currentPage,
                                                  @NotNullTag final Integer pageSize) {
        EmptyAssert.isNotNull(condition);

        PageHelper.enablePage(currentPage, pageSize);
        return PageHelper.setAndReturn(this.queryMapList(condition));
    }

    @Override
    public List<Entity> queryEntityList(@NotNullTag IColumnEnum columnEnum, @NotNullTag Object columnValue) {
        return queryEntityList(this.createEntity(columnEnum, columnValue));
    }

    @Override
    public List<Entity> queryEntityList(@NotNullTag final Entity condition) {
        EmptyAssert.isNotNull(condition);
        return this.baseDao.queryEOList(condition);
    }

    @Override
    public List<Map<String, Object>> queryMapList(@NotNullTag IColumnEnum columnEnum, @NotNullTag Object columnValue) {
        return queryMapList(this.createEntity(columnEnum, columnValue));
    }

    @Override
    public List<Map<String, Object>> queryMapList(@NotNullTag final Entity condition) {
        EmptyAssert.isNotNull(condition);
        return this.baseDao.queryMapList(condition);
    }

    @Override
    public int getCount(@NotNullTag final IColumnEnum columnEnum, @NotNullTag final Object columnValue) {
        return getCount(this.createEntity(columnEnum, columnValue));
    }

    @Override
    public int getCount(@NotNullTag final Entity condition) {
        EmptyAssert.isNotNull(condition);

        condition.resetSelect()
                .addSelectFunction("count", "count", 1);
        Map<String, Object> resultMap = this.getOnlyMap(condition);
        if (EmptyValidate.isEmpty(resultMap)) {
            return 0;
        }
        Long count = (Long) resultMap.get("count");
        return count.intValue();
    }
}
