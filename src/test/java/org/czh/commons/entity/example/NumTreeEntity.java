package org.czh.commons.entity.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.czh.commons.entity.IBaseTreeEntity;

import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021-05-05
 * email 916419307@qq.com
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class NumTreeEntity implements IBaseTreeEntity<NumTreeEntity> {

    private static final long serialVersionUID = 7087808089804339973L;

    private List<NumTreeEntity> children;

    private String id;
    private String parentId;

}
