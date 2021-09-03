package org.czh.commons.service;

import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.entity.Page;
import org.czh.commons.entity.eo.BaseViewEO;
import org.czh.commons.enums.parent.IColumnEnum;

import java.util.List;
import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021-06-21
 * email 916419307@qq.com
 */
public interface IBaseViewService<Entity extends BaseViewEO> extends IBaseEOService<Entity> {

    Entity getOneEntity(@NotNullTag final IColumnEnum columnEnum, @NotNullTag final Object columnValue);

    Entity getOneEntity(@NotNullTag final Entity condition);

    Map<String, Object> getOneMap(@NotNullTag final IColumnEnum columnEnum, @NotNullTag final Object columnValue);

    Map<String, Object> getOneMap(@NotNullTag final Entity condition);

    Entity getOnlyEntity(@NotNullTag final IColumnEnum columnEnum, @NotNullTag final Object columnValue);

    Entity getOnlyEntity(@NotNullTag final Entity condition);

    Map<String, Object> getOnlyMap(@NotNullTag final IColumnEnum columnEnum, @NotNullTag final Object columnValue);

    Map<String, Object> getOnlyMap(@NotNullTag final Entity condition);

    Page<Entity> queryEntityPage(@NotNullTag final Entity condition,
                                 @NotNullTag final Integer currentPage,
                                 @NotNullTag final Integer pageSize);

    Page<Map<String, Object>> queryMapPage(@NotNullTag final Entity condition,
                                           @NotNullTag final Integer currentPage,
                                           @NotNullTag final Integer pageSize);

    List<Entity> queryEntityList(@NotNullTag final IColumnEnum columnEnum, @NotNullTag final Object columnValue);

    List<Entity> queryEntityList(@NotNullTag final Entity condition);

    List<Map<String, Object>> queryMapList(@NotNullTag final IColumnEnum columnEnum, @NotNullTag final Object columnValue);

    List<Map<String, Object>> queryMapList(@NotNullTag final Entity condition);

    int getCount(@NotNullTag final IColumnEnum columnEnum, @NotNullTag final Object columnValue);

    int getCount(@NotNullTag final Entity condition);

}
