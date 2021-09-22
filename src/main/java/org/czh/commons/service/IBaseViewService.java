package org.czh.commons.service;

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

    Entity getOneEntity(final IColumnEnum columnEnum, final Object columnValue);

    Entity getOneEntity(final Entity condition);

    Map<String, Object> getOneMap(final IColumnEnum columnEnum, final Object columnValue);

    Map<String, Object> getOneMap(final Entity condition);

    Entity getOnlyEntity(final IColumnEnum columnEnum, final Object columnValue);

    Entity getOnlyEntity(final Entity condition);

    Map<String, Object> getOnlyMap(final IColumnEnum columnEnum, final Object columnValue);

    Map<String, Object> getOnlyMap(final Entity condition);

    Page<Entity> queryEntityPage(final Entity condition,
                                 final Integer currentPage,
                                 final Integer pageSize);

    Page<Map<String, Object>> queryMapPage(final Entity condition,
                                           final Integer currentPage,
                                           final Integer pageSize);

    List<Entity> queryEntityList(final IColumnEnum columnEnum, final Object columnValue);

    List<Entity> queryEntityList(final Entity condition);

    List<Map<String, Object>> queryMapList(final IColumnEnum columnEnum, final Object columnValue);

    List<Map<String, Object>> queryMapList(final Entity condition);

    int getCount(final IColumnEnum columnEnum, final Object columnValue);

    int getCount(final Entity condition);

}
