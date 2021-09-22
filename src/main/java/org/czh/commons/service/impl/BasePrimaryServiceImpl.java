package org.czh.commons.service.impl;

import org.czh.commons.dao.IBasePrimaryDao;
import org.czh.commons.entity.eo.BasePrimaryEO;
import org.czh.commons.enums.sql.CircleDict;
import org.czh.commons.service.IBasePrimaryService;
import org.czh.commons.utils.FieldUtil;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EqualsAssert;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021-06-28
 * email 916419307@qq.com
 */
public abstract class BasePrimaryServiceImpl<Dao extends IBasePrimaryDao<Entity>, Entity extends BasePrimaryEO>
        extends BaseCommonServiceImpl<Dao, Entity>
        implements IBasePrimaryService<Entity> {

    @Override
    public Entity getById(final Object id) {
        return getById(null, id);
    }

    @Override
    @SuppressWarnings("DuplicatedCode")
    public Entity getById(final String tableName, final Object id) {
        EmptyAssert.isNotNull(id);
        Field idField = FieldUtil.getField(this.clazz, "id");
        EqualsAssert.isEquals(idField.getType(), id.getClass());

        Entity condition = newInstance();
        condition.setTableName(tableName);
        FieldUtil.writeField(condition, idField, id);
        return this.getOnlyEntity(condition);
    }

    @Override
    public int updateById(final Entity entity) {
        EmptyAssert.isNotNull(entity);
        Object id = FieldUtil.readField(entity, "id");
        EmptyAssert.isNotNull(id);

        Entity condition = newInstance();
        condition.setTableName(entity.getTableName());
        FieldUtil.writeField(condition, "id", id);
        return this.update(condition, entity);
    }

    @Override
    public int batchUpdateById(final List<Entity> entityList) {
        return batchUpdateById(null, entityList);
    }

    @Override
    public int batchUpdateById(final String tableName, final List<Entity> entityList) {
        EmptyAssert.isNotEmpty(entityList);

        int count = 0;
        for (Entity entity : entityList) {
            entity.setTableName(tableName);
            count += this.updateById(entity);
        }
        return count;
    }

    @Override
    public int deleteById(final Object id) {
        return deleteById(null, id);
    }

    @Override
    @SuppressWarnings("DuplicatedCode")
    public int deleteById(final String tableName, final Object id) {
        EmptyAssert.isNotNull(id);
        Field idField = FieldUtil.getField(this.clazz, "id");
        EqualsAssert.isEquals(idField.getType(), id.getClass());

        Entity condition = newInstance();
        condition.setTableName(tableName);
        FieldUtil.writeField(condition, idField, id);
        return this.delete(condition);
    }

    @Override
    public List<Entity> queryListByIdList(final List<Object> idList) {
        return queryListByIdList(null, idList);
    }

    @Override
    public List<Entity> queryListByIdList(final String tableName, final List<Object> idList) {
        EmptyAssert.isNotEmpty(idList);

        Entity condition = newInstance();
        condition.setTableName(tableName);
        condition.addWhereCircle("id", CircleDict.IN, idList);
        return this.queryEntityList(condition);
    }
}
