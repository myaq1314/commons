package org.czh.commons.utils.fastjson.serializer;

import org.czh.commons.utils.DateUtil;
import org.czh.commons.validate.EmptyValidate;

import java.util.Date;

/**
 * @author : czh
 * description :
 * date : 2021-07-01
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public class DateToTxtSerializer extends AbstractContextObjectSerializer<Date, String> {

    @Override
    protected String write(Date source, String format) {
        if (EmptyValidate.isNotBlank(format)) {
            return DateUtil.formatToText(source, format);
        } else {
            return DateUtil.formatToText(source);
        }
    }
}
