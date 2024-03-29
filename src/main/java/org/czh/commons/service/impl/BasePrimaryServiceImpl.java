package org.czh.commons.service.impl;

import org.czh.commons.dao.IBasePrimaryDao;
import org.czh.commons.entity.eo.BasePrimaryEO;
import org.czh.commons.enums.sql.CircleDict;
import org.czh.commons.service.IBasePrimaryService;
import org.czh.commons.utils.sql.base.WhereUtil;
import org.czh.commons_core.asserts.EmptyAssert;
import org.czh.commons_core.asserts.EqualsAssert;
import org.czh.commons_core.utils.FieldUtil;

import java.lang.reflect.Field;
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
        condition.setTableNameSQL(tableName);
        FieldUtil.writeField(condition, idField, id);
        return this.getOnlyEntity(condition);
    }

    @Override
    public int updateById(final Entity entity) {
        EmptyAssert.isNotNull(entity);
        Object id = FieldUtil.readField(entity, "id");
        EmptyAssert.isNotNull(id);

        Entity condition = newInstance();
        condition.setTableNameSQL(entity.getTableNameSQL());
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
            entity.setTableNameSQL(tableName);
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
        condition.setTableNameSQL(tableName);
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
        condition.setTableNameSQL(tableName);
        WhereUtil.addCircle(condition, "id", CircleDict.IN, idList.toArray());
        return this.queryEntityList(condition);
    }
}
