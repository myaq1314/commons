package org.czh.commons.service.impl;

import org.czh.commons.dao.IBaseViewDao;
import org.czh.commons.entity.Page;
import org.czh.commons.entity.PageHelper;
import org.czh.commons.entity.eo.BaseViewEO;
import org.czh.commons.enums.parent.IColumnEnum;
import org.czh.commons.service.IBaseViewService;
import org.czh.commons.utils.sql.base.LimitUtil;
import org.czh.commons.utils.sql.base.SelectUtil;
import org.czh.commons_core.asserts.EmptyAssert;
import org.czh.commons_core.exceptions.CommonException;
import org.czh.commons_core.utils.FieldUtil;
import org.czh.commons_core.validate.EmptyValidate;

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

    private Entity createEntity(final IColumnEnum columnEnum, final Object columnValue) {
        EmptyAssert.allNotNull(columnEnum, columnValue);

        Entity entity = this.newInstance();
        FieldUtil.writeField(entity, columnEnum.getField(), columnValue);
        return entity;
    }

    @Override
    public Entity getOneEntity(IColumnEnum columnEnum, Object columnValue) {
        return getOneEntity(this.createEntity(columnEnum, columnValue));
    }

    @Override
    public Entity getOneEntity(final Entity condition) {
        EmptyAssert.isNotNull(condition);

        LimitUtil.set(condition, 1, 1);
        List<Entity> entityList = this.queryEntityList(condition);
        if (EmptyValidate.isEmpty(entityList)) {
            return null;
        }
        return entityList.get(0);
    }

    @Override
    public Map<String, Object> getOneMap(IColumnEnum columnEnum, Object columnValue) {
        return getOneMap(this.createEntity(columnEnum, columnValue));
    }

    @Override
    public Map<String, Object> getOneMap(final Entity condition) {
        EmptyAssert.isNotNull(condition);

        LimitUtil.set(condition, 1, 1);
        List<Map<String, Object>> entityList = this.queryMapList(condition);
        if (EmptyValidate.isEmpty(entityList)) {
            return null;
        }
        return entityList.get(0);
    }

    @Override
    public Entity getOnlyEntity(IColumnEnum columnEnum, Object columnValue) {
        return getOnlyEntity(this.createEntity(columnEnum, columnValue));
    }

    @Override
    public Entity getOnlyEntity(final Entity condition) {
        EmptyAssert.isNotNull(condition);

        LimitUtil.set(condition, 1, 2);
        List<Entity> entityList = this.queryEntityList(condition);
        if (EmptyValidate.isEmpty(entityList)) {
            return null;
        } else if (entityList.size() != 1) {
            throw new CommonException("The getOnlyEntity method gets multiple pieces of data");
        }
        return entityList.get(0);
    }

    @Override
    public Map<String, Object> getOnlyMap(IColumnEnum columnEnum, Object columnValue) {
        return getOnlyMap(this.createEntity(columnEnum, columnValue));
    }

    @Override
    public Map<String, Object> getOnlyMap(final Entity condition) {
        EmptyAssert.isNotNull(condition);

        LimitUtil.set(condition, 1, 2);
        List<Map<String, Object>> entityList = this.queryMapList(condition);
        if (EmptyValidate.isEmpty(entityList)) {
            return null;
        } else if (entityList.size() != 1) {
            throw new CommonException("The getOnlyMap method gets multiple pieces of data");
        }
        return entityList.get(0);
    }

    @Override
    public Page<Entity> queryEntityPage(final Entity condition,
                                        final Integer currentPage,
                                        final Integer pageSize) {
        EmptyAssert.isNotNull(condition);

        PageHelper.enablePage(currentPage, pageSize);
        return PageHelper.setAndReturn(this.queryEntityList(condition));
    }

    @Override
    public Page<Map<String, Object>> queryMapPage(final Entity condition,
                                                  final Integer currentPage,
                                                  final Integer pageSize) {
        EmptyAssert.isNotNull(condition);

        PageHelper.enablePage(currentPage, pageSize);
        return PageHelper.setAndReturn(this.queryMapList(condition));
    }

    @Override
    public List<Entity> queryEntityList(IColumnEnum columnEnum, Object columnValue) {
        return queryEntityList(this.createEntity(columnEnum, columnValue));
    }

    @Override
    public List<Entity> queryEntityList(final Entity condition) {
        EmptyAssert.isNotNull(condition);
        return this.baseDao.queryEOList(condition);
    }

    @Override
    public List<Map<String, Object>> queryMapList(IColumnEnum columnEnum, Object columnValue) {
        return queryMapList(this.createEntity(columnEnum, columnValue));
    }

    @Override
    public List<Map<String, Object>> queryMapList(final Entity condition) {
        EmptyAssert.isNotNull(condition);
        return this.baseDao.queryMapList(condition);
    }

    @Override
    public int getCount(final IColumnEnum columnEnum, final Object columnValue) {
        return getCount(this.createEntity(columnEnum, columnValue));
    }

    @Override
    public int getCount(final Entity condition) {
        EmptyAssert.isNotNull(condition);

        SelectUtil.addFunc(condition, "count", "count", 1);
        Map<String, Object> resultMap = this.getOnlyMap(condition);
        if (EmptyValidate.isEmpty(resultMap)) {
            return 0;
        }
        Long count = (Long) resultMap.get("count");
        return count.intValue();
    }
}
