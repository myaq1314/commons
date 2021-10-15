package org.czh.commons.dao;

import org.apache.ibatis.annotations.Param;
import org.czh.commons.entity.eo.BaseViewEO;
import org.czh.commons_core.parent.dao.IBaseDao;

import java.util.List;
import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021-06-21
 * email 916419307@qq.com
 */
public interface IBaseViewDao<EO extends BaseViewEO> extends IBaseDao<EO> {

    /**
     * 表：student
     * id int, student_name varchar, grade smallint, score decimal(5,2), birthday date
     * id primary key, student_name unique key
     * select distinct max(a.score) as score, grade from student as a where a.grade <= 3 and a.score > 60 group by a.grade having count(1) > 2 order by a.grade limit 0, 3;
     */
    List<EO> queryEOList(@Param("condition") EO condition);

    /**
     * 表：student
     * id int, student_name varchar, grade smallint, score decimal(5,2), birthday date
     * id primary key, student_name unique key
     * select distinct max(a.score) as score, grade, count(1) as count from student as a where a.grade <= 3 and a.score > 60 group by a.grade having count(1) > 2 order by a.grade limit 0, 3;
     */
    List<Map<String, Object>> queryMapList(@Param("condition") EO condition);

}
