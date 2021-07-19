package org.czh.commons.utils.fastjson.deserializer;

import org.czh.commons.utils.fastjson.ConvertUtil;

import java.util.Date;

/**
 * @author : czh
 * description :
 * date : 2021-07-19
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public class DateToTextDeserializer extends AbstractContextObjectDeserializer<Date, String> {

    @Override
    public String deserialize(Date source, String format) {
        return ConvertUtil.dateToText(source, format);
    }
}