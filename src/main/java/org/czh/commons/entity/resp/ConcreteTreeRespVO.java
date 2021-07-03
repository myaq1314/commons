package org.czh.commons.entity.resp;

import lombok.Getter;
import lombok.Setter;
import org.czh.commons.entity.IBaseTreeEntity;

import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021-05-05
 * email 916419307@qq.com
 */
public class ConcreteTreeRespVO<T extends IBaseTreeEntity<T>> implements IBaseTreeEntity<T>, IBaseRespVO {

    private static final long serialVersionUID = 7495052574662756851L;

    @Getter
    @Setter
    protected List<T> children;

}
