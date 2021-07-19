package org.czh.commons.service.impl;

import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.annotations.tag.NotEmptyTag;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.dao.IBasePrimaryDao;
import org.czh.commons.entity.eo.BasePrimaryEO;
import org.czh.commons.enums.sql.CircleDict;
import org.czh.commons.service.IBasePrimaryService;
import org.czh.commons.validate.EmptyAssert;

import java.io.Serializable;
import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021-06-28
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public abstract class BasePrimaryServiceImpl<Dao extends IBasePrimaryDao<Entity>, Entity extends BasePrimaryEO>
        extends BaseCommonServiceImpl<Dao, Entity>
        implements IBasePrimaryService<Entity> {

    @Override
    public Entity getById(@NotNullTag final Serializable id) {
        EmptyAssert.isNotNull(id);

        Entity entity = newInstance();
        entity.setId(id);
        return this.getOnlyEntity(entity);
    }

    @Override
    public Entity getById(@NotBlankTag final String tableName,
                          @NotNullTag final Serializable id) {
        EmptyAssert.isNotBlank(tableName);
        EmptyAssert.isNotNull(id);

        Entity entity = newInstance();
        entity.setTableName(tableName);
        entity.setId(id);
        return this.getOnlyEntity(entity);
    }

    @Override
    public int updateById(@NotNullTag final Entity entity) {
        EmptyAssert.isNotNull(entity);
        EmptyAssert.isNotNull(entity.getId());

        Entity condition = newInstance();
        condition.setTableName(entity.getTableName());
        condition.setId(entity.getId());

        return this.update(condition, entity);
    }

    @Override
    public int batchUpdateById(@NotEmptyTag final List<Entity> entityList) {
        EmptyAssert.isNotEmpty(entityList);

        int count = 0;
        for (Entity entity : entityList) {
            count += this.updateById(entity);
        }
        return count;
    }

    @Override
    public int batchUpdateById(@NotBlankTag final String tableName,
                               @NotEmptyTag final List<Entity> entityList) {
        EmptyAssert.isNotBlank(tableName);
        EmptyAssert.isNotEmpty(entityList);

        int count = 0;
        for (Entity entity : entityList) {
            entity.setTableName(tableName);
            count += this.updateById(entity);
        }
        return count;
    }

    @Override
    public int deleteById(@NotNullTag final Serializable id) {
        EmptyAssert.isNotNull(id);

        Entity condition = newInstance();
        condition.setId(id);
        return this.delete(condition);
    }

    @Override
    public int deleteById(@NotBlankTag final String tableName,
                          @NotNullTag final Serializable id) {
        EmptyAssert.isNotBlank(tableName);
        EmptyAssert.isNotNull(id);

        Entity condition = newInstance();
        condition.setTableName(tableName);
        condition.setId(id);
        return this.delete(condition);
    }

    @Override
    public List<Entity> queryListByIdList(@NotEmptyTag final List<Serializable> idList) {
        EmptyAssert.isNotEmpty(idList);

        Entity condition = newInstance();
        condition.addWhereCircle("id", CircleDict.IN, idList);
        return this.queryEntityList(condition);
    }

    @Override
    public List<Entity> queryListByIdList(@NotBlankTag final String tableName,
                                          @NotEmptyTag final List<Serializable> idList) {
        EmptyAssert.isNotBlank(tableName);
        EmptyAssert.isNotEmpty(idList);

        Entity condition = newInstance();
        condition.setTableName(tableName);
        condition.addWhereCircle("id", CircleDict.IN, idList);
        return this.queryEntityList(condition);
    }
}
