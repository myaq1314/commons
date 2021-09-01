package org.czh.commons.dao.example;

import org.apache.ibatis.annotations.Mapper;
import org.czh.commons.dao.IBaseViewDao;
import org.czh.commons.entity.eo.example.ExampleViewEO;

/**
 * @author : czh
 * description :
 * date : 2021-06-28
 * email 916419307@qq.com
 */
@Mapper
public interface IExampleViewDao extends IBaseViewDao<ExampleViewEO> {

}
