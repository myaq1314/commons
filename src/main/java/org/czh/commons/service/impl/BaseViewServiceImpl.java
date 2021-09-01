package org.czh.commons.service.impl;

import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.annotations.tag.NotEmptyTag;
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
import org.czh.commons.validate.FlagAssert;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author : czh
 * description :
 * date : 2021-06-21
 * email 916419307@qq.com
 */
public abstract class BaseViewServiceImpl<Dao extends IBaseViewDao<Entity>, Entity extends BaseViewEO>
        extends BaseEOServiceImpl<Dao, Entity>
        implements IBaseViewService<Entity> {

    @Override
    public Entity createEntity(@NotBlankTag final String tableName,
                               @NotNullTag final IColumnEnum columnEnum,
                               @NotNullTag final Object columnValue) {
        Entity entity = this.createEntity(columnEnum, columnValue);
        entity.setTableName(tableName);
        return entity;
    }

    @Override
    public Entity createEntity(@NotNullTag final IColumnEnum columnEnum,
                               @NotNullTag final Object columnValue) {
        EmptyAssert.allNotNull(columnEnum, columnValue);

        Entity entity = this.newInstance();
        FieldUtil.writeField(entity, columnEnum.getField(), columnValue);
        return entity;
    }

    @Override
    public Entity createEntity(@NotBlankTag final String tableName,
                               @NotEmptyTag final IColumnEnum[] columnEnums,
                               @NotEmptyTag final Object[] columnValues) {
        Entity entity = this.createEntity(columnEnums, columnValues);
        entity.setTableName(tableName);
        return entity;
    }

    @Override
    public Entity createEntity(@NotEmptyTag final IColumnEnum[] columnEnums,
                               @NotEmptyTag final Object[] columnValues) {
        EmptyAssert.allNotEmpty(columnEnums, columnValues);
        FlagAssert.isTrue(columnEnums.length == columnValues.length);

        Entity entity = this.newInstance();
        IntStream.range(0, columnEnums.length)
                .forEach(i -> FieldUtil.writeField(entity, columnEnums[i].getField(), columnValues[i]));
        return entity;
    }

    @Override
    public Entity getSureOneEntity(@NotNullTag final Entity condition) {
        Entity result = getOneEntity(condition);
        EmptyAssert.isNotNull(result, "The getSureOneEntity method did not query for data");
        return result;
    }

    @Override
    public Entity getOneEntity(@NotNullTag final Entity condition) {
        EmptyAssert.isNotNull(condition);
        EmptyAssert.isBlank(condition.getLimitSQL());

        condition.setLimit(1, 1);
        List<Entity> entityList = this.queryEntityList(condition);
        if (EmptyValidate.isEmpty(entityList)) {
            return null;
        }
        return entityList.get(0);
    }

    @Override
    public Map<String, Object> getSureOneMap(@NotNullTag final Entity condition) {
        Map<String, Object> result = getOneMap(condition);
        EmptyAssert.isNotNull(result, "The getSureOneMap method did not query for data");
        return result;
    }

    @Override
    public Map<String, Object> getOneMap(@NotNullTag final Entity condition) {
        EmptyAssert.isNotNull(condition);
        EmptyAssert.isBlank(condition.getLimitSQL());

        condition.setLimit(1, 1);
        List<Map<String, Object>> entityList = this.queryMapList(condition);
        if (EmptyValidate.isEmpty(entityList)) {
            return null;
        }
        return entityList.get(0);
    }

    @Override
    public Entity getSureOnlyEntity(@NotNullTag final Entity condition) {
        Entity result = getOnlyEntity(condition);
        EmptyAssert.isNotNull(result, "The getSureOnlyEntity method did not query for data");
        return result;
    }

    @Override
    public Entity getOnlyEntity(@NotNullTag final Entity condition) {
        EmptyAssert.isNotNull(condition);
        EmptyAssert.isBlank(condition.getLimitSQL());

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
    public Map<String, Object> getSureOnlyMap(@NotNullTag final Entity condition) {
        Map<String, Object> result = getOnlyMap(condition);
        EmptyAssert.isNotNull(result, "The getSureOnlyEntity method did not query for data");
        return result;
    }

    @Override
    public Map<String, Object> getOnlyMap(@NotNullTag final Entity condition) {
        EmptyAssert.isNotNull(condition);
        EmptyAssert.isBlank(condition.getLimitSQL());

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
    public List<Entity> queryEntityList(@NotNullTag final Entity condition) {
        EmptyAssert.isNotNull(condition);
        return this.baseDao.queryEOList(condition);
    }

    @Override
    public List<Map<String, Object>> queryMapList(@NotNullTag final Entity condition) {
        EmptyAssert.isNotNull(condition);
        return this.baseDao.queryMapList(condition);
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
