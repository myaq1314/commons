package org.czh.commons.service.impl;

import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.annotations.tag.NotEmptyTag;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.dao.IBaseCommonDao;
import org.czh.commons.entity.eo.BaseCommonEO;
import org.czh.commons.enums.parent.IColumnEnum;
import org.czh.commons.service.IBaseCommonService;
import org.czh.commons.utils.FieldUtil;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.FlagAssert;

import java.util.List;
import java.util.stream.IntStream;

/**
 * @author : czh
 * description :
 * date : 2021-06-28
 * email 916419307@qq.com
 */
public abstract class BaseCommonServiceImpl<Dao extends IBaseCommonDao<Entity>, Entity extends BaseCommonEO>
        extends BaseQueryServiceImpl<Dao, Entity>
        implements IBaseCommonService<Entity> {

    @Override
    @SuppressWarnings("DuplicatedCode")
    public Entity createEntity(@NotBlankTag final String tableName,
                               @NotNullTag final IColumnEnum columnEnum,
                               @NotNullTag final Object columnValue) {
        EmptyAssert.isNotBlank(tableName);

        Entity entity = this.createEntity(columnEnum, columnValue);
        entity.setTableName(tableName);
        return entity;
    }

    @Override
    @SuppressWarnings("DuplicatedCode")
    public Entity createEntity(@NotNullTag final IColumnEnum columnEnum,
                               @NotNullTag final Object columnValue) {
        EmptyAssert.allNotNull(columnEnum, columnValue);

        Entity entity = this.newInstance();
        FieldUtil.writeField(entity, columnEnum.getField(), columnValue);
        return entity;
    }

    @Override
    @SuppressWarnings("DuplicatedCode")
    public Entity createEntity(@NotBlankTag final String tableName,
                               @NotEmptyTag final IColumnEnum[] columnEnums,
                               @NotEmptyTag final Object[] columnValues) {
        EmptyAssert.isNotBlank(tableName);

        Entity entity = this.createEntity(columnEnums, columnValues);
        entity.setTableName(tableName);
        return entity;
    }

    @Override
    @SuppressWarnings("DuplicatedCode")
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
