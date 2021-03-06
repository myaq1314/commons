package org.czh.commons.service;

import org.czh.commons.annotations.tag.NotBlankTag;
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
@SuppressWarnings("unused")
public interface IBasePrimaryService<Entity extends BasePrimaryEO> extends IBaseCommonService<Entity> {

    Entity getById(@NotNullTag final java.io.Serializable id);

    Entity getById(@NotBlankTag final String tableName,
                   @NotNullTag final java.io.Serializable id);

    int updateById(@NotNullTag final Entity entity);

    int batchUpdateById(@NotEmptyTag final List<Entity> entityList);

    int batchUpdateById(@NotBlankTag final String tableName,
                        @NotEmptyTag final List<Entity> entityList);

    int deleteById(@NotNullTag final java.io.Serializable id);

    int deleteById(@NotBlankTag final String tableName,
                   @NotNullTag final java.io.Serializable id);

    List<Entity> queryListByIdList(@NotEmptyTag final List<java.io.Serializable> idList);

    List<Entity> queryListByIdList(@NotBlankTag final String tableName,
                                   @NotEmptyTag final List<java.io.Serializable> idList);

}