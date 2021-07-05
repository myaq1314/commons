package org.czh.commons.utils.fastjson.deserializer;

import org.czh.commons.utils.DateUtil;
import org.czh.commons.validate.EmptyValidate;

import java.util.Date;

/**
 * @author : czh
 * description :
 * date : 2021-07-01
 * email 916419307@qq.com
 */
public class TxtToDateDeserializer extends AbstractContextObjectDeserializer<String, Date> {

    @Override
    protected Date deserialize(String source, String format) {
        if (EmptyValidate.isNotBlank(format)) {
            return DateUtil.parseToDate(source, format);
        } else {
            return DateUtil.parseToDate(source);
        }
    }
}
