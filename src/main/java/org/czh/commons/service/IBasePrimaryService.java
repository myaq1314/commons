package org.czh.commons.service;

import org.czh.commons.annotations.tag.NotEmptyTag;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.entity.eo.BasePrimaryEO;

import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021-06-28
 * email 916419307@qq.com
 */
public interface IBasePrimaryService<Entity extends BasePrimaryEO> extends IBaseCommonService<Entity> {

    Entity getById(@NotNullTag final Object id);

    Entity getById(final String tableName, @NotNullTag final Object id);

    int updateById(@NotNullTag final Entity entity);

    int batchUpdateById(@NotEmptyTag final List<Entity> entityList);

    int batchUpdateById(final String tableName, @NotEmptyTag final List<Entity> entityList);

    int deleteById(@NotNullTag final Object id);

    int deleteById(final String tableName, @NotNullTag final Object id);

    List<Entity> queryListByIdList(@NotEmptyTag final List<Object> idList);

    List<Entity> queryListByIdList(final String tableName, @NotEmptyTag final List<Object> idList);

}