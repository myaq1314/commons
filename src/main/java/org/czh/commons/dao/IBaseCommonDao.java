package org.czh.commons.dao;

import org.apache.ibatis.annotations.Param;
import org.czh.commons.entity.eo.BaseCommonEO;

import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021-06-21
 * email 916419307@qq.com
 */
public interface IBaseCommonDao<EO extends BaseCommonEO> extends IBaseViewDao<EO> {

    /**
     * 表：student
     * id int, student_name varchar, grade smallint, score decimal(5,2), birthday date
     * id primary key, student_name unique key
     * <p>
     * insert into student
     * (id, student_name, grade)
     * values (100, 'TOM', 1);
     */
    int insert(@Param("entity") EO entity);

    /**
     * 表：student
     * id int, student_name varchar, grade smallint, score decimal(5,2), birthday date
     * id primary key, student_name unique key
     * <p>
     * insert into student
     * (id, student_name, grade)
     * values (100, 'JAK', 2)
     * on duplicate key
     * update id = values(id), student_name = values(student_name), grade = values(grade);
     */
    int insertExistUpdate(@Param("entity") EO entity);

    /**
     * 表：student
     * id int, student_name varchar, grade smallint, score decimal(5,2), birthday date
     * id primary key, student_name unique key
     * <p>
     * insert into student
     * (id, student_name, grade, score)
     * values (101, 'SALE', 1, 20), (102, 'JEKE', 2, 68);
     */
    int batchInsert(@Param("condition") EO condition,
                    @Param("entityList") List<EO> entityList);

    /**
     * 表：student
     * id int, student_name varchar, grade smallint, score decimal(5,2), birthday date
     * id primary key, student_name unique key
     * <p>
     * insert into student
     * (id, student_name, grade, score)
     * values (101, 'SALE1', 2, 40), (102, 'JEKE1', 3, 32)
     * on duplicate key
     * update id = values(id), student_name = values(student_name), grade = values(grade), score = values(score);
     */
    int batchInsertExistUpdate(@Param("condition") EO condition,
                               @Param("entityList") List<EO> entityList);

    /**
     * 表：student
     * id int, student_name varchar, grade smallint, score decimal(5,2), birthday date
     * id primary key, student_name unique key
     * <p>
     * delete table student as a
     * where a.id in (100, 101, 102);
     */
    int delete(@Param("condition") EO condition);

    /**
     * 表：student
     * id int, student_name varchar, grade smallint, score decimal(5,2), birthday date
     * id primary key, student_name unique key
     * <p>
     * update student as a
     * set a.grade = 1, a.birthday = now()
     * where a.id in (1, 3, 5, 7, 9) and a.grade > 1;
     */
    int update(@Param("condition") EO condition, @Param("entity") EO entity);

}
