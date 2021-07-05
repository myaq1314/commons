package org.czh.commons.utils.event.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.czh.commons.utils.event.IEvent;

/**
 * @author : czh
 * description :
 * date : 2021-06-29
 * email 916419307@qq.com
 */
@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class AbstractEvent<T> implements IEvent<T> {

    protected T payLoad;

}
