package org.czh.commons.utils.copy.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.czh.commons.entity.IBaseEntity;

/**
 * @author : czh
 * description :
 * date : 2021-06-28
 * email 916419307@qq.com
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ExampleTargetEntity implements IBaseEntity {

    private static final long serialVersionUID = -8454155070429728151L;

    private String exampleTargetEntityName;

    private String createDateTxt;

    private Boolean hidden;

    private Integer exampleNum;

    private Long fen;

    private Character character;

    private Integer id;

    private String age;

}
