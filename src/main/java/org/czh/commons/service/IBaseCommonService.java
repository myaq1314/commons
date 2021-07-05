package org.czh.commons.service;

import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.annotations.tag.NotEmptyTag;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.entity.eo.BaseCommonEO;
import org.czh.commons.enums.parent.IColumnEnum;

import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021-06-21
 * email 916419307@qq.com
 */
public interface IBaseCommonService<Entity extends BaseCommonEO> extends IBaseQueryService<Entity> {

    Entity createEntity(@NotBlankTag final String tableName,
                        @NotNullTag final IColumnEnum columnEnum,
                        @NotNullTag final Object columnValue);

    Entity createEntity(@NotNullTag final IColumnEnum columnEnum,
                        @NotNullTag final Object columnValue);

    Entity createEntity(@NotBlankTag final String tableName,
                        @NotEmptyTag final IColumnEnum[] columnEnums,
                        @NotEmptyTag final Object[] columnValues);

    Entity createEntity(@NotEmptyTag final IColumnEnum[] columnEnums,
                        @NotEmptyTag final Object[] columnValues);

    int insert(@NotNullTag final Entity entity);

    int insertExistUpdate(@NotNullTag final Entity entity);

    int batchInsert(@NotNullTag final Entity condition, @NotEmptyTag final List<Entity> entityList);

    int batchInsertExistUpdate(@NotNullTag final Entity condition, @NotEmptyTag final List<Entity> entityList);

    int delete(@NotNullTag final Entity condition);

    int update(@NotNullTag final Entity condition, @NotNullTag final Entity entity);

}
