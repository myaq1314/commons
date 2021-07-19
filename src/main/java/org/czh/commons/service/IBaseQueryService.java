package org.czh.commons.service;

import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.annotations.tag.NotEmptyTag;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.entity.Page;
import org.czh.commons.entity.eo.BaseQueryEO;
import org.czh.commons.enums.parent.IColumnEnum;

import java.util.List;
import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021-06-21
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public interface IBaseQueryService<Entity extends BaseQueryEO> extends IBaseEOService<Entity> {

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

    Entity getSureOneEntity(@NotNullTag final Entity condition);

    Entity getOneEntity(@NotNullTag final Entity condition);

    Map<String, Object> getSureOneMap(@NotNullTag final Entity condition);

    Map<String, Object> getOneMap(@NotNullTag final Entity condition);

    Entity getSureOnlyEntity(@NotNullTag final Entity condition);

    Entity getOnlyEntity(@NotNullTag final Entity condition);

    Map<String, Object> getSureOnlyMap(@NotNullTag final Entity condition);

    Map<String, Object> getOnlyMap(@NotNullTag final Entity condition);

    Page<Entity> queryEntityPage(@NotNullTag final Entity condition,
                                 @NotNullTag final Integer currentPage,
                                 @NotNullTag final Integer pageSize);

    Page<Map<String, Object>> queryMapPage(@NotNullTag final Entity condition,
                                           @NotNullTag final Integer currentPage,
                                           @NotNullTag final Integer pageSize);

    List<Entity> queryEntityList(@NotNullTag final Entity condition);

    List<Map<String, Object>> queryMapList(@NotNullTag final Entity condition);

    int getCount(@NotNullTag final Entity condition);

}
