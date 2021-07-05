package org.czh.commons.dao.example;

import org.apache.ibatis.annotations.Mapper;
import org.czh.commons.dao.IBaseQueryDao;
import org.czh.commons.entity.eo.example.ExampleQueryEO;

/**
 * @author : czh
 * description :
 * date : 2021-06-28
 * email 916419307@qq.com
 */
@Mapper
public interface IExampleQueryDao extends IBaseQueryDao<ExampleQueryEO> {

}
