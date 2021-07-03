package org.czh.commons.entity.eo.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.czh.commons.enums.parent.IColumnEnum;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : czh
 * description :
 * date : 2021-06-25
 * email 916419307@qq.com
 */
@Getter
@AllArgsConstructor
public enum StudentTestColumnEnum implements IColumnEnum {

    ID("id", "id", Long.class), // 主键ID
    STUDENT_NAME("student_name", "studentName", String.class), // 学生姓名
    GRADE("grade", "grade", Integer.class), // 年级
    SCORE("score", "score", BigDecimal.class), // 分数
    BIRTHDAY("birthday", "birthday", Date.class), // 出生日期

    // 占位符
    ;

    public final String column;
    public final String field;
    public final Class<?> type;

}
