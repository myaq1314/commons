package org.czh.commons.service;

import org.czh.commons.annotations.tag.NotEmptyTag;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.entity.eo.BaseCommonEO;

import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021-06-21
 * email 916419307@qq.com
 */
public interface IBaseCommonService<Entity extends BaseCommonEO> extends IBaseViewService<Entity> {

    int insert(@NotNullTag final Entity entity);

    int insertExistUpdate(@NotNullTag final Entity entity);

    int batchInsert(@NotNullTag final Entity condition, @NotEmptyTag final List<Entity> entityList);

    int batchInsertExistUpdate(@NotNullTag final Entity condition, @NotEmptyTag final List<Entity> entityList);

    int delete(@NotNullTag final Entity condition);

    int update(@NotNullTag final Entity condition, @NotNullTag final Entity entity);

}
